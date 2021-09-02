package com.sz.ums.web;

import com.sz.ums.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/api/organization")
@RestController
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;
    @GetMapping("/getOrganization")
    public String getOrganization(){
        return organizationService.getOrganization();
    }
}
