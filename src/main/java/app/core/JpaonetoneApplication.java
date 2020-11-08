package app.core;

import app.core.model.inversend_relation.Car;
import app.core.model.owner_relation.Person;
import app.core.repo.CarRepository;
import app.core.repo.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Optional;

@SpringBootApplication
public class JpaonetoneApplication {

    private final static Logger logger = LoggerFactory.getLogger(JpaonetoneApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(JpaonetoneApplication.class, args);

        PersonRepository personRepository = context.getBean(PersonRepository.class);
        CarRepository carRepository = context.getBean(CarRepository.class);


        Car car = new Car();
        car.setModelName("Nissan");
        carRepository.save(car);

        Person person = new Person("Mikhail");
        personRepository.save(person);


        person.setCar(car);

        personRepository.save(person);

        Optional<Car> optionalCar = carRepository.findById(1L);
        Optional<Person> optionalPerson = personRepository.findById(2L);

        System.out.println(person);
        System.out.println(car);

        if (optionalCar.isPresent() && optionalPerson.isPresent()) {
            //test inverse end side for bidirectional interaction
            logger.info("Car " + optionalCar.get().getModelName() + " is owned by " + optionalCar.get().getPerson().getName());
            //test owner side for bidirectional interaction
            logger.info("Person  " + optionalPerson.get().getName() + " is owner of " + optionalPerson.get().getCar().getModelName());


        }


    }

}
