package com.company;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Create a RandomGraph object
        int n = 20;
        int aWin = 0;
        int draw = 0;
        int bWin = 0;
        for(int i=0; i<100; i++) {
            RandomGraph randomGraph = new RandomGraph(n, 2f / n);
            /*for (Agent a : randomGraph.getAgentsMap().values()) {
                System.out.println("Agent" + a.getId() + ":" + randomGraph.getConnections(a));
            }*/
            // Print the graph
        /*for (int i = 0; i < randomGraph.matrix.length; i++){
            for (int j = 0; j < randomGraph.matrix.length; j++) {
                System.out.print(randomGraph.matrix[i][j] + " ");
            }
            System.out.println();
        }*/

            /*for (int i = 1; i <= randomGraph.matrix.length; i++) {
                System.out.println(i + ":" + randomGraph.getAgentsMap().get(i).getPreferenceList());
            }*/

            //BruteForceAlgorithm alg = new BruteForceAlgorithm(randomGraph, n);
            //System.out.println(alg.findSolution());

            //long startTime = System.currentTimeMillis();
            AbstractAlgorithm alg2 = new HighDegreeAlgorithm(randomGraph, n);
            //System.out.println(alg2.findSolution());
            //long endTime = System.currentTimeMillis();
            //System.out.println("Total execution time: " + (endTime - startTime));

            //startTime = System.currentTimeMillis();
            AbstractAlgorithm alg4 = new HighDegreeRemoveAlgorithm(randomGraph, n);
            //System.out.println(alg4.findSolution());
            //endTime = System.currentTimeMillis();
            //System.out.println("Total execution time: " + (endTime - startTime));
            if (alg2.findSolution()> alg4.findSolution()){
                aWin++;
            } else if (alg2.findSolution()< alg4.findSolution()){
                bWin++;
            } else {
                draw++;
            }

            /*AbstractAlgorithm alg3 = new RandomNAlgorithm(randomGraph, n);
            System.out.println(alg3.findSolution());
            System.out.println("******************************");*/
        }
        System.out.print(aWin + " : "+draw+" : "+bWin);
    }
}
