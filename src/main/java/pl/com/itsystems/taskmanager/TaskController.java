package pl.com.itsystems.taskmanager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String home(Model model) {
        TaskDto tasks = taskService.prepareTaskAggregate();
        model.addAttribute("tasks", tasks);
        model.addAttribute("task", new Task());
        return "home";
    }

    @GetMapping("/search")
    public String searchTask(String title, Model model) {
        List<Task> result = taskService.searchTasks(title);
        model.addAttribute("task", new Task());
        model.addAttribute("tasks", result);
        return "search-result.html";
    }

    @GetMapping("/edit")
    public String editTask(long id, Model model) {
        Optional<Task> task = taskService.findTaskById(id);
        task.ifPresent(value -> model.addAttribute("task", value));
        return "task";
    }

    @GetMapping("/add-task")
    public String addTask(Model model,Task task) {
        model.addAttribute("task", new Task());
        return "task";
    }

    @PostMapping("/upsert")
    public String saveTask(Task task) {
        taskService.saveTask(task);
        return "redirect:/";
    }
}
