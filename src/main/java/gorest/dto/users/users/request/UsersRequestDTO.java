package gorest.dto.users.users.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class UsersRequestDTO {

    String name;

    String email;

    String gender;

    String status;

}
