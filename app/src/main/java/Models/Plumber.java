package Models;

public class Plumber {
    private String area;
    private String typeOfService;
    private String houseType;

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public Plumber(String area, String typeOfService,String type) {
        this.area = area;
        this.typeOfService = typeOfService;
        this.houseType = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTypeOfService() {
        return typeOfService;
    }

    public void setTypeOfService(String typeOfService) {
        this.typeOfService = typeOfService;
    }
}
