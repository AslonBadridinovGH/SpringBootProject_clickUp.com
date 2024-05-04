package uz.pdp.springbootprojectprocesses.entity.les10_1;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.springbootprojectprocesses.entity.template.AbsUUIDEntity;
import uz.pdp.springbootprojectprocesses.entity.Les9.Project;
import uz.pdp.springbootprojectprocesses.entity.enums.Type;
import uz.pdp.springbootprojectprocesses.entity.Les9.Space;


import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Status extends AbsUUIDEntity {



    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Space space;

    @ManyToOne
    private Project project;

    @ManyToOne
    private Category category;

    private String color;

    @Enumerated(value = EnumType.STRING)
    private Type type;

}
