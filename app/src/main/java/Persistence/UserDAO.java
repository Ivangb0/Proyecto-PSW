package Persistence;

import BusinessLogic.User;

public class UserDAO extends DBA<User>{
    public UserDAO(){
        init(User.class);
    }
}


