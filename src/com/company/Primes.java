package com.company;

import java.util.Scanner;

public class Primes {

    public static void main(String[] args) {
        int i;
        for(i=2; i<=100;i++){
            boolean p = inPrime(i);
            if(p==true) System.out.println(i);
        }
    }

    public static boolean inPrime(int n) {
        boolean p=false;
        int i;
        if (n >= 10) {
            for (i = 2; i <= 9; i++) {
                if (n % i == 0)
                    return false;
            }
            p= true;
        }
        if (n < 10) {
            int k=0;
            for (i = 2; i <= 9; i++){
                if(n%i==0){
                    k++;
                }
            }
            if (k==1) p= true;
            else p= false;
        }
        return p;
    }
}
