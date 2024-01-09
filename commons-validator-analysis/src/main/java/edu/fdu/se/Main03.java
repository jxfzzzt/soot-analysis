package edu.fdu.se;

import edu.fdu.se.config.GlobalConfig;
import edu.fdu.se.util.SootUtil;
import soot.*;
import soot.jimple.internal.JAssignStmt;
import soot.jimple.internal.JVirtualInvokeExpr;
import soot.toolkits.graph.ExceptionalUnitGraph;
import soot.toolkits.graph.UnitGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main03 {
    public static void main(String[] args) {
        SootUtil.defaultInitial(GlobalConfig.TARGET_CLASSES_PATH);
        SootClass sootClass = Scene.v().getSootClass("edu.fdu.se.MethodTest");
        SootMethod sootMethod = sootClass.getMethodByName("test04");
        Body body = sootMethod.retrieveActiveBody();

        List<Unit> backwardUnits = new ArrayList<>(body.getUnits());
        Collections.reverse(backwardUnits);

        for (Unit unit : backwardUnits) {
            System.out.println(unit);
        }
       
    }
}
