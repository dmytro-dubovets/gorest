package gorest.stepdefinitions.api.posts;

import com.google.gson.Gson;
import gorest.dto.users.posts.request.PostsRequestDTO;
import gorest.dto.users.posts.response.PostsCreateResponseDTO;
import gorest.dto.users.posts.response.PostsResponseDTO;
import gorest.stepdefinitions.api.common.CommonSteps;
import gorest.util.rest.Context;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PostsSteps extends CommonSteps {

    private static final String ROUT = "/posts";

    private static String USER_POSTS = "/users/%s/posts";

    private String path;

    private Posts posts;

    public Context<PostsResponseDTO> getAllPosts() {
        posts = new Posts(ROUT);
        return posts.get();
    }


    public Context<PostsCreateResponseDTO> createUserPost(PostsRequestDTO postsRequestDTO, int id) {
        path = String.format(USER_POSTS, id);
        posts = new Posts(path);
        return posts.post(new Gson().toJson(postsRequestDTO));
    }

    public Context<PostsResponseDTO> getUserPost(int id) {
        path = String.format(USER_POSTS, id);
        posts = new Posts(path);
        return posts.get();
    }

    public void verifyPostCreatedTitle(Context<PostsCreateResponseDTO> context, String expectedTitle) {
        PostsCreateResponseDTO postsCreateResponseDTO = context.getObjectFromResponse();
        String actualTitle = postsCreateResponseDTO.getData().getTitle();
        performDataTitleCheck(actualTitle, expectedTitle);
    }

    public void verifyPostTitle(Context<PostsResponseDTO> context, String expectedTitle) {
        PostsResponseDTO actual = context.getObjectFromResponse();
        List<PostsResponseDTO.Data> dataList = actual.getData();
        PostsResponseDTO.Data presentedName = dataList.stream().filter(name -> name.getTitle().equals(expectedTitle))
                .findFirst().orElseThrow(() -> new RuntimeException(expectedTitle + " isn't present in the " + dataList));
        String actualTitle = presentedName.getTitle();
        performDataTitleCheck(actualTitle, expectedTitle);
    }

    private void performDataTitleCheck(String actualTitle, String expectedTitle) {
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }
}
