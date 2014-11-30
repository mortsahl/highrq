package highrq.core.services.impl;

import highrq.core.models.entities.Phone;
import highrq.core.services.PhoneService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PhoneServiceImpl implements PhoneService {

    // TODO - sja: Implement me

    @Override
    public Phone findPhone(Long id) {
        return null;
    }

    @Override
    public Phone createPhone(Phone data) {
        return null;
    }

    @Override
    public Phone deletePhone(Long id) {
        return null;
    }

    @Override
    public Phone updatePhone(Long id, Phone data) {
        return null;
    }
}
