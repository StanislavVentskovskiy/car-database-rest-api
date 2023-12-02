package ua.com.foxminded.cardatabase.util;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ua.com.foxminded.cardatabase.model.Car;
import ua.com.foxminded.cardatabase.model.Make;
import ua.com.foxminded.cardatabase.model.Model;
import ua.com.foxminded.cardatabase.model.Type;
import ua.com.foxminded.cardatabase.service.impl.CarServiceImpl;
import ua.com.foxminded.cardatabase.service.impl.MakeServiceImpl;
import ua.com.foxminded.cardatabase.service.impl.ModelServiceImpl;
import ua.com.foxminded.cardatabase.service.impl.TypeServiceImpl;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

@Service
public class DataFileParsing {
    private final ModelServiceImpl modelService;
    private final MakeServiceImpl makeService;
    private final TypeServiceImpl typeService;
    private final CarServiceImpl carService;
    private final String filePath = "C:\\workspace\\car-database-rest-api\\src\\main\\resources\\file.csv";

    @Autowired
    public DataFileParsing(ModelServiceImpl modelService,
                           MakeServiceImpl makeService,
                           TypeServiceImpl typeService,
                           CarServiceImpl carService) {
        this.modelService = modelService;
        this.makeService = makeService;
        this.typeService = typeService;
        this.carService = carService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initDataFromFile() throws IOException {
        saveModels();
        saveMake();
        saveType();
        saveCar();
    }

    public void saveModels() throws IOException {
        List<Model> models = new CsvToBeanBuilder(new FileReader(filePath))
                .withSkipLines(1)
                .withType(Model.class)
                .build()
                .parse();
        HashSet<Model> modelsSet = new HashSet<>(models);
        modelsSet.forEach(model -> modelService.addModel(model));
    }

    public void saveMake() throws IOException {
        List<Make> makes = new CsvToBeanBuilder(new FileReader(filePath))
                .withSkipLines(1)
                .withType(Make.class)
                .build()
                .parse();
        HashSet<Make> makesSet = new HashSet<>(makes);
        makesSet.forEach(make -> makeService.addMake(make));
    }

    public void saveType() throws IOException {
        List<Type> types = new CsvToBeanBuilder(new FileReader(filePath))
                .withSkipLines(1)
                .withType(Type.class)
                .build()
                .parse();
        HashSet<Type> typesSet = new HashSet<>(types);
        typesSet.forEach(type -> typeService.addType(type));
    }

    public void saveCar() throws IOException {
        List<Car> cars = new CsvToBeanBuilder(new FileReader(filePath))
                .withSkipLines(1)
                .withMappingStrategy(new CarMappingStrategy())
                .withType(Car.class)
                .build()
                .parse();
        cars.forEach(car -> carService.addCar(car));
    }

    class CarMappingStrategy extends ColumnPositionMappingStrategy {
        public CarMappingStrategy() {
            this.setType(Car.class);
        }

        @Override
        public Object populateNewBean(String[] line) {
            Car car = new Car();
            car.setId(line[0]);
            car.setYear(Integer.valueOf(line[2]));
            Make make = makeService.getMakeByName(line[1]).get();
            car.setMake(make);
            Model model = modelService.getModelByName(line[3]).get();
            car.setModel(model);
            Type type = typeService.getTypeByName(line[4]).get();
            car.setType(type);

            return car;
        }
    }
}
