/**
 * AdapterTODTO.java 08-abr-2018
 *
 * Copyright 2018 INDITEX.
 * Departamento de Sistemas
 */
package nz.co.propellerhead.springboot.adapt;

import java.util.ArrayList;
import java.util.List;

import nz.co.propellerhead.springboot.dto.CustomerDTO;
import nz.co.propellerhead.springboot.dto.NoteDTO;
import nz.co.propellerhead.springboot.dto.StatusDTO;
import nz.co.propellerhead.springboot.model.Customer;
import nz.co.propellerhead.springboot.model.Note;
import nz.co.propellerhead.springboot.model.Status;

/**
 * The Class AdapterTODTO.
 *
 * @author <a href="${email}">${author}</a>
 */
public class AdapterTODTO {
    /**
     * Adapt.
     *
     * @param dto
     *            dto
     * @return the status
     */
    public static StatusDTO adapt(final Status dto) {
        StatusDTO returnValue = null;
        if (dto != null) {
            returnValue = new StatusDTO();
            returnValue.setDescription(dto.getDescription());
            returnValue.setId(dto.getId());
        }
        return returnValue;
    }

    /**
     * Adapt status list.
     *
     * @param status
     *            status
     * @return the list
     */
    public static List<StatusDTO> adaptStatusList(final List<Status> status) {
        final List<StatusDTO> returnValue = new ArrayList<StatusDTO>();
        if ((status != null) && !status.isEmpty()) {
            for (final Status dto : status) {
                returnValue.add(adapt(dto));
            }
        }
        return returnValue;
    }

    /**
     * Adapt.
     *
     * @param dto
     *            dto
     * @param customer
     *            customer
     * @return the note
     */
    public static NoteDTO adapt(final Note dto, final CustomerDTO customer) {
        NoteDTO returnValue = null;
        if (dto != null) {
            returnValue = new NoteDTO();
            returnValue.setDescription(dto.getDescription());
            returnValue.setId(dto.getId());
            returnValue.setCustomer(customer);
        }
        return returnValue;
    }

    /**
     * Adapt.
     *
     * @param notes
     *            notes
     * @param customer
     *            customer
     * @return the list
     */
    public static List<NoteDTO> adaptNoteList(final List<Note> notes, final CustomerDTO customer) {
        final List<NoteDTO> returnValue = new ArrayList<NoteDTO>();
        if ((notes != null) && !notes.isEmpty()) {

            for (final Note dto : notes) {
                returnValue.add(adapt(dto, customer));
            }
        }
        return returnValue;
    }

    /**
     * Adapt.
     *
     * @param dto
     *            dto
     * @return the customer
     */
    public static CustomerDTO adapt(final Customer dto) {
        CustomerDTO returnValue = null;
        if (dto != null) {
            returnValue = new CustomerDTO();
            returnValue.setAddress(dto.getAddress());
            returnValue.setCreationDateTime(dto.getCreationDateTime());
            returnValue.setId(dto.getId());
            returnValue.setName(dto.getName());
            returnValue.setNotes(adaptNoteList(dto.getNotes(), returnValue));
            returnValue.setStatus(adapt(dto.getStatus()));
        }
        return returnValue;
    }

    /**
     * Adapt customer list.
     *
     * @param customers
     *            customers
     * @return the list
     */
    public static List<CustomerDTO> adaptCustomerList(final List<Customer> customers) {
        final List<CustomerDTO> returnValue = new ArrayList<CustomerDTO>();
        if ((customers != null) && !customers.isEmpty()) {

            for (final Customer customer : customers) {
                returnValue.add(adapt(customer));
            }
        }
        return returnValue;
    }
}
