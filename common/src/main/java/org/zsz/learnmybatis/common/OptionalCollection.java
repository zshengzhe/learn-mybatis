package org.zsz.learnmybatis.common;

import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.apache.commons.collections4.CollectionUtils;

/**
 * OptionalCollection <br/> 采用 CollectionUtils::isEmpty 判空
 *
 * @author Zhang Shengzhe
 * @create 2021-11-18 18:14
 */
public final class OptionalCollection<T> {

  private static final OptionalCollection<?> EMPTY = new OptionalCollection<>();

  /**
   * If true then the value is present, otherwise indicates no value is present
   */
  private final boolean isPresent;
  private final Collection<T> value;

  /**
   * Construct an empty instance.
   */
  private OptionalCollection() {
    this.isPresent = false;
    this.value = null;
  }

  /**
   * Returns an empty {@code OptionalCollection} instance.  No value is present for this OptionalCollection.
   *
   * @return an empty {@code OptionalCollection}
   * @apiNote Though it may be tempting to do so, avoid testing if an object is empty by comparing with {@code ==} against instances returned by
   * {@code Option.empty()}. There is no guarantee that it is a singleton. Instead, use {@link #isPresent()}.
   */
  public static <T> OptionalCollection<T> empty() {
    @SuppressWarnings("unchecked")
    OptionalCollection<T> t = (OptionalCollection<T>) EMPTY;
    return t;
  }

  /**
   * Construct an instance with the value present.
   *
   * @param value the String value to be present
   */
  private OptionalCollection(Collection<T> value) {
    Preconditions.checkArgument(CollectionUtils.isNotEmpty(value), "value is null");
    this.isPresent = true;
    this.value = value;
  }

  /**
   * Return an {@code OptionalCollection} with the specified value present.
   *
   * @param value the value to be present
   * @return an {@code OptionalCollection} with the value present
   */
  public static <T> OptionalCollection<T> of(Collection<T> value) {
    return new OptionalCollection<>(value);
  }

  public static <T> OptionalCollection<T> ofNullable(Collection<T> value) {
    return CollectionUtils.isEmpty(value) ? empty() : of(value);
  }

  public static <T, R> List<R> convertList(Collection<T> value, Function<T, R> convert) {
    return OptionalCollection.ofNullable(value)
        .stream()
        .map(convert)
        .collect(Collectors.toList());
  }

  public static <T, R> Set<R> convertSet(Collection<T> value, Function<T, R> convert) {
    return OptionalCollection.ofNullable(value)
        .stream()
        .map(convert)
        .collect(Collectors.toSet());
  }

  /**
   * If a value is present in this {@code OptionalCollection}, returns the value, otherwise throws {@code NoSuchElementException}.
   *
   * @return the value held by this {@code OptionalCollection}
   * @throws NoSuchElementException if there is no value present
   * @see OptionalCollection#isPresent()
   */
  public Collection<T> get() {
    if (!isPresent) {
      throw new NoSuchElementException("No value present");
    }
    return value;
  }

  /**
   * Return {@code true} if there is a value present, otherwise {@code false}.
   *
   * @return {@code true} if there is a value present, otherwise {@code false}
   */
  public boolean isPresent() {
    return isPresent;
  }

  /**
   * Have the specified consumer accept the value if a value is present, otherwise do nothing.
   *
   * @param consumer block to be executed if a value is present
   * @throws NullPointerException if value is present and {@code consumer} is null
   */
  public void ifPresent(Consumer<Collection<T>> consumer) {
    if (isPresent) {
      consumer.accept(value);
    }
  }

  /**
   * Return the value if present, otherwise return {@code other}.
   *
   * @param other the value to be returned if there is no value present
   * @return the value, if present, otherwise {@code other}
   */
  public Collection<T> orElse(Collection<T> other) {
    return isPresent ? value : other;
  }

  /**
   * Return the value if present, otherwise invoke {@code other} and return the result of that invocation.
   *
   * @param other a {@code Supplier} whose result is returned if no value is present
   * @return the value if present otherwise the result of {@code other.get()}
   * @throws NullPointerException if value is not present and {@code other} is null
   */
  public Collection<T> orElseGet(Supplier<Collection<T>> other) {
    return isPresent ? value : other.get();
  }

