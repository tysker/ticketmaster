package dk.lyngby.dao;

import dk.lyngby.model.Guest;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class GuestDAO {

    private static EntityManagerFactory emf;
    private static GuestDAO instance;

    public static GuestDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new GuestDAO();
        }
        return instance;
    }

    public void createGuest(Guest guest) {
        try(var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(guest);
            em.getTransaction().commit();
        }
    }

    public List<Guest> getAllGuests(String email){
        try(var em = emf.createEntityManager()) {
            TypedQuery<Guest> namedQuery = em.createNamedQuery("Guest.findAll", Guest.class);
            namedQuery.setParameter(email, "email");
            return namedQuery.getResultList();
        }
    }
}
