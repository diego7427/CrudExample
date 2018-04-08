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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * The Class Status.
 *
 * @author <a href="${email}">${author}</a>
 */
@Entity
@Table(name = "STATUS")
public class Status implements Serializable {

    private static final long serialVersionUID = 3630767867504616396L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    /**
     * Obtiene id.
     *
     * @return id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Establece id.
     *
     * @param id
     *            nuevo id
     */
    public void setId(final Integer id) {
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
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof Status)) {
            return false;
        }
        final Status castOther = (Status) other;
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
