parser grammar WACCParser;

options {
  tokenVocab=WACCLexer;
}

// functions
func : type ident OPEN_PARENTHESES 
	  (param_list)? CLOSE_PARENTHESES IS stat END;

param_list: param (COMMA param)*;

param: type ident;

// statements
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

// Assignments
assign_lhs : ident
| array_elem
| pair_elem
;
assign_rhs : expr                                             # expr_arhs
| array_liter                                                 # array_liter_arhs
| NEWPAIR OPEN_PARENTHESES expr COMMA expr CLOSE_PARENTHESES  # newpair_assignment
| pair_elem                                                   # pair_elem_arhs
| CALL ident OPEN_PARENTHESES (arg_list)? CLOSE_PARENTHESES   # func_call_assignment
;
arg_list : expr (COMMA (expr))* ;

// Pairs. There can be pairs of pairs but when nested the inside type is not declared.
pair_type: PAIR OPEN_PARENTHESES pair_elem_type COMMA pair_elem_type CLOSE_PARENTHESES ;

pair_elem: FST expr     
| SND expr                     
;

pair_elem_type : base_type
| array_type
| PAIR
;

// Arrays
array_type: ( base_type | pair_type ) (OPEN_SQUARE CLOSE_SQUARE)+ ;

// WACC Types
type: base_type
| array_type
| pair_type
;

base_type: INT
| BOOL
| CHAR
| STRING
;

// Expressions.
expr : expr (MUL | DIV | MOD) expr
| expr (PLUS | MINUS) expr
| expr (GREATER | GREATER_EQUAL | LESS | LESS_EQUAL) expr
| expr (DOUBLE_EQUALS | NOT_EQUAL) expr
// binary boolean expressions
| expr AND expr
| expr OR expr
// unary expressions
| (NOT | MINUS | LEN | ORD | CHR) expr
// atomic expressions
| OPEN_PARENTHESES expr CLOSE_PARENTHESES
// expression literals
| (int_liter | bool_liter | char_liter | str_liter | pair_liter | ident | array_elem )
;

array_elem : ident (OPEN_SQUARE expr CLOSE_SQUARE)+ ;

int_liter : (PLUS | MINUS) INTEGER
| INTEGER
;

bool_liter : TRUE
| FALSE
;

char_liter : CHAR_LITER ;

str_liter : STRING_LITER ;

array_liter : OPEN_SQUARE (expr (COMMA (expr))*)? CLOSE_SQUARE ;

// There is no such thing as a pair literal
pair_liter : NULL ;

// This is here for legacy purposes
ident: IDENTITY;


// Program
prog: BEGIN func* stat END EOF;
