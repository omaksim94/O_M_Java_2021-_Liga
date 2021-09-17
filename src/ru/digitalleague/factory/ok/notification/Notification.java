package ru.digitalleague.factory.ok.notification;

import java.util.Locale;
import java.util.ResourceBundle;

public abstract class Notification {
     ResourceBundle rb = ResourceBundle.getBundle("ru.digitalleague.factory.ok.resources.notif");
    public abstract String getText();


}
