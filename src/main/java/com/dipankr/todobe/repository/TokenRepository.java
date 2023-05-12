package com.dipankr.todobe.repository;

import com.dipankr.todobe.token.Token;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query(name = """
        select t from Token t inner join User u on u.id = t.user_id
        where u.id = ?1 and (t.expired = false or t.revoked = false)
        """)
    List<Token> findAllValidTokensByUserId(long userId);

    Optional<Token> findByToken(String token);

}
