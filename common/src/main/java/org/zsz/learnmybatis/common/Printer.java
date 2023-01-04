package org.zsz.learnmybatis.common;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Linus Zhang
 * @create 2022-11-16 23:07
 */
@Slf4j
public final class Printer {

  private Printer() {
  }

  private static final Consumer<String> LOG_PRINTLN = str -> log.info("{}", str);

  public static <T> void print(T type) {
    LOG_PRINTLN.accept(type.toString());
  }

  public static <E> void printCollection(Collection<E> list) {
    OptionalCollection.ofNullable(list)
        .stream()
        .map(Object::toString)
        .forEach(LOG_PRINTLN);
  }

  public static void printStrings(String... strings) {
    Arrays.stream(strings).forEach(LOG_PRINTLN);
  }

  public static <T> void printStrings(T type, Function<T, String[]> extract) {
    printStrings(extract.apply(type));
  }

  public static <K, V> void printMapValues(Map<K, V> map) {
    map.values()
        .stream()
        .map(Objects::toString)
        .forEach(LOG_PRINTLN);
  }

  public static <T, K, V> void printMapValues(T type, Function<T, Map<K, V>> extract) {
    printMapValues(extract.apply(type));
  }

}
