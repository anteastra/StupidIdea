package com.anteastra.batchprocessing;

import java.util.Random;

/**
 * Created by anteastra on 09.03.2017.
 */
public class RandomString {

    private static final char[] symbols;

    static {
        StringBuilder tmp = new StringBuilder();
        for (char ch = '0'; ch <= '9'; ++ch)
            tmp.append(ch);
        for (char ch = 'a'; ch <= 'z'; ++ch)
            tmp.append(ch);
        symbols = tmp.toString().toCharArray();
    }

    private final Random random = new Random();

    private final char[] buf;
    private String prefix = "";
    private String postfix = "";

    public RandomString(int length, String prefix, String postfix) {
        this(length);
        this.prefix = prefix;
        this.postfix = postfix;
    }

    public RandomString(int length) {
        if (length < 1)
            throw new IllegalArgumentException("length < 1: " + length);
        buf = new char[length];
    }

    public String nextString() {
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return prefix + (new String(buf)) + postfix;
    }
}
