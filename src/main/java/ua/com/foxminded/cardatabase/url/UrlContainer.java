package ua.com.foxminded.cardatabase.url;

import org.springframework.stereotype.Service;

@Service
public class UrlContainer {
    public static final String getAllCars = "/api/cars/get";
    public static final String getSingleCar = "/api/cars/get/";
    public static final String postCar = "/api/cars";
    public static final String editCar = "/api/cars/";
    public static final String searchCars = "/api/cars/get/search";
    public static final String getAllMakes = "/api/makes/get";
    public static final String getSingleMake = "/api/makes/get/";
    public static final String postMake = "/api/makes";
    public static final String editMake = "/api/makes/";
    public static final String getAllModels = "/api/models/get/";
    public static final String getSingleModel = "/api/models/get/";
    public static final String postModel = "/api/models";
    public static final String editModel = "/api/models/";
    public static final String getAllTypes = "/api/types/get";
    public static final String getSingleType = "/api/types/get/";
    public static final String postType = "/api/types";
    public static final String editType = "/api/types/";
}
