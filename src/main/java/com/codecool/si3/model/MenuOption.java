package com.codecool.si3.model;

public class MenuOption implements Displayable {

    private int id;
    private final String name;
    private final Runnable action;

    public MenuOption(String name, Runnable action){
        this.name = name;
        this.action = action;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id; }

    public String getName() {
        return name;
    }

    public Runnable getAction() {
        return action;
    }

    @Override
    public String[] toStringList() {
        return new String[]{Integer.toString(this.getId()), this.getName()};
    }
}
