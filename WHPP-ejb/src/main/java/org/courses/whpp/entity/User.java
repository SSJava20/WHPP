package org.courses.whpp.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.courses.whpp.utils.PasswordHash;

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com> Created on Jun 13, 2012, 8:06:19
 * PM
 */
@Entity
@Table(name = "usr"/*, catalog = "wh1", schema = ""*/)
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
	@NamedQuery(name = "User.findByUserid", query = "SELECT u FROM User u WHERE u.userid = :userid"),
	@NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")})
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 20)
	@Column(name = "userid", nullable = false, length = 20)
	private String userid;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 32)
	@Column(name = "password", nullable = false, length = 32)
	private String password;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usrId")
	private List<Client> clientList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userid")
	private List<UserGroupRelation> userGroupRelationList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usrAdd")
	private List<Vacation> vacationList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usrUpdated")
	private List<Ticket> ticketList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usrCreated")
	private List<Ticket> ticketList1;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
	private List<Employee> employeeList;

	public User() {
	}

	public User(String userid) {
		this.userid = userid;
	}

	public User(String userid, String password) {
		this.userid = userid;
		this.password = password;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws Exception {
		this.password = PasswordHash.codePassword(password);
	}

	@XmlTransient
	public List<Client> getClientList() {
		return clientList;
	}

	public void setClientList(List<Client> clientList) {
		this.clientList = clientList;
	}

	@XmlTransient
	public List<UserGroupRelation> getUserGroupRelationList() {
		return userGroupRelationList;
	}

	public void setUserGroupRelationList(List<UserGroupRelation> userGroupRelationList) {
		this.userGroupRelationList = userGroupRelationList;
	}

	@XmlTransient
	public List<Vacation> getVacationList() {
		return vacationList;
	}

	public void setVacationList(List<Vacation> vacationList) {
		this.vacationList = vacationList;
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

	@XmlTransient
	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (userid != null ? userid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof User)) {
			return false;
		}
		User other = (User) object;
		if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return userid;
	}
}
