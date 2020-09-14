package com.rakole.tinyurl.service;

import com.rakole.tinyurl.api.TUserService;
import com.rakole.tinyurl.firebase.SecurityService;
import com.rakole.tinyurl.model.TUser;
import com.rakole.tinyurl.repository.TUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (!userExists(email)) {
            System.out.println(tUserRepository.save(TUser.builder().email(email).build()));
            return true;
        }
        return false;
    }
}
