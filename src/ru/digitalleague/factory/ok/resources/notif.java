package ru.digitalleague.factory.ok.resources;

import java.util.ListResourceBundle;

public class notif extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"WELCOME", "Добро пожаловать, %s!\n" +
                        "Ваш аккаунт зарегистрирован на email: %s\n" +
                        "Номер телефона: %s"},
                {"ADV", "Это рекламный текст!"},
                {"UPDATE", "Доступно новое обновление!"}
        };
    }
}