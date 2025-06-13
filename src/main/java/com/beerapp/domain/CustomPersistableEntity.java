package com.beerapp.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.lang.Nullable;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@MappedSuperclass
public class CustomPersistableEntity implements Persistable<UUID>, java.io.Serializable {
    private final long serialVersionUID = 1L;
    private UUID id;

    private Instant createdDate;
    private Instant updatedDate;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @Override
    public @Nullable UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "created_date")
    @CreatedDate
    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    @Column(name = "updated_date")
    @LastModifiedDate
    public Instant getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Instant updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    @Transient
    public boolean isNew() {
        return this.id == null;
    }
}
