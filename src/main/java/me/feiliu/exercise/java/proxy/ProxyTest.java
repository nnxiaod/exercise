package me.feiliu.exercise.java.proxy;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 代理测试类
 *
 * @author liufei17
 * @since 2018/1/10
 */
public class ProxyTest {

    @SuppressWarnings("unchecked")
    public static void main(String[] args)
            throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {

        //代理类字节码
        Class clazzProxy1 = Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);

        //代理类构造方法列表,没有无参构造方法
        System.out.println("...........begin constructors list............");
        Constructor[] constructors = clazzProxy1.getConstructors();
        for (Constructor constructor : constructors) {
            StringBuilder sBuilder = new StringBuilder();
            sBuilder.append(constructor.getName()).append("(");
            Class[] clazzparams = constructor.getParameterTypes();
            for (Class clazzparam : clazzparams) {
                sBuilder.append(clazzparam.getName()).append(",");
            }
            if (clazzparams.length != 0) {
                sBuilder.deleteCharAt(sBuilder.length() - 1);
            }
            sBuilder.append(")");
            System.out.println(sBuilder.toString());
        }

        //代理类方法列表
        System.out.println("...........begin methods list............");
        Method[] methods = clazzProxy1.getMethods();
        for (Method method : methods) {
            StringBuilder sBuilder = new StringBuilder();
            sBuilder.append(method.getName()).append("(");
            Class[] clazzparams = method.getParameterTypes();
            for (Class clazzparam : clazzparams) {
                sBuilder.append(clazzparam.getName()).append(",");
            }
            if (clazzparams.length != 0) {
                sBuilder.deleteCharAt(sBuilder.length() - 1);
            }
            sBuilder.append(")");
            System.out.println(sBuilder.toString());
        }

        //通过字节码创建代理类的实例,不能用newInstance(),构造方法传入InvocationHandler
        System.out.println("...........begin create instance object............");
        Constructor constructor = clazzProxy1.getConstructor(InvocationHandler.class);
        Collection proxy1 = (Collection) constructor.newInstance(new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });
        System.out.println(proxy1);
        proxy1.clear();//无返回值的方法成功

        //一步到位创建代理类实例
        Collection proxy2 = (Collection) Proxy.newProxyInstance(Collection.class.getClassLoader(), new Class[] { Collection.class }, new InvocationHandler() {

            //被代理对象，目标对象
            List<String> target = new ArrayList<>();

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                long beginTime = System.currentTimeMillis();
                Object retVal = method.invoke(target, args);
                long endTime = System.currentTimeMillis();
                System.out.println(method.getName() + " running time is " + (endTime - beginTime));
                return retVal;
            }

        });

        proxy2.add("lhm");
        proxy2.add("zxx");
        proxy2.add("bxd");
        System.out.println(proxy2.size());
        System.out.println(proxy2.getClass().getName());
    }

}
