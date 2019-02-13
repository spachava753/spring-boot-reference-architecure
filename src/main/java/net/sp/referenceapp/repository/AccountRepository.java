package net.sp.referenceapp.repository;

import net.sp.referenceapp.domain.Account;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
  Stream<Account> findByLastName(String lastName) throws DataAccessException;

  Stream<Account> findByFirstName(String firstName) throws DataAccessException;

  Stream<Account> findByContact(String contact) throws DataAccessException;

  Optional<Account> findByUsername(String username) throws DataAccessException;

  Optional<Account> findBySsn(String ssn) throws DataAccessException;
  
}