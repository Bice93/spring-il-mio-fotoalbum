package com.corsojava.fotoalbum.model;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="photos")
public class Photo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull 
	@NotEmpty(message="Il campo 'Title' non può essere vuoto")
	@Size(min=5, max=250)
	private String title;
	
	@NotNull
	@NotEmpty(message="Il campo 'Description' non può essere vuoto")
	@Size(min=5, max=250)
	private String description;
	
	@NotNull 
	@NotEmpty(message="Il campo 'Url' non può essere vuoto")
	@Size(min=5, max=250)
	private String url;
	
	@NotNull
	@NotEmpty(message="Il campo 'Tag' non può essere vuoto")
	@Size(min=3, max=250)
	private String tag;
	
	@Column(columnDefinition = "boolean default false")
	private Boolean isVisible;
	
	@OneToMany(mappedBy = "photo")
	private List<Comment> comments;
	
	@ManyToMany
	private List<Category> categories;
	
	//Getter-Setter
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Boolean getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Boolean isVisible) {
		this.isVisible = isVisible;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
}
