package com.turkraft.springfilter;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.turkraft.springfilter.node.Arguments;
import com.turkraft.springfilter.node.Field;
import com.turkraft.springfilter.node.Filter;
import com.turkraft.springfilter.node.Function;
import com.turkraft.springfilter.node.IExpression;
import com.turkraft.springfilter.node.Input;
import com.turkraft.springfilter.node.Nothing;
import com.turkraft.springfilter.node.predicate.ConditionInfix;
import com.turkraft.springfilter.node.predicate.ConditionPostfix;
import com.turkraft.springfilter.node.predicate.OperationInfix;
import com.turkraft.springfilter.node.predicate.OperationPrefix;
import com.turkraft.springfilter.node.predicate.Priority;
import com.turkraft.springfilter.token.Comparator;
import com.turkraft.springfilter.token.Operator;
import com.turkraft.springfilter.token.input.Bool;
import com.turkraft.springfilter.token.input.Numeral;
import com.turkraft.springfilter.token.input.Text;

public class FilterQueryBuilder {

  private FilterQueryBuilder() {}

  /* FILTER */

  public static Filter filter(IExpression body) {
    return Filter.builder().body(body).build();
  }

  /* NOTHING */

  public static IExpression nothing() {
    return Nothing.builder().build();
  }

  /* PRIORITY */

  public static IExpression priority(IExpression body) {
    return Priority.builder().body(body).build();
  }

  /* FIELD */

  public static IExpression field(String name) {
    return Field.builder().name(name).build();
  }

  /* INPUTS */

  public static IExpression input(Number value) {
    if (value == null)
      return nothing();
    return Input.builder().value(Numeral.builder().value(value).build()).build();
  }

  public static IExpression input(Boolean value) {
    if (value == null)
      return nothing();
    return Input.builder().value(Bool.builder().value(value).build()).build();
  }

  public static IExpression input(String value) {
    if (value == null)
      return nothing();
    return Input.builder().value(Text.builder().value(value).build()).build();
  }

  public static <T extends Enum<T>> IExpression input(Enum<T> value) {
    if (value == null)
      return nothing();
    return input(value.toString());
  }

  public static IExpression input(Date value) {
    if (value == null)
      return nothing();
    return input(FilterConfig.DATE_FORMATTER.format(value));
  }

  //  public static Input input(Object value) {
  //    return Input.builder().value(Text.builder().value(value != null ? value.toString() : null).build()).build();
  //  }

  /* INFIX CONDITIONS */

  private static IExpression condition(IExpression left, Comparator comparator, IExpression right) {
    return ConditionInfix.builder().left(left).comparator(comparator).right(right).build();
  }

  /* EQUAL */

  public static IExpression equal(IExpression left, IExpression right) {
    return condition(left, Comparator.EQUAL, right);
  }

  public static IExpression equal(String field, IExpression value) {
    return equal(field(field), value);
  }

  public static IExpression equal(String field, Boolean value) {
    return equal(field, input(value));
  }

  public static <T extends Number> IExpression equal(String field, T value) {
    return equal(field, input(value));
  }

  public static IExpression equal(String field, String value) {
    return equal(field, input(value));
  }

  public static <T extends Enum<T>> IExpression equal(String field, T value) {
    return equal(field, input(value));
  }

  public static IExpression equal(String field, Date value) {
    return equal(field, input(value));
  }

  /* NOT EQUAL */

  public static IExpression notEqual(IExpression left, IExpression right) {
    return condition(left, Comparator.NOT_EQUAL, right);
  }

  public static IExpression notEqual(String field, IExpression value) {
    return notEqual(field(field), value);
  }

  public static IExpression notEqual(String field, Boolean value) {
    return notEqual(field, input(value));
  }

  public static <T extends Number> IExpression notEqual(String field, T value) {
    return notEqual(field, input(value));
  }

  public static IExpression notEqual(String field, String value) {
    return notEqual(field, input(value));
  }

  public static <T extends Enum<T>> IExpression notEqual(String field, T value) {
    return notEqual(field, input(value));
  }

  public static IExpression notEqual(String field, Date value) {
    return notEqual(field, input(value));
  }

  /* GREATER THAN */

  public static IExpression greaterThan(IExpression left, IExpression right) {
    return condition(left, Comparator.GREATER_THAN, right);
  }

  public static IExpression greaterThan(String field, IExpression value) {
    return greaterThan(field(field), value);
  }

  public static <T extends Number> IExpression greaterThan(String field, T value) {
    return greaterThan(field, input(value));
  }

