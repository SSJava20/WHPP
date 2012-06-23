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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com> Created on Jun 13, 2012, 8:06:17
 * PM
 */
@Entity
@Table(name = "routepoint"/*, catalog = "wh1", schema = ""*/)
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Routepoint.findAll", query = "SELECT r FROM Routepoint r"),
	@NamedQuery(name = "Routepoint.findById", query = "SELECT r FROM Routepoint r WHERE r.id = :id"),
	@NamedQuery(name = "Routepoint.findByName", query = "SELECT r FROM Routepoint r WHERE r.name = :name")})
public class Routepoint implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@NotNull
	@Column(name = "ID", nullable = false)
	private Integer id;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "NAME", nullable = false, length = 50)
	private String name;

	@JoinColumn(name = "ROUTE_ID", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Route routeId;

	@JoinColumn(name = "COORDS_ID", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Coords coordsId;

	public Routepoint() {
	}

	public Routepoint(Integer id) {
		this.id = id;
	}

	public Routepoint(Integer id, String name) {
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

	public Route getRouteId() {
		return routeId;
	}

	public void setRouteId(Route routeId) {
		this.routeId = routeId;
	}

	public Coords getCoordsId() {
		return coordsId;
	}

	public void setCoordsId(Coords coordsId) {
		this.coordsId = coordsId;
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
		if (!(object instanceof Routepoint)) {
			return false;
		}
		Routepoint other = (Routepoint) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return name;
	}
}
