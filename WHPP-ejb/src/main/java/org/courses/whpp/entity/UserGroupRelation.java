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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com>
 * Created on Jun 13, 2012, 8:06:17 PM
 */
@Entity
@Table(name = "grpusr"/*, catalog = "wh1", schema = ""*/, uniqueConstraints = {
	@UniqueConstraint(columnNames = {"userid", "groupid"})})
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "UserGroupRelation.findAll", query = "SELECT u FROM UserGroupRelation u"),
	@NamedQuery(name = "UserGroupRelation.findById", query = "SELECT u FROM UserGroupRelation u WHERE u.id = :id")})
public class UserGroupRelation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
	private Integer id;

	@JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false)
    @ManyToOne(optional = false)
	private User userid;

	@JoinColumn(name = "groupid", referencedColumnName = "groupid", nullable = false)
    @ManyToOne(optional = false)
	private UserGroup groupid;

	public UserGroupRelation() {
	}

	public UserGroupRelation(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUserid() {
		return userid;
	}

	public void setUserid(User userid) {
		this.userid = userid;
	}

	public UserGroup getGroupid() {
		return groupid;
	}

	public void setGroupid(UserGroup groupid) {
		this.groupid = groupid;
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
		if (!(object instanceof UserGroupRelation)) {
			return false;
		}
		UserGroupRelation other = (UserGroupRelation) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.courses.whpp.entity.UserGroupRelation[ id=" + id + " ]";
	}

}
