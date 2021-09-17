package ru.digitalleague.factory.ok.resources;

import java.util.ListResourceBundle;

public class notif_fr extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"WELCOME", "Welcome in FRENCH!"},
                {"ADV", "This is AD text in FRENCH"},
                {"UPDATE", "New update available! in FRENCH"}
        };
    }
}