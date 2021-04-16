package gorest.dto.users.posts.response;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class PostsResponseDTO {

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

        int user_id;

        String title;

        String body;

        String created_at;

        String updated_at;
    }
}
