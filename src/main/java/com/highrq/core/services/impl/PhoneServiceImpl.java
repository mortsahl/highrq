package com.highrq.core.services.impl;

import com.highrq.core.models.entities.Phone;
import com.highrq.core.repositories.PhoneDAO;
import com.highrq.core.services.PhoneService;
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
