package com.Lynsktech.lynsktechbackend.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import com.Lynsktech.lynsktechbackend.model.Post;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * Component Data Access Object for the Post Object
 *
 * @author greglynskey
 */
@Component
public class PostFileDAO implements PostDAO{

    Map<Integer, Post> posts;

    private ObjectMapper objectMapper;
    private static int nextId;
    private String filename;


    public PostFileDAO(@Value("${posts.file}") String filename,
                       ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();
    }

    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    private Post[] getPostsArray(String containsText){
        ArrayList<Post> postArrayList = new ArrayList<>();

        for (Post post : posts.values()) {
            if (containsText == null || post.getPostTitle().contains(containsText)){
                postArrayList.add(post);
            }
        }

        Post[] postArray = new Post[postArrayList.size()];
        postArrayList.toArray(postArray);
        return postArray;
    }

    /**
     * Retrieves all post objects
     *
     * @return An array of post objects
     * @throws IOException if issue with storage
     */
    @Override
    public Post[] getPosts() throws IOException {
        return new Post[0];
    }

    /**
     * Retrieves all post objects with specific title
     *
     * @param containsText query string
     * @return An array of post objects
     * @throws IOException if issue with storage
     */
    @Override
    public Post[] findPostsByTitle(String containsText) throws IOException {
        return new Post[0];
    }

    /**
     * Retrieves post by id
     *
     * @param id of post being retrieved
     * @return singular post object
     * @throws IOException if issue with storage
     */
    @Override
    public Post getPost(int id) throws IOException {
        return null;
    }

    /**
     * Creates a new post object
     *
     * @param newPost new post object
     * @return post object created
     * @throws IOException if issue with storage
     */
    @Override
    public Post createPost(Post newPost) throws IOException {
        return null;
    }

    /**
     * Updates existing post object
     *
     * @param updatedPost post being updated
     * @return updated post object
     * @throws IOException if issue with storage
     */
    @Override
    public Post updatePost(Post updatedPost) throws IOException {
        return null;
    }

    /**
     * Deletes post given id
     *
     * @param id of post being deleted
     * @return true if deleted, false if not
     * @throws IOException if issue with storage
     */
    @Override
    public boolean deletePost(int id) throws IOException {
        return false;
    }
}
