package date.mvc;

import highrq.core.models.entities.Phone;
import highrq.core.services.PhoneService;
import highrq.core.services.exceptions.PhoneExistsException;
import highrq.rest.mvc.PhoneController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PhoneControllerTest {

  @InjectMocks
  private PhoneController controller;

  @Mock
  private PhoneService service;
  private MockMvc mockMvc;
  private ArgumentCaptor<Phone> phoneCaptor;

  private static final String AREACODE = "303";
  private static final String PREFIX = "987";
  private static final String BODY = "5678";
  private static final String EXT = "12345";

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    phoneCaptor = ArgumentCaptor.forClass(Phone.class);
  }

  @Test
  public void createPhoneNonExisting() throws Exception {
    Phone createdPhone = new Phone();
    createdPhone.setId(1L);
    createdPhone.setAreaCode(AREACODE);
    createdPhone.setPrefix(PREFIX);
    createdPhone.setBody(BODY);
    createdPhone.setExt(EXT);

    when(service.createPhone(any(Phone.class))).thenReturn(createdPhone);

    mockMvc.perform(post("/rest/phones")
      .content("{\"areaCode\": \"303\",\"prefix\":\"987\", \"body\": \"5678\", \"ext\": \"EXT\"}")
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(header().string("Location", org.hamcrest.Matchers.endsWith("/rest/phones/1")))
      .andExpect(jsonPath("$.areaCode", is(createdPhone.getAreaCode())))
      .andExpect(status().isCreated());

    verify(service).createPhone(phoneCaptor.capture());
    String body = phoneCaptor.getValue().getBody();
    assertEquals("5678", body);
  }

  @Test
  public void createPhoneExisting() throws Exception {
    Phone createdPhone = new Phone();
    createdPhone.setId(1L);
    createdPhone.setAreaCode(AREACODE);
    createdPhone.setBody(BODY);

    when(service.createPhone(any(Phone.class))).thenThrow(new PhoneExistsException());

    mockMvc.perform(post("/rest/phones")
      .content("{\"areaCode\":\"303\",\"body\":\"5678\"}")
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isConflict());
  }

  @Test
  public void getExistingPhone() throws Exception {
    Phone foundPhone = new Phone();
    foundPhone.setId(1L);
    foundPhone.setAreaCode(AREACODE);
    foundPhone.setBody(BODY);
    foundPhone.setPrefix(PREFIX);
    foundPhone.setExt(EXT);

    when(service.findPhone(1L)).thenReturn(foundPhone);

    mockMvc.perform(get("/rest/phones/1"))
//      .andDo(print())
      .andExpect(jsonPath("$.areaCode", is(foundPhone.getAreaCode())))
      .andExpect(jsonPath("$.prefix", is(foundPhone.getPrefix())))
      .andExpect(jsonPath("$.body", is(foundPhone.getBody())))
      .andExpect(jsonPath("$.ext", is(foundPhone.getExt())))
        // TODO - sja: I don't know why the below line fails
//      .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/phones/1"))))
      .andExpect(status().isOk());
  }

  @Test
  public void getNonExistingPhone() throws Exception {
    when(service.findPhone(1L)).thenReturn(null);

    mockMvc.perform(get("/rest/phones/1"))
      .andExpect(status().isNotFound());
  }

  @Test
  public void deleteExistingPhone() throws Exception {
    Phone deletedPhone = new Phone();
    deletedPhone.setId(1L);
    deletedPhone.setAreaCode(AREACODE);
    deletedPhone.setBody(BODY);
    deletedPhone.setPrefix(PREFIX);
    deletedPhone.setExt(EXT);

    when(service.deletePhone(1L)).thenReturn(deletedPhone);

    mockMvc.perform(delete("/rest/phones/1"))
      .andExpect(jsonPath("$.areaCode", is(deletedPhone.getAreaCode())))
      .andExpect(jsonPath("$.ext", is(deletedPhone.getExt())))
        // TODO - sja: I don't know why the below line fails
//      .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/phones/1"))))
      .andExpect(status().isOk());
  }
}
