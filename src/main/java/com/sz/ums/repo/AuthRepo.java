package com.sz.ums.repo;

import com.sz.ums.domain.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthRepo extends JpaRepository<Auth, Long>, JpaSpecificationExecutor<Auth> {

    @Query(value = "SELECT u1.* FROM ums_auth u1,ums_user u2,ums_role_organization u3,ums_role_auth u4\n" +
            "WHERE u2.org_id = u3.org_id and u3.role_id = u4.role_id and u4.auth_id = u1.id and u4.sys_code = u1.sys_code\n", nativeQuery = true)
    List<Auth> findAuthList(Long userId);

    List<Auth> findAllByMenuNameLikeAndSysCode(String menuName,String sysCode);

    List<Auth> findAllBySysCode(String sysCode);


}
