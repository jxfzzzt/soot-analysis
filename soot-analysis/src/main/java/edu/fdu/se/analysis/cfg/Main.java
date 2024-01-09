package edu.fdu.se.analysis.cfg;

import edu.fdu.se.config.GlobalConfig;
import edu.fdu.se.util.SootUtil;
import soot.*;
import soot.jimple.internal.ImmediateBox;
import soot.jimple.internal.JIfStmt;
import soot.toolkits.graph.ExceptionalUnitGraph;
import soot.toolkits.graph.UnitGraph;

public class Main {
    public static void main(String[] args) {
        SootUtil.defaultInitial(GlobalConfig.TARGET_CLASSES_PATH);

        SootClass sootClass = Scene.v().getSootClass("edu.fdu.se.IfTest");
        SootMethod test = sootClass.getMethodByName("test");

        Body body = test.retrieveActiveBody();
        System.out.println(body.getLocals());
        System.out.println(body.getParameterLocals());
        UnitGraph graph = new ExceptionalUnitGraph(body);


        Unit u = null;
        // 遍历控制流图中的边
        for (Unit unit : graph) {
            if (unit instanceof JIfStmt) {
                u = unit;
            }
            for (Unit successor : graph.getSuccsOf(unit)) {
                System.out.println("Edge: " + unit + " -> " + successor);
            }
            System.out.println("=========");
        }

        assert u != null;
        System.out.println(u);
        JIfStmt stmt = (JIfStmt) u;
        System.out.println(stmt.getUseBoxes());
        for (ValueBox useBox : stmt.getUseBoxes()) {
            if (useBox instanceof ImmediateBox) {
                ImmediateBox immediateBox = (ImmediateBox) useBox;
                Value value = immediateBox.getValue();
                System.out.println(value);

            }
        }
    }
}

