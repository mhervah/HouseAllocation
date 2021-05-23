package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class RandomNAlgorithm extends AbstractAlgorithm {

    private final int numberOfGenerateSolutions;

    public RandomNAlgorithm(AbstractGraph g, int n) {
        super(g, n);
        numberOfGenerateSolutions = n;
    }

    @Override
    float findSolution() {
        List<Integer> houses = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (graph.bestAllocationMap.get(i) == null) {
                houses.add(i);
            }
        }
        float avgEnvyDegree = 1;
        Map<Integer, Integer> bestAllocationMap = graph.bestAllocationMap;
        for (int i = 0; i < numberOfGenerateSolutions; i++) {
            Collections.shuffle(houses);
            Map<Integer, Integer> allocationMap = new HashMap<>(graph.bestAllocationMap);
            List<Agent> agents = graph.getAgentsMap().values().stream().filter(agent -> graph.bestAllocationMap.get(agent.getId()) == null).collect(Collectors.toList());
            for (int a = 0; a < agents.size(); a++) {
                allocationMap.put(agents.get(a).getId(), houses.get(a));
            }

            float currentDegree = graph.envyFreeDegree(allocationMap, false);
            if (currentDegree <= avgEnvyDegree) {
                avgEnvyDegree = currentDegree;
                bestAllocationMap = new HashMap<>(allocationMap);
            }
        }
        //System.out.println(bestAllocationMap);
        return avgEnvyDegree;
    }

    public static void main(String[] args) {
        int n = 5000;
        RandomGraph randomGraph = new RandomGraph(n, 2f/n);
        AbstractAlgorithm alg = new RandomNAlgorithm(randomGraph, n);
        System.out.println(alg.findSolution());
    }
}
