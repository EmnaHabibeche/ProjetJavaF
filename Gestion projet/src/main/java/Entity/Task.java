package Entity;

import java.time.LocalDate;

public class Task {
    private String taskId;
    private String description;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;
    private String projectId;

    public Task(String taskId, String description, String status, LocalDate startDate, LocalDate endDate, String projectId) {
        this.taskId = taskId;
        this.description = description;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectId = projectId;
    }


    // Getters and setters

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDate getStartDate() {
        return startDate;
    }

    // Getter method for end date
    public LocalDate getEndDate() {
        return endDate;
    }

    // Getter method for project ID
    public String getProjectId() {
        return projectId;
    }

}
