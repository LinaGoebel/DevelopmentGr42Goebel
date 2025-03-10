package de.ait.javalessons.controller;

import de.ait.javalessons.model.Car;
import de.ait.javalessons.repositories.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j // Аннотация Lombok для автоматического создания логгера
@RestController // Аннотация Spring, указывающая, что этот класс является REST-контроллером
@RequestMapping("/cars") // Базовый путь для всех методов в этом контроллере
public class RestApiCarController {

    // Список для хранения автомобилей
    private final CarRepository carRepository;

    // Конструктор класса, инициализирующий список автомобилей
    public RestApiCarController(CarRepository carRepository) {
        this.carRepository = carRepository;

        this.carRepository.saveAll(
                List.of(
                        new Car("1", "BMW M1"),
                        new Car("2", "Audi A8"),
                        new Car("3", "Kia Spartage"),
                        new Car("4", "Volvo 960")
                )
        );
    }

    /**
     * Метод для получения всех автомобилей.
     * GET-запрос на /cars
     *
     * @return список всех автомобилей
     */
    @GetMapping
    Iterable<Car> getCars() {
        return carRepository.findAll();
    }

    /**
     * Метод для получения автомобиля по его ID.
     * GET-запрос на /cars/{id}
     *
     * @param id идентификатор автомобиля
     * @return Optional, содержащий автомобиль, если он найден, иначе пустой Optional
     */
    @GetMapping("/{id}")
    Optional<Car> getCarById(@PathVariable String id) {
        Optional<Car> carInDataBase = carRepository.findById(id);
        if (carInDataBase.isPresent()) {
            log.info("Car with id {} was found", id);
            return carInDataBase;
        }
        log.info("Car with id {} was not found", id);
        return Optional.empty();
    }

    /**
     * Метод для добавления нового автомобиля.
     * POST-запрос на /cars
     *
     * @param car объект автомобиля, переданный в теле запроса
     * @return добавленный автомобиль
     */
    @PostMapping
    Car postCar(@RequestBody Car car) {
        Car saveResult = carRepository.save(car);
        return saveResult;
    }

    /**
     * Метод для обновления существующего автомобиля по его ID.
     * PUT-запрос на /cars/{id}
     *
     * @param id  идентификатор автомобиля, который нужно обновить
     * @param car объект автомобиля с новыми данными
     * @return ResponseEntity с обновлённым автомобилем и статусом OK, если автомобиль найден,
     * или с новым автомобилем и статусом CREATED, если автомобиль не найден
     */
    @PutMapping("/{id}")
    ResponseEntity<Car> putCar(@PathVariable String id, @RequestBody Car car) {
        if (carRepository.existsById(id)) {
            Car carToUpdate = carRepository.findById(id).get();
            carToUpdate.setName(car.getName());
            carToUpdate.setId(id);
            Car savedCar = carRepository.save(carToUpdate);
            return new ResponseEntity<>(car, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(car, HttpStatus.CREATED);
        }
    }

    /**
     * Метод для удаления автомобиля по его ID.
     * DELETE-запрос на /cars/{id}
     *
     * @param id идентификатор автомобиля, который нужно удалить
     */
    @DeleteMapping("/{id}")
    void deleteCar(@PathVariable String id) {
        carRepository.deleteById(id);
    }
}
