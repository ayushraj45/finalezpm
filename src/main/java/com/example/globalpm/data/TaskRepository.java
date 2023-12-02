package com.example.globalpm.data;
import com.example.globalpm.entities.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends ListCrudRepository<Task, UUID> {
    @Query(value = "SELECT * FROM TASK WHERE PROJECT_ID = :#{#id}", nativeQuery = true)
    List<Task> findTasksByProjectId(@Param("id") UUID id);
}