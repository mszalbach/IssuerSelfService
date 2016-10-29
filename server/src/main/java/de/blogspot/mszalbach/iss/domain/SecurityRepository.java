package de.blogspot.mszalbach.iss.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by foobarkilla on 22.10.16.
 */
public interface SecurityRepository extends CrudRepository<Security, Long> {

    List<Security> findByIsin(@Param("isin") String isin);
}
