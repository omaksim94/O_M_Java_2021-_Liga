package ru.digitalleague.factory.bad;

import ru.digitalleague.factory.ok.User;

public class Main {

    public static void main(String[] args) {
        User user = new User(2L, "Денис", "denis@gmail.com", "+79522668105");
        String body = "Доброго дня!";

        if (false) {
            sendMailNotification(new MailNotification(body, user, user.getId() % 2 == 0));
        } else {
            sendPhoneNotification(new PhoneNotification(body, user));
        }
    }

    private static void sendPhoneNotification(PhoneNotification notification) {
        System.out.println(notification.getText());
    }

    private static void sendMailNotification(MailNotification notification) {
        System.out.println(notification.getText());
    }
}
