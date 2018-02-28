#### spring-boot 使用:
##### 1.&ensp;pom配置
``` javascript

    <!-- boot必配 -->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.5.10.RELEASE</version>
        <relativePath/>
    </parent>

    <!-- boot功能包必配 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>

    <!-- web支持 若不使用web 可不依赖-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!--  其他依赖按个人需要添加-->
```
##### 2.&ensp;配置文件配置
``` javascript
spring.datasource.url=jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8

#多环境配置-->需要有application-test.properties文件存在
spring.profiles.active=test

server.port=80
```
##### 3.&ensp;代码使用
``` javascript

    /**
     * boot启动器
     * 项目根路径下可以直接使用@SpringBootApplication直接启动
     * 启动器有一个就够
     */
    @SpringBootApplication
    public class BootApplication implements CommandLineRunner {

    	public static void main(String[] args) {
    		SpringApplication.run(BootApplication.class, args);
    	}

    	@Override
    	public void run(String... strings) throws Exception {
    		System.out.println("执行特定代码");
    	}
    }

    /**
     * 使用@Configuration 注解类可以添加自己的对象配置
     */
    @Configuration
    public class AllConfig {

        //传统的sprin获取配置注解,可以直接使用
        @Value("${spring.datasource.url}")
        private String dbUrl;
    }

    /**
     * 替代xml文件模式纯注解实现
     */
    @Bean
    public ThreadPoolTaskExecutor myExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        ...
        return executor;
    }
```
##### 4.&ensp;线程池ThreadPoolTaskExecutor使用
```
    <!--  定义线程监听器-->
    CountDownLatch countDownLatch = new CountDownLatch(queue.size());

    <!--  创建线程-->
    CutFilesThread thread = new CutFilesThread(countDownLatch);

    <!--  线程池使用-->
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;
    taskExecutor.execute(thread);

    <!--  监听器监听线程状态-->
    countDownLatch.await();
```