package com.nesposi3;

public class Pipe<Input> {
    private Input input;
    public void setVal(Input input){
        this.input = input;
    }
    public Input getInput(){
        return input;
    }
}
