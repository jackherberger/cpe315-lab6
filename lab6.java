import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
2KB, direct mapped, 1-word blocks
2KB, direct mapped, 2-word blocks
2KB, direct mapped, 4-word blocks
2KB, 2-way set associative, 1-word blocks
2KB, 4-way set associative, 1-word blocks
2KB, 4-way set associative, 4-word blocks
4KB, direct mapped, 1-word blocks
*/

public class lab6 {

    // global variales for cross-function computing
    private static int arr1[] = new int[512];
    private static int arr2[] = new int[1];
    private static int arr3[] = new int[1];
    private static int arr4[] = new int[1];
    private static int arr5[] = new int[1];
    private static int arr6[] = new int[1];
    private static int arr7[] = new int[1];

    private static int hit1 = 0;
    private static int hit2 = 0;
    private static int hit3 = 0;
    private static int hit4 = 0;
    private static int hit5 = 0;
    private static int hit6 = 0;
    private static int hit7 = 0;

    private static int total = 0;

    public static void main(String[] args) {

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
                total += 1;
                // call functions
                one(num);

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
    }

    // 2KB, direct mapped, 1-word blocks
    public static void one(int num) {
        num = num >> 2;
        int index = num & 0x1FF;
        int tag = num >> 9;
        if (arr1[index] == tag) {
            // hit
            hit1 += 1;
        } else {
            //miss
            arr1[index] = tag;
        }
    }

}
