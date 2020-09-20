package com.codecool.si3.controler;

import com.codecool.si3.view.View;

public class Mainloop {


    public void mainLoop(){
        int menuSize = MenuController.getInstance().getActionMap().size();
        while (true){
            View.getInstance().displayContent();
            int action = InputProvider.getInstance().gatherIntInput("Choose action:", 0, menuSize);
            MenuController.getInstance().getActionMap().get(action).getAction().run();
        }
    }
}
