package ru.digitalleague.factory.ok.notification;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Translator extends Notification {
    private Notification wrappee;
    public Translator(Notification notification) {
        wrappee = notification;
    }

    @Override
    public String getText() {
        translate();
        return wrappee.getText();
    }

    public void translate(){
        System.out.println("На какой язык нужно перевести сообщение?");
        Scanner scanner = new Scanner(System.in);
        String lang = (scanner.nextLine().toLowerCase());
        wrappee.rb = ResourceBundle.getBundle(
                "ru.digitalleague.factory.ok.resources.notif", new Locale(lang));
    }
}
