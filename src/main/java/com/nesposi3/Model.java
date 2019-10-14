package com.nesposi3;

import java.util.ArrayList;

public abstract class Model<Input, Output> {
    public abstract Output lambda();
    public abstract void delta(ArrayList<Input> input);
    //TODO need to make addPipe not need to return a model
    public Model<Output,Output> addPipe(Port<Output> output,Model<Output,Output> input,Output defaultValue){
        Port<Output> newPort = input.addPort(defaultValue);
        Pipe<Output> p = new Pipe<Output>(output,newPort);
        this.pipeList.add(p);
        return input;
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
    private Port<Input> addPort(Input defaultValue){
        Port<Input> p = new Port<>(defaultValue);
        this.inputPorts.add(p);
        return p;
    }
    protected boolean debug;
    protected ArrayList<Port<Input>> inputPorts;
    protected Port<Output> outputPort;
    protected ArrayList<Pipe<Output>> pipeList;
    protected ArrayList<Model<Input,Output>> childList;
}
