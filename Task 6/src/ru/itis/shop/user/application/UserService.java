package ru.itis.shop.user.application;

import ru.itis.shop.user.api.dto.UserDto;
import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(RuntimeException::new);

        return new UserDto(user.getId(), user.getEmail(), user.getProfileDescription());
    }

    public void signUp(String name, String email, String password, String profileDescription) {
        User user = new User(name, email, password, profileDescription);
        userRepository.save(user);
    }

    public boolean signIn(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            return userOptional.get().getPassword().equals(password);
        } else return false;
    }

    public UserDto findById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        return new UserDto(user.getId(), user.getEmail(), user.getProfileDescription());
    }



    public boolean updateInfo(String email, String profileDescription) {
        return userRepository.updateInfo(email, profileDescription);
    }

    public List<UserDto> findAll()  {
        List<UserDto> list = new ArrayList<>();
        if (!userRepository.findAll().isEmpty()) {
            for (User user : userRepository.findAll()) {
                list.add(new UserDto(user.getId(), user.getEmail(), user.getProfileDescription()));
            }
        }
        return list;
    }

    public List<UserDto> findAllByProfileDescription(String profileDescription)  {
        List<UserDto> list = new ArrayList<>();
        List<User> users = userRepository.findAllByProfileDescription(profileDescription);
        if (!users.isEmpty()) {
            for (User user : userRepository.findAllByProfileDescription(profileDescription)) {
                list.add(new UserDto(user.getId(), user.getEmail(), user.getProfileDescription()));
            }
        }
        return list;
    }
}
