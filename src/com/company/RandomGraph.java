package com.company;// Create a Random Graph Using
// Random Edge Generation in Java 

import java.util.*;
import java.io.*;

public class RandomGraph {
    // Number of vertices 
    private int vertices;
    private Map<Integer, Agent> agentsMap;
    private Map<String, Float> envyDegreeMap = new HashMap<>();
    private int edges;

    // p represents the probability 
    private float p;

    // A Random instance to generate random values 
    private Random random = new Random();

    public int[][] matrix;

    public RandomGraph(int n, float p) {
        this.vertices = n;
        this.edges = 0;
        agentsMap = new HashMap<>();
        for (int i = 1; i <= vertices; i++) {
            agentsMap.put(i, new Agent(i, n));
        }

        // p is the probability that there 
        // is an edge between any two vertices 
        // say, 0.4 is the probability that there 
        // is an edge between any two vertices 
        this.p = p;

        this.matrix = new int[n][n];

        // Print the probability p 
        //System.out.println(
         //       "The probability that there is an edge"
        //                + " between any two vertices is : " + p);


        // A for loop to randomly generate edges 
        for (int i = 1; i <= vertices; i++) {
            for (int j = i+1; j <= vertices; j++) {
                // edgeProbability is a random number 
                // between 0.0 and 1.0 
                // If the randomly chosen number 
                // edgeProbability is less than 
                // the probability of an edge p, 
                // say, edgeProbability = 0.2 which is less 
                // than p = 0.4, then add an edge between the 
                // vertex i and the vertex j 
                float edgeProbability = random.nextFloat();
                if (edgeProbability < p)
                    addEdge(i, j);
            }
        }

        /*for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix.length;j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }*/
    }

    // Method to add edges between given vertices 
    void addEdge(int v, int w) {
        edges++;
        matrix[v - 1][w - 1] = 1;
        matrix[w - 1][v - 1] = 1;
    }

    public Map<Integer, Agent> getAgentsMap() {
        return agentsMap;
    }

    public boolean areConnected(Agent a, Agent b) {
        return matrix[a.getId()][b.getId()] == 1;
    }

    public boolean isEnvyFree(Map<Integer, Integer> allocationMap) {
        for (Map.Entry<Integer, Integer> house : allocationMap.entrySet()) {
            Agent a = agentsMap.get(house.getKey());
            List<Integer> highPref = a.getPreferenceList().subList(0, a.getPreferenceList().indexOf(house.getValue()));
            List<Integer> connections = getConnections(a);
            connections.removeIf((c) -> !allocationMap.containsKey(c));

            for (int c : connections) {
                int connectionChoice = allocationMap.get(c);
                int connectionIndexOfChoice = agentsMap.get(c).getPreferenceList().indexOf(connectionChoice);
                for (int p = 0; p < highPref.size(); p++) {
                    if (highPref.get(p) == connectionChoice && p <= connectionIndexOfChoice)
                        return false;
                }
            }
        }
        return true;
    }

    public float envyFreeDegree(Map<Integer, Integer> allocationMap) {
        if(envyDegreeMap.containsKey(allocationMap.values().toString())){
            return envyDegreeMap.get(allocationMap.values().toString());
        }

        float avgDegreeOfEnvy = 0;
        for (Map.Entry<Integer, Integer> house : allocationMap.entrySet()) {
            Agent a = agentsMap.get(house.getKey());
            List<Integer> highPref = a.getPreferenceList().subList(0, a.getPreferenceList().indexOf(house.getValue()));
            List<Integer> connections = getConnections(a);
            connections.removeIf((c) -> !allocationMap.containsKey(c));

            for (int c : connections) {
                float degreeOfEnvy = 0;
                int connectionChoice = allocationMap.get(c);
                int connectionIndexOfChoice = agentsMap.get(c).getPreferenceList().indexOf(connectionChoice);
                for (int p = 0; p < highPref.size(); p++) {
                    if (highPref.get(p) == connectionChoice && p <= connectionIndexOfChoice) {
                        int indexOfChoice = a.getPreferenceList().indexOf(house.getValue());
                        degreeOfEnvy = (float)(indexOfChoice - p)/(this.vertices-1);
                        avgDegreeOfEnvy += degreeOfEnvy;
                    }
                }
            }
        }
        envyDegreeMap.put(allocationMap.values().toString(), avgDegreeOfEnvy/(this.edges));
        return avgDegreeOfEnvy/(this.edges);
    }

    public List<Integer> getConnections(Agent a) {
        List<Integer> connections = new ArrayList<>();
        for (int i = 1; i <= this.matrix[a.getId() - 1].length; i++) {
            if (this.matrix[a.getId() - 1][i - 1] == 1) {
                connections.add(i);
            }
        }
        return connections;
    }

    public List<Integer> getConnections(Agent a, Set<Integer> excluded) {
        List<Integer> connections = getConnections(a);
        connections.removeIf(excluded::contains);
        return connections;
    }

    public List<Agent> getAgentsWithMaxConnections(Set<Integer> excluded, boolean excludeItself) {
        List<Integer> degrees = new ArrayList<>();
        this.agentsMap.values().forEach(agent -> {
            if(excludeItself){
                degrees.add(getConnections(agent, excluded).size());
            } else {
                degrees.add(getConnections(agent).size());
            }
        });

        ArrayIndexComparator comparator = new ArrayIndexComparator(degrees);
        List<Integer> indexes = comparator.createIndexArray();
        indexes.sort(comparator);

        List<Agent> agents = new ArrayList<>();
        Integer degree = null;
        for (int index : indexes) {
            if (!excluded.contains(index)) {
                if (degree == null) {
                    degree = degrees.get(index - 1);
                    agents.add(agentsMap.get(index));
                } else if (degrees.get(index - 1).equals(degree)) {
                    agents.add(agentsMap.get(index));
                } else {
                    return agents;
                }
            }
        }
        return agents;
    }

    public List<Integer> getAgentsSortedByDegreeDec() {
        List<Integer> degrees = new ArrayList<>();
        this.agentsMap.values().forEach(agent -> {
            degrees.add(getConnections(agent).size());
        });
        ArrayIndexComparator comparator = new ArrayIndexComparator(degrees);
        List<Integer> indexes = comparator.createIndexArray();
        indexes.sort(comparator);
        return indexes;
    }

    public Integer getBestAvailableHouse(Agent a, Map<Integer, Integer> allocationMap) {
        for (int p : a.getPreferenceList()) {
            if (!allocationMap.containsValue(p)) {
                return p;
            }
        }
        return null;
    }
}