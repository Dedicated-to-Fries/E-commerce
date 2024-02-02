package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Commodity;
import com.example.ecommerce.exception.ApiException;
import com.example.ecommerce.service.CommodityService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
@RequestMapping("/addCommodity")
public class UploadController {

    @Autowired
    private CommodityService commodityService;

    static final Logger log = LoggerFactory.getLogger(UploadController.class);

    // 日期格式化
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("/yyy/MM/dd/");

    // 资源的 访问 URL
    @Value("${app.minio.base-url}")
    private String baseUrl;

    // API 端点
    @Value("${app.minio.endpoint}")
    private String endpoint;

    // Bucket 存储桶
    @Value("${app.minio.bucket}")
    private String bucket;

    // Acess Key
    @Value("${app.minio.access-key}")
    private String accessKey;

    // Secret Key
    @Value("${app.minio.secret-key}")
    private String secretKey;

    /**
     * 添加商品和上传文件到 Minio 服务器，返回访问地址
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Commodity> upload(@RequestPart Commodity commodity, @RequestPart("file") MultipartFile file) throws Exception{

        // 文件大小
        long size = file.getSize();
        if (size == 0) {
            throw  new ApiException("禁止上传空文件");//如果文件为空，则抛出异常
        }

        // 文件名称
        String fileName = file.getOriginalFilename();

        // 文件后缀
        String ext = "";

        int index = fileName.lastIndexOf(".");
        if (index ==-1) {
            throw new ApiException("禁止上传无后缀的文件");//禁止上传没有后缀(拓展名)的文件
        }

        ext = fileName.substring(index);

        // 文件类型
        String contentType = file.getContentType();
        if (contentType == null) {
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        // 根据日期打散目录，使用 UUID 重命名文件
        String filePath = formatter.format(LocalDate.now()) +
                UUID.randomUUID().toString().replace("-", "") +
                ext;

        log.info("文件名称：{}", fileName);
        log.info("文件大小：{}", size);
        log.info("文件类型：{}", contentType);
        log.info("文件路径：{}", filePath);

        // 实例化客户端
        MinioClient client = MinioClient.builder()
                .endpoint(this.endpoint)
                .credentials(this.accessKey, this.secretKey)
                .build();


        // 上传文件到客户端
        try (InputStream inputStream = file.getInputStream()){
            client.putObject(PutObjectArgs.builder()
                    .bucket(this.bucket)		// 指定 Bucket
                    .contentType(contentType)	// 指定 Content Type
                    .object(filePath)			// 指定文件的路径
                    .stream(inputStream, size, -1) // 文件的 Inputstream 流
                    .build());
        }


        //将commodity对象和图片路径持久化到数据库中
        Commodity commodityUrl=new Commodity();
        commodityUrl.setId(commodity.getId());
        commodityUrl.setName(commodity.getName());
        commodityUrl.setType(commodity.getType());
        commodityUrl.setSales(commodity.getSales());
        commodityUrl.setOrdering(commodity.getOrdering());
        commodityUrl.setStatus(commodity.getStatus());
        commodityUrl.setUrl(this.baseUrl+this.bucket+filePath);//持久化最终的访问路径
        commodityUrl.setCreatedAt(commodity.getCreatedAt());
        commodityService.addCommodity(commodityUrl);


        // 返回最终的访问路径
        return ResponseEntity.ok(commodityUrl);
    }
}

