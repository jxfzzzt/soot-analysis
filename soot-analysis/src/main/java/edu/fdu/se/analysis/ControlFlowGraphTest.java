package edu.fdu.se.analysis;

import edu.fdu.se.util.SootUtil;
import lombok.extern.slf4j.Slf4j;
import soot.*;
import soot.toolkits.graph.ExceptionalUnitGraph;
import soot.toolkits.graph.UnitGraph;

import static edu.fdu.se.config.GlobalConfig.TARGET_CLASSES_PATH;

@Slf4j
public class ControlFlowGraphTest {

    public static void main(String[] args) {
        log.info("classpath: {}", TARGET_CLASSES_PATH);

        String className = "edu.fdu.se.IfTest";
        SootUtil.defaultInitial(TARGET_CLASSES_PATH);
        SootClass sootClass = Scene.v().getSootClass(className);
        for (SootMethod method : sootClass.getMethods()) {
            System.out.println("=========== " + method.getSignature() + " ===========");
            Body body = method.retrieveActiveBody();
            UnitGraph cfg = new ExceptionalUnitGraph(body);

            System.out.println("Control Flow Graph for method: { " + method.getName() + " }");

            for (Unit unit : cfg) {
                System.out.println(unit.toString() + " -> " + cfg.getSuccsOf(unit));
            }
        }
    }
}

