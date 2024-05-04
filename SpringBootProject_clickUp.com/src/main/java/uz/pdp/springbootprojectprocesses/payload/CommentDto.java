package uz.pdp.springbootprojectprocesses.payload;
import lombok.Data;

import java.util.UUID;


@Data
public class CommentDto {

    private String  name;

    private UUID    taskId;

}
