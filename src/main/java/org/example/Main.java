package org.example;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){

        GridSingleton.setRandomSeed();
        System.out.println(Messages.WELCOME);
        GridSingleton.getInstance().renderGrid();
        System.out.println(Messages.MAKEMOVE);

        boolean doNextMove = true;
        do
        {
            doNextMove = GridSingleton.getInstance().revealCell(scanner.nextInt(), (char)scanner.next().charAt(0));
        }
        while(doNextMove == true);

    }

}