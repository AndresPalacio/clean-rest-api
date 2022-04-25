package com.example.service.user.infrastructure.persistence.jpa;

import com.example.service.user.infrastructure.persistence.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRepository extends JpaRepository<UserData, Integer> {

    Collection<UserData> findByFirstNameAndLastName(String firstName, String lastName);
}
