package src.auth;

import src.model.User;

/*
This
 */
public interface AuthService {
        public User authenticate(String user, String password);
}
