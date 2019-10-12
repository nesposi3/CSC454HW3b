package com.nesposi3.NetworkModels;

import com.nesposi3.Model;
import com.nesposi3.Pipe;
import com.nesposi3.Port;

import java.util.ArrayList;

public class Network<Input,Output> extends Model<Input,Output> {
    public Network(){
        this.pipeList = new ArrayList<>();
        this.inputPorts = new ArrayList<>();
        this.inputPorts.add(new Port<>());
        this.inputPorts.add(new Port<>());
        this.outputPort = new Port<Output>();
        this.childList = new ArrayList<>();
    }

    public void addModel(Model<Input,Output> m){
        this.childList.add(m);
    }
    @Override
    public Output lambda() {
        for (Model<Input,Output> m: this.childList
             ) {
            Output output = m.lambda();
            m.getOutputPort().setVal(output);
            for(Pipe<Output> p: this.pipeList){
                p.shiftVal(output);
            }
        }
        return this.outputPort.getVal();
    }

    @Override
    public void delta(ArrayList<Input> input) {
        for (int i = 0; i <this.inputPorts.size() ; i++) {
            this.inputPorts.get(i).setVal(input.get(i));
        }

        for (Model<Input,Output> m:this.childList
             ) {
            ArrayList<Port<Input>> inputPorts = m.getInputPorts();
            ArrayList<Input> deltaInputs = new ArrayList<>();
            for (Port<Input> p:inputPorts
                 ) {
                deltaInputs.add(p.getVal());
            }
            m.delta(deltaInputs);
        }
    }
}
