package me.feiliu.exercise.java.proxy;

/**
 * 代理类。为了代理TargetInterface，我必须也得装成和它一样（实现它）。
 *
 * @author liufei17
 * @since 2018/1/10
 */
public class ProxyClass implements TargetInterface {

    private TargetInterface targetClass;

    public ProxyClass(TargetInterface targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public void target1() {
        // 前增强
        targetClass.target1();
        // 后增强
    }

    @Override
    public void target2() {
        // 前增强
        targetClass.target2();
        // 后增强
    }

    public TargetInterface getTargetInterface() {

        //被代理对象
        TargetInterface targetClass = new TargetClass();

        //代理对象，将被代理对象控制起来
        TargetInterface proxyClass = new ProxyClass(targetClass);

        return proxyClass;
    }
}
