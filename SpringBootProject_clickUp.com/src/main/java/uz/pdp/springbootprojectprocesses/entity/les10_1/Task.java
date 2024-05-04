package uz.pdp.springbootprojectprocesses.entity.les10_1;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.springbootprojectprocesses.entity.template.AbsUUIDEntity;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Task extends AbsUUIDEntity {


    @Column(nullable = false)
    private String name;

    private String description;

    @OneToOne
    private Status status;

    @OneToOne
    private Category category;

    @OneToOne
    private Priority priority;

    @ManyToOne
    private Task parentTask;

    private LocalDate startedDate;
    private LocalDate activeDate;
    private LocalDate dueDate;
    private Long estimateTime;
    private Boolean startTimeHas;
    private Boolean dueTimeHas;

}
