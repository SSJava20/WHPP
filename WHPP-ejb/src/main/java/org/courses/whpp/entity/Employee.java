package org.courses.whpp.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com>
 * Created on Jun 13, 2012, 8:06:19 PM
 */
@Entity
@Table(name = "employee"/*, catalog = "wh1", schema = ""*/)
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
	@NamedQuery(name = "Employee.findById", query = "SELECT e FROM Employee e WHERE e.id = :id"),
	@NamedQuery(name = "Employee.findByFname", query = "SELECT e FROM Employee e WHERE e.fname = :fname"),
	@NamedQuery(name = "Employee.findByLname", query = "SELECT e FROM Employee e WHERE e.lname = :lname"),
	@NamedQuery(name = "Employee.findByPhone", query = "SELECT e FROM Employee e WHERE e.phone = :phone"),
	@NamedQuery(name = "Employee.findByEmail", query = "SELECT e FROM Employee e WHERE e.email = :email"),
	@NamedQuery(name = "Employee.findByBirth", query = "SELECT e FROM Employee e WHERE e.birth = :birth"),
	@NamedQuery(name = "Employee.findByCity", query = "SELECT e FROM Employee e WHERE e.city = :city"),
	@NamedQuery(name = "Employee.findByComment", query = "SELECT e FROM Employee e WHERE e.comment = :comment"),
	@NamedQuery(name = "Employee.findByWorkStatus", query = "SELECT e FROM Employee e WHERE e.workStatus = :workStatus")})
public class Employee implements Serializable {
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
    @Column(name = "FNAME", nullable = false, length = 100)
	private String fname;

	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "LNAME", nullable = false, length = 100)
	private String lname;

	@Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
	@Size(max = 100)
    @Column(name = "PHONE", length = 100)
	private String phone;

	@Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
	@Size(max = 100)
    @Column(name = "EMAIL", length = 100)
	private String email;

	@Column(name = "BIRTH")
    @Temporal(TemporalType.TIMESTAMP)
	private Date birth;

	@Size(max = 100)
    @Column(name = "CITY", length = 100)
	private String city;

	@Size(max = 200)
    @Column(name = "COMMENT", length = 200)
	private String comment;

	@Basic(optional = false)
    @NotNull
    @Column(name = "WORK_STATUS", nullable = false)
	private int workStatus;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "empId")
	private List<Vacation> vacationList;

	@JoinColumn(name = "POSITION", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
	private Position position;

	@JoinColumn(name = "USER_ID", referencedColumnName = "userid", nullable = false)
    @ManyToOne(optional = false)
	private User userId;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "empId")
	private List<Worktime> worktimeList;

	public Employee() {
	}

	public Employee(Integer id) {
		this.id = id;
	}

	public Employee(Integer id, String fname, String lname, int workStatus) {
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.workStatus = workStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(int workStatus) {
		this.workStatus = workStatus;
	}

	@XmlTransient
	public List<Vacation> getVacationList() {
		return vacationList;
	}

	public void setVacationList(List<Vacation> vacationList) {
		this.vacationList = vacationList;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	@XmlTransient
	public List<Worktime> getWorktimeList() {
		return worktimeList;
	}

	public void setWorktimeList(List<Worktime> worktimeList) {
		this.worktimeList = worktimeList;
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
		if (!(object instanceof Employee)) {
			return false;
		}
		Employee other = (Employee) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.courses.whpp.entity.Employee[ id=" + id + " ]";
	}

}
