package edu.hku.cs.sgxverifier;

import java.util.Hashtable;
import java.util.Stack;
import java.util.Vector;

/**
 * Created by max on 22/7/2018.
 */
public class VirtualMachine {
    static VirtualMachine vm;
    static public VirtualMachine getVM() { return vm; }
    static public void init() { vm = new VirtualMachine(); }
    Stack<JObject> stack = new Stack<JObject>();
    Vector<JObject> heap = new Vector<JObject>();

    void push(JObject obj) {
        stack.push(obj);
    }

    JObject pop() {
        return stack.pop();
    }

    JObject malloc_enclave(String class_name) {
       return new JObject(JClass.resolve(class_name), new JValue(JValue.Taint.PlainText, JValue.Memory.Enclave));
    }
}
