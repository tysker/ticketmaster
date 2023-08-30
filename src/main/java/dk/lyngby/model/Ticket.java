package dk.lyngby.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@Table(name = "ticket")
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Enumerated(EnumType.STRING)
    private TicketType ticketType;

//    @Id
//    @MapsId
    @ManyToOne
    private Guest guest;

//    @Id
//    @MapsId
    @ManyToOne
    private Event event;

    public Ticket(double price, int quantity, TicketType ticketType) {
        this.price = price;
        this.quantity = quantity;
        this.ticketType = ticketType;
    }

    public void setTicket(Guest guest, Event event) {
        this.guest = guest;
        this.event = event;
        guest.addTicket(this);
        event.addTicket(this);
    }

    public enum TicketType {
        VIP, STANDARD
    }
}
