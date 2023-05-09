package cat.itacademy.barcelonactiva.RoyoTerol.Marina.s05.t01.n02.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "Flowers")
public class Flower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "ID")
    private Integer id;

    @Column (name = "Name", nullable = false, length = 60, unique = true)
    private String flowerName;

    @Column (name = "Flower's country", nullable = false)
    private String flowerCountry;

}
