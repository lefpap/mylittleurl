package com.lefpap.mylittleurl_api.repository;

import com.lefpap.mylittleurl_api.data.entity.Link;
import com.lefpap.mylittleurl_api.data.projections.LinkSummary;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LinkRepository extends ListCrudRepository<Link, Long> {
    Optional<Link> findByCode(String code);

    @Query
    Optional<LinkSummary> findSummaryByCode(@Param("code") String code);

    @Query
    List<LinkSummary> findSummaries();
}
