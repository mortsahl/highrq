package highrq.core.repositories.jpa;

import highrq.core.models.entities.Phone;
import highrq.core.repositories.PhoneRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JpaPhoneRepo implements PhoneRepo {

  @PersistenceContext
  private EntityManager em;

  @Override
  public Phone findPhone(Long id) {
    return em.find(Phone.class, id);
  }

  @Override
  public Phone createPhone(Phone data) {
    em.persist(data);
    return data;
  }
}
