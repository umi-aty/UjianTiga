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

import com.juaracoding.main.model.Title;
import com.juaracoding.main.model.TitleRowMapper;

@RestController
@RequestMapping("/title")
public class TitleController {

	@Autowired
	JdbcTemplate jdbc;

	public List<Title> getTitle() {
		String sql = "SELECT * FROM tbl_title";
		List<Title> title = jdbc.query(sql, new TitleRowMapper());
		return title;
	}

	public int insertTitle(Title title) {
		return jdbc.update(
				"INSERT INTO tbl_title(worker_ref_id,worker_title,affected_from) values ('"
						+ title.getWorker_ref_id() + "','" + title.getWorker_title() + "','" + title.getAffected_from() + "')");
	}

	public int updateTitle(String id, Title title) {
		return jdbc.update("UPDATE tbl_title SET worker_ref_id = '" + title.getWorker_ref_id() + "', worker_title = '"
				+ title.getWorker_title() + "', affected_from= '" + title.getAffected_from() + "' WHERE worker_ref_id = '" + id
				+ "' ");
	}

	public int deleteTitle(String id) {
		return jdbc.update("DELETE FROM tbl_title WHERE worker_ref_id = '" + id + "'");
	}

//	Create
	@PostMapping("/")
	public String add(@RequestBody Title title) {

		if (this.insertTitle(title) == 1) {
			return "Insert data berhasil";
		} else {
			return "Insert data gagal";
		}
	}

//	Read
	@GetMapping("/")
	public List<Title> list() {
		return getTitle();
	}

//	Update	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Title title, @PathVariable String id) {
		try {
			updateTitle(id, title);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

//	Delete
	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		deleteTitle(id);
	}

}
