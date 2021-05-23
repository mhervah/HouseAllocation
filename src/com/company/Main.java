package com.company;

import java.util.*;

public class Main {


    public static void main(String[] args) {
        comparebyN();
        compareTree();
    }

    public static void test() {
        long start = System.currentTimeMillis();

        int[] n = new int[]{5, 50, 100, 500, 1000};
        //int[] n = new int[]{100};
        System.out.printf("%10s %20s %20s %20s %20s", "N", "p = 2/n", "p = 1/n", "p = 1/100n", "p = 1/sqrt(n)");
        System.out.println();

        int numberofTests = 20;

        for (int i = 0; i < n.length; i++) {
            System.out.printf("%10s", n[i]);
            float[] p = new float[]{2f / n[i], 1f / n[i], 1f / (100 * n[i]), (float) (1f / Math.sqrt(n[i]))};
            for (int k = 0; k < p.length; k++) {
                float average = 0;
                for (int t = 0; t < numberofTests; t++) {
                    RandomGraph randomGraph = new RandomGraph(n[i], p[k]);
                    AbstractAlgorithm alg = new HighDegreeSimpleAlgorithm(randomGraph, n[i], true);
                    average += alg.findSolution();
                }

                System.out.printf("%20f", average / numberofTests);
            }
            System.out.println();

        }

        long end = System.currentTimeMillis();
        System.out.println("It takes " + (end - start) + " ms. Each pair tested " + numberofTests + " times.");
    }

    public static void comparebyP() {
        long start = System.currentTimeMillis();

        int[] n = new int[]{5, 25, 100, 500, 1000, 5000};
        //int[] n = new int[]{100};
        System.out.printf("%10s %20s %19s %17s %23s %15s", "N", "BruteForce", "HighDegree", "RandomN", "HighDegreeSimple", "Bucket");
        System.out.println();

        int numberofTests = 10;

        for (int k = 0; k < 4; k++) {
            System.out.println(new String[]{"p = 2/n", "p = 1/n", "p = 1/100n", "p = 1/sqrt(n)"}[k]);

            for (int i = 0; i < n.length; i++) {
                System.out.printf("%10s", n[i]);
                float[] p = new float[]{2f / n[i], 1f / n[i], 1f / (100 * n[i]), (float) (1f / Math.sqrt(n[i]))};
                float average = 0;
                if (n[i] < 12) {
                    for (int t = 0; t < numberofTests; t++) {
                        RandomGraph randomGraph = new RandomGraph(n[i], p[k]);
                        AbstractAlgorithm alg = new BruteForceAlgorithm(randomGraph, n[i]);
                        average += alg.findSolution();
                    }
                    System.out.printf("%20f", average / numberofTests);
                } else {
                    System.out.printf("%20s", "-");
                }

                if (n[i] < 51) {
                    for (int t = 0; t < numberofTests; t++) {
                        RandomGraph randomGraph = new RandomGraph(n[i], p[k]);
                        AbstractAlgorithm alg = new HighDegreeAlgorithm(randomGraph, n[i], false);
                        average += alg.findSolution();
                    }
                    System.out.printf("%20f", average / numberofTests);
                } else {
                    System.out.printf("%20s", "-");
                }

                if (n[i] < 1001) {
                    average = 0;
                    for (int t = 0; t < numberofTests; t++) {
                        RandomGraph randomGraph = new RandomGraph(n[i], p[k]);
                        AbstractAlgorithm alg = new RandomNAlgorithm(randomGraph, n[i]);
                        average += alg.findSolution();
                    }

                    System.out.printf("%20f", average / numberofTests);

                } else {
                    System.out.printf("%20s", "-");
                }

                if (n[i] < 1001) {
                    average = 0;

                    for (int t = 0; t < numberofTests; t++) {
                        RandomGraph randomGraph = new RandomGraph(n[i], p[k]);
                        AbstractAlgorithm alg = new HighDegreeSimpleAlgorithm(randomGraph, n[i], false);
                        average += alg.findSolution();
                    }

                    System.out.printf("%20f", average / numberofTests);

                } else {
                    System.out.printf("%20s", "-");
                }

                average = 0;
                for (int t = 0; t < numberofTests; t++) {
                    RandomGraph randomGraph = new RandomGraph(n[i], p[k]);
                    AbstractAlgorithm alg = new BucketAlgorithm(randomGraph, n[i]);
                    average += alg.findSolution();
                }

                System.out.printf("%20f", average / numberofTests);
                System.out.println();
            }

        }

        long end = System.currentTimeMillis();
        System.out.println("It takes " + (end - start) + " ms. Each pair tested " + numberofTests + " times.");
    }

