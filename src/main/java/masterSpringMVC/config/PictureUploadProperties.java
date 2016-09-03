package masterSpringMVC.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.DescriptiveResource;
import org.springframework.core.io.Resource;

/**
 * 从application.properties的文件中获取资源。
 * 注意：prefix要与文件的前缀一样，Resource的名字与后一个参数一样
 * MasterSpringMvcApplication添加中@EnableConfigurationProperties({PictureUploadProperties.class})
 * Created by OwenWilliam on 2016/5/17.
 */

@ConfigurationProperties(prefix = "upload.pictures")
public class PictureUploadProperties {

    private Resource uploadPath;
    private Resource anonymousPicture;

    public Resource getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(Resource uploadPath)
    {
        this.uploadPath = uploadPath;
    }

    public Resource getAnonymousPicture()
    {
        return anonymousPicture;
    }

    public void setAnonymousPicture(Resource anonymousPicture) {
        this.anonymousPicture = anonymousPicture;
    }


}
