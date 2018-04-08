/**
 * Customer.java 06-abr-2018
 *
 * Copyright 2018 INDITEX.
 * Departamento de Sistemas
 */
package nz.co.propellerhead.springboot.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * The Class Customer.
 *
 * @author <a href="${email}">${author}</a>
 */

public class CustomerDTO implements Serializable {

    private Long id;
    private StatusDTO status;

    private Date creationDateTime;

    private String name;

    private String address;
    @JsonManagedReference
    private List<NoteDTO> notes = new ArrayList<>();

    /**
     * Obtiene id.
     *
     * @return id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Establece id.
     *
     * @param id
     *            nuevo id
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Obtiene status id.
     *
     * @return status id
     */
    public StatusDTO getStatus() {
        return this.status;
    }

    /**
     * Establece status id.
     *
     * @param status
     *            nuevo status
     */
    public void setStatus(final StatusDTO status) {
        this.status = status;
    }

    /**
     * Obtiene creation date time.
     *
     * @return creation date time
     */
    public Date getCreationDateTime() {
        return this.creationDateTime;
    }

    /**
     * Establece creation date time.
     *
     * @param creationDateTime
     *            nuevo creation date time
     */
    public void setCreationDateTime(final Date creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    /**
     * Obtiene name.
     *
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Establece name.
     *
     * @param name
     *            nuevo name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Obtiene address.
     *
     * @return address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Establece address.
     *
     * @param address
     *            nuevo address
     */
    public void setAddress(final String address) {
        this.address = address;
    }

    /**
     * Obtiene notes.
     *
     * @return notes
     */
    public List<NoteDTO> getNotes() {
        return this.notes;
    }

    /**
     * Establece notes.
     *
     * @param notes
     *            nuevo notes
     */
    public void setNotes(final List<NoteDTO> notes) {
        this.notes = notes;
    }

    /**
     * Anade el note.
     *
     * @param note
     *            note
     */
    public void addNote(final NoteDTO note) {
        this.notes.add(note);
        note.setCustomer(this);
    }

    /**
     * Elimina el note.
     *
     * @param note
     *            note
     */
    public void removeNote(final NoteDTO note) {
        this.notes.remove(note);
        note.setCustomer(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof CustomerDTO)) {
            return false;
        }
        final CustomerDTO castOther = (CustomerDTO) other;
        return new EqualsBuilder().append(this.id, castOther.id).append(this.status, castOther.status)
                .append(this.creationDateTime, castOther.creationDateTime).append(this.name, castOther.name)
                .append(this.address, castOther.address).isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.id).append(this.status).append(this.creationDateTime).append(this.name)
                .append(this.address).toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", this.id).append("status", this.status)
                .append("creationDateTime", this.creationDateTime).append("name", this.name)
                .append("address", this.address).toString();
    }

}
