package com.lostandfound.auth;

import com.lostandfound.model.User;

/**
 * Defines how users are authenticated, without tying the code
 * to any specific data source (database, file, etc.).
 *
 * <p>Callers only see the methods below and not how they're implemented.
 * </p>
 */
public interface UserAuthenticator {
    /**
     * Authenticates a user by username and password.
     *
     * @param username the account name
     * @param password the secret credential
     * @return the authenticated {@link User}, or {@code null} if credentials are invalid
     */
    User authenticate(String username, String password);
}
