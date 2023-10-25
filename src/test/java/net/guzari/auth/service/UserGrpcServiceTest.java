package net.guzari.auth.service;

import net.devh.boot.grpc.client.inject.GrpcClient;
import net.guzari.auth.datareceiver.UserDataReceiver;
import net.guzari.auth.grpc.UserFeatureServiceGrpc;
import net.guzari.auth.grpc.UserRequest;
import net.guzari.auth.grpc.UserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(properties = {
        "grpc.server.inProcessName=test",
        "grpc.server.port=-1",
        "grpc.client.inProcess.address=in-process:test"
})
@DirtiesContext
public class UserGrpcServiceTest {

    @GrpcClient("inProcess")
    private UserFeatureServiceGrpc.UserFeatureServiceBlockingStub userFeatureServiceBlockingStub;

    @MockBean
    private UserService userService;

    @Test
    public void givenExistingUserEmail_findFeaturesByEmail_thenReturnFeaturesList() {
        when(userService.findFeaturesByEmail(UserDataReceiver.ACTIVE_USER_EMAIL)).thenReturn(UserDataReceiver.FEATURES);

        UserResponse response = userFeatureServiceBlockingStub.findFeaturesByEmail(
                UserRequest.newBuilder()
                        .setEmail(UserDataReceiver.ACTIVE_USER_EMAIL)
                        .build());

        assertThat(response.getFeaturesList()).containsAll(UserDataReceiver.FEATURES);
    }

    @Test
    public void givenNotExistingEmail_findFeaturesByEmail_thenReturnEmptyFeaturesList() {
        UserResponse response = userFeatureServiceBlockingStub.findFeaturesByEmail(
                UserRequest.newBuilder()
                        .setEmail(UserDataReceiver.ACTIVE_USER_EMAIL)
                        .build());

        assertThat(response.getFeaturesList()).isEmpty();
    }
}
