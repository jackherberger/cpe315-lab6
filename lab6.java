import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Scanner;

/*
2KB, direct mapped, 1-word blocks
2KB, direct mapped, 2-word blocks
2KB, direct mapped, 4-word blocks
2KB, 2-way set associative, 1-word blocks
2KB, 4-way set associative, 1-word blocksv
2KB, 4-way set associative, 4-word blocks
4KB, direct mapped, 1-word blocks
*/

public class lab6 {

    // global variales for cross-function computing
    private static int arr1[] = new int[512];
    private static int arr2[] = new int[256];
    private static int arr3[] = new int[128];
    
    private static int arr4[][] = new int[256][4];
    private static int arr5[][] = new int[128][8];
    private static int arr6[][] = new int[32][8];
    private static int arr7[] = new int[1024];

    private static int hit1 = 0;
    private static int hit2 = 0;
    private static int hit3 = 0;
    private static int hit4 = 0;
    private static int hit5 = 0;
    private static int hit6 = 0;
    private static int hit7 = 0;

    private static int total = 0;

    public static void main(String[] args) {
        for (int i = 0; i < arr3.length; i++) {
            arr3[i] = -1000;
        }

        for (int i = 0; i < arr6.length; i++) {
            for (int j = 0; j < arr6[i].length; j++) {
                arr6[i][j] = -1000;
            }
        }

        // get file
        if (args.length == 0) {
            System.out.println("Usage: java Lab6 <filename>");
            System.exit(1);
        }
        String filename = args[0];

        // init arrays
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = -1;
        }
        
