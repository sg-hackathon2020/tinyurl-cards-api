package com.rakole.tinyurl.api;

import com.rakole.tinyurl.model.TUser;

import java.util.List;

public interface TUserService {
    boolean verifyAndCreateUser();

    TUser getMyCurrentUser();

    List<TUser> getAllUsers();

    TUser getUser(int userId);

    TUser save(TUser tUser);

}
