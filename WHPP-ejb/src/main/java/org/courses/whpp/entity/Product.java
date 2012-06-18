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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * Created on Jun 13, 2012, 8:06:17 PM
 */
@Entity
@Table(name = "product"/*, catalog = "wh1", schema = ""*/)
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
	@NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.id = :id"),
	@NamedQuery(name = "Product.findByName", query = "SELECT p FROM Product p WHERE p.name = :name"),
	@NamedQuery(name = "Product.findByUnits", query = "SELECT p FROM Product p WHERE p.units = :units"),
	@NamedQuery(name = "Product.findByCode1", query = "SELECT p FROM Product p WHERE p.code1 = :code1"),
	@NamedQuery(name = "Product.findByCode2", query = "SELECT p FROM Product p WHERE p.code2 = :code2")})
public class Product implements Serializable {
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

	@Basic(optional = false)
    @NotNull
    @Column(name = "UNITS", nullable = false)
	private int units;

	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CODE1", nullable = false, length = 20)
	private String code1;

	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CODE2", nullable = false, length = 20)
	private String code2;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
	private List<ProductBalance> productBalanceList;

	@JoinColumn(name = "MANUFACTURER", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
	private Manufacturer manufacturer;

	@JoinColumn(name = "CATEGORY", referencedColumnName = "CATEGORY_ID", nullable = false)
    @ManyToOne(optional = false)
	private Category category;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
	private List<Price> priceList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
	private List<TicketItem> ticketItemList;

	public Product() {
	}

	public Product(Integer id) {
		this.id = id;
	}

	public Product(Integer id, String name, int units, String code1, String code2) {
		this.id = id;
		this.name = name;
		this.units = units;
		this.code1 = code1;
		this.code2 = code2;
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

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public String getCode1() {
		return code1;
	}

	public void setCode1(String code1) {
		this.code1 = code1;
	}

	public String getCode2() {
		return code2;
	}

	public void setCode2(String code2) {
		this.code2 = code2;
	}

	@XmlTransient
	public List<ProductBalance> getProductBalanceList() {
		return productBalanceList;
	}

	public void setProductBalanceList(List<ProductBalance> productBalanceList) {
		this.productBalanceList = productBalanceList;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@XmlTransient
	public List<Price> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<Price> priceList) {
		this.priceList = priceList;
	}

	@XmlTransient
	public List<TicketItem> getTicketItemList() {
		return ticketItemList;
	}

	public void setTicketItemList(List<TicketItem> ticketItemList) {
		this.ticketItemList = ticketItemList;
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
		if (!(object instanceof Product)) {
			return false;
		}
		Product other = (Product) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.courses.whpp.entity.Product[ id=" + id + " ]";
	}

}
