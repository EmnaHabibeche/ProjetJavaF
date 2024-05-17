package DAO;

import Entity.Project;
import Entity.User;

import java.util.List;

public interface ProjectDAO {

    List<Project> getAllProjects();
    void addProject(Project project, User currentUser);
    void addProject(Project project);

}
