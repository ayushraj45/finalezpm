package com.example.globalpm.data;
import com.example.globalpm.entities.Subtask;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubtaskRepository extends ListCrudRepository<Subtask, Long> {
}