package pesiykot.memorius.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pesiykot.memorius.persistence.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User save(User user);
}
