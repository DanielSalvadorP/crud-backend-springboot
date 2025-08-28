package com.example.crudusuarios.Service;

import com.example.crudusuarios.model.UserModel;
import com.example.crudusuarios.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserModel> findUserByState(String state){
        return userRepository.findByState(state);
    }

    public Map<String, Long> resumenUsuarios(){
        long total = userRepository.count();
        long activos = userRepository.countByState("Activo");
        long inactivos = userRepository.countByState("Inactivo");

        return Map.of(
                "total", total,
                "Activos", activos,
                "Inactivos", inactivos
        );
    }
}
