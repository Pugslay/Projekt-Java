package com.example.karty;

public class Tester {
    private int count;
    private String label;
    public Tester() {
        label="stworzone";
        count=0;
    }
    public void start(String funkcja) {
        label=funkcja;
        count=0;
        System.out.println("Wejscie do funkcji"+funkcja);
    }
    public void IF() {
        count++;
        System.out.println("if nr:"+count);
    }
    public void ELSE() {
        count++;
        System.out.println("else ifa nr:"+count);
    }
    public void end() {
        count=0;
    }
}