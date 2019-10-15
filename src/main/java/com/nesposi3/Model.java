package com.nesposi3;

import java.util.ArrayList;

public abstract class Model<Input, Output> {
    public abstract Output lambda();
    public abstract void delta(ArrayList<Input> input);
    public Port<Output> getOutputPort(){
        return this.outputPort;
    }
    public ArrayList<Port<Input>> getInputPorts(){
        return this.inputPorts;
    }
    public ArrayList<Pipe<Output>> getPipes(){
        return this.pipeList;
    }
    public void addPort(Port<Input> p ){
        this.inputPorts.add(p);
    }
    public void addPipe(Port<Output> nextIn ){
        Pipe<Output> p = new Pipe<>(this.outputPort,nextIn);
        this.pipeList.add(p);
    }
    protected boolean debug;
    protected ArrayList<Port<Input>> inputPorts;
    protected Port<Output> outputPort;
    protected ArrayList<Pipe<Output>> pipeList;
    protected ArrayList<Model<Input,Output>> childList;
    public String name;
}
