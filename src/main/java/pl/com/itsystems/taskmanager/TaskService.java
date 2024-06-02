package pl.com.itsystems.taskmanager;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> tasksToDo() {
        return taskRepository.findByStatusOrderByDueDateAsc(Status.TO_DO);
    }

    public List<Task> findInProgressTasks() {
        return taskRepository.findByStatusOrderByDueDateAsc(Status.IN_PROGRESS);
    }

    public List<Task> findDoneTasks() {
        return taskRepository.findByStatusOrderByDueDateAsc(Status.DONE);
    }

    public List<Task> findFailedTasks() {
        return taskRepository.findByDueDateBeforeAndStatusNotOrderByDueDateAsc(LocalDateTime.now(), Status.DONE);
    }

    public Optional<Task> findTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public void saveTask(Task task) {
        if (task.getStatus().equals(null)) {
            task.setStatus(Status.TO_DO);
        }
        taskRepository.save(task);
    }

    public List<Task> searchTasks(String text) {
        return taskRepository.findTaskByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(text, text);
    }

    public TaskDto prepareTaskAggregate() {
        List<Task> tasksToDo = tasksToDo();
        List<Task> tasksDone = findDoneTasks();
        List<Task> tasksInProgress = findInProgressTasks();
        List<Task> tasksDeadLine = findFailedTasks();
        TaskDto tasks = new TaskDto();
        tasks.setTasksToDo(tasksToDo);
        tasks.setTasksDeadLine(tasksDeadLine);
        tasks.setTasksDone(tasksDone);
        tasks.setTasksInProgress(tasksInProgress);
        return tasks;
    }
}
