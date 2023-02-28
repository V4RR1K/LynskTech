package com.lynsktech.api.lynsktechapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lynsktech.api.lynsktechapi.model.BlogPost;

/**
 * Implement the functionality for JSON file-based persistance for Products
 * 
 * @author isTeamTwo
 */
@Component
public class InventoryFileDAO implements InventoryDAO {
    Map<Integer, BlogPost> products;

    private ObjectMapper objectMapper;
    private static int nextId;
    private String filename;

    /**
     * Constructor
     * 
     * @param filename     The filename of the JSON file
     * @param objectMapper Provides JSON Object to/from Java Object serialization
     *                     and deserialization
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    public InventoryFileDAO(@Value("${products.file}") String filename, ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();
    }

    /**
     * Generates the next id for a new {@linkplain BlogPost product}
     * 
     * @return The next id
     */
    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    /**
     * Generates an array of {@linkplain BlogPost products} from the tree map
     * 
     * @return The array of {@link BlogPost products}, may be empty
     */
    private BlogPost[] getProductsArray(String containsText) { // if containsText == null, no filter
        ArrayList<BlogPost> productArrayList = new ArrayList<>();

        for (BlogPost product : products.values()) {
            if (containsText == null || product.getName().contains(containsText)) {
                productArrayList.add(product);
            }
        }

        BlogPost[] productArray = new BlogPost[productArrayList.size()];
        productArrayList.toArray(productArray);
        return productArray;
    }

    /**
     * Generates an array of {@linkplain BlogPost products} from the tree map
     * 
     * @return The array of {@link BlogPost products}, may be empty
     */
    private BlogPost[] getProductsArray() {
        return getProductsArray(null);
    }

    /**
     * Saves the {@linkplain BlogPost products} from the map into the file as an
     * array of JSON objects
     * 
     * @return true if the {@link BlogPost products} were written successfully
     * 
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        BlogPost[] productArray = getProductsArray();

        objectMapper.writeValue(new File(filename), productArray);
        return true;
    }

    /**
     * Loads {@linkplain BlogPost products} from the JSON file into the map
     * <br>
     * Also sets next id to one more than the greatest id found in the file
     * 
     * @return true if the file was read successfully
     * 
     * @throws IOException when file cannot be accessed or read from
     */

    private boolean load() throws IOException {
        products = new TreeMap<>();
        nextId = 0;

        BlogPost[] productArray = objectMapper.readValue(new File(filename), BlogPost[].class);

        for (BlogPost product : productArray) {
            products.put(product.getId(), product);
            if (product.getId() > nextId)
                // Make the next id one greater than the maximum from the file
                nextId = product.getId() + 1;
        }
        return true;
    }

    /**
     ** {@inheritDoc}
     */
    @Override
    public BlogPost[] getProducts() {
        synchronized (products) {
            return getProductsArray();
        }
    }

    /**
     ** {@inheritDoc}
     */
    @Override
    public BlogPost[] findProducts(String containsText) {
        synchronized (products) {
            return getProductsArray(containsText);
        }
    }

    /**
     ** {@inheritDoc}
     */
    @Override
    public BlogPost getProduct(int id) {
        synchronized (products) {
            if (products.containsKey(id))
                return products.get(id);
            else
                return null;
        }
    }

    /**
     ** {@inheritDoc}
     */
    @Override
    public BlogPost createProduct(BlogPost product) throws IOException {
        synchronized (products) {
            BlogPost newProduct = new BlogPost(nextId(), product.getName(), product.getPrice(), product.getDescription(),
                    product.getImageURL());
            products.put(newProduct.getId(), newProduct);
            save(); // may throw an IOException
            return newProduct;
        }
    }

    /**
     ** {@inheritDoc}
     */
    @Override
    public BlogPost updateProduct(BlogPost product) throws IOException {
        synchronized(products) {
            if (products.containsKey(product.getId()) == false)
                return null;  // hero does not exist

            products.put(product.getId(),product);
            save(); // may throw an IOException
            return product;
        }
    }

    /**
     ** {@inheritDoc}
     */
    @Override
    public boolean deleteProduct(int id) throws IOException {
        synchronized (products) {
            if (products.containsKey(id)) {
                products.remove(id);
                return save();
            } else
                return false;
        }
    }
}
