package net.guzari.auth.service;

import net.guzari.auth.datareceiver.UserDataReceiver;
import net.guzari.auth.entity.UserEntity;
import net.guzari.auth.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Test
    void givenActiveUser_findFeaturesByEmail_thenReturnUserFeatures() {
        UserEntity user = UserDataReceiver.getActiveUser();
        String email = user.getEmail();
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        Set<String> expectedFeatures = user.getFeatures();

        Set<String> actualFeatures = userService.findFeaturesByEmail(email);

        assertEquals(expectedFeatures, actualFeatures);
    }

    @Test
    void givenNonActiveUser_findFeaturesByEmail_thenReturnEmptySetOfFeatures() {
        UserEntity user = UserDataReceiver.getNonActiveUser();
        String email = user.getEmail();
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        Set<String> actualFeatures = userService.findFeaturesByEmail(email);

        assertThat(actualFeatures).isEmpty();
    }

    @Test
    void givenNonExistingUserEmail_findFeaturesByEmail_thenReturnEmptySetOfFeatures() {
        when(userRepository.findByEmail(UserDataReceiver.ACTIVE_USER_EMAIL)).thenReturn(Optional.empty());

        Set<String> actualFeatures = userService.findFeaturesByEmail(UserDataReceiver.ACTIVE_USER_EMAIL);

        assertThat(actualFeatures).isEmpty();
    }

    @Test
    void givenExistingActiveUserEmail_isExistUserByEmail_thenReturnTrue() {
        UserEntity user = UserDataReceiver.getActiveUser();
        String email = user.getEmail();
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        Boolean actual = userService.isExistUserByEmail(email);

        assertTrue(actual);
    }

    @Test
    void givenExistingNonActiveUserEmail_isExistUserByEmail_thenReturnFalse() {
        UserEntity user = UserDataReceiver.getNonActiveUser();
        String email = user.getEmail();
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        Boolean actual = userService.isExistUserByEmail(email);

        assertFalse(actual);
    }

    @Test
    void givenNotExistingUserEmail_isExistUserByEmail_thenReturnFalse() {
        when(userRepository.findByEmail(UserDataReceiver.NON_ACTIVE_USER_EMAIL)).thenReturn(Optional.empty());

        Boolean actual = userService.isExistUserByEmail(UserDataReceiver.NON_ACTIVE_USER_EMAIL);

        assertFalse(actual);
    }
}
