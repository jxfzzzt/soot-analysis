package edu.fdu.se;

import edu.fdu.se.config.GlobalConfig;
import edu.fdu.se.util.SootUtil;
import soot.*;
import soot.jimple.internal.*;
import soot.toolkits.graph.ExceptionalUnitGraph;
import soot.toolkits.graph.UnitGraph;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SootUtil.defaultInitial(GlobalConfig.COMMONS_VALIDATOR_PATH);
        SootClass sootClass = Scene.v().getSootClass("org.apache.commons.validator.Field");
        SootMethod sootMethod = sootClass.getMethodByName("validate");

        System.out.println(sootMethod.retrieveActiveBody());

        Body body = sootMethod.retrieveActiveBody();
        UnitGraph cfg = new ExceptionalUnitGraph(body);

        for (Unit unit : cfg) {
            if (unit instanceof JIdentityStmt) {
                System.out.println("======== JIdentityStmt =======");
                JIdentityStmt identityStmt = (JIdentityStmt) unit;
                Value leftOp = identityStmt.getLeftOp();
                Local leftLocal = (Local) leftOp;
                Value rightLocal = identityStmt.getRightOp();
                System.out.println(leftLocal + " " + rightLocal);
            } else if (unit instanceof JAssignStmt) {
                System.out.println("======== JAssignStmt =======");
                JAssignStmt assignStmt = (JAssignStmt) unit;
                Value rightOp = assignStmt.getRightOp();
                if (rightOp instanceof JInstanceFieldRef) {
                    JInstanceFieldRef ref = (JInstanceFieldRef) rightOp;
                    System.out.println(ref.getField());
                }
            } else if (unit instanceof JReturnStmt) {
                System.out.println("======== JReturnStmt =======");
                JReturnStmt returnStmt = (JReturnStmt) unit;
                Local local = (Local) returnStmt.getOp();
                System.out.println(local);
            } else if (unit instanceof JIfStmt) {
                System.out.println("======== JIfStmt =======");
                JIfStmt ifStmt = (JIfStmt)unit;
                List<ValueBox> useBoxes = ifStmt.getCondition().getUseBoxes();
                System.out.println(useBoxes);
            }
        }
    }
}
