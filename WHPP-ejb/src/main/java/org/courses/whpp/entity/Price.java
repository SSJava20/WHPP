package org.courses.whpp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @author Roman Kostyrko <nubaseg@gmail.com>
 * Created on Jun 13, 2012, 8:06:18 PM
 */
@Entity
@Table(name = "price"/*, catalog = "wh1", schema = ""*/)
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Price.findAll", query = "SELECT p FROM Price p"),
	@NamedQuery(name = "Price.findByPriceId", query = "SELECT p FROM Price p WHERE p.priceId = :priceId"),
	@NamedQuery(name = "Price.findByValue", query = "SELECT p FROM Price p WHERE p.value = :value"),
	@NamedQuery(name = "Price.findByActDate", query = "SELECT p FROM Price p WHERE p.actDate = :actDate")})
public class Price implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRICE_ID", nullable = false)
	private Integer priceId;

	// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Basic(optional = false)
    @NotNull
    @Column(name = "VALUE", nullable = false, precision = 10, scale = 2)
	private BigDecimal value;

	@Basic(optional = false)
    @NotNull
    @Column(name = "ACT_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
	private Date actDate;

	@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
	private Product productId;

	public Price() {
	}

	public Price(Integer priceId) {
		this.priceId = priceId;
	}

	public Price(Integer priceId, BigDecimal value, Date actDate) {
		this.priceId = priceId;
		this.value = value;
		this.actDate = actDate;
	}

	public Integer getPriceId() {
		return priceId;
	}

	public void setPriceId(Integer priceId) {
		this.priceId = priceId;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Date getActDate() {
		return actDate;
	}

	public void setActDate(Date actDate) {
		this.actDate = actDate;
	}

	public Product getProductId() {
		return productId;
	}

	public void setProductId(Product productId) {
		this.productId = productId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (priceId != null ? priceId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Price)) {
			return false;
		}
		Price other = (Price) object;
		if ((this.priceId == null && other.priceId != null) || (this.priceId != null && !this.priceId.equals(other.priceId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.courses.whpp.entity.Price[ priceId=" + priceId + " ]";
	}

}
