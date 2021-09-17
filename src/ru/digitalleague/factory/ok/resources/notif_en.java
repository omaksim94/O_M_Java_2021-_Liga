package ru.digitalleague.factory.ok.resources;

import java.util.ListResourceBundle;

public class notif_en extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"WELCOME", "Welcome, %s!\n" +
                        "Your account has been created for email: %s\n" +
                        "Phone: %s"},
                {"ADV", "This is advertisement notification!"},
                {"UPDATE", "New update is available!"}
        };
    }
}