    public static void comparebyN() {
        long start = System.currentTimeMillis();

        int[] n = new int[]{5, 25, 100, 500, 1000, 5000};
        //int[] n = new int[]{100};
        System.out.printf("%20s %20s %19s %17s %23s %15s", "p", "BruteForce", "HighDegree", "RandomN", "HighDegreeSimple", "Bucket");
        System.out.println();

        int numberofTests = 10;

        for (int i = 0; i < n.length; i++) {
            System.out.println("n = "+n[i]);

            for (int k = 0; k < 4; k++) {
                System.out.printf("%20s",new String[]{"2/n", "1/n", "1/100n", "1/sqrt(n)"}[k]);

                float[] p = new float[]{2f / n[i], 1f / n[i], 1f / (100 * n[i]), (float) (1f / Math.sqrt(n[i]))};
                float average = 0;
                if (n[i] < 12) {
                    for (int t = 0; t < numberofTests; t++) {
                        RandomGraph randomGraph = new RandomGraph(n[i], p[k]);
                        AbstractAlgorithm alg = new BruteForceAlgorithm(randomGraph, n[i]);
                        average += alg.findSolution();
                    }
                    System.out.printf("%20.8f", average / numberofTests);
                } else {
                    System.out.printf("%20s", "-");
                }

                if (n[i] < 51) {
                    for (int t = 0; t < numberofTests; t++) {
                        RandomGraph randomGraph = new RandomGraph(n[i], p[k]);
                        AbstractAlgorithm alg = new HighDegreeAlgorithm(randomGraph, n[i], false);
                        average += alg.findSolution();
                    }
                    System.out.printf("%20.8f", average / numberofTests);
                } else {
                    System.out.printf("%20s", "-");
                }

                if (n[i] < 1001) {
                    average = 0;
                    for (int t = 0; t < numberofTests; t++) {
                        RandomGraph randomGraph = new RandomGraph(n[i], p[k]);
                        AbstractAlgorithm alg = new RandomNAlgorithm(randomGraph, n[i]);
                        average += alg.findSolution();
                    }

                    System.out.printf("%20.8f", average / numberofTests);

                } else {
                    System.out.printf("%20s", "-");
                }

                if (n[i] < 1001) {
                    average = 0;

                    for (int t = 0; t < numberofTests; t++) {
                        RandomGraph randomGraph = new RandomGraph(n[i], p[k]);
                        AbstractAlgorithm alg = new HighDegreeSimpleAlgorithm(randomGraph, n[i], false);
                        average += alg.findSolution();
                    }

                    System.out.printf("%20.8f", average / numberofTests);

                } else {
                    System.out.printf("%20s", "-");
                }

                average = 0;
                for (int t = 0; t < numberofTests; t++) {
                    RandomGraph randomGraph = new RandomGraph(n[i], p[k]);
                    AbstractAlgorithm alg = new BucketAlgorithm(randomGraph, n[i]);
                    average += alg.findSolution();
                }

                System.out.printf("%20.8f", average / numberofTests);
                System.out.println();
            }

        }

        long end = System.currentTimeMillis();
        System.out.println("It takes " + (end - start) + " ms. Each pair tested " + numberofTests + " times.");
    }

    public static void compareTree() {
        long start = System.currentTimeMillis();

        int[] n = new int[]{5, 25, 100, 500, 1000, 5000};
        //int[] n = new int[]{100};
        System.out.printf("%10s %20s %19s %17s %23s %15s", "N", "BruteForce", "HighDegree", "RandomN", "HighDegreeSimple", "Bucket");
        System.out.println();

        int numberofTests = 20;


        for (int i = 0; i < n.length; i++) {
            System.out.printf("%10s", n[i]);
            float[] p = new float[]{2f / n[i], 1f / n[i], 1f / (100 * n[i]), (float) (1f / Math.sqrt(n[i]))};
            float average = 0;
            if (n[i] < 12) {
                for (int t = 0; t < numberofTests; t++) {
                    RandomTree randomGraph = new RandomTree(n[i]);
                    AbstractAlgorithm alg = new BruteForceAlgorithm(randomGraph, n[i]);
                    average += alg.findSolution();
                }
                System.out.printf("%20.8f", average / numberofTests);
            } else {
                System.out.printf("%20s", "-");
            }

            if (n[i] < 51) {
                for (int t = 0; t < numberofTests; t++) {
                    RandomTree randomGraph = new RandomTree(n[i]);
                    AbstractAlgorithm alg = new HighDegreeAlgorithm(randomGraph, n[i], false);
                    average += alg.findSolution();
                }
                System.out.printf("%20.8f", average / numberofTests);
            } else {
                System.out.printf("%20s", "-");
            }

            if (n[i] < 1001) {
                average = 0;
                for (int t = 0; t < numberofTests; t++) {
                    RandomTree randomGraph = new RandomTree(n[i]);
                    AbstractAlgorithm alg = new RandomNAlgorithm(randomGraph, n[i]);
                    average += alg.findSolution();
                }

                System.out.printf("%20.8f", average / numberofTests);

            } else {
                System.out.printf("%20s", "-");
            }

            if (n[i] < 1001) {
                average = 0;

                for (int t = 0; t < numberofTests; t++) {
                    RandomTree randomGraph = new RandomTree(n[i]);
                    AbstractAlgorithm alg = new HighDegreeSimpleAlgorithm(randomGraph, n[i], false);
                    average += alg.findSolution();
                }

                System.out.printf("%20.8f", average / numberofTests);

            } else {
                System.out.printf("%20s", "-");
            }

            average = 0;
            for (int t = 0; t < numberofTests; t++) {
                RandomTree randomGraph = new RandomTree(n[i]);
                AbstractAlgorithm alg = new BucketAlgorithm(randomGraph, n[i]);
                average += alg.findSolution();
            }

            System.out.printf("%20.8f", average / numberofTests);
            System.out.println();
        }


        long end = System.currentTimeMillis();
        System.out.println("It takes " + (end - start) + " ms. Each pair tested " + numberofTests + " times.");
    }
}
