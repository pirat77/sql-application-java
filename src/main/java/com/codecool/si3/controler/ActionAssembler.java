package com.codecool.si3.controler;

import com.codecool.si3.model.MenuOption;
import java.util.HashMap;
import java.util.Map;

public class ActionAssembler {
    private final Map<Integer, MenuOption> actionMap;

    public ActionAssembler() {
        actionMap = new HashMap<>();
        mapBuilder();
    }

    private void mapBuilder(){
        populateMapWithActions(actionMap, new UniversalActions());
    }

    private void populateMapWithActions(Map<Integer, MenuOption> actionMap, UniversalActions actions){
        int index = 0;
        for (MenuOption option : actions.returnActions()){
            option.setId(index);
            actionMap.put(index, option);
            index++;
        }
    }

    public Map<Integer, MenuOption> getMap(){
        return this.actionMap; }
}
