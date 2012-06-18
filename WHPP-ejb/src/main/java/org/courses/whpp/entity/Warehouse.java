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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com>
 * Created on Jun 13, 2012, 8:06:17 PM
 */
@Entity
@Table(name = "warehouse"/*, catalog = "wh1", schema = ""*/)
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Warehouse.findAll", query = "SELECT w FROM Warehouse w"),
	@NamedQuery(name = "Warehouse.findById", query = "SELECT w FROM Warehouse w WHERE w.id = :id"),
	@NamedQuery(name = "Warehouse.findByName", query = "SELECT w FROM Warehouse w WHERE w.name = :name")})
public class Warehouse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false)
	private Integer id;

	@Basic(optional = false)
    @NotNull
    @Column(name = "NAME", nullable = false)
	private int name;

	@JoinColumn(name = "COORDS", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
	private Coords coords;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "warehouseId")
	private List<ProductBalance> productBalanceList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sourceWh")
	private List<Ticket> ticketList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "destWh")
	private List<Ticket> ticketList1;

	public Warehouse() {
	}

	public Warehouse(Integer id) {
		this.id = id;
	}

	public Warehouse(Integer id, int name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	public Coords getCoords() {
		return coords;
	}

	public void setCoords(Coords coords) {
		this.coords = coords;
	}

	@XmlTransient
	public List<ProductBalance> getProductBalanceList() {
		return productBalanceList;
	}

	public void setProductBalanceList(List<ProductBalance> productBalanceList) {
		this.productBalanceList = productBalanceList;
	}

	@XmlTransient
	public List<Ticket> getTicketList() {
		return ticketList;
	}

	public void setTicketList(List<Ticket> ticketList) {
		this.ticketList = ticketList;
	}

	@XmlTransient
	public List<Ticket> getTicketList1() {
		return ticketList1;
	}

	public void setTicketList1(List<Ticket> ticketList1) {
		this.ticketList1 = ticketList1;
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
		if (!(object instanceof Warehouse)) {
			return false;
		}
		Warehouse other = (Warehouse) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.courses.whpp.entity.Warehouse[ id=" + id + " ]";
	}

}
