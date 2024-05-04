package uz.pdp.springbootprojectprocesses.payload;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;


@Data
public class ClickAppsDto  {


    @NotNull
    private String  name;

    private UUID    iconID;
}
