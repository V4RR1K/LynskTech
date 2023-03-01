package com.Lynsktech.lynsktechbackend.persistence;

import java.io.IOException;
import com.Lynsktech.lynsktechbackend.model.Post;

/**
 * PostDAO outlines the basic CRUD operations
 */
public interface PostDAO {

    /**
     * Retrieves all post objects
     *
     * @return An array of post objects
     *
     * @throws IOException if issue with storage
     */
    Post[] getPosts() throws IOException;

    /**
     * Retrieves all post objects with specific title
     *
     * @return An array of post objects
     *
     * @throws IOException if issue with storage
     */
    Post[] findPostsByTitle(String containsText) throws IOException;

    /**
     * Retrieves post by id
     *
     * @param id of post being retrieved
     *
     * @return singular post object
     *
     * @throws IOException if issue with storage
     */
    Post getPost(int id) throws IOException;

    /**
     * Creates a new post object
     *
     * @param newPost new post object
     *
     * @return post object created
     *
     * @throws IOException if issue with storage
     */
    Post createPost(Post newPost) throws IOException;

    /**
     * Updates existing post object
     *
     * @param updatedPost post being updated
     *
     * @return updated post object
     *
     * @throws IOException if issue with storage
     */
    Post updatePost(Post updatedPost) throws IOException;

    /**
     * Deletes post given id
     *
     * @param id of post being deleted
     *
     * @return true if deleted, false if not
     *
     * @throws IOException if issue with storage
     */
    boolean deletePost(int id) throws IOException;
}
