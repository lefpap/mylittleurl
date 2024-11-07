DROP TABLE IF EXISTS links CASCADE;
DROP TABLE IF EXISTS click_metadata CASCADE;

CREATE TABLE links (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(100) NOT NULL UNIQUE,
    url TEXT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMPTZ
);

CREATE TABLE click_metadata (
    id BIGSERIAL PRIMARY KEY,
    link_id BIGINT NOT NULL REFERENCES links(id) ON DELETE CASCADE,
    user_agent TEXT,
    referrer_url TEXT,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Indexes
--CREATE INDEX idx_click_metadata_link_id ON url_click_metadata (link_id);
--CREATE INDEX idx_click_metadata_created_at ON url_click_metadata (created_at);
