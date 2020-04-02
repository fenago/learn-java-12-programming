package com.lv.learnjava.ch03_fundamentals;

import com.lv.learnjava.ch02_oop.StaticMembers.SomeClass;
import com.lv.learnjava.ch02_oop.hiding.C;
import com.lv.learnjava.ch02_oop.hiding.D;

import static com.lv.learnjava.ch02_oop.StaticMembers.SomeClass.someMethod;
import static com.lv.learnjava.ch02_oop.StaticMembers.SomeClass.SOME_PROPERTY;

public class Packages {
    public static void main(String... args){
        C c = new C();
        D d = new D();

        SomeClass obj = new SomeClass();
        someMethod(42);
        System.out.println(SOME_PROPERTY);    //prints: abc
    }
}


