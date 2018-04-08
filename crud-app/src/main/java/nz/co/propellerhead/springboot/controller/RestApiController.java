package nz.co.propellerhead.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import nz.co.propellerhead.springboot.dto.CustomerDTO;
import nz.co.propellerhead.springboot.dto.StatusDTO;
import nz.co.propellerhead.springboot.service.CustomerService;
import nz.co.propellerhead.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class RestApiController {

    public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    CustomerService customerService; // Service which will do all data retrieval/manipulation work

    // -------------------Retrieve All Customers---------------------------------------------

    @RequestMapping(value = "/customer/", method = RequestMethod.GET)
    public ResponseEntity<List<CustomerDTO>> listAllCustomers() {
        final List<CustomerDTO> customers = this.customerService.findAllCustomers();
        if (customers.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<CustomerDTO>>(customers, HttpStatus.OK);
    }

    // -------------------Retrieve Single Customer------------------------------------------

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCustomer(@PathVariable("id") final long id) {
        logger.info("Fetching Customer with id {}", id);
        final CustomerDTO customer = this.customerService.findById(id);
        if (customer == null) {
            logger.error("Customer with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Customer with id " + id + " not found"),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CustomerDTO>(customer, HttpStatus.OK);
    }

    // -------------------Create a Customer-------------------------------------------

    @RequestMapping(value = "/customer/", method = RequestMethod.POST)
    public ResponseEntity<?> createCustomer(@RequestBody final CustomerDTO customer,
            final UriComponentsBuilder ucBuilder) {
        logger.info("Creating Customer : {}", customer);

        if (this.customerService.isCustomerExist(customer)) {
            logger.error("Unable to create. A Customer with name {} already exist", customer.getName());
            return new ResponseEntity(
                    new CustomErrorType(
                            "Unable to create. A Customer with name " + customer.getName() + " already exist."),
                    HttpStatus.CONFLICT);
        }
        this.customerService.saveCustomer(customer);

        final HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/customer/{id}").buildAndExpand(customer.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Customer ------------------------------------------------

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCustomer(@PathVariable("id") final long id,
            @RequestBody final CustomerDTO customer) {
        logger.info("Updating Customer with id {}", id);

        final CustomerDTO currentCustomer = this.customerService.findById(id);

        if (currentCustomer == null) {
            logger.error("Unable to update. Customer with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. Customer with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentCustomer.setName(customer.getName());
        currentCustomer.setAddress(customer.getAddress());
        currentCustomer.setStatus(customer.getStatus());
        currentCustomer.setNotes(customer.getNotes());

        this.customerService.updateCustomer(currentCustomer);
        return new ResponseEntity<CustomerDTO>(currentCustomer, HttpStatus.OK);
    }

    // ------------------- Delete a Customer-----------------------------------------

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") final long id) {
        logger.info("Fetching & Deleting Customer with id {}", id);

        final CustomerDTO customer = this.customerService.findById(id);
        if (customer == null) {
            logger.error("Unable to delete. Customer with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. Customer with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        this.customerService.deleteCustomerById(id);
        return new ResponseEntity<CustomerDTO>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Customers-----------------------------

    @RequestMapping(value = "/customer/", method = RequestMethod.DELETE)
    public ResponseEntity<CustomerDTO> deleteAllCustomers() {
        logger.info("Deleting All Customers");

        this.customerService.deleteAllCustomers();
        return new ResponseEntity<CustomerDTO>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/customer/status", method = RequestMethod.GET)
    public ResponseEntity<List<StatusDTO>> listAllComboValues() {
        final List<StatusDTO> status = this.customerService.getAllStatus();

        if (status.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<StatusDTO>>(status, HttpStatus.OK);
    }

}