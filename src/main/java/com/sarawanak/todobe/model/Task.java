package com.sarawanak.todobe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sarawanak.todobe.serdes.DateDeserializer;
import com.sarawanak.todobe.serdes.DateSerializer;
import com.sarawanak.todobe.serdes.PriorityDeserializer;
import com.sarawanak.todobe.serdes.PrioritySerializer;
import com.sarawanak.todobe.serdes.StatusDeserializer;
import com.sarawanak.todobe.serdes.StatusSerializer;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "task")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "priority")
    @JsonSerialize(using = PrioritySerializer.class)
    @JsonDeserialize(using = PriorityDeserializer.class)
    private int priority;

    @Column(name = "status", nullable = false)
    @JsonSerialize(using = StatusSerializer.class)
    @JsonDeserialize(using = StatusDeserializer.class)
    private int status;

    @Column(name = "scheduled_on", nullable = false)
    @JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeserializer.class)
    private Date completionDate;

    @JsonIgnore
    @Column(name = "user_id")
    private int userId;
}
