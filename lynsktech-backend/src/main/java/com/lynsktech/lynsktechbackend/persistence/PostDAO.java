package com.Lynsktech.lynsktechbackend.persistence;

import java.io.IOException;
import com.Lynsktech.lynsktechbackend.model.Post;

/**
 * PostDAO outlines the basic CRUD operations
 */
public interface PostDAO {

    Post[] getPosts() throws IOException;

    Post[] findPostsByTitle(String containsText) throws IOException;

    Post getPost(int id) throws IOException;

    Post createPost(Post newPost) throws IOException;

    Post updatePost(Post updatedPost) throws IOException;

    Post deletePost(int id) throws IOException;
}
