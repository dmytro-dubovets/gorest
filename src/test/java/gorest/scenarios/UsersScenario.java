package gorest.scenarios;

import gorest.dto.users.users.request.UsersRequestDTO;
import gorest.dto.users.users.response.UsersCreateResponseDTO;
import gorest.dto.users.users.response.UsersDeleteResponseDTO;
import gorest.dto.users.users.response.UsersResponseDTO;
import gorest.stepdefinitions.api.users.UsersSteps;
import gorest.util.rest.Context;
import org.apache.http.HttpStatus;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(groups = "users")
public class UsersScenario {

    private final UsersSteps users = new UsersSteps();

    @Test(groups = "users")
    public void getAllUsers() {
        Reporter.log("Get all users");
        Context<UsersResponseDTO> responseDTOContext = users.getAllUsers();
        users.verifyResponse(responseDTOContext, HttpStatus.SC_OK);
    }

    @Test(groups = "users", dataProvider = "createUser")
    public void createNewUserAndDelete(UsersRequestDTO usersRequestDTO) {
        Reporter.log("Creating and deleting new user");
        Context<UsersCreateResponseDTO> responseDTOContext = users.createNewUser(usersRequestDTO);
        users.verifyResponse(responseDTOContext, HttpStatus.SC_OK);
        int id = users.getCreatedUserId(responseDTOContext);
        Context<UsersDeleteResponseDTO> responseDTO = users.deleteUser(id);
        users.verifyResponse(responseDTO, HttpStatus.SC_OK);
    }

    @Test(groups = "users", dataProvider = "updateUser")
    public void updateUser(UsersRequestDTO usersRequestDTO) {
        Reporter.log("Creating user");
        Context<UsersCreateResponseDTO> createdResponse = users.createNewUser(usersRequestDTO);
        users.verifyResponse(createdResponse, HttpStatus.SC_OK);
        int id = users.getCreatedUserId(createdResponse);
        Reporter.log("Updating information about user after creating");
        Context<UsersCreateResponseDTO> updatedResponse = users.updateUser(usersRequestDTO, id);
        users.verifyResponse(updatedResponse, HttpStatus.SC_OK);
        Reporter.log("Deleting user");
        Context<UsersDeleteResponseDTO> deletedResponse = users.deleteUser(id);
        users.verifyResponse(deletedResponse, HttpStatus.SC_OK);
    }

    @DataProvider(name = "createUser")
    public Object[][] newUser() {
        return new Object[][]{
                new Object[]{
                        new UsersRequestDTO()
                                .setName("Dmytro Dubovets")
                                .setGender("Male")
                                .setEmail("testing@email.com")
                                .setStatus("Active")
                }
        };
    }

    @DataProvider(name = "updateUser")
    public Object[][] updateExistUser() {
        return new Object[][]{
                new Object[]{
                        new UsersRequestDTO()
                                .setName("Noname Noname")
                                .setGender("Female")
                                .setEmail("testing@email.com")
                                .setStatus("Active")
                }
        };
    }

}
