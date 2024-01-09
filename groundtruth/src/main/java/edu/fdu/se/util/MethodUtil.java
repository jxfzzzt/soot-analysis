package edu.fdu.se.util;

import soot.SootMethod;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MethodUtil {
    // obtain soot method signature
    public static String getMethodSignature(Class clazz, Method method) {
        String sb = "<" +
                clazz.getName() +
                ": " +
                corp(method.getReturnType().getName()) +
                " " +
                method.getName() +
                "(" +
                Arrays.stream(method.getParameterTypes())
                        .map(Class::getName)
                        .map(MethodUtil::corp)
                        .collect(Collectors.joining(",")) +
                ")" +
                ">";
        return sb;
    }

    public static String getMethodSignature(Class clazz, Constructor constructor) {
        String sb = "<" +
                clazz.getName() +
                ": " +
                "void <init>" +
                "(" +
                Arrays.stream(constructor.getParameterTypes())
                        .map(Class::getName)
                        .map(MethodUtil::corp)
                        .collect(Collectors.joining(",")) +
                ")" +
                ">";
        return sb;
    }

    public static String getMethodSignature(SootMethod sootMethod) {
        return sootMethod.getSignature();
    }

    private static String corp(String name) {
        if (name.charAt(0) == '[') {
            int j = 0;
            int cnt = 0;
            while (name.charAt(j) == '[') {
                j++;
                cnt++;
            }

            if (name.charAt(j) == 'L') j++;

            name = name.substring(j);

            if (name.charAt(name.length() - 1) == ';') {
                name = name.substring(0, name.length() - 1);
            }

            switch (name) {
                case "V":
                    name = "void";
                    break;
                case "Z":
                    name = "boolean";
                    break;
                case "B":
                    name = "byte";
                    break;
                case "C":
                    name = "char";
                    break;
                case "S":
                    name = "short";
                    break;
                case "I":
                    name = "int";
                    break;
                case "J":
                    name = "long";
                    break;
                case "F":
                    name = "float";
                    break;
                case "D":
                    name = "double";
                    break;
                default:
                    break;
            }

            StringBuilder sb = new StringBuilder(name);
            for (int i = 0; i < cnt; i++) sb.append("[]");
            name = sb.toString();
            return name;
        } else {
            return name;
        }
    }
}
