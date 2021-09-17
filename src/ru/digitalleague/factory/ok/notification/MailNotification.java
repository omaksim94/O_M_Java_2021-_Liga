package ru.digitalleague.factory.ok.notification;

import ru.digitalleague.factory.ok.User;

public class MailNotification extends Notification {

    private Messages type;
    private User user;

    public MailNotification(Messages type, User user) {
        this.type = type;
        this.user = user;
    }

    public String getText() {
        return String.format(
                rb.getString(type.toString()),
                user.getName(),
                user.getEmail(),
                user.getPhone()
        );
    }
}
