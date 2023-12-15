package org.example;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.ArrayList;

@XStreamAlias(value = "Book")
public class Book{
    private String title;
    private String publishedYear;
    private String numberOfPages;
    private ArrayList<String> authors;

    public Book() {

    }

    public Book(String title, String publishedYear, String numberOfPages, ArrayList<String> authors) {
        this.title = title;
        this.publishedYear = publishedYear;
        this.numberOfPages = numberOfPages;
        this.authors = authors;
    }
    public String getTitle(){
        return title;
    }
    public String getPublishedYear(){
        return publishedYear;
    }
    public String getNumberOfPages(){
        return numberOfPages;
    }
    public ArrayList<String> getAuthors(){
        return authors;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public void setPublishedYear(String year){
        this.publishedYear=year;
    }
    public void addAuthors(String authorName){
        this.authors.add(authorName);
    }
    public void setNumberOfPages(String num){
        this.numberOfPages=num;
    }
}
