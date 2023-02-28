package com.Lynsktech.lynsktechbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.*;
import java.util.Date;

/**
 * Post class represents a blog post entity
 *
 * @author Greg Lynskey
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
     * Creates a post entity
     * @param id            entity id
     * @param postTitle     entity title
     * @param date          entity date of initial creation
     * @param dateUpdated   entity date of update
     * @param author        entity author
     * @param mdFile        entity mdFile containing blog contents
     * @param headerImage   entity header image for preview
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMdFile() {
        return mdFile;
    }

    public void setMdFile(String mdFile) {
        this.mdFile = mdFile;
    }

    public String getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(String headerImage) {
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
