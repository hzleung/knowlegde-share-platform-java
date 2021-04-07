package com.ly.cloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.ly.cloud" })
@EnableTransactionManagement
@EnableEurekaClient
public class knowlegdeSharePlatformServiceApplication {

	private final static Logger logger = LoggerFactory.getLogger(knowlegdeSharePlatformServiceApplication.class);

    public static void main(String[] args) {
        if (logger.isDebugEnabled()) {
            logger.debug("正在启动知识分享平台后端服务程序...");
        }
        SpringApplication.run(knowlegdeSharePlatformServiceApplication.class, args);
    }
}
  