package com.appzz.helpdesk.repository;

import com.appzz.helpdesk.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}
