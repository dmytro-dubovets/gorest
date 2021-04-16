package gorest.stepdefinitions.api.posts;

import com.jayway.restassured.response.Response;
import gorest.dto.users.posts.response.PostsCreateResponseDTO;
import gorest.dto.users.posts.response.PostsResponseDTO;
import gorest.util.rest.Context;
import gorest.util.rest.Transfer;

public class Posts {

    private final Transfer transfer = new Transfer();
    private String path;

    public Posts() {

    }

    public Posts(String path) {
        this.path = path;
    }

    public Context<PostsResponseDTO> get() {
        Response response = transfer.get(path);
        return new Context<>(response, PostsResponseDTO.class);
    }

    public Context<PostsCreateResponseDTO> post(String body) {
        Response response = transfer.createNewPost(path, body);
        return new Context<>(response, PostsCreateResponseDTO.class);
    }
}
