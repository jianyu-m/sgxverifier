package edu.hku.cs.sgxverifier;

import java.util.Hashtable;

/**
 * Created by max on 22/7/2018.
 */
public class JClass {
    String name;
    int num_fields;
    static Hashtable<String, JClass> resolved_classes = new Hashtable<String, JClass>();
    static {
        // all primitive type is 0
        resolved_classes.put("p", new JClass("p", 0));
    }
    int name_to_index(String name) {
        return 0;
    }

    public JClass(String m, int c) {
        num_fields = c;
        name = m;
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
        return new JClass(cname, 1);
    }
}
