package com.company;

import java.util.*;

public class HighDegreeAlgorithm extends AbstractAlgorithm {

    public HighDegreeAlgorithm(RandomGraph g, int n) {
        super(g, n);
    }

    @Override
    float findSolution() {
        Map<Integer,Integer> allocationMap = new HashMap<>();
        allocationMap = recursion(allocationMap);
        System.out.println(allocationMap);
        return graph.envyFreeDegree(allocationMap);
    }

    public Map<Integer,Integer> recursion(Map<Integer,Integer> allocationMap){
        if (allocationMap.size() == n) {
            return allocationMap;
        }

        List<Agent> agents = graph.getAgentsWithMaxConnections(allocationMap.keySet());

        Map<Integer, Integer> bestHousesForAgents = new HashMap<>();
        Map<Integer, Integer> housesToAgents = new HashMap<>();

        agents.forEach(agent -> {
            Integer bestHouse = graph.getBestAvailableHouse(agent, allocationMap);
            if(!housesToAgents.containsKey(bestHouse)){
                housesToAgents.put(bestHouse, agent.getId());
                bestHousesForAgents.put(bestHouse, agent.getId());
            } else {
                bestHousesForAgents.remove(bestHouse);
            }
        });

        bestHousesForAgents.forEach((key, value) -> {
            allocationMap.put(value, key);
            agents.removeIf(agent -> agent.getId() == value);
        });

        if(agents.isEmpty())
            return recursion(allocationMap);

        float avgEnvyDegree = 1;
        Map<Integer,Integer> bestAllocationMap = null;
        for(Agent agent: agents){
            Map<Integer, Integer> map = new HashMap<>(allocationMap);
            if(map.size() == n)
                return map;
            map.put(agent.getId(), graph.getBestAvailableHouse(agent, map));
            map = recursion(map);
            float currentDegree = graph.envyFreeDegree(map);
            if(currentDegree <= avgEnvyDegree){
                avgEnvyDegree = currentDegree;
                bestAllocationMap = map;
            }
        };

        return bestAllocationMap;
    }
}
