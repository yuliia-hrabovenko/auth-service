package net.guzari.auth.service;

import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import net.guzari.auth.grpc.UserFeatureServiceGrpc;
import net.guzari.auth.grpc.UserRequest;
import net.guzari.auth.grpc.UserResponse;

import java.util.Set;

@GrpcService
@AllArgsConstructor
public class UserGrpcService extends UserFeatureServiceGrpc.UserFeatureServiceImplBase {

    private final UserService userService;

    @Override
    public void findFeaturesByEmail(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        String email = request.getEmail();
        Set<String> featuresByEmail = userService.findFeaturesByEmail(email);

        UserResponse userResponse = UserResponse.newBuilder()
                .addAllFeatures(featuresByEmail)
                .build();

        responseObserver.onNext(userResponse);
        responseObserver.onCompleted();
    }
}
