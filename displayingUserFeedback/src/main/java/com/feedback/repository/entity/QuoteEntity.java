package com.feedback.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "Feedback")
public class QuoteEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	int id;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "permalink")
	String permalink;
	@Column(name = "author")
	String author;
	@Column(name = "quote")
	String quote;
	/**
	 * @return the permalink
	 */
	public String getPermalink() {
		return permalink;
	}
	/**
	 * @param permalink the permalink to set
	 */
	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return the quote
	 */
	public String getQuote() {
		return quote;
	}
	/**
	 * @param quote the quote to set
	 */
	public void setQuote(String quote) {
		this.quote = quote;
	}
	@Override
	public String toString() {
		return "QuoteEntity [id=" + id + ", permalink=" + permalink + ", author=" + author + ", quote=" + quote
				+ "]";
	}
}
