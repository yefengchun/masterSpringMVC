package masterSpringMVC7.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.Serializable;

/**
 * 从application.properties的文件中获取资源。
 * 注意：prefix要与文件的前缀一样，Resource的名字与后一个参数一样
 * MasterSpringMvcApplication添加中@EnableConfigurationProperties({PictureUploadProperties.class})
 * Created by OwenWilliam on 2016/5/17.
 */

@ConfigurationProperties(prefix = "upload.pictures")
public class PictureUploadProperties  implements Serializable{

    private Resource uploadPath;
    private Resource anonymousPicture;

    public Resource getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath)  throws IOException
    {
        this.uploadPath = new DefaultResourceLoader().getResource(uploadPath);
        if (!this.uploadPath.getFile().isDirectory()) {
            throw new IOException("Directory " + uploadPath + " does not exist");
        }
    }

    public Resource getAnonymousPicture()
    {
        return anonymousPicture;
    }

    public void setAnonymousPicture(String anonymousPicture) throws IOException {
        this.anonymousPicture = new DefaultResourceLoader().getResource(anonymousPicture);
        if (!this.anonymousPicture.getFile().isFile()) {
            throw new IOException("File " + anonymousPicture + " does not exist");
        }
    }


}
