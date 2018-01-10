package me.feiliu.exercise.java.proxy;

/**
 * 目标接口实现类，针对这个类进行代理
 *
 * @author liufei17
 * @since 2018/1/10
 */
public class TargetClass implements TargetInterface {

    @Override
    public void target1() {
        System.out.println("target1调用");
    }

    @Override
    public void target2() {

    }

    public TargetInterface getTargetInterface() {

        TargetInterface targetClass = new TargetClass();

        return targetClass;

    }
}
