package dk.lyngby.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@ToString
@NoArgsConstructor
@Table(name = "guest")
@Entity
@NamedQueries({
        @NamedQuery(name = "Guest.findAll", query = "SELECT g FROM Guest g"),
        @NamedQuery(name = "Guest.deleteAllRows", query = "DELETE FROM Guest"),
}
)
public class Guest {

    @Id
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "telephone-number", unique = true)
    private String telephoneNumber;

    @Column(name = "age", nullable = false)
    private int age;

    @OneToMany(mappedBy = "guest",fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Ticket> ticketSet = new HashSet<>();

    public void setTicketSet(Set<Ticket> ticketSet) {
        this.ticketSet = ticketSet;
    }

    @Builder
    public Guest(String email, String name, String surname, String telephoneNumber, int age) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.telephoneNumber = telephoneNumber;
        this.age = age;
    }

    public void addTicket(Ticket ticket) {
        if (ticket != null) {
            ticketSet.add(ticket);
        }
    }


}
