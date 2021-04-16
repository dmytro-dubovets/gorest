package gorest.scenarios;

import gorest.dto.users.posts.request.PostsRequestDTO;
import gorest.dto.users.posts.response.PostsCreateResponseDTO;
import gorest.dto.users.posts.response.PostsResponseDTO;
import gorest.dto.users.users.request.UsersRequestDTO;
import gorest.dto.users.users.response.UsersCreateResponseDTO;
import gorest.dto.users.users.response.UsersDeleteResponseDTO;
import gorest.stepdefinitions.api.posts.PostsSteps;
import gorest.stepdefinitions.api.users.UsersSteps;
import gorest.util.rest.Context;
import org.apache.http.HttpStatus;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(groups = "posts")
public class PostsScenario {

    PostsSteps postsSteps = new PostsSteps();

    UsersSteps usersSteps = new UsersSteps();

    @Test(groups = "posts")
    public void getAllPosts() {
        Reporter.log("Get all posts");
        String title = "Adsum studio ipsam voro umquam cohaero textus tabula.";
        Context<PostsResponseDTO> responseDTOContext = postsSteps.getAllPosts();
        postsSteps.verifyResponse(responseDTOContext, HttpStatus.SC_OK);
        postsSteps.verifyPostTitle(responseDTOContext, title);
    }

    @Test(groups = "posts", dataProvider = "newPost")
    public void createUserPost(PostsRequestDTO postsRequestDTO) {
        Reporter.log("Creating user");
        String expectedTitle = postsRequestDTO.getTitle();
        UsersRequestDTO usersRequestDTO = getNewUser();
        Context<UsersCreateResponseDTO> createResponseDTOContext = usersSteps.createNewUser(usersRequestDTO);
        int id = usersSteps.getCreatedUserId(createResponseDTOContext);
        Reporter.log("Creating new post");
        Context<PostsCreateResponseDTO> createdPost = postsSteps.createUserPost(postsRequestDTO, id);
        postsSteps.verifyResponse(createdPost, HttpStatus.SC_OK);
        postsSteps.verifyPostCreatedTitle(createdPost,expectedTitle);
        Reporter.log("Deleting user");
        Context<UsersDeleteResponseDTO> deletedResponse = usersSteps.deleteUser(id);
        usersSteps.verifyResponse(deletedResponse, HttpStatus.SC_OK);
    }

    @Test(groups = "posts")
    public void getUserPosts() {
        int user_id = 51;
        Reporter.log("Get posts for user_id: " + user_id);
        Context<PostsResponseDTO> createdPost = postsSteps.getUserPost(user_id);
        postsSteps.verifyResponse(createdPost, HttpStatus.SC_OK);
    }

    private UsersRequestDTO getNewUser() {
        return new UsersRequestDTO()
                .setName("Dmytro Dubovets")
                .setGender("Male")
                .setEmail("testing@email.com")
                .setStatus("Active");
    }

    @DataProvider
    public Object[][] newPost() {
        return new Object[][]{
                new Object[]{
                        new PostsRequestDTO()
                                .setTitle("Test title")
                                .setBody("Test body")
                }
        };
    }

}
