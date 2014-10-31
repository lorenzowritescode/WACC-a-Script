parser grammar WACCParser;

options {
  tokenVocab=WACCLexer;
}

func : type ident OPEN_PARENTHESES 
	  (param_list)? CLOSE_PARENTHESES IS stat END ;

stat : SKIP
| type ident DOUBLE_EQUALS assign_rhs
| assign_lhs DOUBLE_EQUALS assign_rhs
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

base_type: INT
| BOOL
| CHAR
| string_char
;

pair_type: PAIR OPEN_PARENTHESES pair_elem_type COLON pair_elem_type CLOSE_PARENTHESES ;

expr : unary_oper expr
| expr binary_oper expr
| OPEN_PARENTHESES expr CLOSE_PARENTHESES
| int_liter
| bool_liter
| char_literal
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

char_literal : APOSTROPHE ANY_CHAR APOSTROPHE ;

escaped_char : END_OF_STRING
| NEWLINE
| TAB
| CARRIAGE_RETURN
| FORM_FEED
| DOUBLE_QUOTES
| BACKSLASH
| APOSTROPHE
;

string_char : ANY_CHAR 
| escaped_char 
;

str_liter : DOUBLE_QUOTES (string_char)* DOUBLE_QUOTES ;

array_liter : OPEN_SQUARE (expr (COMMA (expr))*)? CLOSE_SQUARE ;

pair_elem_type : base_type
| array_type
| PAIR
;

pair_liter : NULL ;

comment : HASH_KEY (ANY_CHAR ~(NEWLINE))* NEWLINE ;

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
prog: BEGIN (expr)*  END EOF ;
