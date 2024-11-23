package u.utd.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String opponents;

    @Column(nullable = false)
    private String ha;

    @Column(nullable = false)
    private String result;

    @Column(nullable = false)
    private String scorers;

    @Column(nullable = false)
    private String attendance;

    @Column(nullable = false)
    private String position;
}
