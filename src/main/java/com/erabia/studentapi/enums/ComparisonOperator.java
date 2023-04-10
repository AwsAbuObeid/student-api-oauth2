package com.erabia.studentapi.enums;

public enum ComparisonOperator {
    GT(">"),LT("<"),EQ("=");

    private final String op;
    ComparisonOperator(String op){
        this.op=op;
    }
    public String getOp() {
        return op;
    }
}
