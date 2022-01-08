package ro.unibuc.tennistournaments.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "signed_in_players")
public class SignedInPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "signed_in_list_id")
    private Long signedInListId;

    @ManyToOne()
    @JoinColumn(name="signed_in_list_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private SignedInList signedInList;

    @Column(name = "player_id")
    private Long playerId;

    @ManyToOne()
    @JoinColumn(name="player_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private Player player;
}
