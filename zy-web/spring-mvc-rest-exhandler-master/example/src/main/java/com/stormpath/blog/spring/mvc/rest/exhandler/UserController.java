package com.stormpath.blog.spring.mvc.rest.exhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @RequestMapping(value = "/add", method = GET)
    public User getUser(@Valid User user) {

    }


    @RequestMapping(value = "/{username}", method = GET)
    public User getUser(@PathVariable String username) {
        return findUser(username);
    }

    /**
     * Simulates a Manager or DAO lookup for a stored user account.
     *
     * @param username the username for the user to lookup.  Supports only 'jsmith' and 'djones' for testing.
     * @return the associated user
     * @throws UnknownResourceException if there is no user with the specified username.
     */
    private User findUser(String username) throws UnknownResourceException {
        if (!StringUtils.hasText(username)) {
            throw new IllegalArgumentException("Username is required.");
        }

        //simulate a successful lookup for 2 users:
        if ("jsmith".equals(username)) {
            return new User("Jane Smith");
        }
        if ("djones".equals(username)) {
            return new User("Don Jones");
        }

        //any other lookup throws an exception (not found):
        throw new UnknownResourceException("Unable to find user with username '" + username + "'");
    }


}
