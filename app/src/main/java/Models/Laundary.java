package Models;

public class Laundary {
    private String area;
    private String typeOfService;

    public Laundary(String area, String typeOfService) {
        this.area = area;
        this.typeOfService = typeOfService;
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
