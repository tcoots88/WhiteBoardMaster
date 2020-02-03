package Models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class ApplicationUser implements UserDetails {


    /*
                                INSTANCE VARIABLES
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "applicationUser")
    private List<Diagram> diagrams;

    private String userName;
    private String password;
    private String firstName;
    private String lastName;


    /*
                                CONSTRUCTORS
    */
    public ApplicationUser() {}

    public ApplicationUser(List<Diagram> diagrams, String userName, String password, String firstName, String lastName) {
        this.diagrams = diagrams;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    /*
                                GETTERS
    */
    public long getId() {
        return id;
    }

    public List<Diagram> getDiagrams() {
        return diagrams;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    /*
                                SETTERS
    */
    public void setDiagrams(List<Diagram> diagrams) {
        this.diagrams = diagrams;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    /*
                                MUTATORS
    */

    public void addDiagram(Diagram diagram){
        diagrams.add(diagram);
    }


    /*
                                UserDetails IMPLEMENTATION
    */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
