package net.xdclass.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.config.OssConfig;
import net.xdclass.service.FileService;
import net.xdclass.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Autowired
    private OssConfig ossConfig;


    @Override
    public String uploadUserImg(MultipartFile file) {

        //获取相关配置
        String bucketname = ossConfig.getBucketname();
        String endpoint = ossConfig.getEndpoint();
        String accessKeyId = ossConfig.getAccessKeyId();
        String accessKeySecret = ossConfig.getAccessKeySecret();
        //创建OSS对象
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        //获取原生文件名  xxx.jpg
        String originalFileName = file.getOriginalFilename();

        //JDK8的日期格式
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        //拼装路径,oss上存储的路径  user/2022/12/1/sdfdsafsdfdsf.jpg
        String folder = dtf.format(ldt);
        String fileName = CommonUtil.generateUUID();
        //todo: lastIndexOf:方法可以返回某个子字符串在字符串中最后出现的位置
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

        // 在OSS上的bucket下创建 user 这个文件夹
        String newFileName = "user/"+folder+"/"+fileName+ extension;

        try {
            PutObjectResult putObjectResult = ossClient.putObject(bucketname,newFileName,file.getInputStream());
            //拼装返回路径
            if(putObjectResult != null){
                String imgUrl = "https://"+bucketname+"."+endpoint+"/"+newFileName;
                return imgUrl;
            }

        } catch (IOException e) {
            log.error("文件上传失败:{}",e);
        } finally {
            //oss关闭服务，不然会造成OOM
            ossClient.shutdown();
        }

        return null;
    }

    public static void main(String[] args) {
       new FileServiceImpl().oss();
    }
    public  void oss(){
        /*
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = "yourAccessKeyId";
        String accessKeySecret = "yourAccessKeySecret";
        // 填写Bucket名称，例如examplebucket。
        String bucketName = "examplebucket";
         */
        String bucketname = ossConfig.getBucketname();
        String endpoint = ossConfig.getEndpoint();
        String accessKeyId = ossConfig.getAccessKeyId();
        String accessKeySecret = ossConfig.getAccessKeySecret();
    }
}
