package reflection;

public class ReflectTest {
    public static void main(String[] args) throws Exception {
        /**
         * 获取class
         */
        //1,使用对象获取类信息
        Class iphone = new IPhone().getClass();
        //2,使用类直接获取
        Class iphone2 = IPhone.class;
        //3,反射获取
        Class<?> aClass = Class.forName("reflection.IPhone");
        /**
         * 创建对象
         */
        IPhone iphone02 = (IPhone) aClass.getConstructor().newInstance();
    }
}
class IPhone {

}