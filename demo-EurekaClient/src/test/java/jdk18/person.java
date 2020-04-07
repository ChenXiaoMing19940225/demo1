package jdk18;

import java.io.Serializable;

public class person implements Serializable {
    private static final long serialVersionUID = -832864699948294613L;
    String name;
    String sex;
    String age;

    public person() {
    }

    public person(String name, String sex, String age){
        this.name = name;
        this.sex = sex;
        this.age= age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
