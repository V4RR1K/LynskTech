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
public class BlogPost {
    // properties of the Product
    static final String STRING_FORMAT = "Product[id=%d, name=%s, price=%f, description=%s, imageURL=%s]";

    @JsonProperty("id")
    private int id;
    @JsonProperty("postTitle")
    private String postTitle;
    @JsonProperty("date")
    private Date date;
    @JsonProperty("dateUpdated")
    private Date dateUpdated;
    @JsonProperty("author")
    private String author;

    /**
     * Creates a product
     * 
     * @param id          The id of the product
     * @param name        The name of the product
     * @param price       The price of the product
     * @param description The description of the product
     */
    public BlogPost(
            @JsonProperty("id") int id,
            @JsonProperty("postTitle") String postTitle,
            @JsonProperty("date") Date date,
            @JsonProperty("dateUpdated") Date dateUpdated,
            @JsonProperty("author") String author) {
        this.id = id;
        this.postTitle = postTitle;
        this.date = date;
        
    }

    /**
     * Gets the product id
     * 
     * @return product id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Gets the product name
     * 
     * @return product name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the product price
     * 
     * @return product price
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Gets the product description
     * 
     * @return product description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the product name
     * 
     * @param name The product name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the product price
     * 
     * @param price The product price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the product description
     * 
     * @param description The product description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the product image URL
     * 
     * @return product image URL
     */
    public String getImageURL() {
        return this.imageURL;
    }

    /**
     * Sets the product image URL
     * 
     * @param imageURL The product image URL
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT, this.id, this.name, this.price, this.description, this.imageURL);
    }
}
