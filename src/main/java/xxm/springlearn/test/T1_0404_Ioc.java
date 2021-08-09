package xxm.springlearn.test;

import org.junit.Test;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.ObjectFactoryCreatingFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import xxm.springlearn.pojo.Person;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.lang.annotation.Target;
import java.util.Map;
import java.util.stream.Stream;

public class T1_0404_Ioc {


    private String name ;

    public static void main(String[] args)throws  Exception {

        //1 配置元信息获取
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class,Object.class);//第二个参数  Class<?> stopClass

        Stream.of(beanInfo.getPropertyDescriptors()).forEach(propertyDescriptor -> {

            System.out.println(propertyDescriptor);
            //可以在里面拿到字段信息改变属性 什么的  元数据编程
            Class<?> propertyType = propertyDescriptor.getPropertyType();
            String name = propertyDescriptor.getName();
            //...

        });

        //2
    }

    /**
     * 依赖查找  通过名称
     */
    @Test
    public void IocSearchByName(){
        //1 实时查找 XML
        ClassPathXmlApplicationContext beanContext = new ClassPathXmlApplicationContext("classpath:/META-INF/depencg-search.xml");
        Person person=(Person) beanContext.getBean("person");//获取类型强转
        System.out.println("实时查找："+person);
        //2 延时查找
        //ObjectFactoryCreatingFactoryBean  是 ObjectFactory的实现
        ObjectFactory<Person> objFactory = (ObjectFactory)beanContext.getBean("objFactory");
        Person object = objFactory.getObject();
        System.out.println("延迟查询："+object);

    }
    /**
     * 依赖查找  通过类型
     */
    @Test
    public void IocSearchByType(){
        //1 查找单个
        ClassPathXmlApplicationContext beanContext = new ClassPathXmlApplicationContext("classpath:/META-INF/depencg-search.xml");
        Person bean = beanContext.getBean(Person.class);
        System.out.println("type single:"+bean);


        if (beanContext instanceof ListableBeanFactory){
            ListableBeanFactory factory=(ListableBeanFactory)beanContext;
            Map<String, Person> beansOfType = factory.getBeansOfType(Person.class);
            System.out.println("list result:"+beansOfType);
        }


    }

}
