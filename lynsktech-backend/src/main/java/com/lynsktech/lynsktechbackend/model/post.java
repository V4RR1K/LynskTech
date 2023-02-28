package com.lynsktech.api.lynsktechapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.*;
import java.util.Date;

/**
 * Post class represents a blog post entity
 *
 *
 * @author: Greg Lynskey
 */
public class Post {
    // properties of the Product
    static final String STRING_FORMAT =
            "Post[id=%d, postTitle=%s, date=%s, dateUpdated=%s, author=%s, mdFile=%s, headerImage=%s]";

    @JsonProperty("id")
    private int id;
    @JsonProperty("postTitle")
    private String postTitle;
    @JsonProperty("date")
    private String date;
    @JsonProperty("dateUpdated")
    private String dateUpdated;
    @JsonProperty("author")
    private String author;
    @JsonProperty("mdFile")
    private String mdFile;
    @JsonProperty("headerImage")
    private String headerImage;
    /**
     * Creates a product
     *
     * @param id          The id of the product
     * @param name        The name of the product
     * @param price       The price of the product
     * @param description The description of the product
     */
    public Post(
            @JsonProperty("id") int id,
            @JsonProperty("postTitle") String postTitle,
            @JsonProperty("date") String date,
            @JsonProperty("dateUpdated") String dateUpdated,
            @JsonProperty("author") String author,
            @JsonProperty("mdFile") String mdFile,
            @JsonProperty("headerImage") String headerImage) {
        this.id = id;
        this.postTitle = postTitle;
        this.date = date;
        this.dateUpdated = dateUpdated;
        this.author = author;
        this.mdFile = mdFile;
        this.headerImage = headerImage;

    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT,
                this.id, this.postTitle, this.date, this.dateUpdated, this.author, this.mdFile, this.headerImage);
    }
}
