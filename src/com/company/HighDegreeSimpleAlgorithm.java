package com.company;

import java.util.*;

public class HighDegreeSimpleAlgorithm extends AbstractAlgorithm {

    private final boolean removed;

    public HighDegreeSimpleAlgorithm(AbstractGraph g, int n, boolean removed) {
        super(g, n);
        this.removed = removed;
    }

    @Override
    float findSolution() {
        Map<Integer,Integer> allocationMap = new HashMap<>();
        while (allocationMap.size() < n){
            List<Agent> agents = graph.getAgentsWithMaxConnections(allocationMap.keySet(), removed);
            Agent a = agents.get(0);
            allocationMap.put(a.getId(), graph.getBestAvailableHouse(a, allocationMap));
        }
        return graph.envyFreeDegree(allocationMap, false);
    }
}
