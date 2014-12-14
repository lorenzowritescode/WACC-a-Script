parser grammar JSTypeParser;

options {
  tokenVocab=JSTypeLexer;
}

library: function*;

typeDef: COL type;

type: NUMBER 
| BOOL 
| CHAR 
| STRING
| array
| pair;

array: ARRAY OP_ANGLE type CL_ANGLE;
pair: PAIR OP_ANGLE type COMMA type CL_ANGLE;

function: FUNC IDENTITY OP_BR arglist CL_BR typeDef BLOCK;

argument: IDENTITY typeDef;

arglist: (argument (COMMA argument)*)?;