package com.example.txuso.moppa;

import java.math.BigInteger;

/**
 * Created by Txuso on 13/01/16.
 */
public class Factorial {

    BigInteger bi = new BigInteger("1");


    public BigInteger process(int num) {
        if (num < 0) {
            System.out.println("Invalid number!");
            System.exit(1);
        }
        for (int i = 1; i < num; i++) {
            bi = bi.multiply(BigInteger.valueOf(i));
        }
        return bi;
    }//process()


}
