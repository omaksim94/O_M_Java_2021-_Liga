package ru.philosophyit.internship.javabase.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String ...args) throws IOException {
        System.out.println(Files.readString(Path.of("src/main/resources/hello.txt")));

        // Отобразите рекурсивно дерево директорий src/main/java/ru/philosophyit/internship/javabase
        Path path = Path.of("src");
        Files.walkFileTree(path, new PrintDir());

    }
}

