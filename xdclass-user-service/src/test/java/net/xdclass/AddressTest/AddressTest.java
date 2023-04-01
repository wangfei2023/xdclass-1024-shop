package net.xdclass.AddressTest;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.BucketInfo;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.config.OssConfig;
import net.xdclass.model.AddressDO;
import net.xdclass.service.AddressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class AddressTest {
    @Autowired
    private AddressService addressService;

    @Autowired
    private OssConfig ossConfig;

    @Test
    public void detail() {
        AddressDO addressDO = addressService.getById(1L);
        log.info(addressDO.toString());
    }


    @Test
    public void oss() {



// 实例化OSSClient对象，使用您的OSS访问密钥

        OSS ossClient = new OSSClientBuilder().build(ossConfig.getEndpoint(), ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());
        BucketInfo bucketInfo = ossClient.getBucketInfo(ossConfig.getBucketname());

// 调用listBuckets()方法来列出所有的存储桶
        List<Bucket> buckets = ossClient.listBuckets();

// 遍历所有的存储桶并输出它们的名称
        for (Bucket bucket : buckets) {
            System.out.println(bucket.getName());
        }

// 关闭OSSClient对象
        ossClient.shutdown();
    }
}
