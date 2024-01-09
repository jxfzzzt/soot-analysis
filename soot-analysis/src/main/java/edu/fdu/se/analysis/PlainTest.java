package edu.fdu.se.analysis;

import edu.fdu.se.config.GlobalConfig;
import edu.fdu.se.util.SootUtil;
import lombok.extern.slf4j.Slf4j;
import soot.*;
import soot.jimple.internal.JAssignStmt;
import soot.jimple.internal.JSpecialInvokeExpr;
import soot.jimple.internal.JVirtualInvokeExpr;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Slf4j
public class PlainTest {

    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        SootUtil.defaultInitial(GlobalConfig.TARGET_CLASSES_PATH);

        SootClass sootClass = Scene.v().getSootClass("edu.fdu.se.MethodTest");
        SootMethod test01 = sootClass.getMethodByName("test06");
        Body body = test01.retrieveActiveBody();
        System.out.println(body);

        for (Unit unit : body.getUnits()) {
            if (unit instanceof JAssignStmt) {
                JAssignStmt assignStmt = (JAssignStmt) unit;
                Value rightOp = assignStmt.getRightOp();
                if (rightOp instanceof JVirtualInvokeExpr) {
                    JVirtualInvokeExpr virtualInvokeExpr = (JVirtualInvokeExpr) rightOp;
                    List<Value> args1 = virtualInvokeExpr.getArgs();
                    System.out.println(args1);
                }
            }
        }
    }

}
