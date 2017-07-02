package com.company;

import java.util.Random;

import static com.company.Main.*;

public class Init {

    public void generate() {
       Random generator = new Random();
        int[] invariants = new int[16];

        do {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    numbers[i][j] = 0;
                    invariants[i * 4 + j] = 0;
                }
            }

            for (int i = 1; i <= 16; i++) {
                int k, l;
                do {
                    k = generator.nextInt(4);
                    l = generator.nextInt(4);
                }
                while (numbers[k][l] != 0);
                numbers[k][l] = i;
                invariants[k * 4 + l] = i;
            }
        }
        while (!check(invariants));

       /* Test for 1 move
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                numbers[i][j] = 4*i+j+1;
            }
        }
        numbers[3][2] = 16;
        numbers[3][3] = 15;
        */

        readPic();
        start = System.nanoTime();
    }

    private static boolean check(int[] invariants) {
        int sum = 0;
        for (int i = 0; i < 16; i++) {
            if (invariants[i] == 0) {
                sum += i / 4;
                continue;
            }
            for (int j = i + 1; j < 16; j++) {
                if (invariants[j] < invariants[i]) {
                    sum++;
                }
            }
        }
        System.out.println(sum % 2 == 0);
        return sum % 2 == 0;
    }

    private void readPic()
    {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                int num = numbers[i][j];
                if(num == 16) {
                    coord = i * 4 + j + 1;
                }
                arrayListGrid.add(new Grid("JG" + num + ".jpg", num));
            }
        }
    }
}
