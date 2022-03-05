package com.crm.library.entity;

import com.crm.library.util.Status;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Data
public class Book {

    @Id
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "number")
    private Integer number;

    @Column(name = "available")
    private boolean available;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    @Nullable
    private User user;

    public Book() {
    }

    public Book(Status status){
        this.status = status;
    }
}
