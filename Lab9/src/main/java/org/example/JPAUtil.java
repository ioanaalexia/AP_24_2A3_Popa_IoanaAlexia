package org.example;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import static jakarta.persistence.Persistence.createEntityManagerFactory;

public class JPAUtil {
    private static final EntityManagerFactory emf;

    static {
        try {
            emf = createEntityManagerFactory("BookManager");
        } catch (Throwable ex) {
            System.err.println("Initial EntityManagerFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    public static void close() {
        if (emf != null) {
            emf.close();
        }
    }
}

