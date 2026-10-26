package com.lostandfound.services;

import com.lostandfound.model.User;
import com.lostandfound.repo.UserRepository;
import com.password4j.Hash;
import com.password4j.Password;

/**
 * This class coordinates between the UI and the Repos.
 *
 * <p>Ui --> Services --> Repositories</p>
 *
 */
public class AuthService {

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password) {
        UserRepository userRepo = new UserRepository();
        boolean verified = false;

        if (userRepo.exists(username)) {

            Hash hash = Password.hash(password).addRandomSalt().withArgon2();
            String hashFromDb = userRepo.findByUsername(username).getPassword();

            verified = Password.check(hash.getResult(), hashFromDb).withArgon2();
        }

        return verified ? userRepo.findByUsername(username) : null;
    }

}
