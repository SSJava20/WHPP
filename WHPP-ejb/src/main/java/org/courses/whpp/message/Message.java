/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.whpp.message;

import java.io.Serializable;

/**
 *
 * @author NGAL
 */
public class Message implements Serializable {

	public Message(Integer driverId, Integer routeId) {
		m_driverId = driverId;
		m_routeId = routeId;
	}

	public Integer getDriverId() {
		return m_driverId;
	}

	public void setDriver_id(Integer driverId) {
		this.m_driverId = driverId;
	}

	public Integer getRouteId() {
		return m_routeId;
	}

	public void setRouteId(Integer routeId) {
		this.m_routeId = routeId;
	}

	private Integer m_driverId = -1;

	private Integer m_routeId = -1;
}