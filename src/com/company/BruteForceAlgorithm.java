package com.company;

import java.util.*;

public class BruteForceAlgorithm extends AbstractAlgorithm {

    public BruteForceAlgorithm(AbstractGraph g, int n) {
        super(g, n);
    }

    @Override
    public float findSolution() {
        Map<Integer, Integer> allocationMap = new HashMap<>();
        Map<Integer, Integer> bestAllocationMap = new HashMap<>();
        float avgEnvyDegree = 1;
        List<List<Integer>> permutations = getPermutationList(n);
        for (List<Integer> permutation : permutations) {
            allocationMap.clear();
            for (int agent : permutation) {
                List<Integer> pref = graph.getAgentsMap().get(agent).getPreferenceList();
                if (allocationMap.isEmpty()) {
                    allocationMap.put(agent, pref.get(0));
                } else {
                    for (int p : pref) {
                        if (!allocationMap.containsValue(p)) {
                            allocationMap.put(agent, p);
                            break;
                        }
                    }
                }
            }
            float current = graph.envyFreeDegree(allocationMap, false);
            if (current < avgEnvyDegree) {
                avgEnvyDegree = current;
                bestAllocationMap = new HashMap<>(allocationMap);
            }
        }
        //System.out.println(bestAllocationMap);
        return avgEnvyDegree;
    }

    public List<List<Integer>> getPermutationList(int n) {

        List<Integer> elements = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            elements.add(i);
        }

        int[] indexes = new int[n];
        for (int i = 0; i < n; i++) {
            indexes[i] = 0;
        }

        List<List<Integer>> permutations = new ArrayList<>();
        permutations.add(new ArrayList<>(elements));

        int i = 0;
        while (i < n) {
            if (indexes[i] < i) {
                Collections.swap(elements, i % 2 == 0 ? 0 : indexes[i], i);
                permutations.add(new ArrayList<>(elements));
                indexes[i]++;
                i = 0;
            } else {
                indexes[i] = 0;
                i++;
            }
        }

        return permutations;
    }

    public static void main(String[] args) {
        int n = 12;
        RandomGraph randomGraph = new RandomGraph(n, 2f/n);
        AbstractAlgorithm alg = new BruteForceAlgorithm(randomGraph, n);
        System.out.println(alg.findSolution());
    }

}
