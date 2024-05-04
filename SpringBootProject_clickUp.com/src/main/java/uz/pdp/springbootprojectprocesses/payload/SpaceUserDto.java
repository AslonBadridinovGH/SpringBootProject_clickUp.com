package uz.pdp.springbootprojectprocesses.payload;
import lombok.Data;

import java.util.UUID;


@Data
public class SpaceUserDto {


    private UUID spaceID;

    private UUID memberID;

}
