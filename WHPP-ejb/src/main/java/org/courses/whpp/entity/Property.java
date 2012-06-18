package org.courses.whpp.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com>
 * Created on Jun 13, 2012, 8:06:17 PM
 */
@Entity
@Table(name = "property"/*, catalog = "wh1", schema = ""*/)
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Property.findAll", query = "SELECT p FROM Property p"),
	@NamedQuery(name = "Property.findByPropertyId", query = "SELECT p FROM Property p WHERE p.propertyId = :propertyId"),
	@NamedQuery(name = "Property.findByPname", query = "SELECT p FROM Property p WHERE p.pname = :pname"),
	@NamedQuery(name = "Property.findByPvalue", query = "SELECT p FROM Property p WHERE p.pvalue = :pvalue")})
public class Property implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PROPERTY_ID", nullable = false)
	private Integer propertyId;

	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PNAME", nullable = false, length = 100)
	private String pname;

	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PVALUE", nullable = false, length = 100)
	private String pvalue;

	@JoinColumn(name = "CATEGORY_ID", referencedColumnName = "CATEGORY_ID", nullable = false)
    @ManyToOne(optional = false)
	private Category categoryId;

	public Property() {
	}

	public Property(Integer propertyId) {
		this.propertyId = propertyId;
	}

	public Property(Integer propertyId, String pname, String pvalue) {
		this.propertyId = propertyId;
		this.pname = pname;
		this.pvalue = pvalue;
	}

	public Integer getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPvalue() {
		return pvalue;
	}

	public void setPvalue(String pvalue) {
		this.pvalue = pvalue;
	}

	public Category getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Category categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (propertyId != null ? propertyId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Property)) {
			return false;
		}
		Property other = (Property) object;
		if ((this.propertyId == null && other.propertyId != null) || (this.propertyId != null && !this.propertyId.equals(other.propertyId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.courses.whpp.entity.Property[ propertyId=" + propertyId + " ]";
	}

}
