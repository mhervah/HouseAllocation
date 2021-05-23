package com.company;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Agent {

    private final List<Integer> preferenceList;
    private final int id;

    public Agent(int id, int n){
        this.id = id;

        preferenceList = new ArrayList<>();

        for(int i = 1; i <= n; i++){
            preferenceList.add(i);
        }
        Collections.shuffle(preferenceList);
    }

    public List<Integer> getPreferenceList() {
        return preferenceList;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return id+"";
    }
}
