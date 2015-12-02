/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Grant
 */
public class LibraryTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.byui_tra12_jar_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();

        LibraryTester lt = new LibraryTester();
        lt.showAuthorsWithBooks(em);
        lt.createNewBook(em, "J.K. Rowling", "Harry Potter and The Goblet of Fire");
        lt.showAuthorsWithBooks(em);

        em.close();
    }

    public void showAuthorsWithBooks(EntityManager em) {
        Query query = em.createQuery("SELECT a FROM Author a");
        List<Author> authors = query.getResultList();

        for (Author author : authors) {
            System.out.println(author.getName());

            for (Book book : author.getBooks()) {
                System.out.println("\tBook: " + book.getTitle());
            }
        }
        
        System.out.println("***********************************");
    }

    public void createNewBook(EntityManager em, String authorName, String bookTitle) {
        em.getTransaction().begin();
        
        Author newAuthor = new Author();
        newAuthor.setName(authorName);
        
        em.persist(newAuthor);
        
        Book newBook = new Book();
        newBook.setTitle(bookTitle);
        
        newBook.setAuthor(newAuthor);
        em.persist(newBook);

        em.getTransaction().commit();
        
        em.refresh(newAuthor);
    }
}
