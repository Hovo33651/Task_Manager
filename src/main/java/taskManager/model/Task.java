package taskManager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import taskManager.typeStatus.TaskStatus;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {
    private long id;
    private String description;
    private Date deadline;
    private TaskStatus taskStatus;
    private User user;
}