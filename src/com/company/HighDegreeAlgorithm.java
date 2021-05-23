package com.company;

import java.util.*;

public class HighDegreeAlgorithm extends AbstractAlgorithm {

    private final boolean removed;
    public HighDegreeAlgorithm(AbstractGraph g, int n, boolean removed) {
        super(g, n);
        this.removed = removed;
    }

    @Override
    float findSolution() {
        Map<Integer,Integer> allocationMap = new HashMap<>();
        allocationMap = recursion(allocationMap);
        //System.out.println(allocationMap);
        //System.out.println("a: "+graph.envyDegreeMap.keySet()+" ");
        return graph.envyFreeDegree(allocationMap, true);
    }

    public Map<Integer,Integer> recursion(Map<Integer,Integer> allocationMap){
        if (allocationMap.size() == n) {
            return allocationMap;
        }

        List<Agent> agents = graph.getAgentsWithMaxConnections(allocationMap.keySet(), removed);

        //System.out.println(agents);

        if(graph.getConnections(agents.get(0)).size() == 0){
            agents.forEach(agent -> {
                Integer bestHouse = graph.getBestAvailableHouse(agent, allocationMap);
                allocationMap.put(agent.getId(), bestHouse);
            });

            //System.out.println(allocationMap.size());

            return allocationMap;
        }

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


        if(allocationMap.size() == n){
            return allocationMap;
        }

        if(agents.isEmpty()) {
            return recursion(allocationMap);
        }

        float avgEnvyDegree = 1;
        Map<Integer,Integer> bestAllocationMap = null;
        for(Agent agent: agents){
            Map<Integer, Integer> map = new HashMap<>(allocationMap);
            map.put(agent.getId(), graph.getBestAvailableHouse(agent, map));
            map = recursion(map);
            float currentDegree = graph.envyFreeDegree(map, true);
            if(currentDegree <= avgEnvyDegree){
                avgEnvyDegree = currentDegree;
                bestAllocationMap = map;
            }
        }

        return bestAllocationMap;
    }

    public static void main(String[] args) {
        int n = 25;
        RandomGraph randomGraph = new RandomGraph(n, 2f/n);
        AbstractAlgorithm alg = new HighDegreeAlgorithm(randomGraph, n, false);
        System.out.println(alg.findSolution());
    }
}
