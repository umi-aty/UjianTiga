package com.juaracoding.main.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class WorkerRowMapper implements RowMapper<Worker> {
	
	@Override
	public Worker mapRow(ResultSet rs, int rowNum) throws SQLException {
		Worker worker = new Worker();
		worker.setWorker_id(rs.getInt("worker_id"));
		worker.setFirst_name(rs.getString("first_name"));
		worker.setLast_name(rs.getString("last_name"));
		worker.setSalary(rs.getDouble("salary"));
		worker.setJoining_date(rs.getString("joining_date"));
		worker.setDepartment(rs.getString("department"));
		
		return worker;
	}

}
