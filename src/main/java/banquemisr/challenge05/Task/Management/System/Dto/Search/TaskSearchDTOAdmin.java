package banquemisr.challenge05.Task.Management.System.Dto.Search;

import banquemisr.challenge05.Task.Management.System.Entity.Task.Priority;
import banquemisr.challenge05.Task.Management.System.Entity.Task.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskSearchDTOAdmin {
    private Long userId; // User ID to identify the user
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