        // read lines and call all functions
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // get the num we are working with
                String[] words = line.trim().split("\\s+");
                int num = Integer.parseInt(words[1], 16);
                // call functions
                one(num);
                two(num);
                three(num);
                four(num);
                five(num);
                six(num);
                seven(num);
                total += 1;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        print();

    }

    // print req information  
    public static void print() {
        System.out.println();
        System.out.println("Cache #1");
        System.out.println("Cache size: 2048B       Associativity: 1       Block size: 1");
        System.out.format("Hits: %d    Hit Rate: %.2f%%\n", hit1, ((float)hit1/total) * 100.0f);
        System.out.println("---------------------------");
        System.out.println("Cache #2");
        System.out.println("Cache size: 2048B       Associativity: 1       Block size: 2");
        System.out.format("Hits: %d    Hit Rate: %.2f%%\n", hit2, ((float)hit2/total) * 100.0f);
        System.out.println("---------------------------");
        System.out.println("Cache #3");
        System.out.println("Cache size: 2048B       Associativity: 1       Block size: 4");
        System.out.format("Hits: %d    Hit Rate: %.2f%%\n", hit3, ((float)hit3/total) * 100.0f);
        System.out.println("---------------------------");
        System.out.println("Cache #4");
        System.out.println("Cache size: 2048B       Associativity: 2       Block size: 1");
        System.out.format("Hits: %d    Hit Rate: %.2f%%\n", hit4, ((float)hit4/total) * 100.0f);
        System.out.println("---------------------------");
        System.out.println("Cache #5");
        System.out.println("Cache size: 2048B       Associativity: 4       Block size: 1");
        System.out.format("Hits: %d    Hit Rate: %.2f%%\n", hit5, ((float)hit5/total) * 100.0f);
        System.out.println("---------------------------");
        System.out.println("Cache #6");
        System.out.println("Cache size: 2048B       Associativity: 4       Block size: 4");
        System.out.format("Hits: %d    Hit Rate: %.2f%%\n", hit6, ((float)hit6/total) * 100.0f);
        System.out.println("---------------------------");
        System.out.println("Cache #7");
        System.out.println("Cache size: 4096B       Associativity: 1       Block size: 1");
        System.out.format("Hits: %d    Hit Rate: %.2f%%\n", hit7, ((float)hit7/total) * 100.0f);
        System.out.println("---------------------------");
        // System.out.println("Total " + total);
    }

    // 2KB, direct mapped, 1-word blocks
    public static void one(int num) {
        num = num >> 2;
        int index = num & 0b111111111;
        int tag = num >> 9;




        if (arr1[index] == tag) {
            // hit
            hit1 += 1;
        } else {
            //miss
            arr1[index] = tag;
        }
    }

    public static void two(int num) {
        num = num >> 3;
        int index = num & 0b11111111;
        int tag = num >> 8;
        if (arr2[index] == tag) {
            // hit
            hit2 += 1;
        } else {
            //miss
            arr2[index] = tag;
        }
    }

    public static void three(int num) {
        int num1;
        num1 = num >>> 4;
        //int index = num & 0b1111111;
        int index = num1 & 127;
        int tag = num1 >>> 7;
        if (tag == -1000) {
            System.out.println("Here");
        }
        if (arr3[index] == tag) {
            if (total == 4999999){
                hit3 += 1;
            }
            // hit
            hit3 += 1;
        } else {
            //miss
            arr3[index] = tag;
        }
    }

    public static void four(int num) {

        num = num >> 2;
        int index = num & 0b11111111;
        int tag = num >> 8;

        if (arr4[index][1] == tag) {
            // hit spot 1
            arr4[index][0] = total;
            hit4 += 1;
        } else if (arr4[index][3] == tag){
            // hit spot 2
            arr4[index][2] = total;
            hit4 += 1;
        }
        else {
            if (arr4[index][0] < arr4[index][2]){
                arr4[index][1] = tag;
                arr4[index][0] = total;
                
            }
            else {
                arr4[index][3] = tag;
                arr4[index][2] = total;
            }
        }
    }

    public static void five(int num) {

        num = num >> 2;
        int index = num & 0b1111111;
        int tag = num >> 7;

        if (arr5[index][1] == tag) {
            // hit spot 1
            arr5[index][0] = total;
            hit5 += 1;
        } else if (arr5[index][3] == tag){
            // hit spot 2
            arr5[index][2] = total;
            hit5 += 1;
        } else if (arr5[index][5] == tag){
            // hit spot 2
            arr5[index][4] = total;
            hit5 += 1;
        } else if (arr5[index][7] == tag){
            // hit spot 2
            arr5[index][6] = total;
            hit5 += 1;
        }
        else {
            int smallest = Integer.MAX_VALUE;
            int spot = -1;

            for (int i = 0; i < arr5[index].length; i+=2){
                if (arr5[index][i] < smallest) {
                    smallest = arr5[index][i];
                    spot = i;
                }
            }
            arr5[index][spot] = total;
            arr5[index][spot + 1] = tag;
        
        }
    }

    public static void six(int num) {

        num = num >> 4;
        int index = num & 0b11111;
        int tag = num >> 5;

        if (arr6[index][1] == tag) {
            if (total == 4999999){
                hit6 += 1;
            }
            // hit spot 1
            arr6[index][0] = total;
            hit6 += 1;
        } else if (arr6[index][3] == tag){
            if (total == 4999999){
                hit6 += 1;
            }
            // hit spot 2
            arr6[index][2] = total;
            hit6 += 1;
        } else if (arr6[index][5] == tag){
            if (total == 4999999){
                hit6 += 1;
            }
            // hit spot 2
            arr6[index][4] = total;
            hit6 += 1;
        } else if (arr6[index][7] == tag){
            if (total == 4999999){
                hit6 += 1;
            }
            // hit spot 2
            arr6[index][6] = total;
            hit6 += 1;
        }
        
        else {
            int smallest = Integer.MAX_VALUE;
            int spot = -1;

            for (int i = 0; i < arr6[index].length; i+=2){
                if (arr6[index][i] < smallest) {
                    smallest = arr6[index][i];
                    spot = i;
                }
            }
            arr6[index][spot] = total;
            arr6[index][spot + 1] = tag;
        
        }
    }

    public static void seven(int num) {
        num = num >> 2;
        int index = num & 0b1111111111;
        int tag = num >> 10;
        if (arr7[index] == tag ) {
            // hit
            hit7 += 1;
        } else {
            //miss
            arr7[index] = tag;
        }
    }

}
