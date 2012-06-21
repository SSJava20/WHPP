package org.courses.whpp.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com> Created on Jun 13, 2012, 8:06:19
 * PM
 */
@Entity
@Table(name = "worktime"/*
 * , catalog = "wh1", schema = ""
 */)
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Worktime.findAll", query = "SELECT w FROM Worktime w"),
    @NamedQuery(name = "Worktime.findById", query = "SELECT w FROM Worktime w WHERE w.id = :id"),
    @NamedQuery(name = "Worktime.findByIntime", query = "SELECT w FROM Worktime w WHERE w.intime = :intime"),
    @NamedQuery(name = "Worktime.findByOuttime", query = "SELECT w FROM Worktime w WHERE w.outtime = :outtime"),
    @NamedQuery(name = "Worktime.findOpenedByEmployeeId", query = "SELECT w FROM Worktime w WHERE w.ountime = null and w.EMP_ID = :EmployeeId")
})
public class Worktime implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@NotNull
	@Column(name = "ID", nullable = false)
	private Integer id;

	@Basic(optional = false)
	@NotNull
	@Column(name = "INTIME", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date intime;

	@Basic(optional = false)
	@Column(name = "OUTTIME", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date outtime;

    public Worktime(Date intime, Date outtime, Employee empId)
    {
        this.intime = intime;
        this.outtime = outtime;
        this.empId = empId;
    }


    public Integer getId()
    {
        return id;
    }

	public Worktime() {
	}

	public Worktime(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getIntime() {
		return intime;
	}

	public void setIntime(Date intime) {
		this.intime = intime;
	}

	public Date getOuttime() {
		return outtime;
	}

	public void setOuttime(Date outtime) {
		this.outtime = outtime;
	}

	public Employee getEmpId() {
		return empId;
	}

	public void setEmpId(Employee empId) {
		this.empId = empId;
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
		if (!(object instanceof Worktime)) {
			return false;
		}
		Worktime other = (Worktime) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.courses.whpp.entity.Worktime[ id=" + id + " ]";
	}
}
