lexer grammar WACCLexer;

//binary integer operators
PLUS: '+' ;
MINUS: '-' ;
EQUAL: '=' ;
MUL: '*' ;
DIV: '/' ;
MOD: '%' ;

//binary equality opearators
GREATER_EQUAL: '>=' ;
GREATER: '>' ;
LESS_EQUAL: '<=' ;
LESS: '<' ;
DOUBLE_EQUALS: '==' ;
NOT_EQUAL: '!=' ;

//binary boolean operators
AND: '&&' ;
OR: '||' ;

//unary boolean operators
NOT: '!' ;

//unary operators
LEN: 'len' ;
ORD: 'ord' ;
CHR: 'chr' ;

//boolean literals
TRUE: 'true' ;
FALSE: 'false' ;

//BASE TYPES
INT: 'int' ;
BOOL: 'bool' ;
CHAR: 'char' ;
STRING: 'string' ;
PAIR: 'pair' ;

//brackets
OPEN_PARENTHESES : '(' ;
CLOSE_PARENTHESES : ')' ;
OPEN_SQUARE: '[' ;
CLOSE_SQUARE: ']' ;

//numbers
fragment DIGIT : '0'..'9' ; 
INTEGER: DIGIT+ ;

//program keywords
BEGIN : 'begin' ;
END : 'end' ;

//function keywords
IS : 'is' ;

//statement keywords
SKIP : 'skip' ;
READ: 'read' ;
FREE: 'free' ;
RETURN: 'return' ;
EXIT: 'exit' ;
PRINTLN: 'println' ;
PRINT: 'print' ;
NULL: 'null' ;

//comments
COMMENT : '#' .*? '\n' -> skip ;

//punctuation
SEMI_COLON: ';' ;
COLON: ':' ;
COMMA: ',' ;
HASH_KEY: '#' ;

//if then else
IF: 'if' ;
THEN: 'then' ;
ELSE: 'else' ;
ENDIF: 'fi' ;

//while loop
WHILE: 'while' ;
DONE: 'done' ;
DO: 'do' ;

//pairs
NEWPAIR: 'newpair' ;
FST: 'fst' ;
SND: 'snd' ;

//function call
CALL: 'call' ;

//string literal
STRING_LITER : '"' .*? '"';

//whitespace
WS : [ \t\r\n]+ -> skip ;

APOSTROPHE: '\'' ;

fragment ANY_CHAR : ~('\\' | '\'' | '"') ;

//escaped characters
END_OF_STRING : '\\0' ;
NEWLINE : '\\n' ;
TAB : '\\t' ;
CARRIAGE_RETURN : '\\r';
FORM_FEED : '\\f';
DOUBLE_QUOTES : '\\"' ;
BACKSLASH : '\\' ;
WHITESPACE : ' ' ;

//identities
fragment ID_BEGIN_CHAR: '_' | 'a'..'z' | 'A'..'Z' ;
fragment ID_CHAR: ID_BEGIN_CHAR | '0'..'9' ;
IDENTITY: ID_BEGIN_CHAR ID_CHAR* ;

//char literal:
CHAR_LITERAL : APOSTROPHE ANY_CHAR APOSTROPHE ;