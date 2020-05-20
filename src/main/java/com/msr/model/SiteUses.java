package com.msr.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Site uses POJO
 *
 * @author Measurabl
 * @since 2019-06-11
 */
@Data
@Table(name="site_uses")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class SiteUses {
    @Id
    private int id;

    private String description;

    private long sizeSqft;

    private int useTypeId;

    private int siteId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getSizeSqft() {
        return sizeSqft;
    }

    public void setSizeSqft(long sizeSqft) {
        this.sizeSqft = sizeSqft;
    }

    public int getUseTypeId() {
        return useTypeId;
    }

    public void setUseTypeId(int useTypeId) {
        this.useTypeId = useTypeId;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

}

////////////////////////////////////////////////////////////
// Copyright 2018  Measurabl, Inc. All rights reserved.
////////////////////////////////////////////////////////////
    