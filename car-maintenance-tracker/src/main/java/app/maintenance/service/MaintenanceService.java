package app.maintenance.service;

import app.car.model.Car;
import app.car.repository.CarRepository;
import app.maintenance.model.Maintenance;
import app.maintenance.repository.MaintenanceRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MaintenanceService {
    private final MaintenanceRepository maintenances;
    private final CarRepository cars;

    public MaintenanceService(MaintenanceRepository maintenances, CarRepository cars) {
        this.maintenances = maintenances;
        this.cars = cars;
    }

    @Transactional
    public Maintenance add(UUID carId, Maintenance maintenance) {
        Car car = cars.findById(carId).orElseThrow(NoSuchElementException::new);
        maintenance.setCar(car);
        return maintenances.save(maintenance);
    }

    public List<Maintenance> listForCar(UUID carId) {
        return maintenances.findAllByCarIdOrderByDateDesc(carId);
    }

    public Optional<Maintenance> get(UUID carId, UUID maintenanceId) {
        return maintenances.findByIdAndCarId(maintenanceId, carId);
    }

    @Transactional
    public void delete(UUID carId, UUID maintenanceId) {
        maintenances.deleteByIdAndCarId(maintenanceId, carId);
    }
}