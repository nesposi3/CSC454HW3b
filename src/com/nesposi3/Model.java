package com.nesposi3;

public abstract class Model {
    public abstract String[] lambda();
    public abstract void delta(String[] input);
    public Model[] children;
}
