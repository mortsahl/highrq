package highrq.core.repositories;

import highrq.core.models.entities.Phone;

public interface PhoneRepo {
  public Phone findPhone(Long id);
  public Phone createPhone(Phone data);
  public Phone deletePhone(Long id);
  public Phone updatePhone(Long id, Phone data);
}
