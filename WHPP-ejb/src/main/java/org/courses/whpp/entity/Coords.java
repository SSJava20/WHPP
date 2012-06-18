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
 * Created on Jun 13, 2012, 8:06:18 PM
 */
@Entity
@Table(name = "coords"/*, catalog = "wh1", schema = ""*/)
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Coords.findAll", query = "SELECT c FROM Coords c"),
	@NamedQuery(name = "Coords.findById", query = "SELECT c FROM Coords c WHERE c.id = :id"),
	@NamedQuery(name = "Coords.findByLatitude", query = "SELECT c FROM Coords c WHERE c.latitude = :latitude"),
	@NamedQuery(name = "Coords.findByLongitude", query = "SELECT c FROM Coords c WHERE c.longitude = :longitude"),
	@NamedQuery(name = "Coords.findByAddress", query = "SELECT c FROM Coords c WHERE c.address = :address")})
public class Coords implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false)
	private Integer id;

	@Basic(optional = false)
    @NotNull
    @Column(name = "LATITUDE", nullable = false)
	private double latitude;

	@Basic(optional = false)
    @NotNull
    @Column(name = "LONGITUDE", nullable = false)
	private double longitude;

	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "ADDRESS", nullable = false, length = 200)
	private String address;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "coordsId")
	private List<Routepoint> routepointList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "coords")
	private List<Warehouse> warehouseList;

	public Coords() {
	}

	public Coords(Integer id) {
		this.id = id;
	}

	public Coords(Integer id, int latitude, int longitude, String address) {
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@XmlTransient
	public List<Routepoint> getRoutepointList() {
		return routepointList;
	}

	public void setRoutepointList(List<Routepoint> routepointList) {
		this.routepointList = routepointList;
	}

	@XmlTransient
	public List<Warehouse> getWarehouseList() {
		return warehouseList;
	}

	public void setWarehouseList(List<Warehouse> warehouseList) {
		this.warehouseList = warehouseList;
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
		if (!(object instanceof Coords)) {
			return false;
		}
		Coords other = (Coords) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return latitude+","+longitude;
	}

}
