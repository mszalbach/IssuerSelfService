package de.blogspot.mszalbach.iss.repo;

import de.blogspot.mszalbach.iss.domain.Security;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by foobarkilla on 22.10.16.
 */
public interface SecurityRepository extends CrudRepository<Security, Long> {

    List<Security> findByIsin(@Param("isin") String isin);
}
