package com.nesposi3;


import com.nesposi3.AtomicModels.MemoryModel;
import com.nesposi3.AtomicModels.XorModel;
import com.nesposi3.NetworkModels.Network;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean debugFlag = false;
        boolean batchFlag = false;
        for (int i = 0; i <args.length ; i++) {
            if(args[i].contains("--debug") || args[i].contains("-d")){
                debugFlag = true;
            }
            if(args[i].contains("--batch") || args[i].contains("-b")){
                batchFlag = true;
            }
        }
        String command;
        Network<String,String> network = new Network<>("0","0");
        MemoryModel memoryModel = new MemoryModel(debugFlag);
        XorModel xor1 = new XorModel(debugFlag);
        XorModel xor2 = new XorModel(debugFlag);
        xor2 = (XorModel) xor1.addPipe(xor1.getOutputPort(), xor2,"0");
        xor2 = (XorModel) memoryModel.addPipe(memoryModel.getOutputPort(),xor2,"0");
        memoryModel = (MemoryModel) xor2.addPipe(xor2.getOutputPort(),memoryModel,"0");
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
                    String out = network.lambda();
                    System.out.println(out+"\n--------------------");
                    network.delta(input);
                    System.out.println("--------------------");

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

                    String out = network.lambda();
                    System.out.println(out);
                    network.delta(input);
                }
            }
        }
    }
}
