package system;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class systemTest {
    private final static String NAME_STRING = "PowerRouter.exe";


    public static void main(String args[]){
        boolean isWindows=System.getProperties().getProperty("os.name").toUpperCase().startsWith("WIN");
      // 1 判断当前系统是windows还是linux
      if(isWindows){
          //杀死进程
          if(finishExeOfWindows()){

          }else{
              System.out.println("进程不存在");
          }
      }
        if(startExeOfWindows()){
            System.out.println("进程已启动");
        }
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
     * windows下启动一个进程
     * @return
     */
    public static boolean startExeOfWindows(){

           /*new ProcessBuilder("test","F:\\powerRouter","");*/
        try {
            /*Runtime.getRuntime().exec("F:\\powerRouter\\PowerRouter.exe");*/
            File file = new File("F:\\powerRouter");
            Runtime.getRuntime().exec("F:\\powerRouter\\PowerRouter.exe",null,file);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
