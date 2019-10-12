package com.nesposi3;

public class Pipe<T>{
    private Port<T> previousOut;
    private Port<T> nextIn;
    public Pipe(Port<T> previousOut, Port<T> nextIn ){
        this.nextIn = nextIn;
        this.previousOut = previousOut;
    }
    public void shiftVal(T newOut){
        nextIn.setVal(previousOut.getVal());
        previousOut.setVal(newOut);
    }


}
