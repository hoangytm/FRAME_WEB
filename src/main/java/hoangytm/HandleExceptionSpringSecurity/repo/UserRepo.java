package hoangytm.HandleExceptionSpringSecurity.repo;

import hoangytm.HandleExceptionSpringSecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author PhanHoang
 * 2/6/2020
 */
@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    public  User findUserByEmail(String email);

}
