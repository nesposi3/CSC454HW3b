package com.nesposi3.AtomicModels;

import com.nesposi3.Model;
import com.nesposi3.Port;

import java.util.ArrayList;
import java.util.List;

public class XorModel extends Model<String,String> {
    private int bit;
    public XorModel(boolean debug,String name){
        this.debug = debug;
        this.bit = 0;
        this.inputPorts = new ArrayList<>();
        this.outputPort = new Port<>("0");
        this.pipeList = new ArrayList<>();
        this.name= name;
    }
    @Override
    public String lambda() {
        String out = ""+bit;
        if(this.debug){
            System.out.println(name + " Lambda: " + out );
        }
        return out;
    }

    @Override
    public void delta(ArrayList<String> input) {
        int b1 = Integer.parseInt(input.get(0));
        int b2 = Integer.parseInt(input.get(1));
        this.bit = b1^b2;
        if(this.debug){
            System.out.println(name + " Delta: " + b1 + "XOR" + b2 + "=" + this.bit );
        }
    }
}
