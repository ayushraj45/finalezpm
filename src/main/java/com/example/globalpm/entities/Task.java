package com.example.globalpm.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Task {

        @Id
        @GeneratedValue
        Long id;

        private String taskName;
        @ManyToOne
        // @JoinColumn(name = "project_id")
        private Project project;

        @OneToMany(mappedBy = "task")
        private List<Subtask> subtasks;

        public Task(String taskName) {
            // this.project=project;
            this.taskName = taskName;
        }

        Task() {
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }
    }
