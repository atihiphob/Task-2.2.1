package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("model1", 1);
      userService.add(car1);
      User user1 = new User("User1", "Lastname1", "user1@mail.ru", car1);
      userService.add(user1);
      Car car2 = new Car("model2", 2);
      userService.add(car2);
      User user2 = new User("User2", "Lastname2", "user2@mail.ru", car2);
      userService.add(user2);
      Car car3 = new Car("model3", 3);
      userService.add(car3);
      User user3 = new User("User3", "Lastname3", "user3@mail.ru", car3);
      userService.add(user3);
      System.out.println(userService.getUserByCarId(1));
      System.out.println(userService.getUserByCarId(2));
      System.out.println(userService.getUserByCarId(3));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }
      List<Car> cars = userService.listCars();
      for (Car car : cars) {
         System.out.println(car.toString());
      }
      context.close();
   }
}
