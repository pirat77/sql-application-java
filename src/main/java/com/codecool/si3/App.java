package com.codecool.si3;

import java.sql.Connection;
import org.postgresql.*;

public class App {

    public static void main(String[] args){
        Connection connection = PostgreSQLJDBC.getInstance().connect();
    }

}
