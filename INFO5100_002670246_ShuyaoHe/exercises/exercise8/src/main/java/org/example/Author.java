package org.example;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("author")
public class Author {
    @XStreamAlias("author")
    private String name;

    public Author(String name) {
        this.name = name;
    }
}
