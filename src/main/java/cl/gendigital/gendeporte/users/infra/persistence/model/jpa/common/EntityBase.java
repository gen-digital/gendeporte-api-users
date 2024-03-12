package cl.gendigital.gendeporte.users.infra.persistence.model.jpa.common;

import cl.gendigital.gendeporte.users.commons.DateUtils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
public abstract class EntityBase implements Serializable{

    private static final long serialVersionUID = -879358140693474742L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "enabled_at")
    private LocalDateTime enabledAt;

    @Column(name = "disabled_at")
    private LocalDateTime disabledAt;

    @Column(name = "expired_at")
    private LocalDateTime expiredAt;

    @Column(name = "locked_at")
    private LocalDateTime lockedAt;

    public abstract String getName();

    public boolean isEnabled() {
        return this.enabledAt != null && DateUtils.nowAfter(this.enabledAt);
    }

    public boolean isDisabled() {
        return this.disabledAt != null && DateUtils.nowAfter(this.disabledAt);
    }

    public boolean isExpired() {
        return this.expiredAt != null && DateUtils.nowAfter(this.expiredAt);
    }

    public boolean isLocked() {
        return this.lockedAt != null && DateUtils.nowAfter(this.lockedAt);
    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}