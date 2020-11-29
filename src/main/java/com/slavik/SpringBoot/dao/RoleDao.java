package com.slavik.SpringBoot.dao;

import com.slavik.SpringBoot.model.Role;

public interface RoleDao {
    Role getOne(Long id);
}