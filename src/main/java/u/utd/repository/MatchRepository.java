package u.utd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import u.utd.entity.Match;

public interface MatchRepository extends JpaRepository<Match, Long> {
}
