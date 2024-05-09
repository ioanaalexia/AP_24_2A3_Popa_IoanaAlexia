package org.example.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.example.entities.BookEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BookRepository {
    private EntityManagerFactory emf;

    public BookRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void createOrUpdate(BookEntity book) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if (book.getId() == null) {
                em.persist(book);
            } else {
                em.merge(book);
            }
            em.getTransaction().commit();
        } finally {
            if (em.isOpen()) em.close();
        }
    }


    public BookEntity findById(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(BookEntity.class, id);
        } finally {
            if (em.isOpen()) em.close();
        }
    }

    public List<BookEntity> findByTitle(String title) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<BookEntity> query = em.createNamedQuery("BookEntity.findByTitle", BookEntity.class);
            query.setParameter("title", "%" + title + "%");
            return query.getResultList();
        } finally {
            if (em.isOpen()) em.close();
        }
    }
}
