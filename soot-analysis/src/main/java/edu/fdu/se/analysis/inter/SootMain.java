package edu.fdu.se.analysis.inter;

import edu.fdu.se.config.GlobalConfig;
import soot.*;
import soot.options.Options;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SootMain {
    public static final String mainClass = "edu.fdu.se.Main";

    public static void main(String[] args) {
        preconfig();
    }

    private static void preconfig() {
        G.reset();
        List<String> argsList = new ArrayList<>();

        argsList.addAll(Arrays.asList("-allow-phantom-refs",
                "-w",
                "-keep-line-number", "enabled"
        ));

        argsList.add("--process-dir");
        argsList.add(GlobalConfig.TARGET_CLASSES_PATH);

        argsList.addAll(Arrays.asList("-p", "jb", "use-original-names:true"));
        String[] args;
        args = argsList.toArray(new String[0]);

        Options.v().parse(args);
        Options.v().set_src_prec(Options.src_prec_java);
        Options.v().set_whole_program(true);
        Options.v().set_allow_phantom_refs(true);
        Options.v().set_keep_line_number(true);
        Options.v().set_verbose(true);
        Options.v().set_keep_line_number(true);
        Options.v().setPhaseOption("cg", "safe-newinstance:true");
        Options.v().setPhaseOption("cg.cha","enabled:false");
        Options.v().set_no_bodies_for_excluded(true);

        // Enable whole-program mode
        Options.v().set_whole_program(true);
        Options.v().set_app(true);
        // Enable SPARK call-graph construction
        Options.v().setPhaseOption("cg.spark","enabled:true");
        Options.v().setPhaseOption("cg.spark","verbose:true");
        Options.v().setPhaseOption("cg.spark","on-fly-cg:true");

        Options.v().set_allow_phantom_refs(true);

        PackManager.v().getPack("wjtp").add(new Transform("wjtp.herosifds", new IFDSDataFlowTransformer()));
        Scene.v().loadNecessaryClasses();

        Options.v().set_main_class(mainClass);

        // Load the main class
        SootClass c = Scene.v().loadClass(mainClass, SootClass.BODIES);
        c.setApplicationClass();

        SootMethod entryPoint = c.getMethodByName("main");
        List<SootMethod> entryPoints = new ArrayList<SootMethod>();
        entryPoints.add(entryPoint);
        Scene.v().setEntryPoints(entryPoints);

        Main.main(args);
    }
}
