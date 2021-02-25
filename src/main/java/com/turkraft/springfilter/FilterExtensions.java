package com.turkraft.springfilter;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Pattern;
import lombok.experimental.ExtensionMethod;

@ExtensionMethod(FilterExtensions.class)
public class FilterExtensions {

  public static char take(StringBuilder sb) {
    return take(sb, 1).charAt(0);
  }

  public static String take(StringBuilder sb, int count) {
    String s = sb.substring(0, count);
    sb.delete(0, count);
    return s;
  }

  public static char index(StringBuilder sb) {
    return sb.charAt(0);
  }

  public static boolean indexIs(StringBuilder sb, char... c) {
    if (sb.length() < c.length) {
      return false;
    }
    for (int i = 0; i < c.length; i++) {
      if (sb.charAt(i) != c[i]) {
        return false;
      }
    }
    return true;
  }

  public static boolean isEmpty(StringBuilder sb) {
    return sb.length() == 0;
  }

  public static String getMatch(StringBuilder sb, Pattern pattern) {
    java.util.regex.Matcher regexMatcher = pattern.matcher(sb);
    if (regexMatcher.find()) {
      return take(sb, regexMatcher.end());
    }
    return null;
  }

  public static <T> LinkedList<T> copy(LinkedList<T> list) {
    return new LinkedList<>(list);
  }

  public static <T> void replaceWith(LinkedList<T> list, LinkedList<T> replacor) {
    list.clear();
    list.addAll(replacor);
  }

  public static <T> T take(LinkedList<T> list) {
    return list.pop();
  }

  public static <T> LinkedList<T> take(LinkedList<T> list, int count) {
    LinkedList<T> result = new LinkedList<>();
    for (int i = 0; i < count; i++) {
      result.add(list.pop());
    }
    return result;
  }

  public static <T> T index(LinkedList<T> list) {
    return list.peekFirst();
  }

  @SafeVarargs
  public static <T> Boolean indexIs(LinkedList<T> list, Class<? extends T>... klasses) {
    return iteratorIs(list.iterator(), klasses);
  }

  @SafeVarargs
  public static <T> Boolean lastIs(LinkedList<T> list, Class<? extends T>... klasses) {
    return iteratorIs(list.descendingIterator(), klasses);
  }

  @SafeVarargs
  private static <T> Boolean iteratorIs(Iterator<T> iterator, Class<? extends T>... klasses) {

    for (Class<? extends T> klass : klasses) {

      if (!iterator.hasNext()) {
        return false;
      }

      T node = iterator.next();

      if (klass != null && !klass.isAssignableFrom(node.getClass())) {
        return false;
      }

    }

    return true;

  }

}
