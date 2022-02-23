package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }
   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public void add(Car car) {
      sessionFactory.getCurrentSession().save(car);
   }
   @Override
   @SuppressWarnings("unchecked")
   public List<Car> listCars() {
      TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car");
      return query.getResultList();
   }
   @Override
   public User getUserByCarId (long carId) {
      Query query = sessionFactory.getCurrentSession().createQuery("from User where carId = :carId");
      query.setParameter("carId", carId);
      User user = (User) query.getSingleResult();
      return user;
   }

   @Override
   public void cleanUsersTable() {
      try (Session session = sessionFactory.openSession()){
         Transaction transaction = session.beginTransaction();
         Query query = session.createSQLQuery("TRUNCATE TABLE users;")
                 .addEntity(User.class);
         query.executeUpdate();
         transaction.commit();
      }catch (Exception ignored) {
      }
   }


}
