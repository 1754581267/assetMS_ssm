package bao.xy.model;

/**
 * @Description: 申领表
 * @CreateTime: 2020-09-02-20-06
 */
public class Application {
    private String id;
    private String staffId;
    private String staffName;
    private String assetsId;
    private String productName;
    private String state;
    private String applicationTime;
    private String returnTime;

    public Application() {
    }

    public Application(String id, String staffId, String staffName, String assetsId, String productName, String state, String applicationTime, String returnTime) {
        this.id = id;
        this.staffId = staffId;
        this.staffName = staffName;
        this.assetsId = assetsId;
        this.productName = productName;
        this.state = state;
        this.applicationTime = applicationTime;
        this.returnTime = returnTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAssetsId() {
        return assetsId;
    }

    public void setAssetsId(String assetsId) {
        this.assetsId = assetsId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(String applicationTime) {
        this.applicationTime = applicationTime;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id='" + id + '\'' +
                ", staffId='" + staffId + '\'' +
                ", staffName='" + staffName + '\'' +
                ", assetsId='" + assetsId + '\'' +
                ", productName='" + productName + '\'' +
                ", state='" + state + '\'' +
                ", applicationTime='" + applicationTime + '\'' +
                ", returnTime='" + returnTime + '\'' +
                '}';
    }
}
