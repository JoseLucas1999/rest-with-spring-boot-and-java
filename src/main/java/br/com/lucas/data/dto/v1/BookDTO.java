package br.com.lucas.data.dto.v1;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import org.springframework.hateoas.RepresentationModel;

//apenas removemos as anotations e adicionamos o extends RepresentationModel
public class BookDTO extends RepresentationModel<BookDTO> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String author;
	private Date launchDate;
	private Double price;
	private String title;
	
	public BookDTO() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(author, id, launchDate, price, title);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookDTO other = (BookDTO) obj;
		return Objects.equals(author, other.author) && id == other.id && Objects.equals(launchDate, other.launchDate)
				&& Objects.equals(price, other.price) && Objects.equals(title, other.title);
	}
	
	
	/*
	 * a classe DTO (Data Transfer Object) como deve conter apenas
	 * os dados necessários para a transferência entre camadas 
	 * (geralmente entre Controller e Service, ou entre API e Cliente). 
	 * O DTO não deve conter regras de negócio nem dependências do JPA (como anotações @Entity).
	 */
	
	

}
