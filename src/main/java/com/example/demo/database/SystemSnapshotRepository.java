package com.example.demo.database;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemSnapshotRepository extends R2dbcRepository<SystemSnapshot, UUID> {
}
