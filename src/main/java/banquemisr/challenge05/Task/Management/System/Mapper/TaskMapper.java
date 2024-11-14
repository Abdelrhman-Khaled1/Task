package banquemisr.challenge05.Task.Management.System.Mapper;

import banquemisr.challenge05.Task.Management.System.Dto.Task.AddTaskDTO;
import banquemisr.challenge05.Task.Management.System.Dto.Task.TaskDto;
import banquemisr.challenge05.Task.Management.System.Dto.Task.UpdateTaskDTO;
import banquemisr.challenge05.Task.Management.System.Entity.Task.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task toEntity(AddTaskDTO dto);

    Task toEntity(UpdateTaskDTO dto);

    List<TaskDto> toDtos(List<Task> entities);

    TaskDto toDto(Task task);
}
