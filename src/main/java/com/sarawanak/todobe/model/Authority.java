package com.sarawanak.todobe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "authorities")
public class Authority {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "authority")
    private String authority;

    public Authority(){}

    public Authority(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }
}
