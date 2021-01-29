package com.leo.aop.cglib.extendsRela;

public class B extends A {
    @Override
    public void run() {
        System.out.println("额外功能！");
        super.run();
    }

    public static void main(String[] args) {
        A a = new B();
        a.run();
    }
}
