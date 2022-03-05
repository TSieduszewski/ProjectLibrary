package com.crm.library.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private boolean active;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Book> books;

    public User() {
    }

    public User(int id, String name, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }
}
