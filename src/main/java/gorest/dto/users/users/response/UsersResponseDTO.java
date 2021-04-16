package gorest.dto.users.users.response;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class UsersResponseDTO {

    Integer code;

    Meta meta;

    List<Data> data;

    @Getter
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class Meta {
        Pagination pagination;
    }

    @Getter
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class Pagination {

        int total;

        int pages;

        int page;

        int limit;

    }

    @Getter
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class Data {

        int id;

        String name;

        String email;

        String gender;

        String status;

        String created_at;

        String updated_at;
    }
}
