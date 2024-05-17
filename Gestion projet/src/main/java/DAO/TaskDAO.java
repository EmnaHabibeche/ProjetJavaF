package DAO;

import Entity.Task;

import java.util.List;

public interface TaskDAO {
    void addTask(Task task);
    List<Task> getTasksByProjectId(String projectId);
}

