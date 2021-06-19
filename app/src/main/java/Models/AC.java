package Models;

public class AC {
    private String area;
    private String typeOfService;
    private String unitType;
    private int numOfUnits;

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public int getNumOfUnits() {
        return numOfUnits;
    }

    public void setNumOfUnits(int numOfUnits) {
        this.numOfUnits = numOfUnits;
    }

    public AC(String area, String typeOfService, String type, int numOfUnits) {
        this.area = area;
        this.typeOfService = typeOfService;
        this.typeOfService = type;
        this.numOfUnits = numOfUnits;
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
