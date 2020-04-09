/**
 * 实现了工厂模式的简单IOC容器。
 */

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class SimpleIOC {
    //字符串-对象的映射
    private Map<String, Object> beanMap = new HashMap<>();
    //构造函数（位置），只要创建了就会去loadBean
    public SimpleIOC(String location) throws Exception {
        loadBeans(location);
    }
    //通过名字拿bean
    public Object getBean(String name){
        Object bean = beanMap.get(name);
        //没有此bean
        if (bean == null) {
            throw new IllegalArgumentException("there is no bean with name " + name);
        }

        return bean;
    }
    //加载bean
    private void loadBeans(String location) throws Exception {
        //通过location找文件夹下的xml文件
        InputStream inputStream = new FileInputStream(location);
        //(抽象类)定义工厂API，使应用程序能够从XML文档获取生成DOM对象树的解析器。
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //定义从XML文档获取DOM文档实例的API。 使用这个类，应用程序员可以从XML获得一个Document
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        //将给定文件的内容解析为XML文档，并返回一个新的DOM Document对象
        Document doc = docBuilder.parse(inputStream);
        //就是标签吧
        Element root = doc.getDocumentElement();
        //根标签的所有孩子节点
        NodeList nodes = root.getChildNodes();
        //遍历bean标签
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            //是个元素
            if (node instanceof Element) {
                Element ele = (Element) node;
                //获取id和class
                String id = ele.getAttribute("id");
                String className = ele.getAttribute("class");

                //加载beanClass
                Class beanClass = null;
                try {
                    beanClass = Class.forName(className);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return;
                }

                //创建bean
                Object bean = beanClass.newInstance();

                //遍历<property>标签（二级标签）
                NodeList propertyNodes = ele.getElementsByTagName("property");
                for (int j = 0; j < propertyNodes.getLength(); j++) {
                    Node propertyNode = propertyNodes.item(j);
                    if (propertyNode instanceof Element) {
                        Element propertyElement = (Element) propertyNode;
                        String name = propertyElement.getAttribute("name");
                        String value = propertyElement.getAttribute("value");

                        // 利用反射将 bean 相关字段访问权限设为可访问
                        Field declaredField = bean.getClass().getDeclaredField(name);
                        declaredField.setAccessible(true);

                        if (value != null && value.length() > 0) {
                            // 将属性值填充到相关字段中
                            declaredField.set(bean, value);
                        }else{
                            String ref = propertyElement.getAttribute("ref");
                            if (ref == null || ref.length() == 0) {
                                throw new IllegalArgumentException("ref config error");
                            }
                            // 将引用填充到相关字段中
                            declaredField.set(bean, getBean(ref));
                        }
                        // 将 bean 注册到 bean 容器中
                        registerBean(id, bean);
                    }

                }
            }
        }

    }
    private void registerBean(String id, Object bean) {
        beanMap.put(id, bean);   //这就是一个bean容器。
    }
}
