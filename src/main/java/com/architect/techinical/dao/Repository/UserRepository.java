package com.architect.techinical.dao.Repository;

import com.architect.techinical.dao.Model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
