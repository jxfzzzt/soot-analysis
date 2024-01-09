package edu.fdu.se.analysis.intra;

import edu.fdu.se.config.GlobalConfig;
import soot.*;
import soot.options.Options;

import java.util.Arrays;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        preconfig();
    }

    private static void preconfig() {
        G.reset();

        Transform transform = new Transform("jtp.analysis", new BodyTransformer() {
            @Override
            protected void internalTransform(Body b, String phaseName, Map<String, String> options) {
                IntraProceduralAnalysis ipa = new IntraProceduralAnalysis(b);
                ipa.doAnalysis();
            }

        });

        Options.v().set_num_threads(1);
        PackManager.v().getPack("jtp").add(transform);

        String[] args = new String[]{"-pp", "-process-dir", GlobalConfig.TARGET_CLASSES_PATH, "-src-prec", "class", "-output-format", "none"};
        System.out.println(Arrays.asList(args));
        soot.Main.main(args);
    }
}
