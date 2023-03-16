package Persistence;

import android.content.Context;

public class UserDAO extends DBA<User>{
    public UserDAO(){
        init(User.class);
    }
}


