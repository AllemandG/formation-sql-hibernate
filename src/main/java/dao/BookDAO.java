package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entity.Book;
import entity.Client;

public class BookDAO {
	private BookDAO() { }
	
	public static Book findBookBDD (Long id) {
		EntityManager em = DatabaseHelper.createEntityManager();
		Book retour = em.find(Book.class, id);
		em.close();
		return retour;
	}
	
	public static void addBookBDD (Book book) {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		em.persist(book);
		DatabaseHelper.commitTxAndClose(em);
	}
	
	public static void addBookListBDD (List<Book> books) {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		for (Book b : books) {
			em.persist(b);
		}
		DatabaseHelper.commitTxAndClose(em);
	}
	
	public static void update (Book book) {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		em.merge(book);
		DatabaseHelper.commitTxAndClose(em);
	}
	
	public static List<Client> getBuyers (Book book) {
		List<Client> buyers = book.getBuyers();
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		TypedQuery<Client> query = em.createQuery(
				"select distinct client "
				+ "from Book b "
				+ "inner join b.buyers client "
				+ "where b.id = :id", Client.class);
		query.setParameter("id", book.getId());
		buyers = query.getResultList();
		DatabaseHelper.commitTxAndClose(em);
		return buyers;
	}
	
	public static List<Book> getAllBoughtBooks () {
		List<Book> books = new ArrayList<>();
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		TypedQuery<Book> query = em.createQuery(
				"select distinct b "
				+ "from Book b "
				+ "inner join fetch b.buyers", Book.class);
		books = query.getResultList();
		DatabaseHelper.commitTxAndClose(em);
		return books;
	}
}
