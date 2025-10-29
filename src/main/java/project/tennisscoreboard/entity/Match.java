package project.tennisscoreboard.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Matches")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Match {

    public Match(Player player1, Player player2, Player winner) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "Player1", referencedColumnName = "id")
    private Player player1;

    @ManyToOne
    @JoinColumn(name = "Player2", referencedColumnName = "id")
    private Player player2;

    @ManyToOne
    @JoinColumn(name = "Winner", referencedColumnName = "id")
    private Player winner;
}
