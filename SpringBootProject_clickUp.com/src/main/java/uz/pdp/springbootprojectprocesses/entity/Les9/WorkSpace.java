package uz.pdp.springbootprojectprocesses.entity.Les9;

import lombok.*;
import uz.pdp.springbootprojectprocesses.entity.les10_1.Attachment;
import uz.pdp.springbootprojectprocesses.entity.template.AbsLongEntity;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames ={"name","owner_id"})})
public class WorkSpace  extends AbsLongEntity {


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String  color;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private User owner;

    @Column(nullable = false)
    private String initialLetter;

    @OneToOne
    private Attachment avatar;


    @PrePersist
    @PreUpdate
    public void setInitialLetterMyMethod(){this.initialLetter=name.substring (0,1);}


    public WorkSpace(String name, String color, User owner, Attachment avatar) {
        this.name = name;
        this.color = color;
        this.owner = owner;
        this.avatar = avatar;
    }

}

