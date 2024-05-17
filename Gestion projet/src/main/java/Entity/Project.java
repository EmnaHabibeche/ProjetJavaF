package Entity;



import java.sql.Date;
import java.util.List;

public class Project {
    private String name;
     private String id;

     private int user_id;
    private java.sql.Date startDate;

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    private java.sql.Date endDate;
    private User owner;
    private List<User> contributors;
    private List<Task> tasks;

    public Project(String name, String id, User owner) {
        this.name = name;
        this.id = id;
        this.owner = owner;
        //this.tasks = new ArrayList<>();
    }

    public Project() {
        this.name=name;
    }


    public Project(String name, Date startDate, Date endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public java.sql.Date getEndDate() {
        return endDate;
    }


    public java.sql.Date getStartDate() {
        return startDate;
    }

    public void setEndDate(java.sql.Date endDate) {
        this.endDate=endDate;
    }

    public void setStartDate(java.sql.Date startDate) {
        this.startDate=startDate;
    }
}
