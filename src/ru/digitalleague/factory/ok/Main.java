package ru.digitalleague.factory.ok;

import ru.digitalleague.factory.ok.notification.*;
import ru.digitalleague.factory.ok.notification.factory.*;

public class Main {

    public static void main(String[] args) {
        User user = new User(2L, "Денис", "denis@gmail.com", "+79522668105");

        NotificationFactory factory = true ? new MailNotificationFactory() : new PhoneNotificationFactory();
        Notification welcomeMessage = new Translator(factory.makeNotification(Messages.WELCOME, user));
        sendNotification(welcomeMessage);
        System.out.println();

        Notification welcomeMessage2 = factory.makeNotification(Messages.WELCOME, user);
        sendNotification(welcomeMessage2);
    }

    private static void sendNotification(Notification notification) {
        System.out.println(notification.getText());
    }
}
