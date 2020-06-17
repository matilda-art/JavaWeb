/**
 * @program: 线程：状态流转和安全
 * @description
 * @author: matilda
 * @create: 2020-06-17 20:39
 **/
//线程中的状态：JDK中通过枚举来描述（enum）
public class EnumDemo {
    enum 性别{
        男,女,神秘人;
    }
    //创建了一个Enum的子类，子类的名称是“性别”
    //同时定义了两个对象：男和女

    public static void main(String[] args) {
        性别 sex = 性别.男;
        性别 sex2 = 性别.女;

        System.out.println(sex);
        System.out.println(sex2);

        性别[] values = 性别.values();
        for (性别 s:values) {
            System.out.println(s);
        }
    }
}
