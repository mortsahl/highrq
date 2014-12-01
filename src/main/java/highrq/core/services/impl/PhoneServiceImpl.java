package highrq.core.services.impl;

import highrq.core.models.entities.Phone;
import highrq.core.repositories.PhoneRepo;
import highrq.core.services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PhoneServiceImpl implements PhoneService {

    // TODO - sja: Implement me
    @Autowired
    private PhoneRepo phoneRepo;

    @Override
    public Phone findPhone(Long id) {
        return phoneRepo.findPhone(id);
    }

    @Override
    public Phone createPhone(Phone data) {
        return phoneRepo.createPhone(data);
    }

    @Override
    public Phone deletePhone(Long id) {
        return phoneRepo.deletePhone(id);
    }

    @Override
    public Phone updatePhone(Long id, Phone data) {
        return phoneRepo.updatePhone(id, data);
    }

}
