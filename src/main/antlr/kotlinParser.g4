parser grammar kotlinParser;

options{ tokenVocab=kotlinLexer; }

kotlinFile: lines=line+;

line: statement (NEWLINE | EOF);

/*
 var name = "Simone"
 val age : Int = 16
 var height : Double = 1.75
 const val text = "Hello World"
 */

 /* TASK 1 : VARIABILI
 --------------------------------------------*/

 statement
    : variableDeclaration #VariableDeclarationStatement
    | assign #AssignStatement
    ;

 variableDeclaration
    : VAR ID (COLONS type)? ASSIGN expression       #VarDeclaration
    | VAL ID (COLONS type)? ASSIGN expression       #ValDeclaration
    | CONST ID (COLONS type)? ASSIGN expression     #ConstDeclaration
    ;

 assign : ID ASSIGN expression;

 type
    : STRING       #stringType
    | INT          #intType
    | DOUBLE       #doubleType
    | BOOLEAN      #booleanType
    | ID           #referenceType
    ;

expression
    : value=BOOL_LIT                                                                    #BoolLiteralExpression
    | value=INT_LIT                                                                     #IntLiteralExpression
    | value=DOUBLE_LIT                                                                  #DoubleLiteralExpression
    | value=STRING_LIT                                                                  #StringLiteralExpression
    | left=expression NEWLINE* openand=PLUS NEWLINE* right=expression                   #BinaryMathExpression
    | left=expression NEWLINE* openand=MINUS NEWLINE* right=expression                  #BinaryMathExpression
    | left=expression NEWLINE* openand=TIMES NEWLINE* right=expression                  #BinaryMathExpression
    | left=expression NEWLINE* openand=DIVISION NEWLINE* right=expression               #BinaryMathExpression
    | left=expression NEWLINE* openand=AND NEWLINE* right=expression                    #BinaryLogicExpression
    | left=expression NEWLINE* openand=OR NEWLINE* right=expression                     #BinaryLogicExpression
    | left=expression NEWLINE* openand=GREATER_THAN NEWLINE* right=expression           #BinaryLogicExpression
    | left=expression NEWLINE* openand=LOWER_THAN NEWLINE* right=expression             #BinaryLogicExpression
    | left=expression NEWLINE* openand=GREATER_EQUAL_THAN NEWLINE* right=expression     #BinaryLogicExpression
    | left=expression NEWLINE* openand=LOWER_EQUAL_THAN NEWLINE* right=expression       #BinaryLogicExpression
    | left=expression NEWLINE* openand=EQUAL NEWLINE* right=expression                  #BinaryLogicExpression
    | MINUS NEWLINE* value=expression                                                   #UnaryMathNegationExpression
    | NOT NEWLINE* value=expression                                                     #UnatyLogicNegationExpression
    | PAREN_OPEN NEWLINE* value=expression NEWLINE* PAREN_OPEN                          #ParenthesysExpression
    | value=ID                                                                          #VarReferenceExpression
    ;



