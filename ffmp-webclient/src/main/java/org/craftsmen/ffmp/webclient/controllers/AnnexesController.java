package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.Annexes;
import com.jrtech.ffmp.data.entities.Contract;
import com.jrtech.templates.services.AnnexesService;
import com.jrtech.templates.services.ContractService;
import com.jrtech.templates.services.ServiceException;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by suelmer on 2016/9/19.
 */

@Controller
@RequestMapping(value = "/rest/contract")
public class AnnexesController {
    @Autowired
    private ContractService contractService;
    @Autowired
    private AnnexesService annexesService;
    @Value("${contract.file.upload.directory}")
    private String fileUploadDirectory;

    private static final List<String> IMAGE_TYPES = Arrays.asList(
            "image/jpg", "image/jpeg", "image/png", "image/gif", "image/bmp", "image/svg+xml"
    );

    private Logger logger = LogManager.getLogger(AnnexesController.class.getName());

    @RequestMapping(value = "/upload/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Map list(@PathVariable String id) {
        logger.debug("获取文件列表"+fileUploadDirectory);
        if (null == id || id.isEmpty()) {
            throw new ServiceException("查看文件的ID为空");
        }
        Contract contract = contractService.findOne(id);
        Iterable<Annexes> list = annexesService.getAnexesByContract(contract);
        for (Annexes image : list) {
            image.setUrl("rest/contract/picture/" + image.getId());
            image.setThumbnailUrl("rest/contract/thumbnail/" + image.getId());
            image.setDownloadUrl("rest/contract/download/" + image.getId());
            image.setDeleteUrl("rest/contract/delete/" + image.getId());
            image.setDeleteType("DELETE");
        }

        Map<String, Object> files = new HashMap<>();
        files.put("files", list);
        logger.debug("Returning: {}", files);
        return files;
    }

    @RequestMapping(value = "/upload/{id}", method = RequestMethod.POST)
    public
    @ResponseBody
    Map upload(MultipartHttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
        logger.debug("开始上传文件");
        if (null == id || id.isEmpty()) {
            throw new ServiceException("上传文件的合同ID为空");
        }
        Iterator<String> itr = request.getFileNames();
        MultipartFile mpf;
        List<Annexes> list = new LinkedList<>();

        String rootPath = request.getSession().getServletContext().getRealPath("/");
        String filePath = rootPath + fileUploadDirectory;
        Contract contract = contractService.findOne(id);
        while (itr.hasNext()) {
            mpf = request.getFile(itr.next());
            logger.debug("Uploading {}", mpf.getOriginalFilename());

            String newFilenameBase = UUID.randomUUID().toString();
            String originalFileExtension = mpf.getOriginalFilename().substring(mpf.getOriginalFilename().lastIndexOf("."));
            String newFilename = newFilenameBase + originalFileExtension;
            String storageDirectory = filePath;
            String contentType = mpf.getContentType();

            File newFile = new File(storageDirectory + "/" + newFilename);
            System.out.println("目标文件---1---"+newFile.getName());
            System.out.println("目标文件----2----"+newFile.getPath());

            try {
                mpf.transferTo(newFile);

                BufferedImage thumbnail = null;
                File thumbnailFile = null;
                String thumbnailFilename = "";
                if (IMAGE_TYPES.contains(contentType)) {
                    thumbnail = Scalr.resize(ImageIO.read(newFile), 128);
                    thumbnailFilename = newFilenameBase + "-thumbnail.png";
                    thumbnailFile = new File(storageDirectory + "/" + thumbnailFilename);
                    ImageIO.write(thumbnail, "png", thumbnailFile);
                } else {
                    thumbnail = ImageIO.read(new File(rootPath + "images/file.png"));
                    thumbnailFilename = "file.png";
                    thumbnailFile = new File(storageDirectory + "/" + thumbnailFilename);
                    ImageIO.write(thumbnail, "png", thumbnailFile);
                }


                Annexes image = new Annexes();
                image.setName(mpf.getOriginalFilename());
                image.setThumbnailFilename(thumbnailFilename);
                image.setNewFileName(newFilename);
                image.setContentType(contentType);
                image.setSize(mpf.getSize());
                image.setThumbnailSize(thumbnailFile.length());
                image.setContract(contract);
                image.setDateCreated(new Date());

                image = annexesService.save(image);

                image.setUrl("rest/contract/picture/" + image.getId());
                image.setThumbnailUrl("rest/contract/thumbnail/" + image.getId());
                image.setDownloadUrl("rest/contract/download/" + image.getId());
                image.setDeleteUrl("rest/contract/delete/" + image.getId());
                image.setDeleteType("DELETE");

                list.add(image);

            } catch (IOException e) {
                logger.error("Could not upload file " + mpf.getOriginalFilename(), e);
            }

        }
        logger.info("上传文件成功");
        Map<String, Object> files = new HashMap<>();
        files.put("files", list);
        return files;
    }

