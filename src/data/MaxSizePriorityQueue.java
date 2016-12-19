/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * A priority queue with a  max size, if an item is added and the maxsize is overwritten the first item is removed
 * This class is copied and modified from: http://stackoverflow.com/questions/7878026/is-there-a-priorityqueue-implementation-with-fixed-capacity-and-custom-comparato
 */
public class MaxSizePriorityQueue<E> extends TreeSet<E> {

    private int maxsize;

    public MaxSizePriorityQueue(int maxSize, Comparator<E> comparator) {
        super(comparator);
        this.maxsize = maxSize;
    }

    
    @Override
    public boolean add(E e) {
        boolean result = super.add(e);
        if(super.size() > maxsize)
            super.pollFirst();
        return result;
    }
}