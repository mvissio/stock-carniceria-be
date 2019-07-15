package com.svcg.StockCustom.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svcg.StockCustom.entity.Rol;
import com.svcg.StockCustom.entity.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Serializable> {

    Optional<User> findByUsername(String username);
    
    Optional<Page<User>> findByUsernameContaining(String username, Pageable pageable);

    Optional<User> findByUserId(Long userId);

    Optional<User> findByEmail(String email);

    Optional<List<User>> findByRolIn(List<Rol> roles, Pageable pageable);


}
