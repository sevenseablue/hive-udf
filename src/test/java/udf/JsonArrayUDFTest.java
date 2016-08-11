package udf;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by seven on 26/07/16.
 */
public class JsonArrayUDFTest {
    @Test
    public void basicJsonArrayTest() throws IOException {
        String jsonString = "[\"a\",\"b\",\"c\"]";
        JsonArrayUDF udf = new JsonArrayUDF();
        ArrayList<String> splits = udf.splitJsonString(jsonString);
        org.junit.Assert.assertEquals(3, splits.size());
        // Test elements are unpacked correctly
        org.junit.Assert.assertEquals("a", splits.get(0));
        org.junit.Assert.assertEquals("b", splits.get(1));
        org.junit.Assert.assertEquals("c", splits.get(2));
    }

    @Test
    public void nestedJsonArrayTest() throws IOException {
        String jsonString = "[{\"a\":1},{\"b\":\"c\"}]";
        JsonArrayUDF udf = new JsonArrayUDF();
        ArrayList<String> splits = udf.splitJsonString(jsonString);
        // Nested objects are just strings
        org.junit.Assert.assertEquals("{\"a\":1}", splits.get(0));
        org.junit.Assert.assertEquals("{\"b\":\"c\"}", splits.get(1));
    }

    @Test
    public void escapedQuoteStringTest() throws IOException {
        String jsonString = "[\"Hi there, \\\"quotes\\\"\"]";
        JsonArrayUDF udf = new JsonArrayUDF();
        ArrayList<String> splits = udf.splitJsonString(jsonString);
        // Escaped quotes are handled
        org.junit.Assert.assertEquals("Hi there, \"quotes\"", splits.get(0));
    }
}