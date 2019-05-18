package com.svcg.StockCustom.repository;

import com.svcg.StockCustom.entity.Rol;
import com.svcg.StockCustom.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Serializable> {

    User findByUsername(String username);

    User findByUserId(Long userId);

    User findByEmail(String email);

    Optional<List<User>> findByRolIn(List<Rol> roles, Pageable pageable);


}
