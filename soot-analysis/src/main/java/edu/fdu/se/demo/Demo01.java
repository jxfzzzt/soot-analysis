package edu.fdu.se.demo;

import edu.fdu.se.config.GlobalConfig;
import edu.fdu.se.util.SootUtil;
import soot.*;
import soot.jimple.internal.JIfStmt;

import java.util.List;

public class Demo01 {
    public static void main(String[] args) {
        SootUtil.defaultInitial(GlobalConfig.TARGET_CLASSES_PATH);

        SootClass sootClass = Scene.v().getSootClass("edu.fdu.se.MethodTest");
        SootMethod test01 = sootClass.getMethodByName("test07");
        Body body = test01.retrieveActiveBody();
        System.out.println(body);

        for (Unit unit : body.getUnits()) {
            if(unit instanceof JIfStmt) {
                JIfStmt ifStmt = (JIfStmt) unit;
                List<ValueBox> useBoxes = ifStmt.getCondition().getUseBoxes();
                for (ValueBox useBox : useBoxes) {
                    System.out.println(useBox);
                }
            }
        }
    }
}
