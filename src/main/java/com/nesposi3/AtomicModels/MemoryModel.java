package com.nesposi3.AtomicModels;

import com.nesposi3.Model;
import com.nesposi3.Port;

import java.util.ArrayList;

public class MemoryModel extends Model<String,String> {
    private int b1, b2;
    public MemoryModel(boolean debug){
        this.debug = debug;
        this.b1 = this.b2 = 0;
        this.inputPorts = new ArrayList<>();
        this.outputPort = new Port<>("0");
        this.pipeList = new ArrayList<>();
    }
    @Override
    public void delta(ArrayList<String> input) {
        String inpString = input.get(0);
        this.b1 = b2;
        this.b2 = Integer.parseInt(inpString);
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
