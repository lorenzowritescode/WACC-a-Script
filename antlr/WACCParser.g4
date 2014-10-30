parser grammar WACCParser;

options {
  tokenVocab=WACCLexer;
}

binaryOper : PLUS | MINUS ;

expr: expr binaryOper expr
| INTEGER
| OPEN_PARENTHESES expr CLOSE_PARENTHESES
;

stat : SKIP
      | type indent EQUALS assign-rhs
      | assign-lhs EQUALS assign-rhs
      | READ assign-lhs
      | FREE expr
      | RETURN expr
      | EXIT expr
      | PRINT expr
      | PRINTLN exprs

// EOF indicates that the program must consume to the end of the input.
prog: (expr)*  EOF ;
