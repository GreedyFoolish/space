package com.example.space.repository;

import com.example.space.model.SpaceUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpaceUserRepository extends JpaRepository<SpaceUser, Long> {

    List<SpaceUser> findByUserName(String name);

}
