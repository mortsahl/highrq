package highrq.core.repositories.jpa;

import highrq.core.models.entities.Phone;
import highrq.core.repositories.PhoneDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class JpaPhoneDAO implements PhoneDAO {

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

    @Override
    public Phone deletePhone(Long id) {
        Phone phone = em.find(Phone.class, id);
        em.remove(phone);
        return phone;
    }

    @Override
    public Phone updatePhone(Long id, Phone data) {
        Phone phone = em.find(Phone.class, id);
        phone.setAreaCode(data.getAreaCode());
        phone.setPrefix(data.getPrefix());
        phone.setBody(data.getBody());
        phone.setExt(data.getExt());
        return phone;
    }
}

