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

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private StatusRepository statusRepository;

    @Override
    public CustomerDTO findById(final Long id) {
        return AdapterTODTO.adapt(this.customerRepository.findOne(id));
    }

    @Override
    public CustomerDTO findByName(final String name) {
        return AdapterTODTO.adapt(this.customerRepository.findByName(name));
    }

    @Override
    public void saveCustomer(final CustomerDTO user) {
        user.setCreationDateTime(new Date());
        this.customerRepository.save(AdapterFromDTO.adapt(user));
    }

    @Override
    public void updateCustomer(final CustomerDTO user) {
        saveCustomer(user);
    }

    @Override
    public void deleteCustomerById(final Long id) {
        this.customerRepository.delete(id);
    }

    @Override
    public void deleteAllCustomers() {
        this.customerRepository.deleteAll();
    }

    @Override
    public List<CustomerDTO> findAllCustomers() {
        return AdapterTODTO.adaptCustomerList(this.customerRepository.findAll());
    }

    @Override
    public boolean isCustomerExist(final CustomerDTO user) {
        return findByName(user.getName()) != null;
    }

    @Override
    public List<StatusDTO> getAllStatus() {
        return AdapterTODTO.adaptStatusList(this.statusRepository.findAll());
    }

}
