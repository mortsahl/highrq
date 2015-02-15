package com.highrq.core.repositories;

import com.highrq.core.models.entities.Phone;

import java.util.List;

public interface PhoneDAO {
  public Phone findPhone(Long id);
  public Phone createPhone(Phone data);
  public Phone deletePhone(Long id);
  public Phone updatePhone(Phone data);
  public List<Phone> findPhonesByAreacode(String Areacode);
}
