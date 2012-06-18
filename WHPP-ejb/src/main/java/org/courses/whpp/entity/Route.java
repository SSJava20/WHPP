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
@Table(name = "route"/*, catalog = "wh1", schema = ""*/)
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Route.findAll", query = "SELECT r FROM Route r"),
	@NamedQuery(name = "Route.findById", query = "SELECT r FROM Route r WHERE r.id = :id"),
	@NamedQuery(name = "Route.findByName", query = "SELECT r FROM Route r WHERE r.name = :name")})
public class Route implements Serializable {
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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "routeId")
	private List<Routepoint> routepointList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "routeId")
	private List<Ticket> ticketList;

	public Route() {
	}

	public Route(Integer id) {
		this.id = id;
	}

	public Route(Integer id, String name) {
		this.id = id;
		this.name = name;
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

	@XmlTransient
	public List<Routepoint> getRoutepointList() {
		return routepointList;
	}

	public void setRoutepointList(List<Routepoint> routepointList) {
		this.routepointList = routepointList;
	}

	@XmlTransient
	public List<Ticket> getTicketList() {
		return ticketList;
	}

	public void setTicketList(List<Ticket> ticketList) {
		this.ticketList = ticketList;
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
		if (!(object instanceof Route)) {
			return false;
		}
		Route other = (Route) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.courses.whpp.entity.Route[ id=" + id + " ]";
	}

}
