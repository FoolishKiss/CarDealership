package com.pluralsight;

import com.pluralsight.config.DatabaseConfig;
import com.pluralsight.ui.UserInterface;
//import com.pluralsight.ui.UserInterface;

import java.sql.Connection;
import java.sql.SQLException;

public class Program {
    public static void main(String[] args) {

        // Create user interface
        UserInterface ui = new UserInterface();

        // Runs user interface
        ui.run();

    }


}
