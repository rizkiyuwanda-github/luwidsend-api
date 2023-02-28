package id.my.rizkiyuwanda.luwidsendapi.bank;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Entity
@Table(name = "bank")
@Data
public class Bank {
    @Size(max = 5)
    @NotNull
    @Id
    @Column(length = 5)
    private String id;

    @NotNull
    @Size(max = 200)
    @Column(length = 200, nullable = false, unique = true)
    private String name;
}
