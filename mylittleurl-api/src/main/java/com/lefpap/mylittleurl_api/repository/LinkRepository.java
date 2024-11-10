package com.lefpap.mylittleurl_api.repository;

import com.lefpap.mylittleurl_api.data.model.Link;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface LinkRepository extends ListCrudRepository<Link, Long> {
    Optional<Link> findByCode(String code);
    Boolean existsByCode(String code);
}
