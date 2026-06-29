package ru.itis.shop.user.infrastructure.persistence;

import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

public class UserFileRepository implements UserRepository {

    private final String fileName;

    public UserFileRepository(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String id = UUID.randomUUID().toString();
            user.setId(id);
            writer.write(user.getId() + "|" +
                    user.getEmail() + "|" +
                    user.getPassword() + "|" +
                    user.getProfileDescription());
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public User findById(String id) {
        try {
            Path p = Path.of(fileName);
            List<String> lines = Files.readAllLines(p);
            for (String s : lines) {
                if (s.startsWith(id + "|")) {
                    String[] parameters = s.split("\\|");
                    if (parameters.length != 4) {
                        System.err.println("Найден пользователь с неполными данными");
                        return null;
                    }
                    String email = parameters[1];
                    String password = parameters[2];
                    String profileDescription = parameters[3];
                    User user = new User(email, password, profileDescription);
                    System.out.println("Найден следующий пользователь:");
                    System.out.println("email: " + email);
                    return user;
                }
            }
            System.out.println("Пользователь с таким id не найден");
            return null;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}