package uz.pdp.springbootprojectprocesses.payload;

import lombok.Data;

import java.util.UUID;


@Data
public class IconDto  {

    private UUID attachmentID;

    private String color;

    private String initialLetter;

    private UUID iconID;
}
