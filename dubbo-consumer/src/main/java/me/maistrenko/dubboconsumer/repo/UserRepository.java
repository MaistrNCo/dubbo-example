package me.maistrenko.dubboconsumer.repo;

import me.maistrenko.dubbodao.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByName(String name);
}
