package com.lefpap.mylittleurl_api.repository;

import com.lefpap.mylittleurl_api.data.model.Link;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LinkRepository extends ListCrudRepository<Link, Long> {
    Optional<Link> findByCode(String code);
}
