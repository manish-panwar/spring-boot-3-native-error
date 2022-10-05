CREATE SCHEMA IF NOT EXISTS some_proxy_service;

CREATE TABLE some_proxy_service.system_snapshot
(
  id                    uuid       NOT NULL,
  created_at                       timestamp without time zone DEFAULT timezone('utc'::text, now()),
  CONSTRAINT system_snapshot_pkey PRIMARY KEY (id)
);