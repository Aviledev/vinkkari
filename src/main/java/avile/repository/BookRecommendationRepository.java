/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avile.repository;

import avile.domain.BookRecommendation;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author teepiik
 */
public interface BookRecommendationRepository extends JpaRepository<BookRecommendation, Long>{
    
}
