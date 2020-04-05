package jdk18;
@FunctionalInterface
public interface testInterface {
//无参
    String getFood();
}
@FunctionalInterface
interface testInterfaceOne{
    String getFoodOne(String a);
}
@FunctionalInterface
interface testInterfaceTwo{
    String getFoodTwo(String a,String b);
}
@FunctionalInterface
interface testInterfaceThree{
    String getFoodThree(String a,String b);
}
@FunctionalInterface
//对象生成器接口
interface testInterfaceFour{
    person getFoodFour(String name,String sex,String age);
}
//过滤接口
@FunctionalInterface
interface testInterfaceFive<T>{
    boolean test(T t);
}