    @RequestMapping(value = "/picture/{id}", method = RequestMethod.GET)
    public void picture(HttpServletRequest request,HttpServletResponse response, @PathVariable String id) {
        if (null == id || id.isEmpty()) {
            throw new ServiceException("查看文件的ID为空");
        }
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        Annexes image = annexesService.findOne(id);
        File imageFile = new File(rootPath+fileUploadDirectory + "/" + image.getNewFileName());
        System.out.println("目标文件----picture----"+imageFile.getPath());
        response.setContentType(image.getContentType());
        response.setContentLength(image.getSize().intValue());
        try {
            InputStream is = new FileInputStream(imageFile);
            IOUtils.copy(is, response.getOutputStream());
            is.close();
        } catch (IOException e) {
            logger.error("Could not show picture " + id, e);
        }
        logger.info("原图" +imageFile.getName());
    }

    @RequestMapping(value = "/thumbnail/{id}", method = RequestMethod.GET)
    public void thumbnail(HttpServletRequest request,HttpServletResponse response, @PathVariable String id) {
        if (null == id || id.isEmpty()) {
            throw new ServiceException("查看文件的ID为空");
        }
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        Annexes image = annexesService.findOne(id);
        File imageFile = new File(rootPath+fileUploadDirectory + "/" + image.getThumbnailFilename());
        System.out.println("目标文件----thumbnail----"+imageFile.getPath());
        response.setContentType(image.getContentType());
        response.setContentLength(image.getThumbnailSize().intValue());
        try {
            InputStream is = new FileInputStream(imageFile);
            IOUtils.copy(is, response.getOutputStream());
            is.close();
        } catch (IOException e) {
            logger.error("Could not show thumbnail " + id, e);
        }
        logger.info("缩略图" +imageFile.getName());
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    List delete(HttpServletRequest request,@PathVariable String id) {
        if (null == id || id.isEmpty()) {
            throw new ServiceException("删除文件的ID为空");
        }
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        Annexes image = annexesService.findOne(id);
        File imageFile = new File(rootPath+fileUploadDirectory + "/" + image.getNewFileName());
        System.out.println("目标文件----delete----"+imageFile.getPath());
        imageFile.delete();
        File thumbnailFile = new File(rootPath+fileUploadDirectory + "/" + image.getThumbnailFilename());
        thumbnailFile.delete();
        annexesService.deleteAnnexes(id);
        logger.info("删除图片 " + imageFile.getName());
        List<Map<String, Object>> results = new ArrayList<>();
        Map<String, Object> success = new HashMap<>();
        success.put("success", true);
        results.add(success);
        return results;
    }

    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
        if (null == id || id.isEmpty()) {
            throw new ServiceException("下载文件的ID为空");
        }
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        Annexes image = annexesService.findOne(id);
        File imageFile = new File(rootPath+fileUploadDirectory + "/" + image.getNewFileName());
        System.out.println("目标文件----download----"+imageFile.getPath());
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType(image.getContentType());
        response.setContentLength(image.getSize().intValue());
        response.setHeader("Content-disposition", "attachment; filename="
                + image.getName());
        try {
            InputStream is = new FileInputStream(imageFile);
            IOUtils.copy(is, response.getOutputStream());
            is.close();
        } catch (IOException e) {
            System.out.println("Could not download" + id);
            e.printStackTrace();
        }
        logger.info("下载图片 " +imageFile.getName());
    }
}
