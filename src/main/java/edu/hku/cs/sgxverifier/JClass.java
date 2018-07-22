package edu.hku.cs.sgxverifier;

import java.util.Hashtable;

/**
 * Created by max on 22/7/2018.
 */
public class JClass {
    static Hashtable<String, JClass> resolved_classes = new Hashtable<String, JClass>();
    static {
        // all primitive type is 0
        resolved_classes.put("p", new JClass("p"));
    }

    String name;
    SGXClassVisitor classVisitor;
    Hashtable<String, JObject> static_fields = new Hashtable<String, JObject>();

    public JClass(String m) {
        name = m;
        if (!m.equals("p"))
            classVisitor = new SGXClassVisitor(m);
    }

    public void putfield(String name, JObject obj) {
        static_fields.put(name, obj);
    }

    public JObject getfield(String name) {
        return static_fields.get(name);
    }

    static JClass resolve(String cname) {
        JClass jc = resolved_classes.get(cname);
        if (jc == null) {
            jc = do_resolve(cname);
            resolved_classes.put(cname, jc);
        }
        return jc;
    }

    static JClass do_resolve(String cname) {
        return new JClass(cname);
    }
}
