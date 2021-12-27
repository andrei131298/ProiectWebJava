package ro.unibuc.springlab8example1.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "full_name")
    private String fullName;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinTable(name = "users_user_details",
            joinColumns = {
                    @JoinColumn(name = "users", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "user_details", referencedColumnName = "id")
            }
    )
    private UserDetails userDetails;

    @Column(name = "institution_id")
    private Long institutionId;

    @ManyToOne()
    @JoinColumn(name="institution_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Institution institution;

    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(name = "account_created")
    private LocalDateTime accountCreated;
}
