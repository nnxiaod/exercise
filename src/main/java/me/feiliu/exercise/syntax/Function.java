package me.feiliu.exercise.syntax;

/**
 * @author liufei17
 * @since 2017/12/11
 */
public interface Function<T, U> {
    U apply(T t);
}
