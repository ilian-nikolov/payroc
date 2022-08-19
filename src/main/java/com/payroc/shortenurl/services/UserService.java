package com.payroc.shortenurl.services;

import com.payroc.shortenurl.ApplicationException;
import com.payroc.shortenurl.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

/**
 * A service to create, retrieve and delete users.
 */
public class UserService {

    private static final Logger logger = LogManager.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean createUser(String name) {
        if (userRepository.createUser(name) != 1) {
            logger.error("Error creating user={}", name);
            return false;
        }
        return true;
    }

    public boolean deleteUser(long id, String name) {
        if (userRepository.deleteUser(id) != 1) {
            logger.error("Error deleting user={}", name);
            return false;
        }
        return true;
    }

    @EventListener({ContextRefreshedEvent.class})
    void contextRefreshedEvent() throws ApplicationException {
        logger.warn("a context refreshed event happened");
        userRepository.findById(1L);
    }

}
