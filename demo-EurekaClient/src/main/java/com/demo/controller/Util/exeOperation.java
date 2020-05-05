package com.demo.controller.Util;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class exeOperation {

    static boolean isWindows=System.getProperties().getProperty("os.name").toUpperCase().startsWith("WIN");
    static boolean isLinux=System.getProperties().getProperty("os.name").toUpperCase().startsWith("LIN");
    private static String NAME_STRING;
    private static String WORK_PATH;
    private static String FULL_PATH;
    private static String NAME_STRING_LINUX;
    private static String FULL_PATH_LINUX;
    private static String WORK_PATH_LINUX;


    @Value("${exeInfo.config.windows.nameString}")
    public  void setNameString(String nameString) {
        NAME_STRING = nameString;
    }

    @Value("${exeInfo.config.windows.workPath}")
    public  void setWorkPath(String workPath) {
        WORK_PATH = workPath;
    }

    @Value("${exeInfo.config.windows.fullPath}")
    public  void setFullPath(String fullPath) {
        FULL_PATH = fullPath;
    }

    @Value("${exeInfo.config.linux.nameStringOfLinux}")
    public  void setNameStringLinux(String nameStringLinux) {
        NAME_STRING_LINUX = nameStringLinux;
    }
    @Value("${exeInfo.config.linux.fullPathOfLinux}")
    public  void setFullPathLinux(String fullPathLinux) {
        FULL_PATH_LINUX = fullPathLinux;
    }
    @Value("${exeInfo.config.linux.workPathOfLinux}")
    public  void setWorkPathLinux(String workPathLinux) {
        WORK_PATH_LINUX = workPathLinux;
    }

    public static void finishExe(){

        if(isWindows){
            finishExeOfWindows();
        }
        if(isLinux){
            finishExeOfLinux();
        }
    }
    public static void  restartExe(){
                new Thread(new Runnable() {
                    @Override
                    public synchronized void run() {
                        try {

                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //windows系统时
                        if(isWindows) {
                            startExeOfWindows();
                        }
                        else{

                        }
                    }
                }).start();
    }

    /**
     * window下杀死进程
     * @return
     */
    public static boolean finishExeOfWindows(){

        BufferedReader bufferedReader = null;
        try {
            Process proc = Runtime.getRuntime().exec("tasklist -fi " + '"' + "imagename eq " + NAME_STRING +'"');
            bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(NAME_STRING)) {
                    //杀死进程
                    Runtime.getRuntime().exec("taskkill -f -im "+NAME_STRING);
                    return true;
                }
            }
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception ex) {}
            }
        }

    }

    /**
     * linux下杀死进程
     */
    public static boolean finishExeOfLinux(){
        return closeLinuxProcess(getPID(NAME_STRING_LINUX));
    }

    /**
     * windows下启动一个进程
     * @return
     */
    public static boolean startExeOfWindows(){
        Process process = null;
        BufferedReader reader =null;
        try{
            //杀掉进程
            process = Runtime.getRuntime().exec(FULL_PATH,null,new File(WORK_PATH));
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while((line = reader.readLine())!=null){
                System.out.println("start .sh "+line);
            }
            if(process != null){
                int extValue = process.waitFor(); //返回码 0 表示正常退出 1表示异常退出
                if(0 == extValue){
                    System.out.println("cmd启动进程命令执行完毕");
                    return true;
                }
            }
            return false;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally{
            if(process!=null){
                process.destroy();
            }

            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {

                }
            }
            return true;
        }
    }

    /**
     * linux下启动一个进程
     * @return
     */
    public static boolean startExeOfLinux(){
        Process process = null;
        BufferedReader reader =null;
        String[] cmd = {"/bin/sh", "-c", FULL_PATH_LINUX};
        try{
            //杀掉进程
            process = Runtime.getRuntime().exec(cmd,null,new File(WORK_PATH_LINUX));
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while((line = reader.readLine())!=null){
                System.out.println("start .sh "+line);
            }
            if(process != null){
                int extValue = process.waitFor(); //返回码 0 表示正常退出 1表示异常退出
                if(0 == extValue){
                    System.out.println("Shell启动脚本命令执行完毕");
                    return true;
                }
            }
            return false;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally{
            if(process!=null){
                process.destroy();
            }

            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {

                }
            }
            return true;
        }
    }

    /**
     * 获取Linux进程的PID
     * @param command
     * @return
     */
    public static String getPID(String command){
        BufferedReader reader =null;
        try{
            //显示所有进程
            Process process = Runtime.getRuntime().exec("ps -ef");
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while((line = reader.readLine())!=null){
                if(line.contains(command)){
                    System.out.println("相关信息 -----> "+command);
                    String[] strs = line.split("\\s+");
                    return strs[1];
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {

                }
            }
        }
        return null;
    }

    /**
     * 关闭Linux进程
     * @param Pid 进程的PID
     */
    public static boolean closeLinuxProcess(String Pid){
        Process process = null;
        BufferedReader reader =null;
        try{
            //杀掉进程
            process = Runtime.getRuntime().exec("kill -9 "+Pid);
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while((line = reader.readLine())!=null){
                System.out.println("kill PID return info -----> "+line);
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally{
            if(process!=null){
                process.destroy();
            }

            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {

                }
            }
          return true;
        }
    }


}
