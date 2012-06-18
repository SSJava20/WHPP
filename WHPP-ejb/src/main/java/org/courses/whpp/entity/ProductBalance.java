package org.courses.whpp.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com>
 * Created on Jun 13, 2012, 8:06:17 PM
 */
@Entity
@Table(name = "productbalance"/*, catalog = "wh1", schema = ""*/)
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "ProductBalance.findAll", query = "SELECT p FROM ProductBalance p"),
	@NamedQuery(name = "ProductBalance.findById", query = "SELECT p FROM ProductBalance p WHERE p.id = :id"),
	@NamedQuery(name = "ProductBalance.findByBalance", query = "SELECT p FROM ProductBalance p WHERE p.balance = :balance"),
	@NamedQuery(name = "ProductBalance.findByReserved", query = "SELECT p FROM ProductBalance p WHERE p.reserved = :reserved")})
public class ProductBalance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false)
	private Integer id;

	@Basic(optional = false)
    @NotNull
    @Column(name = "BALANCE", nullable = false)
	private int balance;

	@Basic(optional = false)
    @NotNull
    @Column(name = "RESERVED", nullable = false)
	private int reserved;

	@JoinColumn(name = "WAREHOUSE_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
	private Warehouse warehouseId;

	@JoinColumn(name = "PRODUCT", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
	private Product product;

	public ProductBalance() {
	}

	public ProductBalance(Integer id) {
		this.id = id;
	}

	public ProductBalance(Integer id, int balance, int reserved) {
		this.id = id;
		this.balance = balance;
		this.reserved = reserved;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getReserved() {
		return reserved;
	}

	public void setReserved(int reserved) {
		this.reserved = reserved;
	}

	public Warehouse getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Warehouse warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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
		if (!(object instanceof ProductBalance)) {
			return false;
		}
		ProductBalance other = (ProductBalance) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.courses.whpp.entity.ProductBalance[ id=" + id + " ]";
	}

}
