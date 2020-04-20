package com.sarawanak.todobe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Data
@JsonIgnoreProperties({"enabled"})
public class User {
    public User(){}

    public User(String username, String password, Integer enabled) {
        this.username = username;
        this.enabled = enabled;
        this.password = password;
    }

    @Id
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "enabled", nullable = false)
    private Integer enabled;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;
}
