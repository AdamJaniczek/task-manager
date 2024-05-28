package pl.com.itsystems.taskmanager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;

@ResponseBody
public interface TaskRepository extends JpaRepository<Task, Long> {

    public List<Task> findByStatusOrderByDueDateAsc(Status status);
    public List<Task> findByDueDateBeforeAndStatusNotOrderByDueDateAsc(LocalDateTime localDateTime, Status status);
    public List<Task> findTaskByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);
}
