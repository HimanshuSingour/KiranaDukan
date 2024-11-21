package com.v1.manage.v8.init.repositories;

import com.v1.manage.v8.init.entity.KiranaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KiranaUserRepository extends JpaRepository<KiranaUser, String> {

    @Query("SELECT k FROM KiranaUser k WHERE k.username = :username")
    KiranaUser findByUserName(String username);
}
