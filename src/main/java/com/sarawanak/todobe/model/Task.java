package com.sarawanak.todobe.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "task")
@Data
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "priority")
    private int priority;

    @Column(name = "status", nullable = false)
    private int status;

    @Column(name = "scheduled_on", nullable = false)
    private String completionDate;

    @Column(name = "user_id")
    private int userId;
}
