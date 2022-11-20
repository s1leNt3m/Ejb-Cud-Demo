package com.services;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Student;

@Remote
public interface StudentServiceRemote {
	public List<Student> findAll();
	public void create(Student student);
	public void delete(int id);
	public void update(Student student);
}
