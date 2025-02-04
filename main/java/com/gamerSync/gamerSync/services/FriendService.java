package com.gamerSync.gamerSync.services;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

import com.gamerSync.gamerSync.models.Friend;
import com.gamerSync.gamerSync.models.Users;
import com.gamerSync.gamerSync.repositories.FriendRepository;

@Service
public class FriendService {

    @Autowired
    private FriendRepository userRepository;

    public Friend findById(Long id) {
        Optional<Friend> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException(
                "Usuario não encontrado! ID " + id + ", Tipo: " + Users.class.getName()));
    }

    public Friend findByUsername(String username) {
        Optional<Friend> user = Optional.ofNullable(this.userRepository.findByUsername(username));
        return user.orElseThrow(() -> new RuntimeException(
                "Usuario não encontrado! ID " + username + ", Tipo: " + Users.class.getName()));
    }
    public List<Friend> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public Friend createUser(Friend obj) {
        Users user = new Users();
        obj.setId(null);
        obj.setUser_id(user.getId());
        obj = this.userRepository.save(obj);
        this.userRepository.save(obj);
        return obj;
    }

    @Transactional
    public Friend updateUser(Friend obj) {
        Friend newObj = findById(obj.getId());
        newObj.setUsername(obj.getUsername());
        return newObj;
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!");
        }
    }

}
