package com.charlie.thread.demo;

public class CpuNum {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        int cpuNums = runtime.availableProcessors();
        System.out.println("my cpu core number = " + cpuNums);
    }
}
