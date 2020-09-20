package com.codecool.si3.controler;

import com.codecool.si3.model.MenuOption;
import com.codecool.si3.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MenuController {
    private Map<Integer, MenuOption> actionMap;

    public MenuController(View view){
        actionMap = new HashMap<>();
        actionMap = new ActionAssembler().getMap();
        view.setCommandList(new ArrayList<>(this.getActionMap().values()));
    }

    public Map<Integer, MenuOption> getActionMap() {
        return actionMap;
    }
}
