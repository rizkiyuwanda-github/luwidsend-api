package id.my.rizkiyuwanda.luwidsendapi.bank;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Entity
@Table(name = "bank")
@Data
public class Bank {
    @Id
    @Column(length = 5)
    private String id;

    @NotEmpty(message = "Name is required")
    @Column(length = 200, nullable = false, unique = true)
    private String name;
}
