package com.msr;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import com.msr.data.SiteRepository;
import com.msr.model.Site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public List<Site> getAllSites()
    {
        return (List<Site>) siteRepository.findAll();
    }

    @GetMapping("/sites/{id}")
    public ResponseEntity<Site> getSitesById(@PathVariable(value = "id") int id)
      throws NoSuchElementException {
        Site site =
            siteRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Site not found on :: " + id));
        return ResponseEntity.ok().body(site);
    }

    @PostMapping("/sites")
    public Site createSite(@Valid @RequestBody Site site) {
      return siteRepository.save(site);
    }


    @PutMapping("/sites/{id}")
    public ResponseEntity<Site> updateSite(
      @PathVariable(value = "id") int id, @Valid @RequestBody Site siteDetails)
      throws NoSuchElementException {

        Site site =
            siteRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Site not found on :: " + id));

        site.setName(siteDetails.getName());
        site.setAddress(siteDetails.getAddress());
        site.setZipcode(siteDetails.getZipcode());
        site.setCity(siteDetails.getCity());
        site.setState(siteDetails.getState());
        final Site updatedSite = siteRepository.save(site);
        return ResponseEntity.ok(updatedSite);
    }

    @DeleteMapping("/sites/{id}")
    public Map<String, Boolean> deleteSite(@PathVariable(value = "id") int id) 
        throws NoSuchElementException {
        Site site =
            siteRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Site not found on :: " + id));

        siteRepository.delete(site);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
  }

}

////////////////////////////////////////////////////////////
// Copyright 2018  Measurabl, Inc. All rights reserved.
////////////////////////////////////////////////////////////
    