package com.nesposi3;

public abstract class Model<Input, Output> {
    public abstract Output lambda();
    public abstract void delta(Input[] input);
    public boolean debug;
    public Model[] children;
    public Pipe[] pipes;
}
