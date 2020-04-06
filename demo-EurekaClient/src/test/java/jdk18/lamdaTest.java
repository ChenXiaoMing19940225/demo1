package jdk18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class lamdaTest{
    public static void main(String[] args){
        //开启一个线程
        new Thread(()->{
            for(int i=0;i<2;i++){
                System.out.println("测试");
            }
        }).start();
        //使用Callable接口开启一个线程



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
        List<person> persons = lamdaTest.filterPerson(lt,p->p.getName().equals("张三"));
        for (person per:persons) {
            System.out.println(per.getName()+"=="+per.getSex()+"=="+per.getAge());
        }



        // jdk1.8提供了四大函数式接口
        // 1、供给形接口 无参有返回值
        supplierTest(()-> {
            System.out.println("supplier");
            return "supplier";
        });

        // 2.Consumer 《T》：消费型接口，有参无返回值
        consumerTest("consumer",str->System.out.println(str));

        // 3.Function 《T,R》：:函数式接口，有参有返回值
        functionTest("function",str->{
            person p=new person();
            p.setName(str);
            System.out.println(p.getName());
            return p;

        });
        // 4.Predicate《T》： 断言型接口，有参有返回值，返回值是boolean类型
        System.out.println(perdicateTest("predicate",str->{
            boolean bool=false;
            if (str==new String("predicate")){
                bool=true;
            }
            return bool;
        }));
    }

      //声明一个与接口中一模一样的实现方法
      public static String getFoodThree(String str,String str1){
        return str+str1;
      };

      //定义一个过滤方法
      public static List<person> filterPerson(List<person> list,testInterfaceFive<person> test){
          List<person> prods=new ArrayList<>();
          for (person p:list) {
              if (test.test(p)){ //这里的具体的接口实现逻辑由lambda传入参数决定
                  prods.add(p);
              }
          }
          return prods;
      }
      public static void supplierTest(Supplier<String> supplier){
          supplier.get();
      }
      public static void consumerTest(String str, Consumer<String> consumer){
          consumer.accept(str);
      }
      public static void functionTest(String str, Function<String,person> function){
           function.apply(str);
      };
      public static boolean perdicateTest(String str, Predicate<String> predicate){
           return predicate.test(str);
      };

}
