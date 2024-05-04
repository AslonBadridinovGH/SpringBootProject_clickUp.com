package uz.pdp.springbootprojectprocesses.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class TaskDto {


    @NotNull
    private String  name;

    private String  description;

    private UUID    statusID;

    private UUID    categoryID;

    private UUID    priorityID;

    private UUID    parentTaskID;

    private String  startedDate;
    private String  dueDate;
    private String  activeDate;
    private Long    estimateTime;
    private Boolean dueTimeHas;
    private Boolean startTimeHas;

}
