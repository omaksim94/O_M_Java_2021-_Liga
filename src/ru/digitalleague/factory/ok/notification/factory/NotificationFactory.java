package ru.digitalleague.factory.ok.notification.factory;

import ru.digitalleague.factory.ok.User;
import ru.digitalleague.factory.ok.notification.Messages;
import ru.digitalleague.factory.ok.notification.Notification;

public interface NotificationFactory {
    Notification makeNotification(Messages type, User user);
}
