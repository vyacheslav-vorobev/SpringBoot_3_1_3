package com.slavik.SpringBoot.service;

import com.slavik.SpringBoot.dao.RoleDao;
import com.slavik.SpringBoot.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp implements RoleService{

    RoleDao roleDao;
    @Autowired
    public RoleServiceImp(RoleDao roleDao){
        this.roleDao = roleDao;
    }
    @Override
    public Role getOne(Long id){
        return roleDao.getOne(id);
    }
}
