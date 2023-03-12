package id.my.rizkiyuwanda.luwidsendapi.bank;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Size(max = 100)
    @Column(length = 100, nullable = false, unique = true)
    private String name;
}
