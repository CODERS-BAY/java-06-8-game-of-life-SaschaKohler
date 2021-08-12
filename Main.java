package com.sksoft;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    private  static  int MAXCOLUMN = 45;
    private  static  int MAXROW = 175;

    public static void main(String[] args) {
        // write your code here
        Random random = new Random();
       int[][] array = new int[MAXCOLUMN][MAXROW];

        for (int i = 0; i < MAXCOLUMN; i++) {
            for (int j = 0; j < MAXROW; j++) {
                if ((random.nextInt(3) + 1) == 1) {
                    array[i][j] = 1;
                } else {
                    array[i][j] = 0;
                }
            }
        }

        printArray(array);

        int counter = 0;

        while (counter < 1000) {
            counter++;
            System.out.print("\033[H\033[2J");
            array = checkArray(array);
       //     System.out.println();
            printArray(array);
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.flush();


        }
    }


    public static void printArray(int[][] array) {
        for (int i = 0; i < MAXCOLUMN; i++) {
            for (int j = 0; j < MAXROW; j++) {
                if (array[i][j] == 1)
                    System.out.print("#");
                else
                    System.out.print(".");
            }
            System.out.println();
        }

    }

    private static int calculateSumOfNeighbors(int[][] array, int i, int j) {
        int sum = 0;
        int x, y;
        int state = 0;

        for (x = -1; x <= 1; x++) {
            for (y = -1; y <= 1; y++) {
                state = checkStatus(array, i + x, j + y);
                sum += state;
            }
        }
        sum -= array[i][j];
        return sum;
    }

    private static int checkStatus(int[][] array, int x, int y) {
        try {
            return array[x][y];
        } catch (Exception e) {
            return 0;
        }
    }

    private static int[][] checkArray(int[][] array) {
        int[][] buffer = new int[MAXCOLUMN][MAXROW];
        for (int i = 0; i < MAXCOLUMN; i++) {
            for (int j = 0; j < MAXROW; j++) {
                int sum = calculateSumOfNeighbors(array, i, j);
                buffer[i][j] = updateStatus(array, i, j, sum);
            }
        }
        return buffer;
    }

    private static int updateStatus(int[][] array, int i, int j, int sum) {
        if (array[i][j] == 1 && (sum < 2 || sum > 3))
            return 0;
        else if ((array[i][j] == 0 && sum == 3) || (array[i][j] == 1 && (sum == 3 || sum == 2)))
            return 1;
        return 0;

    }
}



