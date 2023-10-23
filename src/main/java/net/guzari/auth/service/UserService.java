package net.guzari.auth.service;

import lombok.AllArgsConstructor;
import net.guzari.auth.entity.UserEntity;
import net.guzari.auth.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Set<String> findFeaturesByEmail(String email) {
        return userRepository.findByEmail(email)
                .filter(UserEntity::getActive)
                .map(UserEntity::getFeatures)
                .orElseGet(Set::of);
    }

    public Boolean isExistUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .filter(UserEntity::getActive)
                .isPresent();
    }
}
