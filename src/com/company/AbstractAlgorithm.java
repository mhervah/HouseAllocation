package com.company;

public abstract class AbstractAlgorithm {

    AbstractGraph graph;
    int n;

    public AbstractAlgorithm(AbstractGraph g, int n) {
        this.graph = g;
        this.n=n;
    }

    abstract float findSolution();
}
