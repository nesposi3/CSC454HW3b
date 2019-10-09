package com.nesposi3.AtomicModels;

import com.nesposi3.Model;

public class XorModel extends Model<String,String> {
    private int bit;
    public XorModel(boolean debug){
        this.debug = debug;
        this.bit = 0;
    }
    @Override
    public String lambda() {
        String out = ""+bit;
        if(this.debug){
            System.out.println("XorModel. Lambda: " + out );
        }
        return out;
    }

    @Override
    public void delta(String[] input) {
        int b1 = Integer.parseInt(input[0]);
        int b2 = Integer.parseInt(input[1]);
        this.bit = b1^b2;
        if(this.debug){
            System.out.println("XorModel. Delta: " + b1 + "XOR" + b2 + "=" + this.bit );
        }
    }
}
