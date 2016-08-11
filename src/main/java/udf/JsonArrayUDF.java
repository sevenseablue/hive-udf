package udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector.Category;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector.PrimitiveCategory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.StringObjectInspector;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

/**
 *   UDF to split a JSON array into array of row numbers and json strings
 *
 */
@Description(name="json_array",
        value = "_FUNC_(json) - Returns a array of JSON strings from a JSON Array")
public class JsonArrayUDF extends GenericUDF {
    private StringObjectInspector stringInspector;

    public ArrayList<String> splitJsonString(String jsonString) throws JsonProcessingException, IOException{
        ObjectMapper om = new ObjectMapper();
        ArrayList<Object> root = (ArrayList<Object>) om.readValue(jsonString, ArrayList.class);
        ArrayList<String> json = new ArrayList<String>(root.size());
        for (int i=0; i<root.size(); i++){
            if (root.get(i).getClass() == String.class){
                json.add(String.valueOf(root.get(i)));
            } else {
                json.add(om.writeValueAsString(root.get(i)));
            }
        }
        return json;
    }

    @Override
    public ArrayList<String> evaluate(DeferredObject[] arguments) throws HiveException {
        try {
            String jsonString = this.stringInspector.getPrimitiveJavaObject(arguments[0].get());
            return splitJsonString(jsonString);
        } catch( JsonProcessingException jsonProc) {
            throw new HiveException(jsonProc);
        } catch (IOException e) {
            throw new HiveException(e);
        } catch (NullPointerException npe){
            return null;
        }

    }

    @Override
    public String getDisplayString(String[] arg0) {
        return "json_array(" + arg0[0] + ")";
    }

    @Override
    public ObjectInspector initialize(ObjectInspector[] arguments)
            throws UDFArgumentException {
        if(arguments.length != 1
           || ! arguments[0].getCategory().equals( Category.PRIMITIVE)
           || ((PrimitiveObjectInspector)arguments[0]).getPrimitiveCategory() != PrimitiveCategory.STRING) {
            throw new UDFArgumentException("Usage : json_array(jsonstring) ");
        }
        stringInspector = (StringObjectInspector) arguments[0];

        ArrayList<String> outputColumns = new ArrayList<String>();
        outputColumns.add("json_array");

        ArrayList<ObjectInspector> outputTypes = new ArrayList<ObjectInspector>();
        outputTypes.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        return ObjectInspectorFactory.getStandardListObjectInspector
                (ObjectInspectorFactory.getStandardStructObjectInspector( outputColumns, outputTypes));

    }

}