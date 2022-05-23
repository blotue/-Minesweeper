package org.example;
import java.util.Scanner;
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args ) {

        Board.setRandomSeed();
        System.out.print("Welcome to MineSweeper!\n");
        Board.getInstance().renderBoard();
        System.out.print("Enter row and column\n");
        boolean doNextMove = true;
        do { doNextMove = Board.getInstance().revealTile(scanner.nextInt(),scanner.nextInt());}
        while (doNextMove == true);

    }
}