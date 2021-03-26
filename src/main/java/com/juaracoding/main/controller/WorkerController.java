package com.juaracoding.main.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juaracoding.main.model.Department;
import com.juaracoding.main.model.DepartmentRowMapper;
import com.juaracoding.main.model.Worker;
import com.juaracoding.main.model.WorkerRowMapper;

@RestController
@RequestMapping("/worker")
public class WorkerController {

	@Autowired
	JdbcTemplate jdbc;


	public List<Worker> getWorker() {
		String sql = "SELECT * FROM tbl_worker";
		List<Worker> worker = jdbc.query(sql, new WorkerRowMapper());
		return worker;
	}

	public int insertWorker(Worker worker) {
		return jdbc.update(
				"INSERT INTO tbl_worker(worker_id,first_name,last_name,salary,joining_date,department) values ('"
						+ worker.getWorker_id() + "','" + worker.getFirst_name() + "','" + worker.getLast_name() + "','"
						+ worker.getSalary() + "','" + worker.getJoining_date() + "','" + worker.getDepartment()
						+ "')");
	}

	public int updateWorker(String id, Worker worker) {
		return jdbc.update("UPDATE tbl_worker SET first_name = '" + worker.getFirst_name() + "', last_name = '"
				+ worker.getLast_name() + "', salary = '" + worker.getSalary() + "', joining_date = '"
				+ worker.getJoining_date() + "', department = '" + worker.getDepartment() + "' WHERE worker_id = '" + id
				+ "' ");
	}

	public int deleteWorker(String id) {
		return jdbc.update("DELETE FROM tbl_worker WHERE worker_id = '" + id + "'");
	}
	

//	Gaji Tertinggi
	@GetMapping("/gajitertinggi")
	public List<Worker> listMaxGaji() {
		String sql = "SELECT * FROM tbl_worker ORDER BY salary DESC LIMIT 5";
		List<Worker> worker = jdbc.query(sql, new WorkerRowMapper());
		return worker;
	}

//	Gaji Yang Sama
	@GetMapping("/gajisama")
	public List<Worker> listGaji() {
		String sql = "SELECT * FROM tbl_worker WHERE salary in (SELECT salary FROM tbl_worker GROUP BY salary HAVING COUNT(*) > 1 );";
		List<Worker> worker = jdbc.query(sql, new WorkerRowMapper());
		return worker;
	}
	
	public List<Department> getByDepartment(Department depar) {
		String sql = "CALL GetDepartment ('"+depar.getDepartment()+"')";
		List<Department> work = jdbc.query(sql, new DepartmentRowMapper());
		return work;
	}
//	Show By Department
	@GetMapping("/bagian")
	public List<Department> listByDepartment(@RequestBody Department depar) {
		return getByDepartment(depar);
	}

//	Create
	@PostMapping("/")
	public String add(@RequestBody Worker worker) {

		if (this.insertWorker(worker) == 1) {
			return "Insert data berhasil";
		} else {
			return "Insert data gagal";
		}
	}

//	Read
	@GetMapping("/")
	public List<Worker> list() {
		return getWorker();
	}

//	Update	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Worker worker, @PathVariable String id) {
		try {
			updateWorker(id, worker);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

//	Delete
	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		deleteWorker(id);
	}

}
