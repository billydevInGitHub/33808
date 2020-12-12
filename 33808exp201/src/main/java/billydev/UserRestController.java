package billydev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Controller
public class UserRestController {

    @Autowired
    private CompanyDao companyDao;
    /**
     *company1 获得页面上input中的name属性对应的值  input z中name属性的值跟实体中name属性是一致的不一致的话   company1不能得到输入的值
     照片是使用@RequestParam获得
     */

    @RequestMapping("/companysave")
    @ResponseBody
    public void save(Company company1,@RequestParam(name="fileField",required=false) MultipartFile fileField) throws IOException
    {
        Company company = new Company();
        company.setCompanyAddress(company1.getCompanyAddress());
        company.setCompanyManager(company1.getCompanyManager());
        company.setCompanyName(company1.getCompanyName());
        company.setCompanyPhone(company1.getCompanyPhone());
        company.setCreateDate(new Date());
        company.setManagerQQ(company1.getManagerQQ());
        company.setWhchatID(company1.getWhchatID());
        if(fileField==null)
        {
            company.setCompanyLogo(null);
        }else {
            company.setCompanyLogo(fileField.getBytes());
        }
        companyDao.save(company);
    }

    /**映射页面
     */
    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }
}