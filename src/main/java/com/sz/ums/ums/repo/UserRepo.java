package com.sz.ums.ums.repo;

import com.sz.ums.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User,Long> {
    User findUserByUsernameAndPassword(String username, String password);
}
