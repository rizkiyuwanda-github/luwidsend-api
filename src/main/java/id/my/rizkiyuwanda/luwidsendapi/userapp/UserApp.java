package id.my.rizkiyuwanda.luwidsendapi.userapp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "user_app")
@Data
public class UserApp {
    @Size(max = 100)
    @Email(message = "Email format is required")
    @NotNull
    @Id
    @Column(length = 100)
    private String id;

    @Size(max = 100)
    @NotNull
    @Column(length = 100, nullable = false)
    private String password;

    @Size(max = 50)
    @NotNull
    @Column(length = 50, nullable = false)
    private String role;

    @Size(max = 50)
    @Column(name = "account_id", length = 50)
    private String accountId;
}
