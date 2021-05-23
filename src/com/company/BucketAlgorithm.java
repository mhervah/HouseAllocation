package com.company;

import java.util.*;

public class BucketAlgorithm extends AbstractAlgorithm {

    public BucketAlgorithm(AbstractGraph g, int n) {
        super(g, n);
    }

    @Override
    float findSolution() {
        List<Integer>[] buckets = new ArrayList[n];
        Map<Integer, Integer> bestAllocationMap = new HashMap<>();

        for (int k = 0; k <= Math.sqrt(n); k++) {
            for (int i = 0; i < n; i++) {
                buckets[i] = new ArrayList<>();
            }
            int finalK = k;
            graph.getAgentsMap().forEach((i, agent) -> {
                if (!bestAllocationMap.containsKey(i)) {
                    buckets[agent.getPreferenceList().get(finalK) - 1].add(i);
                }
            });

            for (int i = 0; i < n; i++) {
                if (buckets[i].size() == 1) {
                    bestAllocationMap.put(buckets[i].get(0), i + 1);
                } else {

                    for (int a = 0; a < buckets[i].size(); a++) {
                        //System.out.println(graph.getConnections(graph.getAgentsMap().get(buckets[i].get(a))).size());
                        if (graph.getConnections(graph.getAgentsMap().get(buckets[i].get(a))).size() <= 2) {
                            bestAllocationMap.put(buckets[i].get(a), i + 1);
                        }
                    }
                }
            }

            //System.out.println(Arrays.toString(buckets));

            if (bestAllocationMap.size() == n) {
                //System.out.println(k);
                return graph.envyFreeDegree(bestAllocationMap, false);
            }


        }

        graph.bestAllocationMap = bestAllocationMap;

        AbstractAlgorithm alg = new RandomNAlgorithm(graph, n);

        return alg.findSolution();
    }

}
