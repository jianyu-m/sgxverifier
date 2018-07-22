package edu.hku.cs.sgxverifier;

import java.util.Vector;

/**
 * Created by max on 22/7/2018.
 */
public class JObject {
    // id for resolve object in heap, -1 means not found

    JClass java_class;
    JValue object_value;
    Vector<JObject> field_values;

    public JObject(JClass jc, JValue ov) {
        java_class = jc;
        object_value = ov;
        field_values = new Vector<JObject>(jc.num_fields);
    }
}
