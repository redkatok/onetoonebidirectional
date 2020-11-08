package app.core.model.owner_relation;


import app.core.model.inversend_relation.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id",referencedColumnName = "id")
    private Car car;

    public Person(String name) {
        this.name = name;
    }



}
