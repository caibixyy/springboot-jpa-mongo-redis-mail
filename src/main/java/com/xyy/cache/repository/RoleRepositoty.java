package com.xyy.cache.repository;

import com.xyy.cache.bean.mysqljpa.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepositoty extends JpaRepository<Role,Integer> {

    @Query(value = "select * from role r where r.id in (select role_id from user_role where user_id > ?1)",nativeQuery = true)
    List<Role> findByUidGreaterThan(@Param(value = "id") Integer id);
}
