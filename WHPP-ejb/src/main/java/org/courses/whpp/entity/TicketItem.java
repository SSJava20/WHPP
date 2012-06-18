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
 * Created on Jun 13, 2012, 8:06:19 PM
 */
@Entity
@Table(name = "ticketitem"/*, catalog = "wh1", schema = ""*/)
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "TicketItem.findAll", query = "SELECT t FROM TicketItem t"),
	@NamedQuery(name = "TicketItem.findById", query = "SELECT t FROM TicketItem t WHERE t.id = :id"),
	@NamedQuery(name = "TicketItem.findByQty", query = "SELECT t FROM TicketItem t WHERE t.qty = :qty")})
public class TicketItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false)
	private Integer id;

	@Basic(optional = false)
    @NotNull
    @Column(name = "QTY", nullable = false)
	private int qty;

	@JoinColumn(name = "PRODUCT", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
	private Product product;

	@JoinColumn(name = "TICKET_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
	private Ticket ticketId;

	public TicketItem() {
	}

	public TicketItem(Integer id) {
		this.id = id;
	}

	public TicketItem(Integer id, int qty) {
		this.id = id;
		this.qty = qty;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Ticket getTicketId() {
		return ticketId;
	}

	public void setTicketId(Ticket ticketId) {
		this.ticketId = ticketId;
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
		if (!(object instanceof TicketItem)) {
			return false;
		}
		TicketItem other = (TicketItem) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.courses.whpp.entity.TicketItem[ id=" + id + " ]";
	}

}
