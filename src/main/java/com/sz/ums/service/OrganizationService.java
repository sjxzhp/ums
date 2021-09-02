package com.sz.ums.service;

import com.alibaba.fastjson.JSONObject;
import com.sz.ums.repo.OrganizationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepo organizationRepo;

    public String getOrganization() {
        return JSONObject.toJSONString(organizationRepo.findAll());
    }
}
