package pl.com.itsystems.taskmanager;

import java.util.List;

public class TaskDao {
    private List<Task> tasksToDo;
    private List<Task> tasksInProgress;
    private List<Task> tasksDone;
    private List<Task> tasksDeadLine;

    public TaskDao(List<Task> tasksToDo, List<Task> tasksInProgress, List<Task> tasksDone, List<Task> tasksDeadLine) {
        this.tasksToDo = tasksToDo;
        this.tasksInProgress = tasksInProgress;
        this.tasksDone = tasksDone;
        this.tasksDeadLine = tasksDeadLine;
    }

    public TaskDao() {
    }

    public List<Task> getTasksToDo() {
        return tasksToDo;
    }

    public void setTasksToDo(List<Task> tasksToDo) {
        this.tasksToDo = tasksToDo;
    }

    public List<Task> getTasksInProgress() {
        return tasksInProgress;
    }

    public void setTasksInProgress(List<Task> tasksInProgress) {
        this.tasksInProgress = tasksInProgress;
    }

    public List<Task> getTasksDone() {
        return tasksDone;
    }

    public void setTasksDone(List<Task> tasksDone) {
        this.tasksDone = tasksDone;
    }

    public List<Task> getTasksDeadLine() {
        return tasksDeadLine;
    }

    public void setTasksDeadLine(List<Task> tasksDeadLine) {
        this.tasksDeadLine = tasksDeadLine;
    }
}
