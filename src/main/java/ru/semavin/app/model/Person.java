package ru.semavin.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "person")
@Setter@Getter@AllArgsConstructor@NoArgsConstructor
public class Person {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String pass;

    @Column(name = "role")
    private String role;

    @Column(name = "email")
    private String email;
    @Column(name = "balance")
    private long balance;

    @Column(name = "createdate")
    private LocalDateTime createdate;
    @OneToMany(mappedBy = "person")
    private List<Order> orders;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", pass='" + pass + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", createdate=" + createdate +
                '}';
    }
}
