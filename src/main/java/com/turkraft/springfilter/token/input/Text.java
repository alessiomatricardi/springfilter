package com.turkraft.springfilter.token.input;

import java.text.ParsePosition;
import java.util.Date;
import com.turkraft.springfilter.FilterConfig;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@ToString(callSuper = true)
public class Text implements IInput {

  private String value;

  @Override
  public boolean canBe(Class<?> klass) {
    return String.class.isAssignableFrom(klass) || Date.class.isAssignableFrom(klass)
        || klass.isEnum();
  }

  @Override
  public Object getValueAs(Class<?> klass) {

    if (klass.equals(String.class)) {
      return getValue();
    }

    if (klass.isEnum()) {
      for (Object e : klass.getEnumConstants()) {
        if (getValue().equalsIgnoreCase(e.toString())) {
          return e;
        }
      }
      throw new ClassCastException(
          "The value '" + getValue() + "' didn't match any value of enum " + klass.getSimpleName());
    }

    if (klass.equals(Date.class)) {
      Date date = FilterConfig.DATE_FORMATTER.parse(getValue(), new ParsePosition(0));
      if (date != null) {
        return date;
      }
      // throw specific exception otherwise, the input was not in the correct format
    }

    throw new ClassCastException(
        "Could not cast input '" + getValue() + "' to class " + klass.getSimpleName());

  }

  @Override
  public String generate() {
    if (value == null)
      return null;
    return "'" + value.replace("'", "\\'") + "'";
  }

}
