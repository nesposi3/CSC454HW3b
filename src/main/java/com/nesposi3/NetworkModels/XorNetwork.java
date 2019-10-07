package com.nesposi3.NetworkModels;

import com.nesposi3.AtomicModels.MemoryModel;
import com.nesposi3.AtomicModels.XorModel;
import com.nesposi3.Network;

public class XorNetwork extends Network {
    public XorNetwork(){
        this.children = new Network[3];
        this.children[0] = new XorModel();
        this.children[1] = new XorModel();
        this.children[2] = new MemoryModel();
    }
    @Override
    public String[] lambda() {
        return new String[1];
    }

    @Override
    public void delta(String[] input) {

    }
}
