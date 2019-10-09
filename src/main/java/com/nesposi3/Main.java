package com.nesposi3;


import com.nesposi3.NetworkModels.XorNetwork;

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
        //System.out.println(debugFlag + " " + batchFlag);
        String command;
        XorNetwork network = new XorNetwork();
        while (true){
            command = sc.nextLine();
            if(command.equals("exit")){
                break;
            }else{
                int b1,b2;
                String[] splits = command.split(" ");
                String out = network.lambda();
                System.out.println(out);
                network.delta(splits);
            }
        }
    }
}
