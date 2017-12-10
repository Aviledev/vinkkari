package avile.repository;

import avile.domain.Account;
import avile.domain.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long>{

    List<Recommendation> findRecommendationsByTitleIsLikeIgnoreCase(String title);
    List<Recommendation> findRecommendationsByCheckersNotContaining(Account account);
}
