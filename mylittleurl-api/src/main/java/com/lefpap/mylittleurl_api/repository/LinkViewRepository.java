package com.lefpap.mylittleurl_api.repository;

import com.lefpap.mylittleurl_api.data.model.LinkView;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface LinkViewRepository extends Repository<LinkView, Long> {
    List<LinkView> findAll();
    Optional<LinkView> findByCode(String code);
}
