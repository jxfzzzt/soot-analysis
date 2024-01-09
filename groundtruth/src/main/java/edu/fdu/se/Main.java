package edu.fdu.se;


import edu.fdu.se.util.MethodUtil;
import org.xerial.snappy.BitShuffle;
import org.xerial.snappy.SnappyOutputStream;

import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws Exception {
        Class<BitShuffle> clazz = BitShuffle.class;
        Method m = clazz.getDeclaredMethod("shuffle", byte[].class);
        System.out.println(MethodUtil.getMethodSignature(clazz, m));
    }
}
