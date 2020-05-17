package com.msr.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Lookup types
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
public class UseTypes {

    @Id
    private int id;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

////////////////////////////////////////////////////////////
// Copyright 2018  Measurabl, Inc. All rights reserved.
////////////////////////////////////////////////////////////
    