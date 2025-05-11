//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.scm.services.implementation;

import com.scm.entities.User;
import com.scm.helpers.ResourceNotFoundException;
import com.scm.repositories.UserRepo;
import com.scm.services.UserService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setRolesList(List.of("ROLE_USER"));
        return (User)this.userRepo.save(user);
    }

    public Optional<User> getUserById(String id) {
        return this.userRepo.findById(id);
    }

    public Optional<User> updateUser(User user) {
        User oldUser = (User)this.userRepo.findById(user.getUserId()).orElseThrow(ResourceNotFoundException::new);
        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        oldUser.setAbout(user.getAbout());
        oldUser.setPhoneNumber(user.getPhoneNumber());
        oldUser.setProfilePic(user.getProfilePic());
        oldUser.setEnabled(user.isEnabled());
        oldUser.setEmailVerified(user.isEmailVerified());
        oldUser.setPhoneVerified(user.isPhoneVerified());
        oldUser.setProvider(user.getProvider());
        oldUser.setProviderId(user.getProviderId());
        User save = (User)this.userRepo.save(oldUser);
        return Optional.of(save);
    }

    public void deleteUser(String id) {
        User user = (User)this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not exist"));
        this.userRepo.deleteById(id);
    }

    public boolean isUserExist(String id) {
        User user = (User)this.userRepo.findById(id).orElse((User) null);
        return user != null;
    }

    public boolean isUserExistByEmail(String email) {
        User user = (User)this.userRepo.findByEmail(email).orElse((User) null);
        return user != null;
    }

    public List<User> getAllUsers() {
        return this.userRepo.findAll();
    }

    public User getUserByEmail(String email) {
        return (User)this.userRepo.findByEmail(email).orElse((User) null);
    }

    public boolean existsByEmail(String email) {
        return this.userRepo.existsByEmail(email);
    }
}
