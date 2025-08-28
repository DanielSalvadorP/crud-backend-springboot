package com.example.crudusuarios.controller;


import com.example.crudusuarios.Service.UserService;
import com.example.crudusuarios.model.UserModel;
import com.example.crudusuarios.model.UserSearchDTO;
import com.example.crudusuarios.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserModel> getAllUsers(){
        return userRepository.findAll();
    }

    /*
   @GetMapping(path = "/id/{id_user}")
    public UserSearchDTO searchUserEmailById(@PathVariable("id_user") long id_user){
       UserSearchDTO user =  userRepository.buscarEmailById(id_user);
        if (user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
        return user;
    }*/

    @GetMapping(path = "/state/{state}")
    public List<UserModel> findUserByState(@PathVariable String state){
        return userRepository.findByState(state);
    }

    @GetMapping("/resumenusers")
    public Map<String, Long> resumenUsersData(){
        return userService.resumenUsuarios();
    }

    //Permiso de un admin este permiso no lo pueden tener el saff o el view
    @GetMapping("/{id_user}")
    public ResponseEntity<UserModel> getUserById(@PathVariable("id_user") long id_user){
        return userRepository.findById(id_user)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    //Permiso de un staff

    //Permiso de Admin
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
            mdlUser.setFirstName(user.getFirstName());
            mdlUser.setLastName(user.getLastName());
            mdlUser.setEmail(user.getEmail());
            mdlUser.setState(user.getState());

            userRepository.save(mdlUser);
            return ResponseEntity.ok(mdlUser);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    //Permiso de un staff
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
            user.setFirstName(updates.get("first_name").toString());
        }

        userRepository.save(user);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Nombre actualizado");
        response.put("user",user);

        return ResponseEntity.ok(response);
    }

    //Permiso de un admin:
    @DeleteMapping("/dltUser/{id_user}")
    public ResponseEntity<String> deleteUser(@PathVariable long id_user){
        userRepository.deleteById(id_user);
        return ResponseEntity.ok().build();
    }
}
