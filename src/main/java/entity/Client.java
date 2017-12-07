package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;

@Entity
@SequenceGenerator(name = "seq_client", sequenceName = "seq_client", initialValue = 1, allocationSize = 1)
public class Client {
	@Id
	@GeneratedValue(generator = "seq_client")
	private Long id;
	
	@Column
	@NotBlank
	private String lastname;
	
	@Column
	@NotBlank
	private String firstname;
	
	@Column
	@NotBlank
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@ManyToOne
	private Book favBook;
	
	@ManyToMany
	private List<Book> boughtBooks;

	// Constructor
	public Client(Long id, String lastname, String firstname, Gender gender, Book favBook) {
		this.id = id;
		this.lastname = lastname;
		this.firstname = firstname;
		this.gender = gender;
		this.favBook = favBook;
		boughtBooks = new ArrayList<>();
	}

	public Client(String lastname, String firstname, Gender gender) {
		this(null, lastname, firstname, gender, null);
	}
	
	public Client() { 
		boughtBooks = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", " + lastname + " " + firstname + ", gender=" + gender + ", favBook=" + favBook
				+ "]";
	}

	// Getters and Setters

	public Book getFavBook() {
		return favBook;
	}

	public void setFavBook(Book favBook) {
		this.favBook = favBook;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public List<Book> getBoughtBooks() {
		return boughtBooks;
	}

	public void setBoughtBooks(List<Book> boughtBooks) {
		this.boughtBooks = boughtBooks;
	}
	
	public void addBook (Book book) {
		this.boughtBooks.add(book);
	}

}
