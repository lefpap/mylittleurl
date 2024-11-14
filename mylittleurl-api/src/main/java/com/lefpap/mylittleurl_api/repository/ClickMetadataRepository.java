package com.lefpap.mylittleurl_api.repository;

import com.lefpap.mylittleurl_api.data.entity.ClickMetadata;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClickMetadataRepository extends CrudRepository<ClickMetadata, Long> {

    @Query
    List<ClickMetadata> findByLinkCode(String code);
}
