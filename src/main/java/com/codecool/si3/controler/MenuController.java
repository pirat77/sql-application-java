package com.codecool.si3.controler;

import com.codecool.si3.model.MenuOption;
import com.codecool.si3.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class MenuController {
    private Map<Integer, MenuOption> actionMap;
    private static MenuController menuController;

    public static MenuController getInstance(){
        if (menuController == null) menuController = new MenuController();
        return menuController;
    }

    private MenuController(){
        actionMap = new HashMap<>();
        actionMap = new ActionAssembler().getMap();
        View.getInstance().setCommandList(new ArrayList<>(this.getActionMap().values()));
    }

    public Map<Integer, MenuOption> getActionMap() {
        return actionMap;
    }
}
