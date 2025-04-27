package br.com.lucas.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Books")
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, length = 80)
	private String author;

	@Column(nullable = false, length = 10)
	private String launchDate;

	@Column(nullable = false, length = 3)
	private String price;

	@Column(nullable = false, length = 30)
	private String title;

	public Book() {
		super();
	}

	public long getId() {return id;}

	public void setId(long id) {this.id = id;}

	public String getAuthor() {return author;}

	public void setAuthor(String author) {this.author = author;}

	public String getLaunchDate() {return launchDate;}

	public void setLaunchDate(String launchDate) {this.launchDate = launchDate;}

	public String getPrice() {return price;}

	public void setPrice(String price) {this.price = price;}

	public String getTitle() {return title;}

	public void setTitle(String title) {this.title = title;}

	@Override
	public int hashCode() {
		return Objects.hash(author, id, launchDate, price, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(author, other.author) && id == other.id && Objects.equals(launchDate, other.launchDate)
				&& Objects.equals(price, other.price) && Objects.equals(title, other.title);
	}

}
