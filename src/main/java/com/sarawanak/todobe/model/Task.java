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
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "task")
@Data
public class Task {

    public Task(){}

    public Task(int id, String description, int priority, int status, Date completionDate, User user) {
        this.id = id;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.completionDate = completionDate;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "priority")
    @JsonSerialize(using = PrioritySerializer.class)
    @JsonDeserialize(using = PriorityDeserializer.class)
    private Integer priority;

    @Column(name = "status", nullable = false)
    @JsonSerialize(using = StatusSerializer.class)
    @JsonDeserialize(using = StatusDeserializer.class)
    private Integer status;

    @Column(name = "scheduled_on", nullable = false)
    @JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeserializer.class)
    private Date completionDate;

    @JsonIgnore
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
