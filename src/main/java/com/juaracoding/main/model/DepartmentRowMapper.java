package com.juaracoding.main.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DepartmentRowMapper implements RowMapper<Department> {
	
	@Override
	public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
		Department depar = new Department();
		depar.setDepartment(rs.getString("department"));
		depar.setJumlah_pegawai(rs.getInt("jumlah_pegawai"));
		
		return depar;
	}

}