package com.demo.controller.Util;

import com.demo.controller.domController;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

@Component
public class writeXml {
    static boolean isWindows=System.getProperties().getProperty("os.name").toUpperCase().startsWith("WIN");
    static boolean isLinux=System.getProperties().getProperty("os.name").toUpperCase().startsWith("LIN");
    private static String path;
    private static String pathOfLinux;


    @Value("${exeInfo.config.windows.filePath}")
    public  void setPath(String path) {
        this.path = path;
    }
    @Value("{exeInfo.config.linux.filePathOfLinux}")
    public void setPathOfLinux(String pathOfLinux) {
        this.pathOfLinux = pathOfLinux;
    }


    public static void writeXml(Document document){
        OutputFormat format = OutputFormat.createPrettyPrint();
        //         OutputFormat format = OutputFormat.createCompactFormat();//紧凑的格式（全部弄到一行）
        OutputStream os = null;
        try {
            if(isWindows){
                os = new FileOutputStream(path);
            } else{
                os=new FileOutputStream(pathOfLinux);
            }
            XMLWriter xmlWriter = null;   //有中文使用formant格式
            try {
                xmlWriter = new XMLWriter(os,format);
                xmlWriter.write(document);
                xmlWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
