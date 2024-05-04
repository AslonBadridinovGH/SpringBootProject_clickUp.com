package uz.pdp.springbootprojectprocesses.serviceInterface;

import uz.pdp.springbootprojectprocesses.payload.ApiResponse;
import uz.pdp.springbootprojectprocesses.payload.TaskDto;

public interface TaskService {
    ApiResponse addTask(TaskDto taskDto);
}
