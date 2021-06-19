package Models;

public class FurnitureTransfer
{

    private String area;
    private String houseType;

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public FurnitureTransfer(String area, String type) {
        this.area = area;
        this.houseType = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

}
