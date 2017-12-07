package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;

@Entity
@SequenceGenerator(name = "seq_book", sequenceName = "seq_book", initialValue = 1, allocationSize = 1)
public class Book {
	@Id
	@GeneratedValue(generator = "seq_book")
	private Long id;
	
	@Column
	@NotBlank
	private String title;
	
	@Column
	@NotBlank
	private String author;
	
	@ManyToMany(mappedBy = "boughtBooks")
	private List<Client> buyers;

	// Constructor
	public Book(Long id, String title, String author) {
		this.id = id;
		this.title = title;
		this.author = author;
		buyers = new ArrayList<>();
	}

	public Book(String title, String author) {
		this(null, title, author);
	}
	
	public Book () { 
		buyers = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + "]";
	}

	// Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public List<Client> getBuyers() {
		return buyers;
	}

	public void setBuyers(List<Client> buyers) {
		this.buyers = buyers;
	}
	
	public void addBuyer(Client client) {
		this.buyers.add(client);
	}

}
