package project.tennisscoreboard.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Players")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Name", unique = true, nullable = false)
    private String name;
}
