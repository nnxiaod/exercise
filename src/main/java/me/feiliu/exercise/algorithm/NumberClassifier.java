package me.feiliu.exercise.algorithm;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * 完美数分类问题
 *
 * 完美数 ： 真约数之和 = 数本身
 * 过剩数 ： 真约数之和 > 数本身
 * 不足数 ： 真约数之和 < 数本身
 *
 * 真约数之和 = 所有约数之和 - 该数本身
 *
 * 6是完美数，因为6的约数是1，2，3，1+2+3=6
 * 28也是完美数，因为28的约数是1，2，4，7，14，1+2+4+7+14=28
 *
 * @author liufei
 */
public class NumberClassifier {

    /**
     * 判断candidate是否是number的约数
     */
    public static boolean isFactor(final int number, final int candidate) {
        return number % candidate == 0;
    }

    /**
     * 获取number的约数集合，包含1和number本身
     */
    public static Set<Integer> factors(final int number) {
        Set<Integer> factors = new HashSet<Integer>();
        factors.add(1);
        factors.add(number);

        /*for (int i = 2; i < number; i++) {
            if (isFactor(number, i)) {
                factors.add(i);
            }
        }*/

        /*
         * 优化：约数总是成对出现的。例如对于数字16，我们找到约数2的时候，也就同时找到了约数8，因为2 * 8  = 16。
         * 假如我么成对地采集约数，那么只要检查小于或等于目标数平方根的数就可以了。
         */
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (isFactor(number, i)) {
                factors.add(i);
                factors.add(number / i);
            }
        }

        return factors;
    }

    /**
     * 求真约数之和
     */
    public static Integer aliquotSum(final int number) {
        int aliquotSum = 0;
        Set<Integer> factors = factors(number);
        for (int factor : factors) {
            aliquotSum += factor;
        }
        return aliquotSum - number;
    }

    /**
     * number是否是完美数
     */
    public static boolean isPerfect(final int number) {
        return aliquotSum(number) == number;
    }

    /**
     * number是否是不足数
     */
    public static boolean isDeficient(final int number) {
        return aliquotSum(number) < number;
    }

    /**
     * number是否是过剩数
     */
    public static boolean isAbundant(final int number) {
        return aliquotSum(number) > number;
    }

    /**
     * number是什么数
     */
    public static String is(final int number) {
        return isPerfect(number) ? "完美数" : isAbundant(number) ? "过剩数" : "不足数";
    }

    public static void main(String[] args) {
        System.out.println("命令式：");
        System.out.println("6是：" + NumberClassifier.is(6));
        System.out.println("7是：" + NumberClassifier.is(7));
        System.out.println("函数式：");
        System.out.println("6是：" + NumberClassifier.isByStream(6));
        System.out.println("7是：" + NumberClassifier.isByStream(7));
        System.out.println("64是：" + NumberClassifier.isByStream(64));
    }


    // 以下是Java8 Stream 实现：

    public static IntStream factorsByStream(final int number) {
        return IntStream.range(1, number + 1).filter(e -> number % e == 0);
    }

    public static Integer aliquotSumByStream(final int number) {
        return factorsByStream(number).sum() - number;
    }

    public static boolean isPerfectByStream(final int number) {
        return aliquotSumByStream(number) == number;
    }

    public static boolean isDeficientByStream(final int number) {
        return aliquotSumByStream(number) < number;
    }

    public static boolean isAbundantByStream(final int number) {
        return aliquotSumByStream(number) > number;
    }

    public static String isByStream(final int number) {
        return isPerfectByStream(number) ? "完美数" : isAbundantByStream(number) ? "过剩数" : "不足数";
    }

}
