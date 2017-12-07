package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entity.Book;
import entity.Client;

public class ClientDAO {
	private ClientDAO () {}
	
	public static Client findClientBDD (Long id) {
		EntityManager em = DatabaseHelper.createEntityManager();
		Client retour = em.find(Client.class, id);
		em.close();
		return retour;
	}
	
	public static void addClientBDD (Client client) {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		em.persist(client);
		DatabaseHelper.commitTxAndClose(em);
	}
	
	public static void addClientListBDD (List<Client> clients) {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		for (Client c : clients) {
			em.persist(c);
		}
		DatabaseHelper.commitTxAndClose(em);
	}
	
	public static void updateClientBDD (Client client) {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		em.merge(client);
		DatabaseHelper.commitTxAndClose(em);
	}
	
	public static List<Book> getBoughtBooks (Client client) {
		List<Book> books = client.getBoughtBooks();
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		TypedQuery<Book> query = em.createQuery(
				"select distinct book "
				+ "from Client c "
				+ "inner join c.boughtBooks book "
				+ "where c.id = :id", Book.class);
		query.setParameter("id", client.getId());
		books = query.getResultList();
		DatabaseHelper.commitTxAndClose(em);
		return books;
	}
	
	public static void buyBook (Book book, Client client) {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		client.addBook(book);
		book.addBuyer(client);
		em.merge(client);
		em.merge(book);
		DatabaseHelper.commitTxAndClose(em);
	}
}
