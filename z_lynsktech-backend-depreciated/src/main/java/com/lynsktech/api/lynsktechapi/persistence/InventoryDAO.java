package com.lynsktech.api.lynsktechapi.persistence;

import java.io.IOException;
import com.lynsktech.api.lynsktechapi.model.BlogPost;

/**
 * InventoryDAO is an interface for Product object persistance
 * 
 * @author: itsTeamTwo
 */
public interface InventoryDAO {

    /**
     * Retrieves all {@linkplain BlogPost products}
     * 
     * @return An array of {@link BlogPost products} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    BlogPost[] getProducts() throws IOException;

    /**
     * Finds all {@linkplain BlogPost products} whose name contains the given text
     * 
     * @param containsText The text to match against
     * 
     * @return An array of {@link BlogPost products} whose nemes contains the given
     *         text, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    BlogPost[] findProducts(String containsText) throws IOException;

    /**
     * Retrieves a {@linkplain BlogPost product} with the given id
     * 
     * @param id The id of the {@link BlogPost product} to get
     * 
     * @return a {@link BlogPost product} object with the matching id
     *         <br>
     *         null if no {@link BlogPost product} with a matching id is found
     * 
     * @throws IOException if an issue with underlying storage
     */
    BlogPost getProduct(int id) throws IOException;

    /**
     * Creates and saves a {@linkplain BlogPost product}
     * 
     * @param product {@linkplain BlogPost product} object to be created and saved
     *                <br>
     *                The id of the product object is ignored and a new uniqe id is
     *                assigned
     *                <br>
     *                The name of the product object is compared to existing
     *                products to ensure it is unique
     *
     * @return new {@link BlogPost product} if successful, false otherwise
     * 
     * @throws IOException if an issue with underlying storage
     */
    BlogPost createProduct(BlogPost product) throws IOException;

    /**
     * Updates and saves a {@linkplain BlogPost}
     * 
     * @param {@link BlogPost product} object to be updated and saved
     * 
     * @return updated {@link BlogPost product} if successful, null if
     *         {@link BlogPost product} could not be found
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    BlogPost updateProduct(BlogPost product) throws IOException;

    /**
     * Deletes a {@linkplain BlogPost product} with the given id
     * 
     * @param id The id of the {@link BlogPost product}
     * 
     * @return true if the {@link BlogPost product} was deleted
     *         <br>
     *         false if product with the given id does not exist
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    boolean deleteProduct(int id) throws IOException;

}
