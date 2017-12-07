package main;

import java.util.List;

import dao.BookDAO;
import dao.ClientDAO;
import entity.Book;
import entity.Client;
import entity.MyFactory;

public class Main {

	public static void main(String[] args) {
		
		List<Client> clients = MyFactory.randomClientList(50);
		ClientDAO.addClientListBDD(clients);
		
		List<Book> books = MyFactory.randomBookList(12);
		BookDAO.addBookListBDD(books);
		
		for (int i = 0; i < clients.size(); i++) {
			for (int j = 0; j < randInt(1, books.size() - 1); j++) {
				ClientDAO.buyBook(books.get(j), clients.get(i));
			}
		}
		
		System.out.println("==================================");
		List<Book> boughts = clients.get(1).getBoughtBooks();
		System.out.println("Le client : " + clients.get(1) + " a acheté : ");
		for (Book b : boughts) {
			System.out.println(b);
		}
		
		List<Book> boughts2 = ClientDAO.getBoughtBooks(clients.get(1));
		System.out.println("Le client : " + clients.get(1) + " a acheté : ");
		for (Book b : boughts2) {
			System.out.println(b);
		}
		
		System.out.println("==================================");
		List<Client> buyers = books.get(1).getBuyers();
		System.out.println("Le livre : " + books.get(2) + " a été acheté par :");
		for (Client c : buyers) {
			System.out.println(c);
		}
		
		List<Client> buyers2 = BookDAO.getBuyers(books.get(1));
		System.out.println("Le livre : " + books.get(2) + " a été acheté par :");
		for (Client c : buyers2) {
			System.out.println(c);
		}
		
		System.out.println("==================================");
		List<Book> allBoughtsBooks = BookDAO.getAllBoughtBooks();
		System.out.println("Liste de tous les livres achetés : ");
		for (Book b : allBoughtsBooks) {
			System.out.println(b);
		}
	}

	private static int randInt(int min, int max) {
		return min + (int) (Math.random() * (max - min));
	}

}
