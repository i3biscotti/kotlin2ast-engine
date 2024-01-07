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
    : VAR ID (COLONS type)? ASSIGN expression       #VarDeclarationStatement
    | VAL ID (COLONS type)? ASSIGN expression       #ValDeclarationStatement
    | CONST ID (COLONS type)? ASSIGN expression     #ConstDeclarationStatement
    | ID ASSIGN expression                          #AssignStatement
    ;

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
    | left=expression NEWLINE* operand=PLUS NEWLINE* right=expression                   #BinaryMathExpression
    | left=expression NEWLINE* operand=MINUS NEWLINE* right=expression                  #BinaryMathExpression
    | left=expression NEWLINE* operand=TIMES NEWLINE* right=expression                  #BinaryMathExpression
    | left=expression NEWLINE* operand=DIVISION NEWLINE* right=expression               #BinaryMathExpression
    | left=expression NEWLINE* operand=MODULE* right=expression                         #BinaryMathExpression
    | left=expression NEWLINE* operand=AND NEWLINE* right=expression                    #BinaryLogicExpression
    | left=expression NEWLINE* operand=OR NEWLINE* right=expression                     #BinaryLogicExpression
    | left=expression NEWLINE* operand=GREATER_THAN NEWLINE* right=expression           #BinaryLogicExpression
    | left=expression NEWLINE* operand=LOWER_THAN NEWLINE* right=expression             #BinaryLogicExpression
    | left=expression NEWLINE* operand=GREATER_EQUAL_THAN NEWLINE* right=expression     #BinaryLogicExpression
    | left=expression NEWLINE* operand=LOWER_EQUAL_THAN NEWLINE* right=expression       #BinaryLogicExpression
    | left=expression NEWLINE* operand=EQUAL NEWLINE* right=expression                  #BinaryLogicExpression
    | left=expression NEWLINE* operand=NOT_EQUAL NEWLINE* right=expression              #BinaryLogicExpression
    | operand=MINUS NEWLINE* value=expression                                           #UnaryMathExpression
    | operand=PLUS NEWLINE* value=expression                                            #UnaryMathExpression
    | NOT NEWLINE* value=expression                                                     #UnaryLogicNegationExpression
    | PAREN_OPEN NEWLINE* value=expression NEWLINE* PAREN_CLOSE                         #ParenthesisExpression
    | value=ID                                                                          #VarReferenceExpression
    ;

