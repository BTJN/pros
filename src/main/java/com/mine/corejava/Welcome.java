package com.mine.corejava;

public class Welcome {
    public static void main(String[] args) {
        String greetings = "hello world";
        System.out.println(greetings);
        for (int i = 0; i < greetings.length(); i++) 
            System.out.print("=");
        System.out.println();
    }
}
