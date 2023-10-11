package be.vinci.ipl.authentication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity(name = "credentials")
public class SafeCredentials {
    @Id
    @Column(nullable = false)
    private String pseudo;
    @Column(name = "password", nullable = false)
    private String hashedPassword;

    public SafeCredentials() {

    }
}
