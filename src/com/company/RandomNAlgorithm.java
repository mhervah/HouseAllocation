package com.company;

import java.util.*;

public class RandomNAlgorithm extends AbstractAlgorithm{

    private final int numberOfGenerateSolutions;
    public RandomNAlgorithm(RandomGraph g, int n) {
        super(g, n);
        numberOfGenerateSolutions = 5*n;
    }

    @Override
    float findSolution() {
        List<Integer> houses = new ArrayList<>();
        for (int i = 1; i<=n; i++){
            houses.add(i);
        }
        float avgEnvyDegree = 1;
        Map<Integer,Integer> bestAllocationMap = null;
        for (int i = 0; i<numberOfGenerateSolutions; i++){
            Collections.shuffle(houses);
            Map<Integer,Integer> allocationMap = new HashMap<>();
            for (int agent = 1; agent<=n; agent++){
                allocationMap.put(agent, houses.get(agent-1));
            }
            float currentDegree = graph.envyFreeDegree(allocationMap);
            if(currentDegree <= avgEnvyDegree){
                avgEnvyDegree = currentDegree;
                bestAllocationMap = new HashMap<>(allocationMap);
            }
        }
        System.out.println(bestAllocationMap);
        return avgEnvyDegree;
    }
}
