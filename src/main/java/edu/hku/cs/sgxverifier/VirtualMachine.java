package edu.hku.cs.sgxverifier;

import java.util.*;

/**
 * Created by max on 22/7/2018.
 */
public class VirtualMachine {
    static VirtualMachine vm;
    static public VirtualMachine getVM() { return vm; }
    static public void init() { vm = new VirtualMachine(); }
    Stack<JObject> stack = new Stack<JObject>();
    JFrame cur_frame = new JFrame("", "");
    Stack<Stack<JObject>> stacks = new Stack<Stack<JObject>>();
    Stack<JFrame> frames = new Stack<JFrame>();
    Vector<JObject> heap = new Vector<JObject>();

    void push(JObject obj) {
        stack.push(obj);
    }

    JObject pop() {
        return stack.pop();
    }

    JObject malloc_enclave(String class_name) {
       return new JObject(class_name, new JValue(JValue.PlainText, JValue.Memory.Enclave));
    }

    void new_frame(String cname, String func) {
        stacks.push(stack);
        frames.push(cur_frame);
        cur_frame = new JFrame(cname, func);
        stack = new Stack<JObject>();
    }

    void frame_return(boolean ret) {
        JObject ret_value = null;
        if (ret) {
             ret_value = stack.pop();
        }
        stack = stacks.pop();
        cur_frame = frames.pop();
        if (ret) {
            stack.push(ret_value);
        }
    }
}
