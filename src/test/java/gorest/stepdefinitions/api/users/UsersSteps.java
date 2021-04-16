package gorest.stepdefinitions.api.users;

import com.google.gson.Gson;
import gorest.dto.users.users.request.UsersRequestDTO;
import gorest.dto.users.users.response.UsersCreateResponseDTO;
import gorest.dto.users.users.response.UsersDeleteResponseDTO;
import gorest.dto.users.users.response.UsersResponseDTO;
import gorest.stepdefinitions.api.common.CommonSteps;
import gorest.util.rest.Context;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UsersSteps extends CommonSteps{

    private static final String ROUT = "/users";

    Users users = new Users(ROUT);

    public Context<UsersResponseDTO> getAllUsers() {
        return users.get();
    }

    public Context<UsersCreateResponseDTO> createNewUser(UsersRequestDTO usersRequestDTO) {
        return users.post(new Gson().toJson(usersRequestDTO));
    }

    public Context<UsersDeleteResponseDTO> deleteUser(int id) {
        return users.delete(id);
    }

    public Context<UsersCreateResponseDTO> updateUser(UsersRequestDTO usersRequestDTO, int id) {
        return users.put(new Gson().toJson(usersRequestDTO), id);
    }

    public void verifyUsersName(Context<UsersResponseDTO> context, String expectedName) {
        UsersResponseDTO actual = context.getObjectFromResponse();
        List<UsersResponseDTO.Data> listOfData = actual.getData();
        UsersResponseDTO.Data presentedName = listOfData.stream().filter(name -> name.getName().equals(expectedName))
                .findFirst().orElseThrow(() -> new RuntimeException(expectedName + " isn't present in the " + listOfData));
        String actualName = presentedName.getName();
        performDataNameCheck(actualName, expectedName);
    }

    public int getCreatedUserId(Context<UsersCreateResponseDTO> context) {
        UsersCreateResponseDTO createdUser = context.getObjectFromResponse();
        return createdUser.getData().getId();
    }

    private void performDataNameCheck(String actualName, String expectedName) {
        assertThat(actualName).isEqualTo(expectedName);
    }
}
