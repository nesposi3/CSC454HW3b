package com.nesposi3;

public abstract class Network {
    public abstract String[] lambda();
    public abstract void delta(String[] input);
    public Network[] children;
}
