package com.balavignesh.restdemo.dao;

import com.balavignesh.restdemo.domain.AccountFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountFileRepository extends JpaRepository<AccountFile, Long> {
}
