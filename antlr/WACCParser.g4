parser grammar WACCParser;

options {
  tokenVocab=WACCLexer;
}

binaryOper : PLUS | MINUS ;

param_list: param (COMMA param_list)*
;

param: type ident;

type: base_type
| array_type
| pair_type
;

base_type: INT
| BOOL
| CHAR
| STRING
;

array_type: type OPEN_SQUARE_BRACKET CLOSE_SQUARE_BRACKET;

pair_type: PAIR OPEN_PARENTHESES pair_elem_type COLON pair_elem_type CLOSE_PARENTHESES ;

expr: expr binaryOper expr
| INTEGER
| OPEN_PARENTHESES expr CLOSE_PARENTHESES
;

unary_oper: NOT
| HYPHEN
| LEN
| ORD
| CHR
;

binary_oper: MUL
| DIV
| MOD
| PLUS
| MINUS
| GREATER
| GREATER_EQUAL
| LESS
| LESS_EQUAL
| DOUBLE_EQUALS
| NOT_EQUAL
| AND
| OR
;

ident: ID_BEGIN_CHAR (ID_CHAR)* ;



// EOF indicates that the program must consume to the end of the input.
prog: (expr)*  EOF ;
