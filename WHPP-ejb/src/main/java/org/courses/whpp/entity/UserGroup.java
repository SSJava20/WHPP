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

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com>
 * Created on Jun 13, 2012, 8:06:17 PM
 */
@Entity
@Table(name = "grp"/*, catalog = "wh1", schema = ""*/)
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "UserGroup.findAll", query = "SELECT u FROM UserGroup u"),
	@NamedQuery(name = "UserGroup.findByGroupid", query = "SELECT u FROM UserGroup u WHERE u.groupid = :groupid")})
public class UserGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "groupid", nullable = false, length = 20)
	private String groupid;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "groupid")
	private List<UserGroupRelation> userGroupRelationList;

	public UserGroup() {
	}

	public UserGroup(String groupid) {
		this.groupid = groupid;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	@XmlTransient
	public List<UserGroupRelation> getUserGroupRelationList() {
		return userGroupRelationList;
	}

	public void setUserGroupRelationList(List<UserGroupRelation> userGroupRelationList) {
		this.userGroupRelationList = userGroupRelationList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (groupid != null ? groupid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof UserGroup)) {
			return false;
		}
		UserGroup other = (UserGroup) object;
		if ((this.groupid == null && other.groupid != null) || (this.groupid != null && !this.groupid.equals(other.groupid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.courses.whpp.entity.UserGroup[ groupid=" + groupid + " ]";
	}

}
