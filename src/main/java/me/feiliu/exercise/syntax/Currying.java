package me.feiliu.exercise.syntax;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 柯里化函数
 *
 * @author liufei17
 */
public class Currying {

    private Integer b = 2;

    private Integer getB() {
        b++;
        return b;
    }

    private Stream calculate1(Stream stream, final Integer a) {
        return stream.map(new Function<Integer, Integer>() {

            @Override
            public Integer apply(Integer t) {
                return t * a + b;
            }
        });
    }

    private Stream calculate2(Stream stream, Int a) {
        /*return stream.map(new Function<Integer, Integer>() {

            @Override
            public Integer apply(Integer t) {
                return t * a.getA() + getB();
            }
        });*/

        // final int b=2;

        return stream.map(t -> (Integer) t * a.getA() + (b += 1));
    }

    class Int {

        private Integer a;

        public Int(Integer a) {
            this.a = a;
        }

        public Integer getA() {
            return a;
        }
    }

    private void test() {
        List list = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(calculate1(list.stream(), 3).collect(Collectors.toList()));
        System.out.println(calculate1(list.stream(), 3).collect(Collectors.toList()));
        System.out.println(calculate2(list.stream(), new Int(3)).collect(Collectors.toList()));
        System.out.println(calculate2(list.stream(), new Int(3)).collect(Collectors.toList()));
    }

    public static void main(String[] args) {
        Currying c = new Currying();
        c.test();
    }
}

