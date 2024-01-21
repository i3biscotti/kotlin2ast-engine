lexer grammar KotlinLexer;

/*
var name = "Simone";
val age : Int = 16;
var height : Double = 1.75;
const val text = "Hello World";
*/

/* TASK 1 : VARIABILI
--------------------------------------------*/

// Whitespace
NL                  : '\r\n' | '\r' | '\n' ;
WS                  : [\t ]+ -> skip ;

VAL                 : 'val';
CONST               : 'const';
VAR                 : 'var';

COLONS              : ':';
ASSIGN              : '=';
COMMA               : ',';

//Atomic types
INT                 : 'Int';
DOUBLE              : 'Double';
STRING              : 'String';
BOOLEAN             : 'Boolean';
UNIT                : 'Unit';

//Literals

INT_LIT
    : DecDigitNoZero DecDigit*
    | DecDigit
    ;

BOOL_LIT : 'true' | 'false';

DOUBLE_LIT
     : DecDigits '.' DecDigits
     | DecDigits
     ;

STRING_LIT
  : UnterminatedStringLiteral '"'
  ;

//TASK 2
PLUS                        : '+';
MINUS                       : '-';
DIVISION                    : '/';
TIMES                       : '*';
MODULE                      : '%';

GREATER_EQUAL_THAN          : '>=';
LOWER_EQUAL_THAN            : '<=';
GREATER_THAN                : '>';
LOWER_THAN                  : '<';
EQUAL                       : '==';
NOT_EQUAL                   : '!=';

AND                         : '&&';
OR                          : '||';
NOT                         : '!';

PAREN_OPEN                  : '(';
PAREN_CLOSE                 : ')';

FUN                         : 'fun';
GRAPH_OPEN                  : '{';
GRAPH_CLOSE                 : '}';
RETURN                      : 'return';

CLASS                       : 'class';
CONSTRUCTOR                 : 'constructor';
INIT                        : 'init';
THIS                        : 'this';
DOT                         : '.';

// Identifiers
ID                 : [A-Za-z_][A-Za-z0-9_]* ;


UnterminatedStringLiteral
  : '"' (~["\\\r\n] | '\\' (. | EOF))*
  ;

fragment DecDigit: '0'..'9';
fragment DecDigitNoZero: '1'..'9';
fragment DecDigitOrSeparator: DecDigit | '_';

fragment DecDigits
    : DecDigit DecDigitOrSeparator* DecDigit
    | DecDigit
    ;