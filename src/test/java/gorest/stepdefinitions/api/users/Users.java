package gorest.stepdefinitions.api.users;

import com.jayway.restassured.response.Response;
import gorest.dto.users.users.response.UsersCreateResponseDTO;
import gorest.dto.users.users.response.UsersDeleteResponseDTO;
import gorest.dto.users.users.response.UsersResponseDTO;
import gorest.util.rest.Context;
import gorest.util.rest.Transfer;

public class Users {

    private final Transfer transfer = new Transfer();
    private String path;

    public Users() {
    }

    public Users(String path) {
        this.path = path;
    }

    public Context<UsersResponseDTO> get() {
        Response response = transfer.get(path);
        return new Context<>(response, UsersResponseDTO.class);
    }

    public Context<UsersCreateResponseDTO> post(String body) {
        Response response = transfer.post(path, body);
        return new Context<>(response, UsersCreateResponseDTO.class);
    }

    public Context<UsersDeleteResponseDTO> delete(int id) {
        Response response = transfer.delete(path, id);
        return new Context<>(response, UsersDeleteResponseDTO.class);
    }

    public Context<UsersCreateResponseDTO> put(String body, int id) {
        Response response = transfer.put(path, body, id);
        return new Context<>(response, UsersCreateResponseDTO.class);
    }
}
