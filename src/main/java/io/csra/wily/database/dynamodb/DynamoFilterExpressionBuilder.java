package io.csra.wily.database.dynamodb;

import java.util.ArrayList;
import java.util.List;

public class DynamoFilterExpressionBuilder {

    private final List<String> expressionList = new ArrayList<>();
    private final List<DynamoExpressionAttribute> attributes = new ArrayList<>();

    public DynamoFilterExpressionBuilder expression(String expression) {
        expressionList.add(expression);
        return this;
    }

    public DynamoFilterExpressionBuilder expression(DynamoExpressionAttribute attribute) {
        expressionList.add(attribute.getFilterExpression());
        attributes.add(attribute);
        return this;
    }

    public DynamoFilterExpressionBuilder and() {
        expressionList.add(" AND ");
        return this;
    }

    public DynamoFilterExpressionBuilder or() {
        expressionList.add(" OR ");
        return this;
    }

    public String build() {
        StringBuilder ret = new StringBuilder();
        expressionList.forEach(ret::append);
        return ret.toString();
    }

    public List<DynamoExpressionAttribute> attributes() {
        return attributes;
    }

}
