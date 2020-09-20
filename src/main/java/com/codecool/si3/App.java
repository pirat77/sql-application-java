package com.codecool.si3;

import com.codecool.si3.controler.Mainloop;

public class App {

    public static void main(String[] args){
        System.out.println("Welcome home!");
        Mainloop mainloop = new Mainloop();
        mainloop.mainLoop();
    }
}
