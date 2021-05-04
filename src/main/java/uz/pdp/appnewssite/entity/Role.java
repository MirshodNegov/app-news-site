package uz.pdp.appnewssite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appnewssite.entity.enums.Huquq;
import uz.pdp.appnewssite.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role extends AbsEntity {

    @Column(unique = true,nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<Huquq> huquqList;

    @Column(length = 500)
    private String description;

}
