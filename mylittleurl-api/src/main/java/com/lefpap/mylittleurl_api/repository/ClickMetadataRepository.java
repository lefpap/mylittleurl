package com.lefpap.mylittleurl_api.repository;

import com.lefpap.mylittleurl_api.data.model.ClickMetadata;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClickMetadataRepository extends CrudRepository<ClickMetadata, Long> {
    String FIND_BY_LINK_CODE = """
        SELECT
            m.id,
            m.link_id,
            m.user_agent,
            m.referrer_url,
            m.created_at
        FROM
            click_metadata m
        JOIN
            links l ON l.id = m.link_id
        WHERE
            l.code = :code;
    """;

    @Query(FIND_BY_LINK_CODE)
    List<ClickMetadata> findByLinkCode(String code);
}
