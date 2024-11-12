package com.example.Task.Management.System.Mapper;

import com.example.Task.Management.System.Dto.Task.AddTaskDTO;
import com.example.Task.Management.System.Dto.Task.TaskDto;
import com.example.Task.Management.System.Dto.Task.UpdateTaskDTO;
import com.example.Task.Management.System.Entity.Task.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task toEntity(AddTaskDTO dto);

    Task toEntity(UpdateTaskDTO dto);

    List<TaskDto> toDtos(List<Task> entities);

    TaskDto toDto(Task task);
}
