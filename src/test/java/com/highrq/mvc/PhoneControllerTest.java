package com.highrq.mvc;

import com.highrq.core.models.entities.Phone;
import com.highrq.core.models.entities.enums.PhoneType;
import com.highrq.core.services.AccountService;
import com.highrq.core.services.PhoneService;
import com.highrq.core.services.exceptions.PhoneExistsException;
import com.highrq.api.controllers.impl.PhoneControllerImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PhoneControllerTest {

    @InjectMocks
    private PhoneControllerImpl controller;

    @Mock
    private PhoneService service;

    @Mock
    private AccountService accountService;

    private MockMvc mockMvc;
    private ArgumentCaptor<Phone> phoneCaptor;

    private static final String AREACODE = "303";
    private static final String PREFIX = "987";
    private static final String BODY = "5678";
    private static final String EXT = "12345";
    private static final String TYPE = PhoneType.CELL.getValue();

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
        createdPhone.setAreacode(AREACODE);
        createdPhone.setPrefix(PREFIX);
        createdPhone.setBody(BODY);
        createdPhone.setExt(EXT);
        createdPhone.setType(TYPE);

        when(service.createPhone(any(Phone.class))).thenReturn(createdPhone);

        mockMvc.perform(post("/api/phones")
                .content("{\"areacode\": \"303\",\"prefix\":\"987\", \"body\": \"5678\", \"ext\": \"12345\", \"type\" : \"Cell\", \"type\": \"Cell\"}")  // TODO - sja: Need accountId in test
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", org.hamcrest.Matchers.endsWith("/api/phones/1")))
                .andExpect(jsonPath("$.areacode", is(createdPhone.getAreacode())))
                .andExpect(jsonPath("$.type", is(createdPhone.getType())))
 //               .andExpect(jsonPath("$.accountId", is(createdPhone.getAccountId())))
                .andExpect(status().isCreated());


        verify(service).createPhone(phoneCaptor.capture());
        String body = phoneCaptor.getValue().getBody();
        assertEquals("5678", body);
    }

    @Test
    public void createPhoneExisting() throws Exception {
        Phone createdPhone = new Phone();
        createdPhone.setId(1L);
        createdPhone.setAreacode(AREACODE);
        createdPhone.setBody(BODY);

        when(service.createPhone(any(Phone.class))).thenThrow(new PhoneExistsException());

        mockMvc.perform(post("/api/phones")
                .content("{\"areacode\":\"303\",\"body\":\"5678\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    public void getExistingPhone() throws Exception {
        Phone foundPhone = new Phone();
        foundPhone.setId(1L);
        foundPhone.setAreacode(AREACODE);
        foundPhone.setBody(BODY);
        foundPhone.setPrefix(PREFIX);
        foundPhone.setExt(EXT);
        foundPhone.setType(TYPE);

        when(service.findPhone(1L)).thenReturn(foundPhone);

        mockMvc.perform(get("/api/phones/1"))
//      .andDo(print())
                .andExpect(jsonPath("$.areacode", is(foundPhone.getAreacode())))
                .andExpect(jsonPath("$.prefix", is(foundPhone.getPrefix())))
                .andExpect(jsonPath("$.body", is(foundPhone.getBody())))
                .andExpect(jsonPath("$.ext", is(foundPhone.getExt())))
                .andExpect(jsonPath("$.type", is(foundPhone.getType())))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/phones/1"))))
                .andExpect(status().isOk());
    }

    @Test
    public void getNonExistingPhone() throws Exception {
        when(service.findPhone(1L)).thenReturn(null);

        mockMvc.perform(get("/api/phones/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteExistingPhone() throws Exception {
        Phone deletedPhone = new Phone();
        deletedPhone.setId(1L);
        deletedPhone.setAreacode(AREACODE);
        deletedPhone.setExt(EXT);

        when(service.deletePhone(1L)).thenReturn(deletedPhone);

        mockMvc.perform(delete("/api/phones/1"))
                .andExpect(jsonPath("$.areacode", is(deletedPhone.getAreacode())))
                .andExpect(jsonPath("$.ext", is(deletedPhone.getExt())))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/phones/1"))))
                .andExpect(status().isOk());
    }


    // TODO - sja: implement findPhonesForAccount test

}
