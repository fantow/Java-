package com.fantow.Java文件IO;

import java.io.*;

public class Test1 {
    public static void main(String[] args) throws IOException {
        String filePath = "D:\\源码学习\\Java基础练习\\src\\main\\java\\com\\fantow\\Java文件IO\\1.txt";

        byte[] data = "456\n".getBytes();

//        FileOutputStream fos = new FileOutputStream(filePath,false);

        FileWriter writer = new FileWriter(filePath);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
//        bos.write();

        for(int i = 0;i < 10;i++){
            try {
                writer.write("aaa");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        writer.flush();

        System.out.println("--- end ---");
    }
}
