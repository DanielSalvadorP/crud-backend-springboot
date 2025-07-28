package com.example.crudusuarios.controller;


import com.example.crudusuarios.model.UserModel;
import com.example.crudusuarios.model.UserSearchDTO;
import com.example.crudusuarios.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<UserModel> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(path = "/email/{id_user}")
    public UserSearchDTO searchUserEmailById(@PathVariable("id_user") long id_user){
        return userRepository.buscarEmailById(id_user);

    }

    @PostMapping("/saveuser")
    public UserModel crearUsuario(@RequestBody UserModel user){
        return userRepository.save(user);
    }

    @PutMapping(path = "/updateUser/{id_user}")
    public ResponseEntity<UserModel> updateUser(@PathVariable long id_user,
                                                @RequestBody UserModel user) {
        Optional<UserModel> optionalUserModel = userRepository.findById(id_user);

        //Map<String, Object> = necesario para los Path

        if(optionalUserModel.isPresent()) {
            UserModel mdlUser = optionalUserModel.get(); //mdlUser es como un apodo digamos o una variable UserModel para hacer mas corto ese optionalUserModel.get()
            mdlUser.setFirst_name(user.getFirst_name());
            mdlUser.setLast_name(user.getLast_name());
            mdlUser.setEmail(user.getEmail());
            mdlUser.getState();

            userRepository.save(mdlUser);
            return ResponseEntity.ok(user);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping(path = "/updatedatauser/{id_user}")
    public ResponseEntity<?> actualizarDatoUser(@PathVariable Long id_user, Map<String, Object> updates) {
        Optional<UserModel> optionalUserModel = userRepository.findById(id_user);

        if(optionalUserModel.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message","Usuario no encontrado"));
        }

        UserModel user = optionalUserModel.get();

        if(updates.containsKey("first_name")){
            user.setFirst_name(updates.get("first_name").toString());
        }

        userRepository.save(user);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Nombre actualizado");
        response.put("user",user);

        return ResponseEntity.ok(response);
    }
}
