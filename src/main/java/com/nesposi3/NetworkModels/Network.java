package com.nesposi3.NetworkModels;

import com.nesposi3.Model;
import com.nesposi3.Pipe;
import com.nesposi3.Port;

import java.util.ArrayList;

public class Network<Input,Output> extends Model<Input,Output> {
    private Model<Input,Output> firstChild;
    private Model<Input, Output> finalChild;

    public Network(){
        this.childList = new ArrayList<>();
    }

    public void addModel(Model<Input,Output> m){
        this.childList.add(m);
    }
    @Override
    public Output lambda() {
        Output firstOutput = this.firstChild.lambda();
        this.firstChild.getOutputPort().setVal(firstOutput);
        for (Pipe<Output> p :this.firstChild.getPipes()
             ) {
            p.shiftVal(firstOutput);
        }

        for (Model<Input,Output> m: this.childList
             ) {
            Output output = m.lambda();
            m.getOutputPort().setVal(output);
            for(Pipe<Output> p: m.getPipes()){
                p.shiftVal(output);
            }
        }
        Output finalOutput = this.finalChild.lambda();
        for (Pipe<Output> p :this.finalChild.getPipes()
        ) {
            p.shiftVal(firstOutput);
        }
        return finalOutput;
    }

    @Override
    public void delta(ArrayList<Input> input) {
        this.firstChild.delta(input);
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

        ArrayList<Port<Input>> inputPorts = finalChild.getInputPorts();
        ArrayList<Input> deltaInputs = new ArrayList<>();
        for (Port<Input> p:inputPorts
        ) {
            deltaInputs.add(p.getVal());
        }
        finalChild.delta(deltaInputs);

    }

    /**
     * Tell the network which child to give its input to
     * @param m
     */
    public void setFirstChild(Model<Input,Output> m){
        this.firstChild = m;
    }

    /**
     * Tell the network which child o get its output from
     * @param m
     */
    public void setFinalChild(Model<Input,Output> m){
        this.finalChild = m;
    }

}
