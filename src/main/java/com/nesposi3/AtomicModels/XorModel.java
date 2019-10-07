package com.nesposi3.AtomicModels;

import com.nesposi3.Network;

public class XorModel extends Network {
    private int bit;
    public XorModel(){
        this.bit = 0;
    }
    @Override
    public String[] lambda() {
        String[] out = {""+bit};
        return out;
    }

    @Override
    public void delta(String[] input) {
        int b1 = Integer.parseInt(input[0]);
        int b2 = Integer.parseInt(input[1]);
        this.bit = b1^b2;
    }
}