package com.sarawanak.todobe.repository;

import com.sarawanak.todobe.model.Task;

import java.util.List;

public interface CustomTaskRepository {
    List<Task> findByCriteria(Integer userId, Integer priority, Integer status);
}
