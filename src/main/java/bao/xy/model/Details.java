package bao.xy.model;

/**
 * @Description: 资产详情
 * @CreateTime: 2020-09-01-20-26
 */
public class Details {
    private String assetsId;
    private String entryDate;
    private String proof;
    private String financeId;
    private String productId;
    private String assetClass;
    private String productName;
    private String specification;
    private String srorageTime;
    private String unit;
    private String number;
    private String unitPrice;
    private String staffName;
    private String assetsState;

    public Details() {
    }

    public Details(String assetsId, String entryDate, String proof, String financeId, String productId, String assetClass, String productName, String specification, String srorageTime, String unit, String number, String unitPrice, String staffName, String assetsState) {
        this.assetsId = assetsId;
        this.entryDate = entryDate;
        this.proof = proof;
        this.financeId = financeId;
        this.productId = productId;
        this.assetClass = assetClass;
        this.productName = productName;
        this.specification = specification;
        this.srorageTime = srorageTime;
        this.unit = unit;
        this.number = number;
        this.unitPrice = unitPrice;
        this.staffName = staffName;
        this.assetsState = assetsState;
    }

    public String getAssetsId() {
        return assetsId;
    }

    public void setAssetsId(String assetsId) {
        this.assetsId = assetsId;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getProof() {
        return proof;
    }

    public void setProof(String proof) {
        this.proof = proof;
    }

    public String getFinanceId() {
        return financeId;
    }

    public void setFinanceId(String financeId) {
        this.financeId = financeId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getAssetClass() {
        return assetClass;
    }

    public void setAssetClass(String assetClass) {
        this.assetClass = assetClass;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getSrorageTime() {
        return srorageTime;
    }

    public void setSrorageTime(String srorageTime) {
        this.srorageTime = srorageTime;
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

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getAssetsState() {
        return assetsState;
    }

    public void setAssetsState(String assetsState) {
        this.assetsState = assetsState;
    }

    @Override
    public String toString() {
        return "Details{" +
                "assetsId='" + assetsId + '\'' +
                ", entryDate='" + entryDate + '\'' +
                ", proof='" + proof + '\'' +
                ", financeId='" + financeId + '\'' +
                ", productId='" + productId + '\'' +
                ", assetClass='" + assetClass + '\'' +
                ", productName='" + productName + '\'' +
                ", specification='" + specification + '\'' +
                ", srorageTime='" + srorageTime + '\'' +
                ", unit='" + unit + '\'' +
                ", number='" + number + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", staffName='" + staffName + '\'' +
                ", assetsState='" + assetsState + '\'' +
                '}';
    }
}
