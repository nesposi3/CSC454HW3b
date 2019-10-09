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
        String command;
        XorNetwork network = new XorNetwork();
        if(batchFlag){
            command = sc.nextLine();
            if(command.length()%2!=0){
                System.out.println("Incorrect trajectory format, uneven number of bits");
            }else{
                for (int i = 0; i <command.length() ; i+=2) {
                    String b1 = command.substring(i,i+1);
                    String b2 = command.substring(i+1,i+2);
                    String[] inp = {b1,b2};

                    String out = network.lambda();
                    System.out.println(out);
                    network.delta(inp);
                }
            }
        }else {

            while (true) {
                command = sc.nextLine();
                if (command.equals("exit")) {
                    break;
                } else {
                    String[] splits = command.split(" ");
                    String out = network.lambda();
                    System.out.println(out);
                    network.delta(splits);
                }
            }
        }
    }
}
