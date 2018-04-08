/**
 * AdaptFromDTO.java 08-abr-2018
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
 * The Class AdaptFromDTO.
 *
 * @author <a href="${email}">${author}</a>
 */
public class AdapterFromDTO {

    /**
     * Adapt.
     *
     * @param dto
     *            dto
     * @return the status
     */
    public static Status adapt(final StatusDTO dto) {
        Status returnValue = null;
        if (dto != null) {
            returnValue = new Status();
            returnValue.setDescription(dto.getDescription());
            returnValue.setId(dto.getId());
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
    public static Note adapt(final NoteDTO dto, final Customer customer) {
        Note returnValue = null;
        if (dto != null) {
            returnValue = new Note();
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
    public static List<Note> adaptNoteList(final List<NoteDTO> notes, final Customer customer) {
        final List<Note> returnValue = new ArrayList<Note>();
        if ((notes != null) && !notes.isEmpty()) {
            for (final NoteDTO dto : notes) {
                if (!dto.getDescription().isEmpty()) {
                    returnValue.add(adapt(dto, customer));
                }
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
    public static Customer adapt(final CustomerDTO dto) {
        Customer returnValue = null;
        if (dto != null) {
            returnValue = new Customer();
            returnValue.setAddress(dto.getAddress());
            returnValue.setCreationDateTime(dto.getCreationDateTime());
            returnValue.setId(dto.getId());
            returnValue.setName(dto.getName());
            returnValue.setNotes(adaptNoteList(dto.getNotes(), returnValue));
            returnValue.setStatus(adapt(dto.getStatus()));
        }
        return returnValue;
    }

}