  public static <T extends Number> IExpression greaterThan(String field, Date value) {
    return greaterThan(field, input(value));
  }

  /* GREATER THAN OR EQUAL */

  public static IExpression greaterThanOrEqual(IExpression left, IExpression right) {
    return condition(left, Comparator.GREATER_THAN_OR_EQUAL, right);
  }

  public static IExpression greaterThanOrEqual(String field, IExpression value) {
    return greaterThanOrEqual(field(field), value);
  }

  public static <T extends Number> IExpression greaterThanOrEqual(String field, T value) {
    return greaterThanOrEqual(field, input(value));
  }

  public static <T extends Number> IExpression greaterThanOrEqual(String field, Date value) {
    return greaterThanOrEqual(field, input(value));
  }

  /* LESS THAN */

  public static IExpression lessThan(IExpression left, IExpression right) {
    return condition(left, Comparator.LESS_THAN, right);
  }

  public static IExpression lessThan(String field, IExpression value) {
    return lessThan(field(field), value);
  }

  public static <T extends Number> IExpression lessThan(String field, T value) {
    return lessThan(field, input(value));
  }

  public static <T extends Number> IExpression lessThan(String field, Date value) {
    return lessThan(field, input(value));
  }

  /* LESS THAN OR EQUAL */

  public static IExpression lessThanOrEqual(IExpression left, IExpression right) {
    return condition(left, Comparator.LESS_THAN_OR_EQUAL, right);
  }

  public static IExpression lessThanOrEqual(String field, IExpression value) {
    return lessThanOrEqual(field(field), value);
  }

  public static <T extends Number> IExpression lessThanOrEqual(String field, T value) {
    return lessThanOrEqual(field, input(value));
  }

  public static <T extends Number> IExpression lessThanOrEqual(String field, Date value) {
    return lessThanOrEqual(field, input(value));
  }

  /* LIKE */

  public static IExpression like(String field, Field pattern) {
    return condition(field(field), Comparator.LIKE, pattern);
  }

  public static IExpression like(String field, String pattern) {
    return condition(field(field), Comparator.LIKE, input(pattern));
  }

  /* IN */

  public static IExpression in(String field, List<IExpression> args) {
    return condition(field(field), Comparator.IN, Arguments.builder().values(args).build());
  }

  /* POSTFIX CONDITIONS */

  private static IExpression condition(IExpression left, Comparator comparator) {
    return ConditionPostfix.builder().left(left).comparator(comparator).build();
  }

  public static IExpression isNull(IExpression left) {
    return condition(left, Comparator.NULL);
  }

  public static IExpression isNull(String field) {
    return isNull(field(field));
  }

  public static IExpression isNotNull(IExpression left) {
    return condition(left, Comparator.NOT_NULL);
  }

  public static IExpression isNotNull(String field) {
    return isNotNull(field(field));
  }

  public static IExpression isEmpty(IExpression left) {
    return condition(left, Comparator.EMPTY);
  }

  public static IExpression isEmpty(String field) {
    return isEmpty(field(field));
  }

  public static IExpression isNotEmpty(IExpression left) {
    return condition(left, Comparator.NOT_EMPTY);
  }

  public static IExpression isNotEmpty(String field) {
    return isNotEmpty(field(field));
  }

  /* INFIX OPERATIONS */

  private static IExpression operation(IExpression left, Operator operator, IExpression right) {
    return OperationInfix.builder().left(left).operator(operator).right(right).build();
  }

  /* AND */

  public static IExpression and(IExpression left, IExpression right) {
    return operation(left, Operator.AND, right);
  }

  public static IExpression and(Collection<IExpression> expressions) {
    if (expressions == null || expressions.size() == 0)
      return nothing();
    Iterator<IExpression> i = expressions.iterator();
    if (expressions.size() == 1) {
      return i.next();
    }
    IExpression ands = i.next();
    while (i.hasNext()) {
      ands = and(ands, i.next());
    }
    return ands;
  }

  public static IExpression and(IExpression... expressions) {
    return and(Arrays.asList(expressions));
  }

  /* OR */

  public static IExpression or(IExpression left, IExpression right) {
    return operation(left, Operator.OR, right);
  }

  public static IExpression or(Collection<IExpression> expressions) {
    if (expressions == null || expressions.size() == 0)
      return nothing();
    Iterator<IExpression> i = expressions.iterator();
    if (expressions.size() == 1) {
      return i.next();
    }
    IExpression ors = i.next();
    while (i.hasNext()) {
      ors = or(ors, i.next());
    }
    return ors;
  }

