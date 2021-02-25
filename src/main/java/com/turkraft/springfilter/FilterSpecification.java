package com.turkraft.springfilter;

import java.util.HashMap;
import java.util.Objects;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import com.turkraft.springfilter.node.IExpression;

public class FilterSpecification<T> implements Specification<T> {

  private static final long serialVersionUID = 1L;

  private final String input;

  private final IExpression filter;

  public FilterSpecification(String input) {
    Objects.requireNonNull(input);
    this.input = input;
    this.filter = null;
  }

  public FilterSpecification(IExpression filter) {
    Objects.requireNonNull(filter);
    this.filter = filter;
    this.input = null;
  }

  @Override
  public Predicate toPredicate(
      Root<T> root,
      CriteriaQuery<?> query,
      CriteriaBuilder criteriaBuilder) {
    if (input != null) {
      return FilterCompiler.compile(input, root, query, criteriaBuilder);
    }
    return (Predicate) filter.generate(root, query, criteriaBuilder, new HashMap<>());
  }

  public String getInput() {
    return input;
  }

}
