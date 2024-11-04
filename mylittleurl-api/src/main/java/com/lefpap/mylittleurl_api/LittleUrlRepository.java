package com.lefpap.mylittleurl_api;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LittleUrlRepository extends ListCrudRepository<LittleUrl, Long> {
    Optional<LittleUrl> findByCode(String code);
    List<LittleUrl> findByUrl(String url);
}
