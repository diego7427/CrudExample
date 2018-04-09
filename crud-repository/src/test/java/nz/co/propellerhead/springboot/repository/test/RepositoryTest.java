/**
 * RepositoryTest.java 09-abr-2018
 *
 * Copyright 2018 INDITEX.
 * Departamento de Sistemas
 */
package nz.co.propellerhead.springboot.repository.test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import nz.co.propellerhead.springboot.configuration.JpaConfiguration;
import nz.co.propellerhead.springboot.model.Customer;
import nz.co.propellerhead.springboot.model.Note;
import nz.co.propellerhead.springboot.repositories.CustomerRepository;
import nz.co.propellerhead.springboot.repositories.StatusRepository;

/**
 * The Class RepositoryTest.
 *
 * @author <a href="${email}">${author}</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { JpaConfiguration.class })
@ActiveProfiles("local")
@EnableAutoConfiguration
@Transactional
public class RepositoryTest {

    /** The customer repository. */
    @Autowired
    CustomerRepository customerRepository;

    /** The status repository. */
    @Autowired
    StatusRepository statusRepository;

    /**
     * Test creation of a cutomer.
     */
    @Test
    @Rollback(true)
    public void testCreate() {
        final Customer entity = new Customer();
        entity.setAddress("Address");
        entity.setName("Name");
        entity.setCreationDateTime(new Date());
        entity.setStatus(this.statusRepository.findAll().get(0));
        final Note note = new Note();
        note.setCustomer(entity);
        note.setDescription("Description");
        entity.setNotes(Arrays.asList(note));

        this.customerRepository.save(entity);

        final List<Customer> findAll = this.customerRepository.findAll();
        org.junit.Assert.assertEquals(1, findAll.size());
    }

    /**
     * Test delete a customer.
     */
    @Test
    @Rollback(true)
    public void testDelete() {
        final Customer entity = new Customer();
        entity.setAddress("Address");
        entity.setName("Name");
        entity.setCreationDateTime(new Date());
        entity.setStatus(this.statusRepository.findAll().get(0));
        final Note note = new Note();
        note.setCustomer(entity);
        note.setDescription("Description");
        entity.setNotes(Arrays.asList(note));

        this.customerRepository.save(entity);

        List<Customer> findAll = this.customerRepository.findAll();
        org.junit.Assert.assertEquals(1, findAll.size());
        this.customerRepository.delete(findAll.get(0).getId());
        findAll = this.customerRepository.findAll();
        org.junit.Assert.assertEquals(0, findAll.size());
    }

}
