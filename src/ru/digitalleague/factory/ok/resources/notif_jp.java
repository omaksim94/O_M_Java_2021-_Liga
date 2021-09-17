package ru.digitalleague.factory.ok.resources;

import java.util.ListResourceBundle;

public class notif_jp extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"WELCOME", "ようこそ %s!"},
                {"ADV", "This is AD text in JAPANESE"},
                {"UPDATE", "New update available! in JAPANESE"}
        };
    }
}