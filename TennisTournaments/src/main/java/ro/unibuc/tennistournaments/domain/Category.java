package ro.unibuc.tennistournaments.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.unibuc.tennistournaments.enums.TournamentCategory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tournament_category")
    @Enumerated(EnumType.STRING)
    private TournamentCategory tournamentCategory;

    @Column(name = "prize_money")
    private Integer prizeMoney;

    @Column(name = "tax")
    private Integer tax;

    @OneToMany(targetEntity=Tournament.class, mappedBy="tournamentType",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Tournament> tournamentList = new ArrayList<>();
}
