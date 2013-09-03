/**
 * 
 */
package com.coolisland.trackmystocks.database;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.coolisland.trackmystocks.utils.StringUtils;

/**
 * @author Silvio
 * 
 */
public class AccountBO {
	private BigDecimal id;
	private String name;
	private BigDecimal parentId;

	public AccountBO() {
	}

	public AccountBO(ResultSet rs) throws SQLException {
		try {
			setId(rs.getBigDecimal("ID"));
			setName(rs.getString("NAME"));
			setParentId(rs.getBigDecimal("PARENT_ID"));
		} catch (SQLException e) {
			System.out.println(rs.toString());
			e.printStackTrace();
			throw new SQLException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public String toString() {
		StringBuffer out = new StringBuffer(200);
		String myIndent = StringUtils.INDENT;

		StringUtils.appendHeader(out, "Account", "");

		StringUtils.appendNameValueLine(out, "ID", getId(), myIndent);
		StringUtils.appendNameValueLine(out, "Name", getName(), myIndent);
		StringUtils.appendNameValueLine(out, "Parent Account Id", getParentId(), myIndent);

		return out.toString();
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getParentId() {
		return parentId;
	}

	public void setParentId(BigDecimal parentId) {
		this.parentId = parentId;
	}

}
