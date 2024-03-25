package cn.liyw.service;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.region.Region;
import org.springframework.stereotype.Component;

@Component
public class COSClientUtil {



    public COSClient getCOSClient() {
        try {
            // 1 初始化用户身份信息（secretId, secretKey）。
            COSCredentials cred = new BasicCOSCredentials("xxxxxx",
                    "xxxxxxx");
            // 2 设置 bucket 的地域
            Region region = new Region("na-toronto");
            ClientConfig clientConfig = new ClientConfig(region);
            clientConfig.setHttpProtocol(HttpProtocol.https);
            // 3 生成 cos 客户端。
            return new COSClient(cred, clientConfig);
        } catch (Exception e) {
        }
        return null;
    }


}
