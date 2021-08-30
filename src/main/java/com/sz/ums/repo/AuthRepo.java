package com.sz.ums.repo;

import com.sz.ums.domain.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthRepo extends JpaRepository<Auth, Long>, JpaSpecificationExecutor<Auth> {

    @Query(value = "SELECT u1.* FROM ums_auth u1,ums_user_organization u3,ums_user u4,ums_role_organization u5,ums_role_auth u6\n" +
            "WHERE u4.id = u3.user_id and u3.org_id = u5.org_id and u5.role_id = u6.role_id and u6.auth_id = u1.id and u6.sys_code = u1.sys_code\n", nativeQuery = true)
    List<Auth> findAuthList(Long userId);


}
