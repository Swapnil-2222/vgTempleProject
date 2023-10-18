package com.techvg.temple.repository;

import com.techvg.temple.domain.Delegate;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Delegate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DelegateRepository extends JpaRepository<Delegate, Long>, JpaSpecificationExecutor<Delegate> {}
