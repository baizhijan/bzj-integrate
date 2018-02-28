#### 技术点说明
---
##### 1. web配置
&emsp;&emsp;常规配置详细看项目

#### 2. 功能说明
##### 2.1. processor
&emsp;&emsp; spring数据绑定功能,自定义name配置绑定

    <!-- 使用方式: -->
    <!-- mvc配置文件中添加 -->
    <mvc:annotation-driven>
        <mvc:argument-resolvers>
            <bean class="com.tcl.mp.dsp.admanage.processor.CollBackConvertParameterProcessor">
                <constructor-arg name="annotationNotRequired" value="true"/>
            </bean>
        </mvc:argument-resolvers>
    </mvc:annotation-driven>

    <!--  bean对象上直接添加注解-->
    @ConvertTag
    public class AdjustBo {

        @ConvertValue(value = "campaign_name")
        private Integer adset_id;
    }

