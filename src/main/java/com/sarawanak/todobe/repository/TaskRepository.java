package com.sarawanak.todobe.repository;

import com.sarawanak.todobe.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
