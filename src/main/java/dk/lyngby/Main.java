package dk.lyngby;

import dk.lyngby.config.HibernateConfig;
import dk.lyngby.dao.GuestDAO;
import dk.lyngby.model.Event;
import dk.lyngby.model.Guest;
import dk.lyngby.model.Ticket;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;

public class Main {

    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    public static void main(String[] args) {
        persistNew();

        try (var em = emf.createEntityManager()) {
            TypedQuery<Guest> query = em.createQuery("SELECT g FROM Guest g", Guest.class);
            query.getResultList().forEach(System.out::println);

        }


    }

    private static void persistNew() {
        Guest steve = Guest.builder()
                .email("steve@mail.com")
                .name("Steve")
                .surname("Jobs")
                .telephoneNumber("12345678")
                .age(56)
                .build();

        Guest frank = Guest.builder()
                .email("frank@mail.com")
                .name("Frank")
                .surname("Hansen")
                .telephoneNumber("123456789")
                .age(25)
                .build();

        Event roskildeFestival = new Event(
                "Roskilde Festival",
                LocalDate.of(2021, 6, 26),
                LocalDate.of(2021, 7, 3));

        Ticket ticket = new Ticket(750D, 1, Ticket.TicketType.VIP);
        Ticket ticket2 = new Ticket(70D, 1, Ticket.TicketType.STANDARD);

        ticket.setTicket(steve, roskildeFestival);
        ticket2.setTicket(frank, roskildeFestival);


        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(steve);
            em.persist(frank);
            em.persist(roskildeFestival);
            em.persist(ticket);
            em.persist(ticket2);
            em.getTransaction().commit();
        }
    }

    private static void persistEntities() {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();

            Guest steve = Guest.builder()
                    .email("steve@mail.com")
                    .name("Steve")
                    .surname("Jobs")
                    .telephoneNumber("12345678")
                    .age(56)
                    .build();

            Guest frank = Guest.builder()
                    .email("frank@mail.com")
                    .name("Frank")
                    .surname("Hansen")
                    .telephoneNumber("123456789")
                    .age(25)
                    .build();

            Event roskildeFestival = new Event(
                    "Roskilde Festival",
                    LocalDate.of(2021, 6, 26),
                    LocalDate.of(2021, 7, 3));


            em.getTransaction().commit();
        }
    }
}
