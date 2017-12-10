package avile.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account extends AbstractPersistable<Long> {

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @Length(max = 100)
    private String firstname;

    @Length(max = 100)
    private String lastname;

    @NotBlank
    @Length(min = 6, max = 200)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Recommendation> checkedRecommendations = new ArrayList<>();

    @OneToMany(mappedBy = "creator")
    private List<Recommendation> createdRecommendations = new ArrayList<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Recommendation> getCheckedRecommendations() {
        return checkedRecommendations;
    }

    public void setCheckedRecommendations(List<Recommendation> checkedRecommendations) {
        this.checkedRecommendations = checkedRecommendations;
    }

    public List<Recommendation> getCreatedRecommendations() {
        return createdRecommendations;
    }

    public void setCreatedRecommendations(List<Recommendation> createdRecommendations) {
        this.createdRecommendations = createdRecommendations;
    }
}
