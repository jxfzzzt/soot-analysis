package edu.fdu.se;

import edu.fdu.se.config.GlobalConfig;
import edu.fdu.se.util.SootUtil;
import soot.*;
import soot.jimple.internal.JAssignStmt;
import soot.jimple.internal.JInstanceFieldRef;

public class Main04 {
    public static void main(String[] args) {
        SootUtil.defaultInitial(GlobalConfig.TARGET_CLASSES_PATH);
        SootClass sootClass = Scene.v().getSootClass("edu.fdu.se.MethodTest");
        SootMethod sootMethod = sootClass.getMethodByName("test05");

        Body body = sootMethod.retrieveActiveBody();
        System.out.println(body);
        for (Unit unit : body.getUnits()) {
            if (unit instanceof JAssignStmt) {
                JAssignStmt assignStmt = (JAssignStmt) unit;
                Value leftOp = assignStmt.getLeftOp();
                Value rightOp = assignStmt.getRightOp();
                if (rightOp instanceof JInstanceFieldRef) {
                    JInstanceFieldRef instanceFieldRef = (JInstanceFieldRef) rightOp;
                    Value base = instanceFieldRef.getBase();
                    System.out.println("rightop: " + unit);
                } else if(leftOp instanceof JInstanceFieldRef){
                    JInstanceFieldRef instanceFieldRef = (JInstanceFieldRef) leftOp;
                    Value base = instanceFieldRef.getBase();
                    System.out.println("leftop: " + unit);
                }
            }
        }
    }
}
