package be.vinci.ipl.authentication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "credentials")
public class SafeCredentials {
    @Id
    @Column(nullable = false)
    private String pseudo;
    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String hashedPassword;

}
