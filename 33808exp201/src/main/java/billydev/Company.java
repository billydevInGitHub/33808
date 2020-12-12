package billydev;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Company {


    @Id
    @GeneratedValue

    private int CompanyId;

    private String CompanyName;

    private String CompanyManager;

    private String CompanyPhone;

    private Date CreateDate;

    //@Lob 通常与@Basic同时使用，提高访问速度
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name=" company_logo", columnDefinition="longblob", nullable=true)
    private byte[] CompanyLogo;


    private String CompanyAddress;

    private String managerQQ;

    private String WhchatID;

    public int getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(int companyId) {
        CompanyId = companyId;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getCompanyManager() {
        return CompanyManager;
    }

    public void setCompanyManager(String companyManager) {
        CompanyManager = companyManager;
    }

    public String getCompanyPhone() {
        return CompanyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        CompanyPhone = companyPhone;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    public byte[] getCompanyLogo() {
        return CompanyLogo;
    }

    public void setCompanyLogo(byte[] companyLogo) {
        CompanyLogo = companyLogo;
    }

    public String getCompanyAddress() {
        return CompanyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        CompanyAddress = companyAddress;
    }

    public String getManagerQQ() {
        return managerQQ;
    }

    public void setManagerQQ(String managerQQ) {
        this.managerQQ = managerQQ;
    }

    public String getWhchatID() {
        return WhchatID;
    }

    public void setWhchatID(String whchatID) {
        WhchatID = whchatID;
    }
//get set方法省略~~~~~~~

    @Override
    public String toString() {
        return CompanyAddress + CompanyId + CompanyLogo + CompanyManager + CompanyName + CompanyPhone;
    }

}
