package org.example;

import java.util.Scanner;

public class Container {

    private static Scanner sc;

    public static void init(){
        sc = new Scanner(System.in);
        //왜 스태틱 변수가 되야하지?

    }

    public static void close(){
        sc.close();
    }

    public static Scanner getScanner(){
        //왜 void init을 안하고  > 스캐너를 나오게 하는거라서? getScanner로 하나 더만들었을까?
        return sc;

    }
}
