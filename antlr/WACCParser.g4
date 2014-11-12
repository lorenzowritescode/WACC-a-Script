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

stat : SKIP							# skip_stat
| type ident EQUAL assign_rhs		# variable_declaration
| assign_lhs EQUAL assign_rhs		# variable_assigment
| READ assign_lhs					# read_stat
| FREE expr							# free_stat
| RETURN expr						# return_stat
| EXIT expr							# exit_stat
| PRINT expr						# print_stat
| PRINTLN expr 						# println_expr
| IF expr THEN stat ELSE stat ENDIF	# if_stat
| WHILE expr DO stat DONE			# while_stat
| BEGIN stat END 					# block_stat
| stat SEMI_COLON stat 				# sequential_stat
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

param_list: param (COMMA param)*
;

param: type ident;

array_type: ( base_type | pair_type ) (OPEN_SQUARE CLOSE_SQUARE)+ ;

type: base_type
| array_type
| pair_type
;

pair_type: PAIR OPEN_PARENTHESES pair_elem_type COMMA pair_elem_type CLOSE_PARENTHESES ;

single_expr : OPEN_PARENTHESES expr CLOSE_PARENTHESES 
| factor 
;

expr : expr (MUL | DIV | MOD) expr
| expr (PLUS | MINUS) expr
| expr (GREATER | GREATER_EQUAL | LESS | LESS_EQUAL) expr
| expr (DOUBLE_EQUALS | NOT_EQUAL) expr
| expr AND expr
| expr OR expr
| unary_oper expr
| single_expr
;

factor : int_liter		
| bool_liter			
| char_liter 			
| str_liter 			
| pair_liter 			
| ident 				
| array_elem 			
;

array_elem : ident (OPEN_SQUARE expr CLOSE_SQUARE)+ ;

int_liter : int_sign INTEGER
| INTEGER
;

int_sign : PLUS
| MINUS
;

bool_liter : TRUE
| FALSE
;

char_liter : CHAR_LITER ;

str_liter : STRING_LITER ;

array_liter : OPEN_SQUARE (expr (COMMA (expr))*)? CLOSE_SQUARE ;

pair_liter : NULL ;

unary_oper: NOT
| MINUS
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
prog: BEGIN func* stat END;
