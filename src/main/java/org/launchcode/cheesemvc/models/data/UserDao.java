package org.launchcode.cheesemvc.models.data;

import org.launchcode.cheesemvc.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/*

@Transactional - Most often used on CrudRepository interfaces. Specifies that if any data operation within the class throws a runtime exception,
all associated database queries should be rolled back (that is, undone).  This can prevent data corruption caused by exceptions.
 */
@Repository
@Transactional
public interface UserDao extends CrudRepository<User, Integer> {
}
