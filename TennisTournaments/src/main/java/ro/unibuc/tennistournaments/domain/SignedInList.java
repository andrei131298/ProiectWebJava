package ro.unibuc.tennistournaments.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "signed_in_lists")
public class SignedInList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tournament_id")
    private Long tournamentId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tournament_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private Tournament tournament;

    @OneToMany(targetEntity= SignedInPlayer.class, mappedBy="signedInList",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SignedInPlayer> signedInPlayer = new ArrayList<>();
}
