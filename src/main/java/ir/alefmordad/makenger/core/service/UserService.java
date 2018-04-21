package ir.alefmordad.makenger.core.service;

import ir.alefmordad.makenger.core.entities.User;
import ir.alefmordad.makenger.core.manager.UserManager;

public class UserService extends Service<User, String> {

    private static UserService userService;

    private UserService(){
        manager = UserManager.getInstance();
    }

    public static UserService getUserService() {
        if (userService == null)
            userService = new UserService();
        return userService;
    }

}