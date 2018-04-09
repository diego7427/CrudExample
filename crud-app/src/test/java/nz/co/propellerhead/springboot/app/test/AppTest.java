/**
 * AppTest.java 09-abr-2018
 *
 * Copyright 2018 INDITEX.
 * Departamento de Sistemas
 */
package nz.co.propellerhead.springboot.app.test;
/**
 * RepositoryTest.java 09-abr-2018
 *
 * Copyright 2018 INDITEX.
 * Departamento de Sistemas
 */

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import nz.co.propellerhead.CustomerCRUDApp;
import nz.co.propellerhead.springboot.controller.AppController;
import nz.co.propellerhead.springboot.controller.RestApiController;
import nz.co.propellerhead.springboot.dto.CustomerDTO;
import nz.co.propellerhead.springboot.dto.NoteDTO;
import nz.co.propellerhead.springboot.dto.StatusDTO;

/**
 * The Class RepositoryTest.
 *
 * @author <a href="${email}">${author}</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { CustomerCRUDApp.class })
@ActiveProfiles("local")
@EnableAutoConfiguration
@Transactional
public class AppTest {

    /** The customer repository. */
    @Autowired
    AppController appController;

    /** The status repository. */
    @Autowired
    RestApiController restApiController;

    /**
     * Test creation of a cutomer.
     *
     * @return combo values
     */
    @Test
    @Rollback(false)
    public void getComboValues() {
        final ResponseEntity<List<StatusDTO>> listAllComboValues = this.restApiController.listAllComboValues();
        Assert.assertEquals(listAllComboValues.getBody().size(), 3);
    }

    /**
     * Anade el user.
     */
    @Test
    @Rollback(false)
    public void addUser() {
        final CustomerDTO entity = new CustomerDTO();
        entity.setAddress("Address");
        entity.setName("Name");
        entity.setCreationDateTime(new Date());
        final ResponseEntity<List<StatusDTO>> listAllComboValues = this.restApiController.listAllComboValues();
        entity.setStatus(listAllComboValues.getBody().get(0));
        final NoteDTO note = new NoteDTO();
        note.setCustomer(entity);
        note.setDescription("Description");
        entity.setNotes(Arrays.asList(note));

        final ResponseEntity<List<CustomerDTO>> listAllCustomers = this.restApiController.listAllCustomers();
        final ResponseEntity<?> customer = this.restApiController.createCustomer(entity,
                UriComponentsBuilder.newInstance());

        final ResponseEntity<List<CustomerDTO>> listAllCustomers2 = this.restApiController.listAllCustomers();

        Assert.assertNotEquals(listAllCustomers, listAllCustomers2);
    }

}
