package gorest.dto.users.posts.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Getter
@NoArgsConstructor
@Accessors(chain = true)
public class PostsCreateResponseDTO {

    Integer code;

    PostsResponseDTO.Meta meta;

    PostsResponseDTO.Data data;

}
