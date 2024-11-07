package com.lefpap.mylittleurl_api.repository;

import com.lefpap.mylittleurl_api.data.model.LinkClickMetadata;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkClickMetadataRepository extends ListCrudRepository<LinkClickMetadata, Long> {
}
