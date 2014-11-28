package highrq.core.services;

import highrq.core.models.entities.Phone;

public interface PhoneService {
  public Phone findPhone(Long id);
  public Phone createPhone(Phone data);
  public Phone deletePhone(Long id);
  public Phone updatePhone(Long id, Phone data);
}