  /**
   * Return the contained value, if present, otherwise throw an exception to be created by the provided supplier.
   *
   * @param <X>               Type of the exception to be thrown
   * @param exceptionSupplier The supplier which will return the exception to be thrown
   * @return the present value
   * @throws X                    if there is no value present
   * @throws NullPointerException if no value is present and {@code exceptionSupplier} is null
   * @apiNote A method reference to the exception constructor with an empty argument list can be used as the supplier. For example, {@code
   * IllegalStateException::new}
   */
  public <X extends Throwable> Collection<T> orElseThrow(Supplier<X> exceptionSupplier) throws X {
    if (isPresent) {
      return value;
    } else {
      throw exceptionSupplier.get();
    }
  }

  /**
   * If a value is present, apply the provided mapping function to it, and if the result is non-null, return an {@code Optional} describing the
   * result.  Otherwise return an empty {@code Optional}.
   *
   * @param <U>    The type of the result of the mapping function
   * @param mapper a mapping function to apply to the value, if present
   * @return an {@code Optional} describing the result of applying a mapping function to the value of this {@code Optional}, if a value is present,
   * otherwise an empty {@code Optional}
   * @throws NullPointerException if the mapping function is null
   * @apiNote This method supports post-processing on optional values, without the need to explicitly check for a return status.  For example, the
   * following code traverses a stream of file names, selects one that has not yet been processed, and then opens that file, returning an {@code
   * Optional<FileInputStream>}:
   *
   * <pre>{@code
   *     Optional<FileInputStream> fis =
   *         names.stream().filter(name -> !isProcessedYet(name))
   *                       .findFirst()
   *                       .map(name -> new FileInputStream(name));
   * }</pre>
   * <p>
   * Here, {@code findFirst} returns an {@code Optional<String>}, and then {@code map} returns an {@code Optional<FileInputStream>} for the desired
   * file if one exists.
   */
  public <U> OptionalCollection<U> map(Function<Collection<T>, Collection<U>> mapper) {
    Objects.requireNonNull(mapper);
    if (!isPresent()) {
      return empty();
    }
    return OptionalCollection.ofNullable(mapper.apply(value));
  }

  public Stream<T> stream() {
    if (!isPresent()) {
      return Stream.empty();
    }
    return StreamSupport.stream(spliterator(), false);
  }

  public Spliterator<T> spliterator() {
    if (!isPresent()) {
      return Spliterators.emptySpliterator();
    }
    return Spliterators.spliterator(this.value, 0);
  }

  /**
   * Indicates whether some other object is "equal to" this OptionalCollection. The other object is considered equal if:
   * <ul>
   * <li>it is also an {@code OptionalCollection} and;
   * <li>both instances have no value present or;
   * <li>the present values are "equal to" each other via {@code ==}.
   * </ul>
   *
   * @param obj an object to be tested for equality
   * @return {code true} if the other object is "equal to" this object otherwise {@code false}
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof OptionalCollection)) {
      return false;
    }

    OptionalCollection<T> other = (OptionalCollection<T>) obj;
    return (isPresent && other.isPresent)
        ? CollectionUtils.isEqualCollection(value, other.value)
        : isPresent == other.isPresent;
  }

  /**
   * Returns the hash code value of the present value, if any, or 0 (zero) if no value is present.
   *
   * @return hash code value of the present value or 0 if no value is present
   */
  @Override
  public int hashCode() {
    return isPresent ? value.hashCode() : 0;
  }

  /**
   * {@inheritDoc}
   * <p>
   * Returns a non-empty string representation of this object suitable for debugging. The exact presentation format is unspecified and may vary
   * between implementations and versions.
   *
   * @return the string representation of this instance
   * @implSpec If a value is present the result must include its string representation in the result. Empty and present instances must be
   * unambiguously differentiable.
   */
  @Override
  public String toString() {
    return isPresent
        ? String.format("OptionalCollection[%s]", value)
        : "OptionalCollection.empty";
  }

}
