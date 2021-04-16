package gorest.dto.users.users.response;

import groovy.util.logging.Slf4j;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Slf4j
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class UsersDeleteResponseDTO {

    Integer code;

    UsersResponseDTO.Meta meta;

    UsersResponseDTO.Data data;
}
