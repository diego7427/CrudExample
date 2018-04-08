/**
 * Customer.java 06-abr-2018
 *
 * Copyright 2018 INDITEX.
 * Departamento de Sistemas
 */
package nz.co.propellerhead.springboot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * The Class Customer.
 *
 * @author <a href="${email}">${author}</a>
 */
@Entity(name = "CUSTOMER")
@Table(name = "CUSTOMER")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STATUS_ID")
    private Status status;

    @Column(name = "CREATION_DATETIME", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date creationDateTime;

    @NotEmpty
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Note> notes = new ArrayList<>();

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
    public Status getStatus() {
        return this.status;
    }

    /**
     * Establece status id.
     *
     * @param status
     *            nuevo status
     */
    public void setStatus(final Status status) {
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
    public List<Note> getNotes() {
        return this.notes;
    }

    /**
     * Establece notes.
     *
     * @param notes
     *            nuevo notes
     */
    public void setNotes(final List<Note> notes) {
        this.notes = notes;
    }

    /**
     * Anade el note.
     *
     * @param note
     *            note
     */
    public void addNote(final Note note) {
        this.notes.add(note);
        note.setCustomer(this);
    }

    /**
     * Elimina el note.
     *
     * @param note
     *            note
     */
    public void removeNote(final Note note) {
        this.notes.remove(note);
        note.setCustomer(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof Customer)) {
            return false;
        }
        final Customer castOther = (Customer) other;
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
