package edu.fdu.se.util;

import soot.G;
import soot.Scene;
import soot.options.Options;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SootUtil {
    public static void defaultInitial(String classespath) {
        G.reset();
        List<String> argsList = new ArrayList<>();

        argsList.addAll(Arrays.asList("-allow-phantom-refs",
                "-w",
                "-keep-line-number", "enabled"
        ));

        argsList.add("--process-dir");
        argsList.add(classespath);

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
        Options.v().setPhaseOption("cg", "all-reachable:true");
        Options.v().set_no_bodies_for_excluded(true);
        Options.v().set_app(true);

        // load necessary class and method
        Scene.v().loadNecessaryClasses();
    }
}
