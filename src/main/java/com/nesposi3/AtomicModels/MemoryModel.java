package com.nesposi3.AtomicModels;

import com.nesposi3.Model;

public class MemoryModel extends Model<String,String> {
    private int b1, b2;
    public MemoryModel(boolean debug){
        this.debug = debug;
        this.b1 = this.b2 = 0;
    }
    @Override
    public void delta(String[] input) {
        this.b1 = b2;
        this.b2 = Integer.parseInt(input[0]);
        if(this.debug){
            System.out.println("Memory Model Delta. State: " + "{ " + b1 +" "+b2+" "+"}");
        }
    }

    @Override
    public String lambda(){
        String out = ""+b1;
        if(this.debug){
            System.out.println("Memory Model lambda. " + out);
        }
        return out;
    }

}
