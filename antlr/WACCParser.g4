parser grammar WACCParser;

options {
  tokenVocab=WACCLexer;
}

base_type: INT
| BOOL
| CHAR
| STRING
;

pair_elem_type : base_type
| array_type
| PAIR
;

func : type ident OPEN_PARENTHESES 
	  (param_list)? CLOSE_PARENTHESES IS stat END ;

stat : SKIP
| type ident EQUAL assign_rhs
| assign_lhs EQUAL assign_rhs
| READ assign_lhs
| FREE expr
| RETURN expr
| EXIT expr
| PRINT expr
| PRINTLN expr
| IF expr THEN stat ELSE stat ENDIF
| WHILE expr DO stat DONE
| BEGIN stat END
| stat SEMI_COLON stat
;

assign_lhs : ident
| array_elem
| pair_elem
;

assign_rhs : expr
| array_liter
| NEWPAIR OPEN_PARENTHESES expr COMMA expr CLOSE_PARENTHESES
| pair_elem
| CALL ident OPEN_PARENTHESES (arg_list)? CLOSE_PARENTHESES
;

arg_list : expr (COMMA (expr))* ;

pair_elem: FST expr
| SND expr
;

binaryOper : PLUS | MINUS ;

param_list: param (COMMA param_list)*
;

param: type ident;

array_type: ( base_type | pair_type ) (OPEN_SQUARE CLOSE_SQUARE)+ ;

type: base_type
| array_type
| pair_type
;

pair_type: PAIR OPEN_PARENTHESES pair_elem_type COMMA pair_elem_type CLOSE_PARENTHESES ;

expr : unary_oper expr
| expr binary_oper expr
| OPEN_PARENTHESES expr CLOSE_PARENTHESES
| int_liter
| bool_liter
| CHAR_LITERAL
| str_liter
| pair_liter
| ident
| array_elem
;

array_elem : ident OPEN_SQUARE expr CLOSE_SQUARE ;

int_liter : int_sign INTEGER
| INTEGER
;

int_sign : PLUS
| MINUS
;

bool_liter : TRUE
| FALSE
;

str_liter : STRING_LITER ;

array_liter : OPEN_SQUARE (expr (COMMA (expr))*)? CLOSE_SQUARE ;

pair_liter : NULL ;

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

// remove?
ident: IDENTITY;


// EOF indicates that the program must consume to the end of the input.
prog: BEGIN (stat | func )*  END;
