package edu.fdu.se.analysis;

import edu.fdu.se.config.GlobalConfig;
import edu.fdu.se.util.SootUtil;
import lombok.extern.slf4j.Slf4j;
import soot.Body;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;

import java.lang.reflect.InvocationTargetException;

@Slf4j
public class PlainTest {

    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        SootUtil.defaultInitial(GlobalConfig.TARGET_CLASSES_PATH);

        SootClass sootClass = Scene.v().getSootClass("edu.fdu.se.MethodTest");
        SootMethod test01 = sootClass.getMethodByName("test01");
        Body body = test01.retrieveActiveBody();
        System.out.println(body);

    }

}
