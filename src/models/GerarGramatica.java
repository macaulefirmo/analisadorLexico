package models;

public class GerarGramatica {

    public static Gramatica gerarGramatica() {
        Gramatica gr = new Gramatica();
        gr.addRule(new Regra("<S>", new SimboloSintatico.Array("<Global Declaration>", "<S 1>")));
        gr.addRule(new Regra("<S 1>", new SimboloSintatico.Array("<Global Declaration>", "<S 1>")));
        gr.addRule(new Regra("<S 1>", new SimboloSintatico.Array("")));
        gr.addRule(new Regra("<Global Declaration>", new SimboloSintatico.Array("<Start Def>")));
        gr.addRule(new Regra("<Global Declaration>", new SimboloSintatico.Array("<Var Def>")));
        gr.addRule(new Regra("<Global Declaration>", new SimboloSintatico.Array("<Const Def>")));
        gr.addRule(new Regra("<Global Declaration>", new SimboloSintatico.Array("<Struct Def>")));
        gr.addRule(new Regra("<Global Declaration>", new SimboloSintatico.Array("<Function Def>")));
        gr.addRule(new Regra("<Global Declaration>", new SimboloSintatico.Array("<Procedure Def>")));
        gr.addRule(new Regra("<Global Declaration>", new SimboloSintatico.Array("<Typedef Def>")));
        gr.addRule(new Regra("<Function Def>", new SimboloSintatico.Array("'function'", "<Type>", "<Declarator>", "'('", "<Parameter List>", "')'", "'{'", "<Stmt Or Declaration List>", "'}'")));
        gr.addRule(new Regra("<Procedure Def>", new SimboloSintatico.Array("'procedure'", "IDENTIFICADOR", "'('", "<Parameter List>", "')'", "'{'", "<Stmt Or Declaration List>", "'}'")));
        gr.addRule(new Regra("<Typedef Def>", new SimboloSintatico.Array("'typedef'", "<Typedef Def lf>")));
        gr.addRule(new Regra("<Typedef Def lf>", new SimboloSintatico.Array("<Type>", "IDENTIFICADOR", "';'")));
        gr.addRule(new Regra("<Typedef Def lf>", new SimboloSintatico.Array("<Struct Def>", "IDENTIFICADOR", "';'")));
        gr.addRule(new Regra("<Var Def>", new SimboloSintatico.Array("'var'", "'{'", "<Declaration List>", "'}'")));
        gr.addRule(new Regra("<Const Def>", new SimboloSintatico.Array("'const'", "'{'", "<Declaration List>", "'}'")));
        gr.addRule(new Regra("<Struct Def>", new SimboloSintatico.Array("'struct'", "IDENTIFICADOR", "<Struct Def lf>")));
        gr.addRule(new Regra("<Struct Def lf>", new SimboloSintatico.Array("'{'", "<Declaration List>", "'}'")));
        gr.addRule(new Regra("<Struct Def lf>", new SimboloSintatico.Array("'extends'", "IDENTIFICADOR", "'{'", "<Declaration List>", "'}'")));
        gr.addRule(new Regra("<Parameter List>", new SimboloSintatico.Array("<Parameter Declaration>", "<Parameter List 1>")));
        gr.addRule(new Regra("<Parameter List 1>", new SimboloSintatico.Array("','", "<Parameter Declaration>", "<Parameter List 1>")));
        gr.addRule(new Regra("<Parameter List 1>", new SimboloSintatico.Array("")));
        gr.addRule(new Regra("<Parameter Declaration>", new SimboloSintatico.Array("<Type>", "<Declarator>")));
        gr.addRule(new Regra("<Declaration List>", new SimboloSintatico.Array("<Declaration>", "<Declaration List 1>")));
        gr.addRule(new Regra("<Declaration List 1>", new SimboloSintatico.Array("<Declaration>", "<Declaration List 1>")));
        gr.addRule(new Regra("<Declaration List 1>", new SimboloSintatico.Array("")));
        gr.addRule(new Regra("<Declaration>", new SimboloSintatico.Array("<Type>", "<Init Declarator List>", "';'")));
        gr.addRule(new Regra("<Init Declarator List>", new SimboloSintatico.Array("<Init Declarator>", "<Init Declarator List 1>")));
        gr.addRule(new Regra("<Init Declarator List 1>", new SimboloSintatico.Array("','", "<Init Declarator>", "<Init Declarator List 1>")));
        gr.addRule(new Regra("<Init Declarator List 1>", new SimboloSintatico.Array("")));
        gr.addRule(new Regra("<Init Declarator>", new SimboloSintatico.Array("<Declarator>", "<Init Declarator lf>")));
        gr.addRule(new Regra("<Init Declarator lf>", new SimboloSintatico.Array("'='", "<Initializer>")));
        gr.addRule(new Regra("<Init Declarator lf>", new SimboloSintatico.Array("")));
        gr.addRule(new Regra("<Initializer>", new SimboloSintatico.Array("<Assign Expr>")));
        gr.addRule(new Regra("<Initializer>", new SimboloSintatico.Array("'{'", "<Initializer List>", "<Initializer lf>")));
        gr.addRule(new Regra("<Initializer lf>", new SimboloSintatico.Array("'}'")));
        gr.addRule(new Regra("<Initializer lf>", new SimboloSintatico.Array("','", "'}'")));
        gr.addRule(new Regra("<Initializer List>", new SimboloSintatico.Array("<Initializer>", "<Initializer List 1>")));
        gr.addRule(new Regra("<Initializer List 1>", new SimboloSintatico.Array("','", "<Initializer>", "<Initializer List 1>")));
        gr.addRule(new Regra("<Initializer List 1>", new SimboloSintatico.Array("")));
        gr.addRule(new Regra("<Declarator>", new SimboloSintatico.Array("IDENTIFICADOR", "<Declarator 1>")));
        gr.addRule(new Regra("<Declarator 1>", new SimboloSintatico.Array("'['", "<Declarator 1 lf>")));
        gr.addRule(new Regra("<Declarator 1>", new SimboloSintatico.Array("")));
        gr.addRule(new Regra("<Declarator 1 lf>", new SimboloSintatico.Array("<Cond Expr>", "']'", "<Declarator 1>")));
        gr.addRule(new Regra("<Declarator 1 lf>", new SimboloSintatico.Array("']'", "<Declarator 1>")));
        gr.addRule(new Regra("<Stmt>", new SimboloSintatico.Array("<Iteration Stmt>")));
        gr.addRule(new Regra("<Stmt>", new SimboloSintatico.Array("<Expr Stmt>")));
        gr.addRule(new Regra("<Stmt>", new SimboloSintatico.Array("<Compound Stmt>")));
        gr.addRule(new Regra("<Stmt>", new SimboloSintatico.Array("<Print Stmt>")));
        gr.addRule(new Regra("<Stmt>", new SimboloSintatico.Array("<Scan Stmt>")));
        gr.addRule(new Regra("<Stmt>", new SimboloSintatico.Array("<If Stmt>")));
        gr.addRule(new Regra("<Stmt>", new SimboloSintatico.Array("<Return Stmt>")));
        gr.addRule(new Regra("<Stmt Or Declaration List>", new SimboloSintatico.Array("<Stmt>", "<Stmt Or Declaration List 1>")));
        gr.addRule(new Regra("<Stmt Or Declaration List>", new SimboloSintatico.Array("<Var Def>", "<Stmt Or Declaration List 1>")));
        gr.addRule(new Regra("<Stmt Or Declaration List 1>", new SimboloSintatico.Array("<Stmt>", "<Stmt Or Declaration List 1>")));
        gr.addRule(new Regra("<Stmt Or Declaration List 1>", new SimboloSintatico.Array("<Var Def>", "<Stmt Or Declaration List 1>")));
        gr.addRule(new Regra("<Stmt Or Declaration List 1>", new SimboloSintatico.Array("")));
        gr.addRule(new Regra("<Start Def>", new SimboloSintatico.Array("'start'", "'('", "')'", "'{'", "<Stmt Or Declaration List>", "'}'")));
        gr.addRule(new Regra("<Print Stmt>", new SimboloSintatico.Array("'print'", "'('", "<Argument List>", "')'", "';'")));
        gr.addRule(new Regra("<Scan Stmt>", new SimboloSintatico.Array("'scan'", "'('", "<Argument List>", "')'", "';'")));
        gr.addRule(new Regra("<Iteration Stmt>", new SimboloSintatico.Array("'while'", "'('", "<Expr>", "')'", "<Stmt>")));
        gr.addRule(new Regra("<If Stmt>", new SimboloSintatico.Array("'if'", "<Expr>", "'then'", "<Stmt>", "<If Stmt lf>")));
        gr.addRule(new Regra("<If Stmt lf>", new SimboloSintatico.Array("'else'", "<Stmt>")));
        gr.addRule(new Regra("<If Stmt lf>", new SimboloSintatico.Array("")));
        gr.addRule(new Regra("<Return Stmt>", new SimboloSintatico.Array("'return'", "<Expr>", "';'")));
        gr.addRule(new Regra("<Compound Stmt>", new SimboloSintatico.Array("'{'", "<Compound Stmt lf>")));
        gr.addRule(new Regra("<Compound Stmt lf>", new SimboloSintatico.Array("'}'")));
        gr.addRule(new Regra("<Compound Stmt lf>", new SimboloSintatico.Array("<Stmt Or Declaration List>", "'}'")));
        gr.addRule(new Regra("<Expr Stmt>", new SimboloSintatico.Array("<Expr>", "';'")));
        gr.addRule(new Regra("<Expr Stmt>", new SimboloSintatico.Array("';'")));
        gr.addRule(new Regra("<Expr>", new SimboloSintatico.Array("<Assign Expr>", "<Expr 1>")));
        gr.addRule(new Regra("<Expr 1>", new SimboloSintatico.Array("','", "<Assign Expr>", "<Expr 1>")));
        gr.addRule(new Regra("<Expr 1>", new SimboloSintatico.Array("")));
        gr.addRule(new Regra("<Assign Expr>", new SimboloSintatico.Array("<Cond Expr>")));
        gr.addRule(new Regra("<Assign Expr>", new SimboloSintatico.Array("<Postfix Expr>", "'='", "<Assign Expr>")));
        gr.addRule(new Regra("<Cond Expr>", new SimboloSintatico.Array("<Logical Or Expr>")));
        gr.addRule(new Regra("<Logical Or Expr>", new SimboloSintatico.Array("<Logical And Expr>", "<Logical Or Expr 1>")));
        gr.addRule(new Regra("<Logical Or Expr 1>", new SimboloSintatico.Array("'||'", "<Logical And Expr>", "<Logical Or Expr 1>")));
        gr.addRule(new Regra("<Logical Or Expr 1>", new SimboloSintatico.Array("")));
        gr.addRule(new Regra("<Logical And Expr>", new SimboloSintatico.Array("<Equal Expr>", "<Logical And Expr 1>")));
        gr.addRule(new Regra("<Logical And Expr 1>", new SimboloSintatico.Array("'&&'", "<Equal Expr>", "<Logical And Expr 1>")));
        gr.addRule(new Regra("<Logical And Expr 1>", new SimboloSintatico.Array("")));
        gr.addRule(new Regra("<Equal Expr>", new SimboloSintatico.Array("<Relational Expr>", "<Equal Expr 1>")));
        gr.addRule(new Regra("<Equal Expr 1>", new SimboloSintatico.Array("<Equal Op>", "<Relational Expr>", "<Equal Expr 1>")));
        gr.addRule(new Regra("<Equal Expr 1>", new SimboloSintatico.Array("")));
        gr.addRule(new Regra("<Relational Expr>", new SimboloSintatico.Array("<Additive Expr>", "<Relational Expr 1>")));
        gr.addRule(new Regra("<Relational Expr 1>", new SimboloSintatico.Array("<Relational Op>", "<Additive Expr>", "<Relational Expr 1>")));
        gr.addRule(new Regra("<Relational Expr 1>", new SimboloSintatico.Array("")));
        gr.addRule(new Regra("<Additive Expr>", new SimboloSintatico.Array("<Mult Expr>", "<Additive Expr 1>")));
        gr.addRule(new Regra("<Additive Expr 1>", new SimboloSintatico.Array("<Additive Op>", "<Mult Expr>", "<Additive Expr 1>")));
        gr.addRule(new Regra("<Additive Expr 1>", new SimboloSintatico.Array("")));
        gr.addRule(new Regra("<Mult Expr>", new SimboloSintatico.Array("<Unary Expr>", "<Mult Expr 1>")));
        gr.addRule(new Regra("<Mult Expr 1>", new SimboloSintatico.Array("<Mult Op>", "<Unary Expr>", "<Mult Expr 1>")));
        gr.addRule(new Regra("<Mult Expr 1>", new SimboloSintatico.Array("")));
        gr.addRule(new Regra("<Unary Expr>", new SimboloSintatico.Array("<Unary Op>", "<Unary Expr>")));
        gr.addRule(new Regra("<Unary Expr>", new SimboloSintatico.Array("<Postfix Expr>")));
        gr.addRule(new Regra("<Postfix Expr>", new SimboloSintatico.Array("<Primary Expr>", "<Postfix Expr 1>")));
        gr.addRule(new Regra("<Postfix Expr 1>", new SimboloSintatico.Array("<Postfix Op>", "<Postfix Expr 1>")));
        gr.addRule(new Regra("<Postfix Expr 1>", new SimboloSintatico.Array("")));
        gr.addRule(new Regra("<Primary Expr>", new SimboloSintatico.Array("IDENTIFICADOR")));
        gr.addRule(new Regra("<Primary Expr>", new SimboloSintatico.Array("NUMERO")));
        gr.addRule(new Regra("<Primary Expr>", new SimboloSintatico.Array("CADEIACARECTERES")));
        gr.addRule(new Regra("<Primary Expr>", new SimboloSintatico.Array("'true'")));
        gr.addRule(new Regra("<Primary Expr>", new SimboloSintatico.Array("'false'")));
        gr.addRule(new Regra("<Primary Expr>", new SimboloSintatico.Array("'('", "<Expr>", "')'")));
        gr.addRule(new Regra("<Equal Op>", new SimboloSintatico.Array("'=='")));
        gr.addRule(new Regra("<Equal Op>", new SimboloSintatico.Array("'!='")));
        gr.addRule(new Regra("<Relational Op>", new SimboloSintatico.Array("'<'")));
        gr.addRule(new Regra("<Relational Op>", new SimboloSintatico.Array("'>'")));
        gr.addRule(new Regra("<Relational Op>", new SimboloSintatico.Array("'>='")));
        gr.addRule(new Regra("<Relational Op>", new SimboloSintatico.Array("'<='")));
        gr.addRule(new Regra("<Relational Op>", new SimboloSintatico.Array("'<='")));
        gr.addRule(new Regra("<Additive Op>", new SimboloSintatico.Array("'+'")));
        gr.addRule(new Regra("<Additive Op>", new SimboloSintatico.Array("'-'")));
        gr.addRule(new Regra("<Mult Op>", new SimboloSintatico.Array("'*'")));
        gr.addRule(new Regra("<Mult Op>", new SimboloSintatico.Array("'/'")));
        gr.addRule(new Regra("<Unary Op>", new SimboloSintatico.Array("'++'")));
        gr.addRule(new Regra("<Unary Op>", new SimboloSintatico.Array("'--'")));
        gr.addRule(new Regra("<Unary Op>", new SimboloSintatico.Array("'!'")));
        gr.addRule(new Regra("<Postfix Op>", new SimboloSintatico.Array("'++'")));
        gr.addRule(new Regra("<Postfix Op>", new SimboloSintatico.Array("'--'")));
        gr.addRule(new Regra("<Postfix Op>", new SimboloSintatico.Array("'['", "<Expr>", "']'")));
        gr.addRule(new Regra("<Postfix Op>", new SimboloSintatico.Array("'('", "<Postfix Op lf>")));
        gr.addRule(new Regra("<Postfix Op>", new SimboloSintatico.Array("'.'", "IDENTIFICADOR")));
        gr.addRule(new Regra("<Postfix Op lf>", new SimboloSintatico.Array("')'")));
        gr.addRule(new Regra("<Postfix Op lf>", new SimboloSintatico.Array("<Argument List>", "')'")));
        gr.addRule(new Regra("<Argument List>", new SimboloSintatico.Array("<Assign Expr>", "<Argument List 1>")));
        gr.addRule(new Regra("<Argument List 1>", new SimboloSintatico.Array("','", "<Assign Expr>", "<Argument List 1>")));
        gr.addRule(new Regra("<Argument List 1>", new SimboloSintatico.Array("")));
        gr.addRule(new Regra("<Type>", new SimboloSintatico.Array("'int'")));
        gr.addRule(new Regra("<Type>", new SimboloSintatico.Array("'string'")));
        gr.addRule(new Regra("<Type>", new SimboloSintatico.Array("'float'")));
        gr.addRule(new Regra("<Type>", new SimboloSintatico.Array("'bool'")));
        gr.addRule(new Regra("<Type>", new SimboloSintatico.Array("IDENTIFICADOR")));

        gr.setInicialSimbolo(new SimboloSintatico("<S>"));
        return gr;
    }
}
