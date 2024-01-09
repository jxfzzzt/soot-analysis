package edu.fdu.se.util;

import cn.hutool.core.util.ReferenceUtil;
import lombok.NonNull;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;

public class ObjectUtil {

    public static <T> T getObject(Class<?> clazz) {
        T o = null;
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            int parameterCount = constructor.getParameterCount();
            constructor.setAccessible(true);
            try {
                o = (T) constructor.newInstance(new Object[parameterCount]);
                break;
            } catch (Throwable t) {
                t.printStackTrace();
                // do nothing
            }
        }
        return o;
    }

    public static Object copyObject(Object object) {
        if (object == null) {
            return null;
        }

        if (Object.class.equals(object.getClass())) return new Object();

        // solve InputStream
        if (object instanceof InputStream) {
            if (((InputStream) object).markSupported()) {
                try {
                    ((InputStream) object).reset();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }

        if (checkIsSerializable(object.getClass())) {
            Serializable s = (Serializable) object;
            return SerializationUtils.clone(s);
        } else {
            return object;
        }
    }

    private static boolean checkIsSerializable(@NonNull Class<?> clazz) {
        Class<?> superclass = clazz.getSuperclass();
        List<Class<?>> superClassInterfaces = Arrays.asList(superclass.getInterfaces());
        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class<?> inter : interfaces) {
            if (!superClassInterfaces.contains(inter) && Serializable.class.equals(inter)) {
                return true;
            }
        }
        return false;
    }

}
