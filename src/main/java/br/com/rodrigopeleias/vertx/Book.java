package br.com.rodrigopeleias.vertx;

import java.util.concurrent.atomic.AtomicInteger;

public class Book {

	private static final AtomicInteger COUNTER = new AtomicInteger();
	
	private final int id;
	
	private String name;
	
	private String author;
	
	private int numberOfPages;

	public Book(String name, String author, int numberOfPages) {
		this.id = COUNTER.getAndIncrement();
		this.name = name;
		this.author = author;
		this.numberOfPages = numberOfPages;
	}
	
	public Book() {
		this.id = COUNTER.getAndIncrement();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}
}
