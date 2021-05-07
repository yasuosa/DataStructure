package com.rpy;

import com.rpy.list.SeqList;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        SeqList<Integer> seqList=new SeqList<>();
        for (int i = 0; i < 10; i++) {
            seqList.add(i);
        }
        seqList.remove(1);
        seqList.remove(9);
        int size = seqList.size();
        for (int i = 0; i < size; i++) {
            System.out.print(seqList.get(i));
        }
    }
}
