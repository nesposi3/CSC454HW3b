package com.nesposi3.AtomicModels;

import com.nesposi3.Network;

public class MemoryModel extends Network {
    private int b1, b2;
    @Override
    public void delta(String[] input) {
        this.b1 = b2;
        this.b2 = Integer.parseInt(input[0]);
    }

    @Override
    public String[] lambda(){
        String[] out = {""+b1};
        return out;
    }

}