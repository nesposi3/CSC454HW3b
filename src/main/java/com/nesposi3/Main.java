package com.nesposi3;


public class Main {

    public static void main(String[] args) {
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
        System.out.println(debugFlag + " " + batchFlag);
    }
}
