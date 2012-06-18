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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Roman Kostyrko <nubaseg@gmail.com>
 * Created on Jun 13, 2012, 8:06:19 PM
 */
@Entity
@Table(name = "ticket"/*, catalog = "wh1", schema = ""*/)
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Ticket.findAll", query = "SELECT t FROM Ticket t"),
	@NamedQuery(name = "Ticket.findById", query = "SELECT t FROM Ticket t WHERE t.id = :id"),
	@NamedQuery(name = "Ticket.findByTicketNum", query = "SELECT t FROM Ticket t WHERE t.ticketNum = :ticketNum"),
	@NamedQuery(name = "Ticket.findByClientId", query = "SELECT t FROM Ticket t WHERE t.clientId = :clientId"),
	@NamedQuery(name = "Ticket.findByStatus", query = "SELECT t FROM Ticket t WHERE t.status = :status"),
	@NamedQuery(name = "Ticket.findByType", query = "SELECT t FROM Ticket t WHERE t.type = :type"),
	@NamedQuery(name = "Ticket.findByCreateTime", query = "SELECT t FROM Ticket t WHERE t.createTime = :createTime"),
	@NamedQuery(name = "Ticket.findByUpdateTime", query = "SELECT t FROM Ticket t WHERE t.updateTime = :updateTime")})
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false)
	private Integer id;

	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "TICKET_NUM", nullable = false, length = 10)
	private String ticketNum;

	@Basic(optional = false)
    @NotNull
    @Column(name = "CLIENT_ID", nullable = false)
	private int clientId;

	@Basic(optional = false)
    @NotNull
    @Column(name = "STATUS", nullable = false)
	private int status;

	@Basic(optional = false)
    @NotNull
    @Column(name = "TYPE", nullable = false)
	private int type;

	@Basic(optional = false)
    @NotNull
    @Column(name = "CREATE_TIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	@Basic(optional = false)
    @NotNull
    @Column(name = "UPDATE_TIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	@JoinColumn(name = "SOURCE_WH", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
	private Warehouse sourceWh;

	@JoinColumn(name = "USR_UPDATED", referencedColumnName = "userid", nullable = false)
    @ManyToOne(optional = false)
	private User usrUpdated;

	@JoinColumn(name = "USR_CREATED", referencedColumnName = "userid", nullable = false)
    @ManyToOne(optional = false)
	private User usrCreated;

	@JoinColumn(name = "ROUTE_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
	private Route routeId;

	@JoinColumn(name = "DEST_WH", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
	private Warehouse destWh;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ticketId")
	private List<TicketItem> ticketItemList;

	public Ticket() {
	}

	public Ticket(Integer id) {
		this.id = id;
	}

	public Ticket(Integer id, String ticketNum, int clientId, int status, int type, Date createTime, Date updateTime) {
		this.id = id;
		this.ticketNum = ticketNum;
		this.clientId = clientId;
		this.status = status;
		this.type = type;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTicketNum() {
		return ticketNum;
	}

	public void setTicketNum(String ticketNum) {
		this.ticketNum = ticketNum;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Warehouse getSourceWh() {
		return sourceWh;
	}

	public void setSourceWh(Warehouse sourceWh) {
		this.sourceWh = sourceWh;
	}

	public User getUsrUpdated() {
		return usrUpdated;
	}

	public void setUsrUpdated(User usrUpdated) {
		this.usrUpdated = usrUpdated;
	}

	public User getUsrCreated() {
		return usrCreated;
	}

	public void setUsrCreated(User usrCreated) {
		this.usrCreated = usrCreated;
	}

	public Route getRouteId() {
		return routeId;
	}

	public void setRouteId(Route routeId) {
		this.routeId = routeId;
	}

	public Warehouse getDestWh() {
		return destWh;
	}

	public void setDestWh(Warehouse destWh) {
		this.destWh = destWh;
	}

	@XmlTransient
	public List<TicketItem> getTicketItemList() {
		return ticketItemList;
	}

	public void setTicketItemList(List<TicketItem> ticketItemList) {
		this.ticketItemList = ticketItemList;
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
		if (!(object instanceof Ticket)) {
			return false;
		}
		Ticket other = (Ticket) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.courses.whpp.entity.Ticket[ id=" + id + " ]";
	}

}
