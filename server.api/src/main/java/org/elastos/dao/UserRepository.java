package org.elastos.dao;

import org.elastos.dto.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByPhone(String phone);
    Optional<User> findByDid(String did);
}
