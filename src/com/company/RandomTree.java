package com.company;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class RandomTree extends AbstractGraph{

    // Function to Generate Random Tree
    public RandomTree(int n) {
        this.vertices = n;
        this.edges = 0;
        agentsMap = new HashMap<>();
        for (int i = 1; i <= vertices; i++) {
            agentsMap.put(i, new Agent(i, n));
        }

        int m = n - 2;
        int[] prufer = new int[m];

        // Loop to Generate Random Array
        for (int i = 0; i < m; i++) {
            prufer[i] = this.random.nextInt(m + 1) + 1;
        }

        int vertex_set[] = new int[vertices];

        // Initialize the array of vertices
        for (int i = 0; i < vertices; i++)
            vertex_set[i] = 0;

        // Number of occurrences of vertex in code
        for (int i = 0; i < vertices - 2; i++)
            vertex_set[prufer[i] - 1] += 1;

        int j = 0;
        this.matrix = new int[vertices][vertices];

        // Find the smallest label not present in
        // prufer[].
        for (int i = 0; i < vertices - 2; i++) {
            for (j = 0; j < vertices; j++) {

                // If j+1 is not present in prufer set
                if (vertex_set[j] == 0) {

                    // Remove from Prufer set and print
                    // pair.

                    this.addEdge(j + 1, prufer[i]);
                    vertex_set[j] = -1;

                    vertex_set[prufer[i] - 1]--;

                    break;
                }
            }
        }

        j = 0;


        // For the last element
        int a = 0,b = 0;
        for (int i = 0; i < vertices; i++) {
            if (vertex_set[i] == 0 && j == 0) {

                j++;
                a = i+1;
            }
            else if (vertex_set[i] == 0 && j == 1) {
                b = i+1;
            }
        }

        this.addEdge(a,b);

     }


    // Driver Code
    public static void main(String[] args) {
        int n = 1000;
        AbstractGraph randomGraph = new RandomTree(n);
        AbstractAlgorithm alg = new BucketAlgorithm(randomGraph, n);
        System.out.println(alg.findSolution());
    }
}

