package com.sarawanak.todobe.repository;

import com.sarawanak.todobe.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>, CustomTaskRepository {
//    @Query(value = "SELECT t FROM Task t WHERE t.userId = ?1")
//    List<Task> findByUserId(Integer userId);
}
