package com.nesposi3;

import java.util.ArrayList;

public abstract class Model<Input, Output> {
    public abstract Output lambda();
    public abstract void delta(ArrayList<Input> input);
    public void addPipe(Port output,Model<Output,Output> input){
        Port<Output> newPort = input.addPort();
        Pipe<Output> p = new Pipe<Output>(output,newPort);
        this.pipeList.add(p);
    }
    public Port<Output> getOutputPort(){
        return this.outputPort;
    }
    public ArrayList<Port<Input>> getInputPorts(){
        return this.inputPorts;
    }
    public ArrayList<Pipe<Output>> getPipes(){
        return this.pipeList;
    }
    private Port<Input> addPort(){
        Port<Input> p = new Port<>();
        this.inputPorts.add(p);
        return p;
    }
    protected boolean debug;
    protected ArrayList<Port<Input>> inputPorts;
    protected Port<Output> outputPort;
    protected Model[] children;
    protected Pipe[] pipes;
    protected ArrayList<Pipe<Output>> pipeList;
    protected ArrayList<Model<Input,Output>> childList;
}
