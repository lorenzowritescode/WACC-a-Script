lexer grammar JSTypeLexer;

FUNC: 'function';
COL: ':';
OP_BR: '(';
CL_BR: ')';
OP_CURLY: '{';
CL_CURLY: '}';
OP_ANGLE: '<';
CL_ANGLE :'>';
COMMA: ',';

//BASE TYPES
NUMBER: 'number' ;
BOOL: 'boolean' ;
CHAR: 'char' ;
STRING: 'string' ;

PAIR: 'Pair';
ARRAY: 'Array';
OBJECT: 'Object';

COMMENT: ('//' ANY_CHAR* '\n' | '/*' ANY_CHAR* '*/' )-> skip;

//identities
fragment ID_BEGIN_CHAR: '_' | 'a'..'z' | 'A'..'Z' ;
fragment ID_CHAR: ID_BEGIN_CHAR | '0'..'9' ;
IDENTITY: ID_BEGIN_CHAR ID_CHAR* ;

//whitespace
WS : [ \t\r\n]+ -> skip ;

fragment ANY_CHAR: ~('}' | '}' ) ;
fragment ANY_STRING: (WS | ANY_CHAR)+;
BLOCK: OP_CURLY ANY_STRING BLOCK? ANY_STRING CL_CURLY -> skip;