package edu.hku.cs.sgxverifier;

import java.util.Hashtable;
import java.util.Vector;

/**
 * Created by max on 22/7/2018.
 */
public class JObject {
    // id for resolve object in heap, -1 means not found

    JClass java_class;
    JValue object_value;
    Hashtable<String, JObject> field_values;

    public JObject(String cname, JValue ov) {
        java_class = JClass.resolve(cname);
        object_value = ov;
        field_values = new Hashtable<String, JObject>();
    }

    public void putfield(String field, JObject obj) {
        field_values.put(field, obj);
    }

    public JObject getfield(String field) {
        return field_values.get(field);
    }
}
