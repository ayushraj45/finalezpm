package com.example.globalpm.data;
import com.example.globalpm.entities.Subtask;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubtaskRepository extends ListCrudRepository<Subtask, UUID> {
}