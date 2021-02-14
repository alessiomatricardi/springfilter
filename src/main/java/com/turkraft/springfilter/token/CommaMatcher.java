package com.turkraft.springfilter.token;

import com.turkraft.springfilter.Extensions;

import lombok.experimental.ExtensionMethod;

@ExtensionMethod(Extensions.class)
public class CommaMatcher extends Matcher<Comma> {

  @Override
  public Comma match(StringBuilder input) {

    if (input.index() == ',') {
      input.take();
      return Comma.builder().build();
    }

    return null;

  }

}
