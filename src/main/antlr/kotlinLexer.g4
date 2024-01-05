lexer grammar kotlinLexer;

/*
var name = "Simone";
val age : Int = 16;
var height : Double = 1.75;
const val text = "Hello World";
*/

/* TASK 1 : VARIABILI
--------------------------------------------*/

// Whitespace
NEWLINE            : '\r\n' | '\r' | '\n' ;
WS                 : [\t ]+ -> skip ;

VAL                 : 'val';
CONST               : 'const';
VAR                 : 'var';

COLONS              : ':';
ASSIGN              : '=';

//Atomic types
INT                 : 'Int';
DOUBLE              : 'Double';
STRING              : 'String';
BOOLEAN             : 'Boolean';

// Identifiers
ID                 : [A-Za-z_][A-Za-z0-9_]* ;

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

fragment DecDigit: '0'..'9';
fragment DecDigitNoZero: '1'..'9';
fragment DecDigitOrSeparator: DecDigit | '_';

fragment DecDigits
    : DecDigit DecDigitOrSeparator* DecDigit
    | DecDigit
    ;

STRING_LIT
  : UnterminatedStringLiteral '"'
  ;

UnterminatedStringLiteral
  : '"' (~["\\\r\n] | '\\' (. | EOF))*
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

AND                         : '&&';
OR                          : '||';
NOT                         : '!';

PAREN_OPEN                  : '(';
PAREN_CLOSE                 : ')';