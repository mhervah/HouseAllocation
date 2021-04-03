package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArrayIndexComparator implements Comparator<Integer>
{
    private final List<Integer> array;

    public ArrayIndexComparator(List<Integer> array)
    {
        this.array = array;
    }

    public List<Integer> createIndexArray()
    {
        List<Integer> indexes= new ArrayList<>(array.size());
        for (int i = 1; i <= array.size(); i++)
        {
            indexes.add(i); // Autoboxing
        }
        return indexes;
    }

    @Override
    public int compare(Integer index1, Integer index2)
    {
        // Autounbox from Integer to int to use as array indexes
        return array.get(index2-1).compareTo(array.get(index1-1));
    }
}