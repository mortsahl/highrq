package highrq.mvc.resources;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import highrq.api.resources.AccountResource;
import highrq.core.models.entities.Phone;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AccountResourceTest {

    protected ObjectMapper mapper = new ObjectMapper();

    @Test
    public void test() throws JsonProcessingException {
        Phone phone1 = new Phone();
        phone1.setAreaCode("303");
        phone1.setPrefix("555");
        phone1.setBody("6666");
        phone1.setExt("12345");
        Phone phone2 = new Phone();
        phone2.setAreaCode("304");
        phone2.setPrefix("556");
        phone2.setBody("6667");
        phone2.setExt("12346");
        List<Phone> phones = new ArrayList<>();
        phones.add(phone2);
        phones.add(phone1);
        AccountResource ar = new AccountResource();
        ar.setFname("fname");
        ar.setLname("lname");
        ar.setPassword("password");
        ar.setUsername("username");
        ar.setRole("role");
        ar.setPhones(phones);

        String accountAsJson = mapper.writeValueAsString(ar.toAccount());

        System.out.print(accountAsJson);

    }

}
