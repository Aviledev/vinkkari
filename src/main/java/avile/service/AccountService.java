package avile.service;

import avile.domain.Account;
import avile.domain.Recommendation;
import avile.repository.AccountRepository;
import avile.repository.RecommendationRepository;
import org.apache.regexp.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RecommendationRepository recommendationRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public Account getAuthenticatedAccount() {
        try {
            return accountRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isAuthenticated () {
        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }

    public void save(Account account) {
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
    }

    public Account getAccount(Long id) {return  accountRepository.findOne(id);}

    @Transactional
    public void toggleChecked(Account account, Recommendation recommendation) {
        if(account.getCheckedRecommendations().contains(recommendation)) {
            account.getCheckedRecommendations().remove(recommendation);
            recommendation.getCheckers().remove(account);
        } else {
            account.getCheckedRecommendations().add(recommendation);
            recommendation.getCheckers().add(account);
        }
        accountRepository.save(account);
        recommendationRepository.save(recommendation);
    }

}
