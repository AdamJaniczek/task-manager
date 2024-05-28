package pl.com.itsystems.taskmanager;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> tasksToDo() {
        return taskRepository.findByStatusOrderByDueDateAsc(Status.TO_DO);
    }

    public List<Task> tasksInProgress() {
        return taskRepository.findByStatusOrderByDueDateAsc(Status.IN_PROGRESS);
    }

    public List<Task> tasksDone() {
        return taskRepository.findByStatusOrderByDueDateAsc(Status.DONE);
    }

    public List<Task> tasksFailed() {
        return taskRepository.findByDueDateBeforeAndStatusNotOrderByDueDateAsc(LocalDateTime.now(), Status.DONE);
    }

    public Optional<Task> findTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public void saveTask(Task task) {
        taskRepository.save(task);
    }
    public List<Task> searchTasks(String text) {
        return taskRepository.findTaskByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(text, text);
    }

    public Duration timeSpentOnTask(Task task) {
        Duration result = Duration.ZERO;
        if (task.getStatus().equals(Status.DONE)) {
            try {
                result = Duration.between(task.getCreationDate(), task.getCompletionDate());
            } catch (NullPointerException e) {
                return result;
            }
        }
        return result;
    }
}
