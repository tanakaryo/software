package jp.co.app.mcrsrvc.usr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.app.mcrsrvc.usr.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
