package ir.alefmordad.makenger.core.manager;

import ir.alefmordad.makenger.core.dao.UserDao;
import ir.alefmordad.makenger.core.entities.User;

public class UserManager extends Manager<User, String> {

    private static UserManager instance;

    public static UserManager getInstance() {
        if (instance == null)
            instance = new UserManager();
        return instance;
    }

    private UserManager() {
        setDao(UserDao.getInstance());
    }

}
