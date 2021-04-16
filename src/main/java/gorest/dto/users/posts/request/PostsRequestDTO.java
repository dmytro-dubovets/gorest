package gorest.dto.users.posts.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class PostsRequestDTO {

    String title;

    String body;

    /*@Data
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class User {

        int user_id;

    }*/
}
