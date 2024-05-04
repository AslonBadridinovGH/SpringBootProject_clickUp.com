package uz.pdp.springbootprojectprocesses.payload;

import lombok.Data;

import java.util.UUID;


@Data
public class TaskTagDto {

    private UUID taskID;

    private UUID tagID;

}
