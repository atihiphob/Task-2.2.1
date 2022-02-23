package hiber.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table (name = "cars")
public class Car {


    @Autowired
    @OneToOne(mappedBy = "car", optional = false, orphanRemoval = true)
    @JoinColumn (name = "id")
    private User user = new User();

    @Column
    private String model;

    @Column
    private int series;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long Id;


    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long carId;

    public Car (){
    }

    public Car (String model, int series) {
        this.model = model;
        this.series = series;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }
    public void setSeries(int series) {
        this.series = series;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public void setId(Long id) {
        this.carId = id;
    }
    public Long getId() {
        return carId;
    }

    @Override
    public String toString() {
        return "Car { model = '" + model + ", series = " + series + " }";
    }


}