  public static IExpression or(IExpression... expressions) {
    return or(Arrays.asList(expressions));
  }

  /* PREFIX OPERATIONS */

  private static IExpression operation(Operator operator, IExpression right) {
    return OperationPrefix.builder().operator(operator).right(right).build();
  }

  /* NOT */

  public static IExpression not(IExpression right) {
    return operation(Operator.NOT, right);
  }

  /* FUNCTIONS */

  public static IExpression function(String name, List<IExpression> args) {
    return Function.builder().name(name).arguments(Arguments.builder().values(args).build()).build();
  }

  public static IExpression function(Function.Type name, List<IExpression> args) {
    return function(name.name().toLowerCase(), args);
  }

  public static IExpression function(String name, IExpression... args) {
    return function(name, args == null ? Collections.emptyList() : Arrays.asList(args));
  }

  public static IExpression function(Function.Type name, IExpression... args) {
    return function(name.name().toLowerCase(), args);
  }

  public static IExpression absolute(IExpression arg) {
    return function(Function.Type.ABSOLUTE, arg);
  }

  public static IExpression absolute(String field) {
    return absolute(field(field));
  }

  public static IExpression min(IExpression arg) {
    return function(Function.Type.MIN, arg);
  }

  public static IExpression min(String field) {
    return min(field(field));
  }

  public static IExpression max(IExpression arg) {
    return function(Function.Type.MAX, arg);
  }

  public static IExpression max(String field) {
    return max(field(field));
  }

  public static IExpression average(IExpression arg) {
    return function(Function.Type.AVERAGE, arg);
  }

  public static IExpression average(String field) {
    return average(field(field));
  }

  public static IExpression sum(IExpression arg) {
    return function(Function.Type.SUM, arg);
  }

  public static IExpression sum(String field) {
    return sum(field(field));
  }

  public static IExpression size(IExpression arg) {
    return function(Function.Type.SIZE, arg);
  }

  public static IExpression size(String field) {
    return size(field(field));
  }

  public static IExpression length(IExpression arg) {
    return function(Function.Type.LENGTH, arg);
  }

  public static IExpression length(String field) {
    return length(field(field));
  }

  public static IExpression trim(IExpression arg) {
    return function(Function.Type.TRIM, arg);
  }

  public static IExpression trim(String field) {
    return trim(field(field));
  }

  public static IExpression currentDate() {
    return function(Function.Type.CURRENTDATE);
  }

  public static IExpression currentTime() {
    return function(Function.Type.CURRENTTIME);
  }

  public static IExpression currentTimestamp() {
    return function(Function.Type.CURRENTTIMESTAMP);
  }

  /* HELPERS */

  public static <T extends Number> List<IExpression> numbers(List<T> args) {
    if (args == null)
      return Collections.emptyList();
    return args.stream().map(a -> input(a)).collect(Collectors.toList());
  }

  @SafeVarargs
  public static <T extends Number> List<IExpression> numbers(T... args) {
    if (args == null)
      return Collections.emptyList();
    return numbers(Arrays.asList(args));
  }

  public static List<IExpression> bools(List<Boolean> args) {
    if (args == null)
      return Collections.emptyList();
    return args.stream().map(a -> input(a)).collect(Collectors.toList());
  }

  public static List<IExpression> bools(Boolean... args) {
    if (args == null)
      return Collections.emptyList();
    return bools(Arrays.asList(args));
  }

  public static List<IExpression> strings(List<String> args) {
    if (args == null)
      return Collections.emptyList();
    return args.stream().map(a -> input(a)).collect(Collectors.toList());
  }

  public static List<IExpression> strings(String... args) {
    if (args == null)
      return Collections.emptyList();
    return strings(Arrays.asList(args));
  }

  public static <T extends Enum<T>> List<IExpression> enums(List<T> args) {
    if (args == null)
      return Collections.emptyList();
    return args.stream().map(a -> input(a)).collect(Collectors.toList());
  }

  @SafeVarargs
  public static <T extends Enum<T>> List<IExpression> enums(T... args) {
    if (args == null)
      return Collections.emptyList();
    return enums(Arrays.asList(args));
  }

  public static List<IExpression> dates(List<Date> args) {
    if (args == null)
      return Collections.emptyList();
    return args.stream().map(a -> input(a)).collect(Collectors.toList());
  }

  public static List<IExpression> dates(Date... args) {
    if (args == null)
      return Collections.emptyList();
    return dates(Arrays.asList(args));
  }

}
