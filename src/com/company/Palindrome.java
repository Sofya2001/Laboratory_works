package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        ArrayList<String> ar= new ArrayList<String>();
        for(int i=0;i<3;i++){
            ar.add(i,s.nextLine());
        }
        for(int i=0;i<3;i++){
            System.out.println(Palindrome(ar.get(i)));
        }

    }

    public static String reverseString(String s) {
        String r = "";
        int l = s.length();
        for (int i = l-1; i >= 0; i--) {
            r = r + s.charAt(i);
        }
        return r;
    }
    public static boolean Palindrome(String s){
        boolean p=false;
        String sr=reverseString(s);
        if(s.equals(sr)){  p=true;}
        else { p= false;}
        return p;
    }
}
