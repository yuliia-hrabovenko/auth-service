package net.guzari.auth.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Builder
@Document(collection = "user")
public class UserEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String email;
    private Boolean active;
    private Set<String> features;
}
