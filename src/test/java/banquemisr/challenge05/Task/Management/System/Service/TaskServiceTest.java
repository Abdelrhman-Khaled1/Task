package banquemisr.challenge05.Task.Management.System.Service;

import banquemisr.challenge05.Task.Management.System.Dto.Task.AddTaskDTO;
import banquemisr.challenge05.Task.Management.System.Dto.Task.TaskDto;
import banquemisr.challenge05.Task.Management.System.Dto.Task.UpdateTaskDTO;
import banquemisr.challenge05.Task.Management.System.Entity.Task.Priority;
import banquemisr.challenge05.Task.Management.System.Entity.Task.Status;
import banquemisr.challenge05.Task.Management.System.Entity.Task.Task;
import banquemisr.challenge05.Task.Management.System.Entity.User.User;
import banquemisr.challenge05.Task.Management.System.Exception.TaskNotFoundException;
import banquemisr.challenge05.Task.Management.System.Exception.UserMisMatchException;
import banquemisr.challenge05.Task.Management.System.Mapper.TaskMapper;
import banquemisr.challenge05.Task.Management.System.Repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    @Spy
    private TaskService taskService;

    private User user;
    private Task task;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");

        task = new Task();
        task.setId(1L);
        task.setUser(user);

        doReturn(user).when(taskService).getLoggedInUser();
    }

    @Test
    void testSaveTask() {
        AddTaskDTO addTaskDTO = new AddTaskDTO();
        TaskDto expectedDto = new TaskDto();

        when(taskMapper.toEntity(addTaskDTO)).thenReturn(task);
        when(taskRepository.save(task)).thenReturn(task);
        when(taskMapper.toDto(task)).thenReturn(expectedDto);


        TaskDto result = taskService.saveTask(addTaskDTO);

        assertEquals(expectedDto, result);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void testGetTaskById_TaskNotFound() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.getTaskById(1L));
    }

    @Test
    void testGetTaskById_UserMismatch() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        User anotherUser = new User();
        anotherUser.setId(2L);

        doReturn(anotherUser).when(taskService).getLoggedInUser();

        assertThrows(UserMisMatchException.class, () -> taskService.getTaskById(1L));
    }

    @Test
    void testUpdateTask() {
        UpdateTaskDTO updateTaskDTO = new UpdateTaskDTO();
        Task updatedTask = new Task();
        updatedTask.setId(1L);
        updatedTask.setUser(user);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskMapper.toEntity(updateTaskDTO)).thenReturn(updatedTask);
        when(taskRepository.save(updatedTask)).thenReturn(updatedTask);
        when(taskMapper.toDto(updatedTask)).thenReturn(new TaskDto());

        TaskDto result = taskService.updateTask(1L, updateTaskDTO);

        assertNotNull(result);
        verify(taskRepository).save(updatedTask);
    }

    @Test
    void testDeleteTask_UserMismatch() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        User anotherUser = new User();
        anotherUser.setId(2L);

        doReturn(anotherUser).when(taskService).getLoggedInUser();

        assertThrows(UserMisMatchException.class, () -> taskService.deleteTask(1L));
    }

    @Test
    void testChangeStatus() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(task)).thenReturn(task);
        when(taskMapper.toDto(task)).thenReturn(new TaskDto());

        TaskDto result = taskService.changeStatus(1L, "IN_PROGRESS");

        assertNotNull(result);
        assertEquals(Status.IN_PROGRESS, task.getStatus());
        verify(taskRepository).save(task);
    }

    @Test
    void testChangePriority() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(task)).thenReturn(task);
        when(taskMapper.toDto(task)).thenReturn(new TaskDto());

        TaskDto result = taskService.changePriority(1L, "HIGH");

        assertNotNull(result);
        assertEquals(Priority.HIGH, task.getPriority());
        verify(taskRepository).save(task);
    }

    @Test
    void deleteTask_ShouldThrowException_WhenIdIsZero() {
        Long invalidId = 0L;

        assertThrows(IllegalArgumentException.class, () -> taskService.deleteTask(invalidId),
                "ID must be greater than 0");
    }
}
