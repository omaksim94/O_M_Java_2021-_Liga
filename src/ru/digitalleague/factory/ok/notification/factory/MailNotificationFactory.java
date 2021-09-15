package ru.digitalleague.factory.ok.notification.factory;

import ru.digitalleague.factory.ok.User;
import ru.digitalleague.factory.ok.notification.Notification;
import ru.digitalleague.factory.ok.notification.MailNotification;

public class MailNotificationFactory implements NotificationFactory {
    public Notification makeNotification(String body, User user) {
        return new MailNotification(body, user, hasAdv(user));
    }

    private boolean hasAdv(User user) {
        return user.getId() % 2 == 0;
    }
}
