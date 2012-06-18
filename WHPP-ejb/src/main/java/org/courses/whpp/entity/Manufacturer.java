package org.courses.whpp.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com>
 * Created on Jun 13, 2012, 8:06:19 PM
 */
@Entity
@Table(name = "manufacturer"/*, catalog = "wh1", schema = ""*/)
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Manufacturer.findAll", query = "SELECT m FROM Manufacturer m"),
	@NamedQuery(name = "Manufacturer.findById", query = "SELECT m FROM Manufacturer m WHERE m.id = :id"),
	@NamedQuery(name = "Manufacturer.findByName", query = "SELECT m FROM Manufacturer m WHERE m.name = :name"),
	@NamedQuery(name = "Manufacturer.findByDescr", query = "SELECT m FROM Manufacturer m WHERE m.descr = :descr"),
	@NamedQuery(name = "Manufacturer.findByAdrress", query = "SELECT m FROM Manufacturer m WHERE m.adrress = :adrress"),
	@NamedQuery(name = "Manufacturer.findBySite", query = "SELECT m FROM Manufacturer m WHERE m.site = :site")})
public class Manufacturer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false)
	private Integer id;

	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NAME", nullable = false, length = 100)
	private String name;

	@Size(max = 100)
    @Column(name = "DESCR", length = 100)
	private String descr;

	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "ADRRESS", nullable = false, length = 200)
	private String adrress;

	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "SITE", nullable = false, length = 100)
	private String site;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "manufacturer")
	private List<Product> productList;

	public Manufacturer() {
	}

	public Manufacturer(Integer id) {
		this.id = id;
	}

	public Manufacturer(Integer id, String name, String adrress, String site) {
		this.id = id;
		this.name = name;
		this.adrress = adrress;
		this.site = site;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getAdrress() {
		return adrress;
	}

	public void setAdrress(String adrress) {
		this.adrress = adrress;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	@XmlTransient
	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Manufacturer)) {
			return false;
		}
		Manufacturer other = (Manufacturer) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.courses.whpp.entity.Manufacturer[ id=" + id + " ]";
	}

}
