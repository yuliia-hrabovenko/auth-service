package net.guzari.auth.datareceiver;

import net.guzari.auth.entity.UserEntity;

import java.util.List;
import java.util.Set;

public final class UserDataReceiver {

    public static final String ACTIVE_USER_EMAIL = "some@gmail.com";
    public static final String NON_ACTIVE_USER_EMAIL = "user@gmail.com";
    public static final Set<String> FEATURES = Set.of("f1", "f2");
    public static final Set<String> FEATURES_1 = Set.of("f1", "f2", "f3");

    public static List<UserEntity> getUsers() {
        return List.of(getActiveUser(), getNonActiveUser());
    }

    public static UserEntity getActiveUser() {
        return UserEntity.builder()
                .id("1")
                .email(ACTIVE_USER_EMAIL)
                .active(true)
                .features(FEATURES)
                .build();
    }

    public static UserEntity getNonActiveUser() {
        return UserEntity.builder()
                .id("2")
                .email(NON_ACTIVE_USER_EMAIL)
                .active(false)
                .features(FEATURES_1)
                .build();
    }
}
