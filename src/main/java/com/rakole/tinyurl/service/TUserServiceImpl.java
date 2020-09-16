package com.rakole.tinyurl.service;

import com.rakole.tinyurl.api.TUserService;
import com.rakole.tinyurl.exception.UserNotFoundException;
import com.rakole.tinyurl.firebase.SecurityService;
import com.rakole.tinyurl.model.TUser;
import com.rakole.tinyurl.repository.TUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TUserServiceImpl implements TUserService {

    private final TUserRepository tUserRepository;

    private final SecurityService securityService;

    @Autowired
    public TUserServiceImpl(TUserRepository tUserRepository, SecurityService securityService) {
        this.tUserRepository = tUserRepository;
        this.securityService = securityService;
    }

    protected boolean userExists(String email) {
        return tUserRepository.existsByEmail(email);
    }

    @Override
    public boolean verifyAndCreateUser() {
        String email = securityService.getUser().getEmail();
        return !userExists(email);
    }

    @Override
    public TUser getMyCurrentUser() {
        return tUserRepository.findByEmail(securityService.getUser().getEmail());
    }

    @Override
    public List<TUser> getAllUsers() {
        return tUserRepository.findAll();
    }

    @Override
    public TUser getUser(int userId) {
        return tUserRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public TUser save(TUser tUser) {
        return tUserRepository.save(tUser);
    }
}
