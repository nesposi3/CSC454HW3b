package com.nesposi3.NetworkModels;

import com.nesposi3.AtomicModels.MemoryModel;
import com.nesposi3.AtomicModels.XorModel;
import com.nesposi3.Model;
import com.nesposi3.Pipe;

public class XorNetwork extends Model<String,String> {
    public XorNetwork(boolean debug){
        this.debug = debug;
        this.children = new Model[3];
        this.children[0] = new XorModel(debug);
        this.children[1] = new XorModel(debug);
        this.children[2] = new MemoryModel(debug);
        this.pipes = new Pipe[3];
        this.pipes[0] = new Pipe<String>();
        this.pipes[1] = new Pipe<String>();
        this.pipes[2] = new Pipe<String>();

    }
    @Override
    public String lambda() {
        for (int i = 0; i <this.children.length ; i++) {
            this.pipes[i].setVal(this.children[i].lambda());
            //System.out.println(this.pipes[i].getInput() + children[i].getClass().toString());
        }
        return (String) this.pipes[this.pipes.length-1].getInput();
    }

    @Override
    public void delta(String[] input) {
        if(debug){
            System.out.println("Network input: " + input[0] + " " + input[1]);
        }
        String xor1Out = ((String) this.pipes[0].getInput());
        String memOut = ((String) this.pipes[2].getInput());
        String[] xor2In = {xor1Out,memOut};
        String[] memIn = {(String)this.pipes[1].getInput()};
        this.children[0].delta(input);
        this.children[1].delta(xor2In);
        this.children[2].delta(memIn);
    }
}
