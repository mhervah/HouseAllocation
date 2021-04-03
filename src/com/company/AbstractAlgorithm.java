package com.company;

public abstract class AbstractAlgorithm {

    RandomGraph graph;
    int n;

    public AbstractAlgorithm(RandomGraph g, int n) {
        this.graph = g;
        this.n=n;
    }

    abstract float findSolution();
}
