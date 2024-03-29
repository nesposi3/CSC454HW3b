package com.nesposi3;


import com.nesposi3.AtomicModels.MemoryModel;
import com.nesposi3.AtomicModels.XorModel;
import com.nesposi3.NetworkModels.Network;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final int NUM_ATOMIC_TICKS = 3;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean debugFlag = false;
        boolean batchFlag = false;
        boolean injectFlag = false;
        for (int i = 0; i <args.length ; i++) {
            if(args[i].contains("--debug") || args[i].contains("-d")){
                debugFlag = true;
            }
            if(args[i].contains("--batch") || args[i].contains("-b")){
                batchFlag = true;
            }
            if(args[i].contains("--inject") || args[i].contains("-i")){
                injectFlag = true;
            }
        }
        int numTicks = injectFlag?1:NUM_ATOMIC_TICKS;
        String command;
        //Defining network structure
        Network<String,String> network = new Network<>(numTicks,debugFlag);
        MemoryModel memoryModel = new MemoryModel(debugFlag,"Memory");
        XorModel xor1 = new XorModel(debugFlag,"XOR1");
        XorModel xor2 = new XorModel(debugFlag,"XOR2");
        Port<String> xor2P1 = new Port<>("0");
        Port<String> xor2P2 = new Port<>("0");
        Port<String> memP1 = new Port<>("0");
        xor1.addPipe(xor2P1);
        xor2.addPort(xor2P1);
        xor2.addPort(xor2P2);
        xor2.addPipe(memP1);
        memoryModel.addPort(memP1);
        memoryModel.addPipe(xor2P2);
        network.setFirstChild(xor1);
        network.setFinalChild(xor2);
        network.addModel(memoryModel);

        if(batchFlag){
            command = sc.nextLine();
            if(command.length()%2!=0){
                System.out.println("Incorrect trajectory format, uneven number of bits");
            }else{
                for (int i = 0; i <command.length() ; i+=2) {
                    String b1 = command.substring(i,i+1);
                    String b2 = command.substring(i+1,i+2);
                    ArrayList<String> input = new ArrayList<>();
                    input.add(b1);
                    input.add(b2);
                    String out = network.tick(input);
                    System.out.println(out);

                }
            }
        }else {
            while (true) {
                command = sc.nextLine();
                if (command.equals("exit")) {
                    break;

                } else {
                    ArrayList<String> input = new ArrayList<>();
                    String[] splits = command.split(" ");
                    input.add(splits[0]);
                    input.add(splits[1]);

                    String out = network.tick(input);
                    System.out.println(out);
                }
            }
        }
    }
}
