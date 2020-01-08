//package com.guangmai.qiaoQ;
//
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//
///**
// * @Authod: dongyang
// * @Description:    修改启动类，继承 SpringBootServletInitializer 并重写 configure 方法  （此类用户tomcat部署时打成war使用）
// * @Date: 创建于 15:17 2019/12/30
// * @Modified By:
// */
//public class SpringBootStartApplication extends SpringBootServletInitializer {
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        // 注意这里要指向原先用main方法执行的Application启动类
//        return builder.sources(GunsApplication.class);
//    }
//}