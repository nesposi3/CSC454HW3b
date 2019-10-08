package com.nesposi3.NetworkModels;

import com.nesposi3.AtomicModels.MemoryModel;
import com.nesposi3.Model;

public class XorNetwork extends Model {
    int bit;
    public XorNetwork(){
        this.children = new Model[3];
        this.children[0] = new com.nesposi3.AtomicModels.XorModel();
        this.children[1] = new com.nesposi3.AtomicModels.XorModel();
        this.children[2] = new MemoryModel();
        this.bit = 0;
    }
    @Override
    public String[] lambda() {
        String[] out = {""+bit};
        return out;
    }

    @Override
    public void delta(String[] input) {
        com.nesposi3.AtomicModels.XorModel xor1 = (com.nesposi3.AtomicModels.XorModel) this.children[0];
        com.nesposi3.AtomicModels.XorModel xor2 = (com.nesposi3.AtomicModels.XorModel) this.children[1];
        MemoryModel memoryModel = (MemoryModel) this.children[2];
        String[] xor1Out = xor1.lambda();
        xor1.delta(input);
        String[] xor2Input = {xor1Out[0], memoryModel.lambda()[0]};
        String[] xor2Output = xor2.lambda();
        memoryModel.delta(xor2Output);
        this.bit = Integer.parseInt(xor1Out[0]);
    }
}
