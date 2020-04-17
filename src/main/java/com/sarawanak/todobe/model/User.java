package com.sarawanak.todobe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "todo_user")
@Data
@JsonIgnoreProperties({"id", "password"})
public class User {
    public User(){}

    public User(int id, String username, String password, String name) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;
}
