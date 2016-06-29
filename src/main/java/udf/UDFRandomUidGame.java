package udf;

import java.util.List;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StandardListObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.io.Text;

/**
 * 随机用户匹配游戏列表中的一款游戏
 *
 * @author huzhirong
 */
public class UDFRandomUidGame extends GenericUDF {

    //声明全局输入变量
    StandardListObjectInspector dimGameListOI;
    ObjectInspector uidOI;
    //声明输出变量
    private final Text text = new Text();

    //处理业务逻辑
    @Override
    public Object evaluate(DeferredObject[] vals) throws HiveException {
        text.clear();
        Text uid = (Text) vals[0].get();
        Text seq = new Text(",");
        @SuppressWarnings("unchecked")
        List<Text> list = (List<Text>) dimGameListOI.getList(vals[1].get());
        Text game = list.get((int) (Math.random() * list.size()));
        text.append(game.getBytes(), 0, game.getLength());
        text.append(seq.getBytes(), 0, seq.getLength());
        text.append(uid.getBytes(), 0, uid.getLength());
        return text;
    }

    //类似于java的toString方法，hive中在使用explain的时候调用该方法
    @Override
    public String getDisplayString(String[] args) {
        StringBuilder sb = new StringBuilder();
        if (args != null && args.length > 0) {
            for (String arg : args) {
                sb.append(arg).append(',');
            }
        }
        if (sb.length() > 0) {
            sb.setCharAt(sb.length() - 1, ')');
        } else {
            sb.append(')');
        }
        sb.insert(0, "random_list_element(");

        return sb.toString();
    }

    @Override
    public ObjectInspector initialize(ObjectInspector[] ois) throws UDFArgumentException {
        uidOI = (ObjectInspector) ois[0];
        dimGameListOI = (StandardListObjectInspector) ois[1];
        //返回值类型
        return PrimitiveObjectInspectorFactory.writableStringObjectInspector;
    }

}
