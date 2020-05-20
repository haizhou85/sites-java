package com.msr;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;
import java.util.NoSuchElementException;

import com.msr.data.SiteRepository;
import com.msr.model.Site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Respond to site requests
 *
 * @author Measurabl
 * @since 2019-06-06
 */
@RestController
@RequestMapping("/api")
public class BuildingsController {

    /* Sample Output messages. */
    private final static String SAMPLE_RESPONSE_BASE = "This is a sample response to test if your BuildingController is responding appropriately.  ";
    static final String SAMPLE_PARAM_PROVIDED = SAMPLE_RESPONSE_BASE + "The request param you passed was: ";
    static final String NO_SAMPLE_PARAM_PROVIDED = SAMPLE_RESPONSE_BASE + "No request param was provided.";
    static final String SAMPLE_EXCEPTION_MESSAGE = SAMPLE_RESPONSE_BASE + "An expected error was thrown.";

    /**
     * Used simply to check if your BuildingsController is responding to requests.
     * Has no function other than echoing.
     * @return A sample message based on the input parameters.
     * @throws Exception Only when 'throwError' is true.
     */
    @ApiOperation("Returns a sample message for baseline controller testing.")
    @GetMapping("/sample")
    public String getSampleResponse(@ApiParam("The message that will be echoed back to the user.")
                                    @RequestParam(required = false) final String message,
                                    @ApiParam("Forces this endpoint to throw a generic error.")
                                    @RequestParam(required = false) final boolean throwError) throws Exception {
        String response;
        if(throwError) {
            throw new Exception(SAMPLE_EXCEPTION_MESSAGE);
        } else if (StringUtils.isEmpty(message)){
            response = NO_SAMPLE_PARAM_PROVIDED;
        } else {
            response = SAMPLE_PARAM_PROVIDED + message;
        }
        return response;
    }

    @Autowired
    private SiteRepository siteRepository;
    
    @GetMapping("/sites")
    List<Site> getAllSites() {
        return (List<Site>) siteRepository.findAll();
    }

    @GetMapping("/sites/{id}")
    Site getSiteById(@PathVariable int id) {
      return siteRepository.findById(id)
       .orElseThrow(() -> new NoSuchElementException("Site not found on :: " + id));
    }

    @PostMapping("/sites")
    Site newSite(@RequestBody Site newSite) {
      return siteRepository.save(newSite);
    }


    @PutMapping("/sites/{id}")
    Site updateSite(@PathVariable int id, @RequestBody Site newSiteDetails) {
        return siteRepository
                .findById(id)
                .map(site -> {
                    site.setName(newSiteDetails.getName());
                    site.setAddress(newSiteDetails.getAddress());
                    site.setZipcode(newSiteDetails.getZipcode());
                    site.setCity(newSiteDetails.getCity());
                    site.setState(newSiteDetails.getState());
                    return siteRepository.save(site);
                })
                .orElseGet(() -> {
                    newSiteDetails.setId(id);
                    return siteRepository.save(newSiteDetails);
                  });
    }

    @DeleteMapping("/sites/{id}")
    void deleteSite(@PathVariable int id) {
        siteRepository.deleteById(id);
    }
}

////////////////////////////////////////////////////////////
// Copyright 2018  Measurabl, Inc. All rights reserved.
////////////////////////////////////////////////////////////
    