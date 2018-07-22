package edu.hku.cs.sgxverifier;

public class JFrame {
    int bci;
    String class_name;
    String func_name;
    JFrame(String c, String f) {
        class_name = c;
        func_name = f;
        bci = 0;
    }

    @Override
    public String toString() {
        return class_name + " " + func_name + ":" + bci;
    }
}
