package jdk18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class lamdaTest{
    public static void main(String[] args){
        //开启一个线程
        new Thread(()->{
            for(int i=0;i<10;i++){
                System.out.println("测试");
            }
        }).start();


        //有返回值无参声明式函数接口
       testInterface t =()->{
           return "one";
        };
        System.out.println(t.getFood());



       //有返回值一个参数声明式函数接口 可省略元括号
        testInterfaceOne t1 = a -> {
           return "two";
        };
        System.out.println(t1.getFoodOne("two"));


        //有返回值两个参数 有返回值的时候大括号可以省略
        testInterfaceTwo t2 = (a,b)-> a+b;
        System.out.println(t2.getFoodTwo("th","ree"));


        /* 更简单的写法
           参数数量和类型要与接口中定义的一致
           返回值类型要与接口中定义的一致
         */
        testInterfaceThree t3 = lamdaTest::getFoodThree;
        System.out.println(t3.getFoodThree("TH","REE"));


       /* testInterfaceFour t4=person::new;
        t4.getFoodFour("zhangs","15","sdf");*/

       //lambda表达式foreach遍历
        List list = new ArrayList();
        Collections.addAll(list,1,2,3,4,5);
//        list.forEach(System.out::println);
        list.forEach(s->System.out.println(s));






        //实现条件过滤的三种方式（1.使用设计模式每次把过滤接口当做参数传入 2.使用匿名内部类 3.lambda表达式）
        // 使用lambda表达式进行过滤
        List<person> lt=new ArrayList<>();//构造一个初始list
        Collections.addAll(lt,new person("张三","male","15"),new person("李四","male","15"));
        List<person> persons = lamdaTest.filterPerson(lt,(p)->p.getName().equals("张三"));
        for (person per:persons) {
            System.out.println(per.getName()+"=="+per.getSex()+"=="+per.getAge());
        }

    }

      //声明一个与接口中一模一样的实现方法
      public static String getFoodThree(String str,String str1){
        return str+str1;
      };

      //定义一个过滤方法
      public static List<person> filterPerson(List<person> list,testInterfaceFive<person> test){
          List<person> prods=new ArrayList<>();
          for (person p:list) {
              if (test.test(p)){
                  prods.add(p);
              }
          }
          return prods;
      }


}
