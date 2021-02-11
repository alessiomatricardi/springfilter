package com.torshid.springfilter;

import java.util.LinkedList;

import com.torshid.compiler.Tokenizer;
import com.torshid.compiler.exception.TokenizerException;
import com.torshid.compiler.token.IToken;
import com.torshid.compiler.token.Matcher;
import com.torshid.springfilter.token.SpaceMatcher;
import com.torshid.springfilter.token.WordMatcher;
import com.torshid.springfilter.token.predicate.ComparatorMatcher;
import com.torshid.springfilter.token.predicate.OperatorMatcher;
import com.torshid.springfilter.token.predicate.ParenthesisMatcher;
import com.torshid.springfilter.token.statement.FieldMatcher;
import com.torshid.springfilter.token.statement.input.BoolMatcher;
import com.torshid.springfilter.token.statement.input.NumeralMatcher;
import com.torshid.springfilter.token.statement.input.TextMatcher;

public class FilterTokenizer {

  private FilterTokenizer() {}

  private static Matcher<?>[] matchers = new Matcher<?>[] {

      new SpaceMatcher(), new ParenthesisMatcher(), new OperatorMatcher(), new ComparatorMatcher(), new BoolMatcher(),
      new NumeralMatcher(), new FieldMatcher(), new WordMatcher(), new TextMatcher()

  };

  public static LinkedList<IToken> tokenize(String input) throws TokenizerException {
    return Tokenizer.tokenize(matchers, input);
  }

}
