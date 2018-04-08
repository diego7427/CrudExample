package nz.co.propellerhead.springboot.service;

import java.util.List;

import nz.co.propellerhead.springboot.dto.CustomerDTO;
import nz.co.propellerhead.springboot.dto.StatusDTO;

public interface CustomerService {

    CustomerDTO findById(Long id);

    CustomerDTO findByName(String name);

    void saveCustomer(CustomerDTO user);

    void updateCustomer(CustomerDTO user);

    void deleteCustomerById(Long id);

    void deleteAllCustomers();

    List<CustomerDTO> findAllCustomers();

    boolean isCustomerExist(CustomerDTO user);

    List<StatusDTO> getAllStatus();
}