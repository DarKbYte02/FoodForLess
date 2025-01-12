package com.ipn.mx.domain.Service;

import com.ipn.mx.domain.Entity.User;
import com.ipn.mx.domain.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void saveUser(User user){
        userRepository.save(user);
    }

    public User getUserById(Long id){return userRepository.findById(id).orElse(null);}

    public User getUserByCorreoUsuario(String correoUsuario){return userRepository.findByCorreoUsuario(correoUsuario);}

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User updateUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
