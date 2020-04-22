package Implementation.repositories;

import java.util.List;

import Implementation.dtos.UserDTO;

public interface IUserRepository extends IRepository<UserDTO> {

	List<UserDTO> findByName(String username);
}