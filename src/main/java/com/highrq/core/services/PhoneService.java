package com.highrq.core.services;

import com.highrq.core.models.entities.Phone;

public interface PhoneService {
  public Phone findPhone(Long id);
  public Phone createPhone(Phone data);
  public Phone deletePhone(Long id);
  public Phone updatePhone(Phone data);
}
