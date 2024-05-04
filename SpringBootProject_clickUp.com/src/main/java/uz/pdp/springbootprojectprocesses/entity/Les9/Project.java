package uz.pdp.springbootprojectprocesses.entity.Les9;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.springbootprojectprocesses.entity.template.AbsUUIDEntity;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Project extends AbsUUIDEntity {


    @Column(nullable = false)
    private String  name;

    @ManyToOne
    private Space space;

    private String  accessType;

    private boolean archived;

    private String  color;
}
