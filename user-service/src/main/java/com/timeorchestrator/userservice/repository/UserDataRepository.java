package com.timeorchestrator.userservice.repository;

import com.timeorchestrator.userservice.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository extends JpaRepository<UserData, String> {

}
