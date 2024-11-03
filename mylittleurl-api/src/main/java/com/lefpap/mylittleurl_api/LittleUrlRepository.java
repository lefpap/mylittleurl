package com.lefpap.mylittleurl_api;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LittleUrlRepository extends ListCrudRepository<LittleUrl, Long> {
    Optional<LittleUrl> findByCode(String code);
    Optional<LittleUrl> findByUrl(String url);
}
