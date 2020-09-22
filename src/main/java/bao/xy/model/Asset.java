package bao.xy.model;

/**
 * @Description: 资产
 * @CreateTime: 2020-09-01-09-40
 */
public class Asset {

    private String id;
    private String assetClass;
    private String financeId;
    private String productId;
    private String careStaffId;
    private String state;

    public Asset() {
    }

    public Asset(String id, String assetClass, String financialId, String productId, String careStaffId, String state) {
        this.id = id;
        this.assetClass = assetClass;
        this.financeId = financialId;
        this.productId = productId;
        this.careStaffId = careStaffId;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssetClass() {
        return assetClass;
    }

    public void setAssetClass(String assetClass) {
        this.assetClass = assetClass;
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

    public String getCareStaffId() {
        return careStaffId;
    }

    public void setCareStaffId(String careStaffId) {
        this.careStaffId = careStaffId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "id='" + id + '\'' +
                ", assetClass='" + assetClass + '\'' +
                ", financeId='" + financeId + '\'' +
                ", productId='" + productId + '\'' +
                ", careStaffId='" + careStaffId + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
