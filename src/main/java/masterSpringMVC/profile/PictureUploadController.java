package masterSpringMVC.profile;

import masterSpringMVC.config.PictureUploadProperties;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;


/**
 * 文件上传刷新处理逻辑
 * Created by OwenWilliam on 2016/5/17.
 * the model attributes will be reset,so add @SessionAttributes("picturePath")
 */

@Controller
@SessionAttributes("picturePath")
public class PictureUploadController {

   // public static final Resource PICTURES_DIR = new FileSystemResource("./pictures");

    private final Resource picturesDir;
    private final Resource anonymousPicture;

    private final MessageSource messageSource;

    /**
     * 自动获取picturesDir和annonymouspicture
     * @param uploadProperties
     */
    @Autowired
    public PictureUploadController(PictureUploadProperties uploadProperties, MessageSource messageSource)
    {
        picturesDir = uploadProperties.getUploadPath();
        anonymousPicture = uploadProperties.getAnonymousPicture();
        this.messageSource = messageSource;
    }
    @RequestMapping("upload")
    public String uploadPage()
    {
        return "profile/uploadPage";
    }

    /**
     * 上传图片
     * @param file
     * @param redirectAttributes
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String onUpload(MultipartFile file, RedirectAttributes redirectAttributes, Model model) throws IOException
    {
       // throw new IOException("Some Message");
        if (file.isEmpty() || !isImage(file))
        {
            redirectAttributes.addFlashAttribute("error", "Incorrect file.Please upload a picture.");
            return "redirect:/upload";
        }

        Resource picturePath =  copyFileToPictures(file);
        //文件的路径放到session中
        model.addAttribute("picturePath", picturePath);
        return "profile/uploadPage";
        }

    /**
     * 图片输出到对应文件夹
     * @param file
     * @return
     * @throws IOException
     */
    private Resource copyFileToPictures(MultipartFile file) throws IOException
    {
        //后缀名（如：.jpg）
        String fileExtension = getFileExtension(file.getOriginalFilename());
        //路径+文件名（无后缀）
        File tempFile = File.createTempFile("pic",fileExtension, picturesDir.getFile());
        try (InputStream in = file.getInputStream();
             OutputStream out = new FileOutputStream(tempFile))
        {
            //to write an input stream to an output stream
            IOUtils.copy(in, out);
        }
        return new FileSystemResource(tempFile);
    }

    /**
     * 刷新显示图片
     * @param response
     * @param picturePath
     * @throws IOException
     */
    @RequestMapping(value = "/uploadedPicture")
    public void getUploadedPicture(HttpServletResponse response, @ModelAttribute("picturePath") Resource picturePath) throws
            IOException {
      /*  ClassPathResource classPathResource = new ClassPathResource("/images/anonymous.png");
                response.setHeader("Content-Type", URLConnection.guessContentTypeFromName(classPathResource.getFilename()));
        IOUtils.copy(classPathResource.getInputStream(), response.
                getOutputStream());
                IOUtils.copy(anonymousPicture.getInputStream(), response.getOutputStream());
        */
        // the code will display erro,because the Path should not like this use.
        /**the function paramer like : @ModelAttribute("picturePath") Path picturePath
         *  response.setHeader("Content-Type", URLConnection.guessContentTypeFromName(picturePath.toString()));
         *  Files.copy(picturePath, response.getOutputStream());
         */
        response.setHeader("Content-Type", URLConnection.guessContentTypeFromName(picturePath.toString()));
        Path path = Paths.get(picturePath.getURI());
        Files.copy(path, response.getOutputStream());

    }

    /**
     * 默认的图上路径
     * @return
     */
    @ModelAttribute("picturePath")
    public Resource picturePath()
    {
        return anonymousPicture;
    }


    /**
     * 是否是图片
     * @param file
     * @return
     */
    private boolean isImage(MultipartFile file)
    {
        return file.getContentType().startsWith("image");
    }

    /**
     * 获取后缀：如.jpg
     * @param name
     * @return
     */
    private static String getFileExtension(String name)
    {
        return name.substring(name.lastIndexOf("."));
    }

    @ExceptionHandler(IOException.class)
    public ModelAndView handleIOException(Locale locale)
    {
        ModelAndView modelAndView = new ModelAndView("profile/uploadPage");
       // modelAndView.addObject("error", exception.getMessage());
        modelAndView.addObject("error",messageSource.getMessage("upload.io.exception", null, locale));
        return modelAndView;
    }

    @RequestMapping("uploadError")
    public ModelAndView onUploadError(Locale locale)
    {
        ModelAndView modelAndView = new ModelAndView("profile/uploadPage");
     //   modelAndView.addObject("error", request.getAttribute(WebUtils.ERROR_MESSAGE_ATTRIBUTE));
        modelAndView.addObject("error", messageSource.getMessage("upload.file.too.big", null, locale));
        return modelAndView;
    }

}
