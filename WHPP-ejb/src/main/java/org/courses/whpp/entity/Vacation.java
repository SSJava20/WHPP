package org.courses.whpp.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author Roman Kostyrko <nubaseg@gmail.com>
 * Created on Jun 13, 2012, 8:06:18 PM
 */
@Entity
@Table(name = "vacation"/*, catalog = "wh1", schema = ""*/)
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Vacation.findAll", query = "SELECT v FROM Vacation v"),
	@NamedQuery(name = "Vacation.findById", query = "SELECT v FROM Vacation v WHERE v.id = :id"),
	@NamedQuery(name = "Vacation.findByType", query = "SELECT v FROM Vacation v WHERE v.type = :type"),
	@NamedQuery(name = "Vacation.findByDateStart", query = "SELECT v FROM Vacation v WHERE v.dateStart = :dateStart"),
	@NamedQuery(name = "Vacation.findByDateFinish", query = "SELECT v FROM Vacation v WHERE v.dateFinish = :dateFinish"),
	@NamedQuery(name = "Vacation.findByDateAdd", query = "SELECT v FROM Vacation v WHERE v.dateAdd = :dateAdd")})
public class Vacation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false)
	private Integer id;

	@Basic(optional = false)
    @NotNull
    @Column(name = "TYPE", nullable = false)
	private int type;

	@Basic(optional = false)
    @NotNull
    @Column(name = "DATE_START", nullable = false)
    @Temporal(TemporalType.DATE)
	private Date dateStart;

	@Basic(optional = false)
    @NotNull
    @Column(name = "DATE_FINISH", nullable = false)
    @Temporal(TemporalType.DATE)
	private Date dateFinish;

	@Basic(optional = false)
    @NotNull
    @Column(name = "DATE_ADD", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
	private Date dateAdd;

	@JoinColumn(name = "EMP_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
	private Employee empId;

	@JoinColumn(name = "USR_ADD", referencedColumnName = "userid", nullable = false)
    @ManyToOne(optional = false)
	private User usrAdd;

	public Vacation() {
	}

	public Vacation(Integer id) {
		this.id = id;
	}

	public Vacation(Integer id, int type, Date dateStart, Date dateFinish, Date dateAdd) {
		this.id = id;
		this.type = type;
		this.dateStart = dateStart;
		this.dateFinish = dateFinish;
		this.dateAdd = dateAdd;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateFinish() {
		return dateFinish;
	}

	public void setDateFinish(Date dateFinish) {
		this.dateFinish = dateFinish;
	}

	public Date getDateAdd() {
		return dateAdd;
	}

	public void setDateAdd(Date dateAdd) {
		this.dateAdd = dateAdd;
	}

	public Employee getEmpId() {
		return empId;
	}

	public void setEmpId(Employee empId) {
		this.empId = empId;
	}

	public User getUsrAdd() {
		return usrAdd;
	}

	public void setUsrAdd(User usrAdd) {
		this.usrAdd = usrAdd;
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
		if (!(object instanceof Vacation)) {
			return false;
		}
		Vacation other = (Vacation) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.courses.whpp.entity.Vacation[ id=" + id + " ]";
	}

}
