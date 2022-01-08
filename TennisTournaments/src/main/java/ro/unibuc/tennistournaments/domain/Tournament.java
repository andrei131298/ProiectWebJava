package ro.unibuc.tennistournaments.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.unibuc.tennistournaments.enums.LevelType;
import ro.unibuc.tennistournaments.enums.TournamentType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tournaments")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "level_accepted")
    @Enumerated(EnumType.STRING)
    private LevelType levelAccepted;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "sign_in_start_date")
    private Date signInStartDate;

    @Column(name = "sign_in_end_date")
    private Date signInEndDate;

    @Column(name = "number_of_players")
    private Integer numberOfPlayers;

    @Column(name = "tournament_type")
    @Enumerated(EnumType.STRING)
    private TournamentType tournamentType;

    @Column(name = "category_id")
    private Long categoryId;

    @ManyToOne()
    @JoinColumn(name="category_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private Category category;

    @OneToOne(mappedBy = "tournament")
    @JsonIgnore
    private SignedInList signedInList;

    @OneToMany(targetEntity= PlayerPosition.class, mappedBy="tournament",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PlayerPosition> playerPositions = new ArrayList<>();
}
