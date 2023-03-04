package ua.com.foxminded.cardatabase.model;

import com.opencsv.bean.CsvBindByPosition;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cars")
public class Car {

    @Id
    @Column(name = "id")
    private String id;

    @CsvBindByPosition(position = 2)
    @Column(name = "year")
    private Integer year;

    @OneToOne
    @JoinColumn(name = "make_id")
    private Make make;

    @OneToOne
    @JoinColumn(name = "model_id")
    private Model model;

    @OneToOne
    @JoinColumn(name = "type_id")
    private Type type;
}
