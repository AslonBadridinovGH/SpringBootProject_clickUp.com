package uz.pdp.springbootprojectprocesses.payload;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class CheckList_ItemDto {   // CheckListItem


    @NotNull
    private String name;

    private UUID checkListID;

    private boolean  resolved;

    private UUID assignedUserID;

}