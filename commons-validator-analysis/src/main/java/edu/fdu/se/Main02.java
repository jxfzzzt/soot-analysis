package edu.fdu.se;

import edu.fdu.se.config.GlobalConfig;
import edu.fdu.se.util.SootUtil;
import org.checkerframework.checker.units.qual.A;
import soot.*;
import soot.jimple.internal.JAssignStmt;
import soot.jimple.internal.JInterfaceInvokeExpr;
import soot.jimple.internal.JVirtualInvokeExpr;
import soot.toolkits.graph.ExceptionalUnitGraph;
import soot.toolkits.graph.UnitGraph;

import java.util.ArrayList;
import java.util.List;

public class Main02 {

    public static void main(String[] args) {
        SootUtil.defaultInitial(GlobalConfig.COMMONS_VALIDATOR_PATH);
        SootClass sootClass = Scene.v().getSootClass("org.apache.commons.validator.Form");
        List<Type> list = new ArrayList<>();
        SootMethod sootMethod = sootClass.getMethod("org.apache.commons.validator.ValidatorResults validate(java.util.Map,java.util.Map,int,java.lang.String)"); // org.apache.commons.validator.ValidatorResults validate(java.util.Map,java.util.Map,int,java.lang.String)
        System.out.println(sootMethod.getSubSignature());
        Body body = sootMethod.retrieveActiveBody();
        UnitGraph cfg = new ExceptionalUnitGraph(body);

        System.out.println(body);
    }
}
