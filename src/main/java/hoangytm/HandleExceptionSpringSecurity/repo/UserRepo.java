package hoangytm.HandleExceptionSpringSecurity.repo;

import hoangytm.HandleExceptionSpringSecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author PhanHoang
 * 2/6/2020
 */
@Repository
@Transactional
public interface UserRepo extends JpaRepository<User,Long> {
      User findUserByEmail(String email);
     void deleteUserByEmail(String email);

}
