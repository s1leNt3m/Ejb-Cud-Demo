package com.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.entities.Student;
import com.utils.HibernateUtil;

/**
 * Session Bean implementation class StudentService
 */
@Stateless
@LocalBean
public class StudentService implements StudentServiceRemote {

	/**
	 * Default constructor.
	 */
//	@PersistenceContext(unitName = "EjbCud")
//	private EntityManager em;
	static final SessionFactory factory = HibernateUtil.getsessionFactory();

	public StudentService() {
		// TODO Auto-generated constructor stub

	}

	public Student getStudentById(int id) {
		Student student = null;
		Transaction transaction = null;
		try (Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			student = session.find(Student.class, id);
			session.close();
		} catch (Exception e) {
			// TODO: handle exception
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

		return student;
	}

	@Override
	public List<Student> findAll() {
		Transaction transaction = null;
		List<Student> students = null;
		try (Session session = factory.openSession()) {
			transaction = session.beginTransaction();

			students = session.createQuery("SELECT s FROM student s").getResultList();
			session.close();

		} catch (Exception e) {
			// TODO: handle exception
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return students;
	}

	@Override
	public void create(Student student) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
		try (Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			session.save(student);
			session.close();
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
		Student student = null;
		try (Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			student = getStudentById(id);
			session.delete(student);
			session.close();
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

	}

	@Override
	public void update(Student student) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
		try (Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			session.update(student);
			session.close();
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

	}

}
