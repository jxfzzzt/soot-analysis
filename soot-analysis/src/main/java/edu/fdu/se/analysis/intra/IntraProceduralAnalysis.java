package edu.fdu.se.analysis.intra;

import soot.Body;
import soot.SootMethod;
import soot.Unit;
import soot.toolkits.graph.ExceptionalUnitGraph;
import soot.toolkits.scalar.BackwardFlowAnalysis;

import java.util.HashSet;
import java.util.Set;

public class IntraProceduralAnalysis extends BackwardFlowAnalysis<Unit, Set<FlowAbstraction>> {

    private SootMethod sootMethod;

    public IntraProceduralAnalysis(Body b) {
        super(new ExceptionalUnitGraph(b));
        this.sootMethod = b.getMethod();
        System.out.println("\n ====================================   " + sootMethod.getSignature());
    }


    @Override
    protected synchronized void flowThrough(Set<FlowAbstraction> taintsIn, Unit unit, Set<FlowAbstraction> taintsOut) {
        System.out.println(unit);
    }

    @Override
    protected Set<FlowAbstraction> newInitialFlow() {
        return new HashSet<FlowAbstraction>();
    }

    @Override
    protected Set<FlowAbstraction> entryInitialFlow() {
        return new HashSet<FlowAbstraction>();
    }

    @Override
    protected void merge(Set<FlowAbstraction> in1, Set<FlowAbstraction> in2, Set<FlowAbstraction> out) {
        out.addAll(in1);
        out.addAll(in2);
    }

    @Override
    protected void copy(Set<FlowAbstraction> source, Set<FlowAbstraction> dest) {
        dest.clear();
        dest.addAll(source);
    }

    @Override
    protected void doAnalysis() {
        super.doAnalysis();
    }

    public SootMethod getSootMethod() {
        return sootMethod;
    }
}
