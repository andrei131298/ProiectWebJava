package ro.unibuc.tennistournaments.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.unibuc.tennistournaments.enums.LevelType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "level")
    @Enumerated(EnumType.STRING)
    private LevelType level;

    @Column(name = "pocket_budget")
    private Integer pocketBudget;

    @Column(name = "points")
    private Integer points;

    @OneToMany(targetEntity= SignedInPlayer.class, mappedBy="player",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SignedInPlayer> signedInPlayer = new ArrayList<>();

    @OneToMany(targetEntity= PlayerPosition.class, mappedBy="player",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PlayerPosition> playerPositions = new ArrayList<>();
}
