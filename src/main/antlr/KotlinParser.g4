parser grammar KotlinParser;

options{ tokenVocab=KotlinLexer; }

kotlinFile: lines=line+;

line: statement (NL+ | EOF);

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
    | functionDefinition                            #FunctionDefinitionStatement
    | classDefinition                               #ClassDefinitionStatement
    | expression                                    #ExpressionDefinitionStatement
    | objectProperty ASSIGN expression              #ObjectPropertyAssignmentStatement
    | RETURN expression                             #ReturnStatement
    | ifDefinition                                  #IfDefinitionStatement
    | whileDefinition                               #WhileDefinitionStatement
    | forDefinition                                 #ForDefinitionStatement
    | listOfDefinition                              #ListOfDefinitionStatement
    ;

 type
    : STRING       #StringType
    | INT          #IntType
    | DOUBLE       #DoubleType
    | BOOLEAN      #BooleanType
    | UNIT         #UnitType
    | ID           #CustomType
    ;

expression
    : value=BOOL_LIT                                                                    #BoolLiteralExpression
    | value=INT_LIT                                                                     #IntLiteralExpression
    | value=DOUBLE_LIT                                                                  #DoubleLiteralExpression
    | value=STRING_LIT                                                                  #StringLiteralExpression
    | left=expression     operand=PLUS                  right=expression                #BinaryMathExpression
    | left=expression     operand=MINUS                 right=expression                #BinaryMathExpression
    | left=expression     operand=TIMES                 right=expression                #BinaryMathExpression
    | left=expression     operand=DIVISION              right=expression                #BinaryMathExpression
    | left=expression     operand=MODULE*               right=expression                #BinaryMathExpression
    | left=expression     operand=AND                   right=expression                #BinaryLogicExpression
    | left=expression     operand=OR                    right=expression                #BinaryLogicExpression
    | left=expression     operand=GREATER_THAN          right=expression                #BinaryComparisonExpression
    | left=expression     operand=LOWER_THAN            right=expression                #BinaryComparisonExpression
    | left=expression     operand=GREATER_EQUAL_THAN    right=expression                #BinaryComparisonExpression
    | left=expression     operand=LOWER_EQUAL_THAN      right=expression                #BinaryComparisonExpression
    | left=expression     operand=EQUAL                 right=expression                #BinaryComparisonExpression
    | left=expression     operand=NOT_EQUAL             right=expression                #BinaryComparisonExpression
    |                     operand=MINUS                 value=expression                #UnaryMathExpression
    |                     operand=PLUS                  value=expression                #UnaryMathExpression
    | PLUS PLUS ID                                                                      #PreIncrementExpression
    | ID PLUS PLUS                                                                      #PostIncrementExpression
    | MINUS MINUS ID                                                                    #PreDecrementExpression
    | ID MINUS MINUS                                                                    #PostDecrementExpression
    | NOT  value=expression                                                             #UnaryLogicNegationExpression
    | PAREN_OPEN  value=expression  PAREN_CLOSE                                         #ParenthesisExpression
    | value=ID                                                                          #VarReferenceExpression
    | listOfDefinition                                                                  #ListOfExpression
    | functionOrClassInstanceCall                                                       #FunctionOrClassInstanceCallExpression
    | objectProperty                                                                    #ObjectPropertyReferenceExpression
    | objectMethodCall                                                                  #ObjectMethodCallExpression
    ;

//task3
ifBlock
    : IF PAREN_OPEN expression PAREN_CLOSE block
    ;

elseIfBlock
    : ELSE IF PAREN_OPEN expression PAREN_CLOSE block
    ;

elseBlock
    : ELSE block
    ;

ifDefinition
    : ifBlock elseIfBlock* elseBlock?
    ;

//task4
whileDefinition :
    WHILE PAREN_OPEN whileCondition=expression PAREN_CLOSE whileBlock=block
    ;

//task5
forDefinition :
    FOR PAREN_OPEN ID IN iterator=expression PAREN_CLOSE forBlock=block
    ;

listOfDefinition :
    LIST_OF PAREN_OPEN (expression COMMA)* expression COMMA? PAREN_CLOSE
    ;

parameter
    : (VAL | VAR )? ID COLONS type
    ;

block: GRAPH_OPEN NL* (statement NL+)* NL* GRAPH_CLOSE;

functionDefinition
    : FUN name=ID PAREN_OPEN
        (parameter COMMA)* parameter?
    PAREN_CLOSE
    (COLONS returnType=type)?
    block;

classDefinition:
    PRIVATE? CLASS name=ID (PAREN_OPEN (parameter COMMA)* parameter? PAREN_CLOSE)? (COLONS parentClassType=type)?
    GRAPH_OPEN (NL+ (classStatement NL+)* )? GRAPH_CLOSE;

classStatement
   : initBlock                  #InitStatement
   | constructorBlock           #SecondaryConstructorStatement
   | functionDefinition         #MethodDefinitionStatement
   ;

/*
removed BooleanLit.kt and fixed VarDeclarationStatement transpiling logic

*/

initBlock: INIT GRAPH_OPEN NL* (statement NL+)*  GRAPH_CLOSE ;

constructorBlock:
    CONSTRUCTOR PAREN_OPEN NL*
        (parameter COMMA)* parameter?
    PAREN_CLOSE COLONS thisConstructor block?;

thisConstructor: THIS PAREN_OPEN (expression COMMA)* expression? PAREN_CLOSE;

functionOrClassInstanceCall: name=ID PAREN_OPEN (expression COMMA)* expression? PAREN_CLOSE;
objectProperty: objectName=(ID | THIS) DOT propertyName=ID;
objectMethodCall: objectName=(ID | THIS) DOT functionOrClassInstanceCall;
