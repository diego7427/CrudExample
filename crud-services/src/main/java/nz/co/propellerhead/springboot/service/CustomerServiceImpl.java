/**
 * CustomerServiceImpl.java 09-abr-2018
 *
 * Copyright 2018 INDITEX.
 * Departamento de Sistemas
 */
package nz.co.propellerhead.springboot.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nz.co.propellerhead.springboot.adapt.AdapterFromDTO;
import nz.co.propellerhead.springboot.adapt.AdapterTODTO;
import nz.co.propellerhead.springboot.dto.CustomerDTO;
import nz.co.propellerhead.springboot.dto.StatusDTO;
import nz.co.propellerhead.springboot.repositories.CustomerRepository;
import nz.co.propellerhead.springboot.repositories.StatusRepository;

/**
 * The Class CustomerServiceImpl.
 *
 * @author <a href="${email}">${author}</a>
 */
@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private StatusRepository statusRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerDTO findById(final Long id) {
        return AdapterTODTO.adapt(this.customerRepository.findOne(id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerDTO findByName(final String name) {
        return AdapterTODTO.adapt(this.customerRepository.findByName(name));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveCustomer(final CustomerDTO user) {
        if (user.getId() == null) {
            user.setCreationDateTime(new Date());
        }
        this.customerRepository.save(AdapterFromDTO.adapt(user));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateCustomer(final CustomerDTO user) {
        saveCustomer(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteCustomerById(final Long id) {
        this.customerRepository.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAllCustomers() {
        this.customerRepository.deleteAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CustomerDTO> findAllCustomers() {
        return AdapterTODTO.adaptCustomerList(this.customerRepository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCustomerExist(final CustomerDTO user) {
        return findByName(user.getName()) != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<StatusDTO> getAllStatus() {
        return AdapterTODTO.adaptStatusList(this.statusRepository.findAll());
    }

}
