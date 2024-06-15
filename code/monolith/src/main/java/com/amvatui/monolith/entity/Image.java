package com.amvatui.monolith.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, exclude = {"parent"})
@Entity
public class Image extends BaseEntity {
    @Column(length = 2048)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Post parent;
}
