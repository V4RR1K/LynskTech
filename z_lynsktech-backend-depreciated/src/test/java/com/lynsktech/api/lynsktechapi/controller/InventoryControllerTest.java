package com.lynsktech.api.lynsktechapi.controller;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lynsktech.api.lynsktechapi.model.BlogPost;
import com.lynsktech.api.lynsktechapi.persistence.InventoryDAO;

/**
 * Test the Inventory Controller class
 * 
 * @author itsTeamTwo
 */

@Tag("Controller-tier")

public class InventoryControllerTest {
    private InventoryDAO mockInventoryDAO;
    private InventoryController inventoryController;

    @BeforeEach
    public void setupInventoryFileDAO() {
        mockInventoryDAO = mock(InventoryDAO.class);
        inventoryController = new InventoryController(mockInventoryDAO);
    }

    /**
     * Unit testing for testing getProduct - Getting a single product
     * 
     * @throws IOException
     */
    @Test
    public void testGetProduct() throws IOException { // getProduct may throw IOException
        // Setup
        BlogPost product = new BlogPost(99, "perfume", 34.99, "Dior Perfume", "a url");
        // When the same id is passed in, our mock Product DAO will return the Product
        // object
        when(mockInventoryDAO.getProduct(product.getId())).thenReturn(product);

        // Invoke
        ResponseEntity<BlogPost> response = inventoryController.getProduct(product.getId());

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    public void testGetProductNotFound() throws Exception { // createProduct may throw IOException
        // Setup
        int productId = 99;
        // When the same id is passed in, our mock Product DAO will return null,
        // simulating no product found
        when(mockInventoryDAO.getProduct(productId)).thenReturn(null);

        // Invoke
        ResponseEntity<BlogPost> response = inventoryController.getProduct(productId);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetProductHandleException() throws Exception { // createProduct may throw IOException
        // Setup
        int productId = 99;
        // When getProduct is called on the Mock Product DAO, throw an IOException
        doThrow(new IOException()).when(mockInventoryDAO).getProduct(productId);

        // Invoke
        ResponseEntity<BlogPost> response = inventoryController.getProduct(productId);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testCreateProduct() throws IOException { // createProduct may throw IOException
        // Setup
        BlogPost product = new BlogPost(99, "Whiskey", 54.99, "1997 Whiskey", "a url");

        // when createProduct is called, return true simulating successful
        // creation and save
        when(mockInventoryDAO.createProduct(product)).thenReturn(product);

        // Invoke
        ResponseEntity<BlogPost> response = inventoryController.createProduct(product);

        // Analyze
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    public void testCreateProductFailed() throws IOException { // createProduct may throw IOException
        // Setup
        BlogPost product = new BlogPost(99, "coffe beans", 34.99, "Beans from Italy", "a url");

        // when createProduct is called, return false simulating failed
        // creation and save
        when(mockInventoryDAO.createProduct(product)).thenReturn(null);

        // Invoke
        ResponseEntity<BlogPost> response = inventoryController.createProduct(product);

        // Analyze
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testCreateProductHandleException() throws IOException { // createProduct may throw IOException
        // Setup
        BlogPost product = new BlogPost(99, "perfume", 34.99, "Gucci Perfume", "a url");

        // When createProduct is called on the Mock Product DAO, throw an IOException
        doThrow(new IOException()).when(mockInventoryDAO).createProduct(product);

        // Invoke
        ResponseEntity<BlogPost> response = inventoryController.createProduct(product);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testUpdateProducts() throws IOException {
        BlogPost product = new BlogPost(99, "perfume", 34.99, "Gucci Perfume", "a url");

        when(mockInventoryDAO.updateProduct(product)).thenReturn(product);
        ResponseEntity<BlogPost> response = inventoryController.updateProduct(product);
        product.setName("perfume updated");

        response = inventoryController.updateProduct(product);

        assertEquals(HttpStatus.OK, response.getStatusCode()); 
        assertEquals(product, response.getBody());

    }

    @Test
    public void testUpdateProductFailed() throws IOException {
        BlogPost product = new BlogPost(99, "perfume", 34.99, "Gucci Perfume" , "a url");

        when(mockInventoryDAO.updateProduct(product)).thenReturn(null);

        when(mockInventoryDAO.updateProduct(product)).thenReturn(null);
        ResponseEntity<BlogPost> response = inventoryController.updateProduct(product);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void testUpdateProductHandleException() throws IOException {
        BlogPost product = new BlogPost(99, "perfume", 34.99, "Gucci Perfume", "a url");

        doThrow(new IOException()).when(mockInventoryDAO).updateProduct(product);
        ResponseEntity<BlogPost> response = inventoryController.updateProduct(product);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

    }

    @Test
    public void testGetProducts() throws IOException { // May throw IOException
        ResponseEntity<BlogPost[]> response = inventoryController.getProducts();
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        // Setup
        BlogPost[] products = new BlogPost[3];
        products[0] = new BlogPost(99, "perfume", 34.99, "Gucci Perfume", "a url");
        products[1] = new BlogPost(100, "perfume again", 44.99, "Hermes Perfume", "a url");
        products[2] = new BlogPost(101, "perfume again, again", 44.99, "Dior Perfume", "a url");

        // When getProducts is called return the products created
        when(mockInventoryDAO.getProducts()).thenReturn(products);

        // Invoke
        response = inventoryController.getProducts();

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
    }

    @Test
    public void testGetHeroesHandleException() throws IOException {
        // Setup
        // When getHeroes is called on the Mock Hero DAO, throw an IOException
        doThrow(new IOException()).when(mockInventoryDAO).getProducts();

        // Invoke
        ResponseEntity<BlogPost[]> response = inventoryController.getProducts();

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testSearchProducts() throws IOException {
        // Setup
        String searchStr = "fume";
        BlogPost[] products = new BlogPost[3];
        products[0] = new BlogPost(99, "perfume", 34.99, "Gucci Perfume", "a url");
        products[1] = new BlogPost(100, "perfume again", 44.99, "Hermes Perfume", "a url");
        products[2] = new BlogPost(101, "perfume again, again", 44.99, "Dior Perfume", "a url");

        // Try to search
        when(mockInventoryDAO.findProducts(searchStr)).thenReturn(products);

        // Invoke
        ResponseEntity<BlogPost[]> response = inventoryController.searchProduct(searchStr);

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());

        // Invoke
        searchStr = "zz";
        response = inventoryController.searchProduct(searchStr);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testSearchProductsHandleException() throws IOException {
        // Setup
        String searchStr = "zz";

        // Throw IOE when find product is called on mock inventory dao
        doThrow(new IOException()).when(mockInventoryDAO).findProducts(searchStr);

        // Invoke
        ResponseEntity<BlogPost[]> response = inventoryController.searchProduct(searchStr);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testDeleteProduct() throws IOException {
        // Setup
        int productId = 99;

        when(mockInventoryDAO.deleteProduct(productId)).thenReturn(true);

        // Invoke
        ResponseEntity<BlogPost> response = inventoryController.deleteProduct(productId);

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteProductNotFound() throws IOException {
        int productID = 99;

        when(mockInventoryDAO.deleteProduct(productID)).thenReturn(false);

        ResponseEntity<BlogPost> response = inventoryController.deleteProduct(productID);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void testDeleteProductHandleException() throws IOException {
        // Setup 
        int productID = 99;

        // Throw IOE when delete product is called on mock inventory dao
        doThrow(new IOException()).when(mockInventoryDAO).deleteProduct(productID);

        // Invoke
        ResponseEntity<BlogPost> response = inventoryController.deleteProduct(productID);
        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}