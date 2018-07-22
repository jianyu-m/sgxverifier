package edu.hku.cs.sgxverifier;

/**
 * Created by max on 22/7/2018.
 */
public class JValue {

    static final int Secret = 1;
    static final int PlainText = 0;
    enum Memory {
        Enclave,
        Untrusted
    }
    int taint;
    Memory mem;
    Object value;
    JValue(int t, Memory m) {
        taint = t;
        mem = m;
    }
}
