package bao.xy.model;

/**
 * @Description:
 * @CreateTime: 2020-08-31-19-21
 */
public class Product {

    private String id;
    private String name;
    private String specification;
    private String unit;
    private String number;
    private String unitPrice;
    private String srorageTime;

    public Product() {
    }

    public Product(String id, String name, String specification, String unit, String number, String unitPrice, String srorageTime) {
        this.id = id;
        this.name = name;
        this.specification = specification;
        this.unit = unit;
        this.number = number;
        this.unitPrice = unitPrice;
        this.srorageTime = srorageTime;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getSrorageTime() {
        return srorageTime;
    }

    public void setSrorageTime(String srorageTime) {
        this.srorageTime = srorageTime;
    }

    @Override
    public String toString() {
        return "product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", specification='" + specification + '\'' +
                ", unit='" + unit + '\'' +
                ", number='" + number + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", srorageTime='" + srorageTime + '\'' +
                '}';
    }
}
