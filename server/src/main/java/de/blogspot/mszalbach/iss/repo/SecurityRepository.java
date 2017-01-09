package de.blogspot.mszalbach.iss.repo;

import de.blogspot.mszalbach.iss.domain.Security;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Extends automatically created Rest Repository with additional finder methods.
 */
public interface SecurityRepository extends CrudRepository<Security, Long> {

    List<Security> findByIsin(@Param("isin") String isin);
}
