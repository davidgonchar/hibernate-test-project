package hibernate.test;

import hibernate.test.dto.EmployeeEntity;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

public class TestHibernate {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            //Add new Employee object
//            session.beginTransaction();
            EmployeeEntity emp = new EmployeeEntity();
            emp.setEmail("david.gonchar@gmail.com");
            emp.setFirstName("david ");
            emp.setLastName("gonchar");
            session.save(emp);
//            session.getTransaction().commit();
        } catch (ConstraintViolationException e) {
            System.out.println("Failure: " + e.getMessage());
//            session.getTransaction().rollback();
        }

        Query query = session.createQuery("from EmployeeEntity");
        List<EmployeeEntity> empList = (List<EmployeeEntity>)query.list();
        for (EmployeeEntity empl : empList) {
            System.out.println("List of Employees::"+empl.getEmployeeId()+", "+empl.getEmail());
        }
        HibernateUtil.shutdown();
    }
}
