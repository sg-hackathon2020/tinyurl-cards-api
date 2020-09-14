package com.rakole.tinyurl.api;

import com.rakole.tinyurl.model.TUser;

import java.util.List;

public interface TUserService {
    boolean verifyAndCreateUser();

    List<TUser> getAllUsers();

}
