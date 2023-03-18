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

    private final ObjectMapper objectMapper;
    private static int nextId;
    private final String filename;


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

    private Post[] getPostArray() {
        return getPostsArray(null);
    }

    private boolean save() throws IOException {
        Post[] postArray = getPostArray();

        objectMapper.writeValue(new File(filename), postArray);
        return true;
    }

    /**
     * Loads posts from json file into the map
     *
     * Fetches the greatest id in the file and sets ++nextId
     *
     * @return true if file read successfully
     *
     * @throws IOException
     */
    private boolean load() throws IOException {
        posts = new TreeMap<>();
        nextId = 0;

        Post[] postArray = objectMapper.readValue(new File(filename), Post[].class);

        for (Post post :  postArray){
            posts.put(post.getId(), post);
            if (post.getId() > nextId){
                nextId = post.getId() + 1;
            }
        }
        return true;
    }

    /**
     * Retrieves all post objects
     *
     * @return An array of post objects
     * @throws IOException if issue with storage
     */
    @Override
    public Post[] getPosts() throws IOException {
        synchronized (posts) {
            return getPostArray();
        }
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
        synchronized (posts) {
            return getPostsArray(containsText);
        }
    }

    /**
     * Retrieves post by id
     *
     * @param id of post being retrieved
     * @return singular post object
     * @throws IOException if issue with storage
     */
    @Override
    public Post getPost (int id) throws IOException {
        synchronized (posts){
            if (posts.containsKey(id)){
                return posts.get(id);
            } else {
                return null;
            }
        }
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
        synchronized (posts){
            Post createdPost = new Post(nextId(),
                newPost.getPostTitle(),
                newPost.getDate(),
                newPost.getDateUpdated(),
                newPost.getAuthor(),
                newPost.getMdFile(),
                newPost.getHeaderImage());
            posts.put(createdPost.getId(), createdPost);
            save();
            return createdPost;
        }
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
        synchronized (posts){
            if (!posts.containsKey(updatedPost.getId())){
                return null;
            } else {
                posts.put(updatedPost.getId(), updatedPost);
                save();
                return updatedPost;
            }
        }
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
        synchronized (posts){
            if(posts.containsKey(id)){
                posts.remove(id);
                return save();
            } else {
                return false;
            }
        }
    }
}
