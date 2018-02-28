package com.bzj.spring.processor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestParameterPropertyValues;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * 数据绑定参数转换器
 *
 * @author aaronbai
 * @create 2018-02-28 15:29
 **/
public class ConvertParameterProcessor extends ServletModelAttributeMethodProcessor {
    public ConvertParameterProcessor(boolean annotationNotRequired) {
        super(annotationNotRequired);
    }

    @Override
    protected void bindRequestParameters(WebDataBinder binder, NativeWebRequest request) {
        ServletRequest servletRequest = request.getNativeRequest(ServletRequest.class);
        ServletRequestDataBinder servletBinder = (ServletRequestDataBinder) binder;
        bind(servletRequest, servletBinder);
    }

    private void bind(ServletRequest request, ServletRequestDataBinder dataBinder) {
        MutablePropertyValues mpvs = new ServletRequestParameterPropertyValues(request);
        MultipartRequest multipartRequest = WebUtils.getNativeRequest(request, MultipartRequest.class);
        if (multipartRequest != null) {
            bindMultipart(multipartRequest.getMultiFileMap(), mpvs);
        }

        String attr = HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE;
        Map<String, String> uriVars = (Map<String, String>) request.getAttribute(attr);
        if (uriVars != null) {
            for (Map.Entry<String, String> entry : uriVars.entrySet()) {
                if (mpvs.contains(entry.getKey())) {
                    logger.warn("Skipping URI variable '" + entry.getKey()
                            + "' since the request contains a bind value with the same name.");
                } else {
                    mpvs.addPropertyValue(entry.getKey(), entry.getValue());
                }
            }
        }

        doExtendBind(mpvs, dataBinder);
        dataBinder.bind(mpvs);
    }

    protected void bindMultipart(Map<String, List<MultipartFile>> multipartFiles, MutablePropertyValues mpvs) {
        for (Map.Entry<String, List<MultipartFile>> entry : multipartFiles.entrySet()) {
            String key = entry.getKey();
            List<MultipartFile> values = entry.getValue();
            if (values.size() == 1) {
                MultipartFile value = values.get(0);
                if (!value.isEmpty()) {
                    mpvs.add(key, value);
                }
            } else {
                mpvs.add(key, values);
            }
        }
    }

    public void doExtendBind(MutablePropertyValues mpvs, ServletRequestDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        Class<?> targetClass = target.getClass();
        ConvertTag classAnn = targetClass.getAnnotation(ConvertTag.class);
        if (classAnn != null) {
            Field[] fields = targetClass.getDeclaredFields();
            for (Field field : fields) {
                ConvertValue paramNameAnnotation = field.getAnnotation(ConvertValue.class);
                if (paramNameAnnotation != null
                        && StringUtils.isNotBlank(paramNameAnnotation.value())
                        && mpvs.getPropertyValue(paramNameAnnotation.value()) != null) {
                    mpvs.add(field.getName(), mpvs.getPropertyValue(paramNameAnnotation.value()).getValue());
                    mpvs.removePropertyValue(paramNameAnnotation.value());
                }
            }
        }
    }
}
