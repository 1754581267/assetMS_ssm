package bao.xy.model;

/**
 * @Description:
 * @CreateTime: 2020-08-30-20-03
 */
public class Finance {

    private String id;
    private String staffId;
    private String proof;
    private String entryDate;
    private String state;

    public Finance() {
    }

    public Finance(String id, String staffId, String proof, String entryDate, String state) {
        this.id = id;
        this.staffId = staffId;
        this.proof = proof;
        this.entryDate = entryDate;
        this.state = state;
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

    public String getProof() {
        return proof;
    }

    public void setProof(String proof) {
        this.proof = proof;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Finance{" +
                "id='" + id + '\'' +
                ", staffId='" + staffId + '\'' +
                ", proof='" + proof + '\'' +
                ", entryDate='" + entryDate + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
