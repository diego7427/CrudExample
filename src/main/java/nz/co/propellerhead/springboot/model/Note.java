/**
 * Status.java 06-abr-2018
 *
 * Copyright 2018 INDITEX.
 * Departamento de Sistemas
 */
package nz.co.propellerhead.springboot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * The Class Note.
 *
 * @author <a href="${email}">${author}</a>
 */
@Entity(name = "CUSTOMER_NOTE")
@Table(name = "CUSTOMER_NOTE")
public class Note implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

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
    public Customer getCustomer() {
        return this.customer;
    }

    /**
     * Establece customer.
     *
     * @param customer
     *            nuevo customer
     */
    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof Note)) {
            return false;
        }
        final Note castOther = (Note) other;
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
