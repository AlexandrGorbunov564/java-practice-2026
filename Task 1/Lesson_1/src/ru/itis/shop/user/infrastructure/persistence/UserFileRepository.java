package ru.itis.shop.user.infrastructure.persistence;

import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;
import ru.itis.shop.user.infrastructure.persistence.UserMapper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserFileRepository implements UserRepository {

    private final String fileName;
    private final UserMapper userMapper;

    public UserFileRepository(String fileName, UserMapper userMapper) {
        this.fileName = fileName;
        this.userMapper = userMapper;
    }

    @Override
    public void save(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String id = UUID.randomUUID().toString();
            user.setId(id);
            writer.write(userMapper.toLine(user));
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> findById(String id) {
        try {
            Path p = Path.of(fileName);
            List<String> lines = Files.readAllLines(p);
            for (String s : lines) {
                if (s.startsWith(id + "|")) {
                    User user = userMapper.fromLine(s);
                    System.out.println("Найден следующий пользователь:");
                    System.out.println("email: " + user.getEmail());
                    return Optional.of(user);
                }
            }
            System.out.println("Пользователь с таким id не найден");
            return Optional.empty();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }
}