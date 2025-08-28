package com.example.crudusuarios.repository;

import com.example.crudusuarios.model.UserModel;
import com.example.crudusuarios.model.UserSearchDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findById(long idUser);

    List<UserModel> findByState(String state);
    long count();
    long countByState(String state);

}