package uz.pdp.springbootprojectprocesses.payload;

import lombok.Data;
import uz.pdp.springbootprojectprocesses.entity.enums.Type;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class StatusDto  {

    @NotNull
    private String name;

    private UUID spaceID;

    private UUID projectID;

    private UUID categoryID;

    private String color;

    private Type type;

}
