package ru.itis.shop.user.infrastructure.persistence;

import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;
import ru.itis.shop.app.Main;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository {

    private final UserMapper userMapper;

    public UserRepositoryJdbcImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.empty();
    }

    @Override
    public boolean updateInfo(String email, String newDescription) {
        return false;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(Main.DB_URL, Main.DB_USER, Main.DB_PASSWORD)) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("select * from users")) {
                    while (resultSet.next()) {
                        System.out.println(resultSet.getString("name") +
                                " " + resultSet.getString("email"));
                        String s = resultSet.getString(1) + "|" + resultSet.getString(2) + "|" +
                                resultSet.getString(3) + "|" + resultSet.getString(4) + "|" +
                                resultSet.getString(5);
                        users.add(userMapper.fromLine(s));
                    }
                }
            }
            return users;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
