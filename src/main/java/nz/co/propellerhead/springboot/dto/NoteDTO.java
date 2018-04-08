/**
 * Status.java 06-abr-2018
 *
 * Copyright 2018 INDITEX.
 * Departamento de Sistemas
 */
package nz.co.propellerhead.springboot.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * The Class Note.
 *
 * @author <a href="${email}">${author}</a>
 */

public class NoteDTO implements Serializable {

    private Long id;
    private String description;
    @JsonBackReference
    private CustomerDTO customer;

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
     * Obtiene description.
     *
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Establece description.
     *
     * @param description
     *            nuevo description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Obtiene customer.
     *
     * @return customer
     */
    public CustomerDTO getCustomer() {
        return this.customer;
    }

    /**
     * Establece customer.
     *
     * @param customer
     *            nuevo customer
     */
    public void setCustomer(final CustomerDTO customer) {
        this.customer = customer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof NoteDTO)) {
            return false;
        }
        final NoteDTO castOther = (NoteDTO) other;
        return new EqualsBuilder().append(this.id, castOther.id).append(this.description, castOther.description)
                .isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.id).append(this.description).toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", this.id).append("description", this.description).toString();
    }

}
