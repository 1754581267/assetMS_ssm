package bao.xy.model;

/**
 * @Description:
 * @CreateTime: 2020-08-27-17-54
 */
public class Staff {

    private String id;
    private String work;
    private String name;
    private String uname;
    private String pwd;
    private String powers;
    private String sex;
    private Integer age;
    private String phone;

    public Staff() {
    }

    public Staff(String id, String work, String name, String uname, String pwd, String powers, String sex, Integer age, String phone) {
        this.id = id;
        this.work = work;
        this.name = name;
        this.uname = uname;
        this.pwd = pwd;
        this.powers = powers;
        this.sex = sex;
        this.age = age;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPowers() {
        return powers;
    }

    public void setPowers(String powers) {
        this.powers = powers;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id='" + id + '\'' +
                ", work='" + work + '\'' +
                ", name='" + name + '\'' +
                ", uname='" + uname + '\'' +
                ", pwd='" + pwd + '\'' +
                ", powers='" + powers + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                '}';
    }
}
