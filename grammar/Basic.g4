grammar Basic;

@header {
package org.blinkenlights.basic.gen;
}

program : (line NEWLINE | NEWLINE)* ;

line : lineNum statement ;

lineNum : INT ;
statement : endStatement | forStatement | gosubStatement | gotoStatement | letStatement | nextStatement | printStatement | returnStatement ;
endStatement : 'END' ;
forStatement : 'FOR' VARNAME '=' INT 'TO' INT ;
gosubStatement : 'GOSUB' INT ;
gotoStatement : 'GOTO' INT ;
letStatement : 'LET' VARNAME '=' expression ;
nextStatement : 'NEXT' VARNAME ;
printStatement : 'PRINT' arg (argSeparator arg)* ;
returnStatement : 'RETURN' ;

//args : arg* ;
arg : QUOTED_STRING # printQuotedString
    | expression # printExpression
    | VARNAME # printVariable
    ;
argSeparator : (','|';') ;
expression : INT # value
           | VARNAME # variable
           | expression '*' expression # multiplication
           | expression '/' expression # division
           | expression '+' expression # addition
           | expression '-' expression # subtraction
           | '(' expression ')' # parentheses
           ;

VARNAME : [a-zA-Z][a-zA-Z0-9_]* ;
QUOTED_STRING : '"' ('\\"'|.)*? '"' ;
STRING : [a-zA-Z]+ ;
INT : '-'?[0-9]+ ;
WS : [ \t]+ -> skip;
NEWLINE : '\r'? '\n' ;
