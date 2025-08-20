package com.junction.junction_project.domain.user.repositroy;


import com.junction.junction_project.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findBySocialEmail(String socialEmail);
}
