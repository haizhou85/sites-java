package com.msr.data;

import com.msr.model.Site;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository functionality for Site
 *
 * @author Measurabl
 * @since 2019-06-06
 */
@Repository
public interface SiteRepository extends PagingAndSortingRepository<Site, Integer> {
}

////////////////////////////////////////////////////////////
// Copyright 2018  Measurabl, Inc. All rights reserved.
////////////////////////////////////////////////////////////
    