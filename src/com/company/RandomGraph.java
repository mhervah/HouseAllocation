package com.company;// Create a Random Graph Using
// Random Edge Generation in Java 

import java.util.*;
import java.io.*;

public class RandomGraph extends AbstractGraph {
    // Number of vertices 

    // A Random instance to generate random values


    public RandomGraph(int n, float p) {
        this.vertices = n;
        agentsMap = new HashMap<>();
        for (int i = 1; i <= vertices; i++) {
            agentsMap.put(i, new Agent(i, n));
        }

        // p is the probability that there 
        // is an edge between any two vertices 
        // say, 0.4 is the probability that there 
        // is an edge between any two vertices 
        // p represents the probability

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
}