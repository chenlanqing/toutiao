package com.nowcoder.util;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.jsoup.helper.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GeneratePDFUtils {

    private static Logger LOGGER = LoggerFactory.getLogger(GeneratePDFUtils.class);

    /**
     * 将 velocity 文件转为 pdf流
     * @param vmFilePath    vm文件路径
     * @param params        需要填充的参数
     * @return              pdf流
     */
    public static InputStream vm2Pdf(String vmFilePath, Map<String, Object> params) {
        String html = velocityToHtml(vmFilePath, params);
        if (StringUtil.isBlank(html)) {
            return null;
        }
        return htmlToPDF(html);
    }

    /**
     * 将 velocity 文件转为 html字符串
     * @param vmFilePath    vm文件路径
     * @param params        需要填充的参数
     * @return              转换后的html字符串
     */
    public static String velocityToHtml(String vmFilePath, Map<String, Object> params) {
        if (StringUtil.isBlank(vmFilePath)){
            return null;
        }

        VelocityEngine engine = new VelocityEngine();
        engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        engine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        engine.init();

        Template template = null;
        try {
            template = engine.getTemplate(vmFilePath, "UTF-8");
        } catch (Exception e) {
            LOGGER.error("Load velocity file failed:", e);
            return null;
        }

        VelocityContext context = new VelocityContext();

        if (params != null) {
            for (String key : params.keySet()) {
                context.put(key, params.get(key));
            }
        }
        StringWriter writer = new StringWriter();

        try {
            template.merge(context, writer);
        } catch (Exception e) {
            LOGGER.error("velocity template render failed.", e);
            return null;
        }

        return writer.toString();
    }

    /**
     * 将html转换为 pdf相应的流
     *
     * @param htmlContent   需要转换的html
     * @return  pdf相应的流
     */
    public static InputStream htmlToPDF(String htmlContent) {
        ITextRenderer render = new ITextRenderer();
        ITextFontResolver resolver = render.getFontResolver();
        String fontPath = "font/";
        List<String> trueTypeList = new ArrayList<>();
        trueTypeList.add(fontPath + "simsun.ttc");//添加字体

        for (String trueType : trueTypeList) {
            try {
                resolver.addFont(trueType, com.lowagie.text.pdf.BaseFont.IDENTITY_H, com.lowagie.text.pdf.BaseFont.EMBEDDED);
            } catch (Exception e) {
                LOGGER.error("Add font failed:", e);
            }
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        try {
            render.setDocumentFromString(htmlContent);
            render.layout();
            render.createPDF(out);
        } catch (Exception e) {
            LOGGER.error("Failed to generate pdf file, the source htmls is:" + htmlContent, e);
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}