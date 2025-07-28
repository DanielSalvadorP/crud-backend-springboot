package com.example.crudusuarios.repository;

import com.example.crudusuarios.model.UserModel;
import com.example.crudusuarios.model.UserSearchDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    @Query("SELECT new com.example.crudusuarios.model.UserSearchDTO(u.id_user, u.first_name, u.last_name, u.email, u.state) " +
            "FROM UserModel u WHERE u.id_user = :idUser")
    UserSearchDTO buscarEmailById(@Param("idUser") long idUser);
}