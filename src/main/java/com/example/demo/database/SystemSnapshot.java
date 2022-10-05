package com.example.demo.database;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@Table("some_proxy_service.system_snapshot")
@NoArgsConstructor
@AllArgsConstructor
public class SystemSnapshot implements Persistable<UUID> {

    @Id
    @Column("id")
    private UUID id;

    @Column("created_at")
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime createdAt;

    @Transient
    private boolean newItem;

    @Override
    @Transient
    public boolean isNew() {
        return isNewItem();
    }
}
