package highrq.core.services;

import highrq.core.models.entities.Phone;
import highrq.core.repositories.PhoneDAO;
import highrq.core.services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneDAO phoneDAO;

    @Override
    public Phone findPhone(Long id) {
        return phoneDAO.findPhone(id);
    }

    @Override
    public Phone createPhone(Phone data) {
        return phoneDAO.createPhone(data);
    }

    @Override
    public Phone deletePhone(Long id) {
        return phoneDAO.deletePhone(id);
    }

    @Override
    public Phone updatePhone(Phone data) {
        return phoneDAO.updatePhone(data);
    }

}
