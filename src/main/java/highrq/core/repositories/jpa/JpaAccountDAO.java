package highrq.core.repositories.jpa;

import highrq.core.models.entities.Account;
import highrq.core.repositories.AccountDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

@Repository
public class JpaAccountDAO implements AccountDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Account> findAllAccounts() {
        Query query = em.createNamedQuery("Account.findAllAccounts");
        return query.getResultList();
    }

    @Override
    public Account findAccount(Long id) {
        return em.find(Account.class, id);
    }

    @Override
    public Account findAccountByUsername(String username) {
        Query query = em.createNamedQuery("Account.findAccountByUsername");
        query.setParameter(1, username);
        List<Account> accounts = query.getResultList();
        if (accounts.size() == 0) {
            return null;
        } else {
            return accounts.get(0);
        }
    }

    @Override
    public List<Account> findAccountsByRole(String role) {
        Query query = em.createNamedQuery("Account.findAccountsByRole");
        query.setParameter(1, role);
        List<Account> accounts = query.getResultList();
        if (accounts.size() == 0) {
            return Collections.EMPTY_LIST;
        }
        else {
            return accounts;
        }
    }

    @Override
    public Account createAccount(Account data) {
        em.persist(data);
        return data;
    }
}
