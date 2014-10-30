parser grammar WACCParser;

options {
  tokenVocab=WACCLexer;
}

func : type ident OPEN_PARANTHESES 
	  (param_list)? CLOSE PARENTHESES IS stat END ;

stat : SKIP
| type ident EQUALS assign_rhs
| assign_lhs EQUALS assign_rhs
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

param_list: param
| param COMMA param_list
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

// EOF indicates that the program must consume to the end of the input.
prog: (expr)*  EOF ;
