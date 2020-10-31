package com.fantow.基础;

import java.io.*;

public class CloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
//        Object1 parent = new Object1();
//        Object1 son1 = (Object1) parent.clone();
//
//        System.out.println(parent == son1);
//        System.out.println(parent.str == son1.str);
//
//
//        Object2 parent2 = new Object2();
//        Object2 son2 = (Object2) parent2.clone();
//
//        System.out.println(parent2 == son2);
//        System.out.println(parent2.str == son2.str);

        Object2 object2 = new Object2();
        Object2 clone = (Object2) object2.clone();

        System.out.println(object2 == clone);
        System.out.println(object2.str == clone.str);

    }
}

class Object1 implements Cloneable{

    String str = "123";

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Object2 implements Cloneable,Serializable{

    String str = "123";

    @Override
    protected Object clone() throws CloneNotSupportedException {

        try {

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bos);
            os.writeObject(this);

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);

            Object object = ois.readObject();

            return object;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return null;
    }
}
