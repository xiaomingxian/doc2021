package xxm.springlearn.pojo;

import lombok.Data;

@Data
public class Person {

    String name;
    Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
