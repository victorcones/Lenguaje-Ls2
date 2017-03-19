[33mcommit e53af07c9317626bac1c31517b83f69d22c92b07[m
Author: Hector <hectorlarez1@gmail.com>
Date:   Sun Mar 19 00:19:33 2017 -0430

    Incorporacion de codigo semantico hasta condiciones (funcional).

[1mdiff --git a/Lexico.jlex b/Lexico.jlex[m
[1mindex 7126535..ff549a2 100644[m
[1m--- a/Lexico.jlex[m
[1m+++ b/Lexico.jlex[m
[36m@@ -103,9 +103,8 @@[m [mBLOQUEDECOMENTARIO = (({LETRAS})|({DIGITOS})|({ESPACIOOTERMINADOR}))*[m
 TKTITULO = ({TITULO})({DOSPUNTOS})({NOMBREDELTITULO})({PUNTO})[m
 TKCOMENTARIO = ({COMENTARIOS})({BLOQUEDECOMENTARIO})({PUNTO})[m
 [m
[31m-REAL = ({DIGITOS})+({PUNTO})({DIGITOS})+[m
[31m-ENTERO = ({DIGITOS})+[m
[31m-TKNUM = {REAL}|{ENTERO}[m
[32m+[m[32mREAL = ({RESTA})?({DIGITOS})+({PUNTO})({DIGITOS})+[m
[32m+[m[32mENTERO = ({RESTA})?({DIGITOS})+[m
 [m
 ALFANUMERICO = ({LETRAS}|{DIGITOS})*[m
 TKIDSIMBOLOS = ([A-Z])({DIGITOS})*[m
[36m@@ -166,9 +165,13 @@[m [mTKIDPARAMETROS = ([a-z])({ALFANUMERICO})[m
 {[m
    return Token(sym.PROBABILIDAD);[m
 }[m
[31m-<YYINITIAL> {TKNUM}                       [m
[32m+[m[32m<YYINITIAL> {REAL}[m[41m                       [m
 {[m
[31m-   return Token(sym.TKNUM);[m
[32m+[m[32m   return Token(sym.REAL);[m
[32m+[m[32m}[m
[32m+[m[32m<YYINITIAL> {ENTERO}[m
[32m+[m[32m{[m
[32m+[m[32m   return Token(sym.ENTERO);[m
 }[m
 <YYINITIAL> {TIPOREAL}                       [m
 {[m
[1mdiff --git a/MensajesError.java b/MensajesError.java[m
[1mindex 6dd54ec..33c61fe 100644[m
[1m--- a/MensajesError.java[m
[1m+++ b/MensajesError.java[m
[36m@@ -1,7 +1,13 @@[m
 class MensajesError {[m
 [m
[32m+[m	[32mstatic final String parametroNoDeclarado = "El parametro no ha sido declarado previamente";[m
[32m+[m	[32mstatic final String parametroRedeclarado = "El parametro ya ha sido declarado anteriormente";[m
 	static final String simboloRedeclarado = "El simbolo ya ha sido declarado anteriormente";[m
 	static final String simboloNoDeclarado = "El simbolo no ha sido declarado previamente";[m
[32m+[m	[32mstatic final String numeroPasosInvalido = "El numero de pasos debe ser mayor que cero";[m
[32m+[m	[32mstatic final String numeroAnguloInvalido = "El angulo debe estar entre cero y noventa";[m
[32m+[m	[32mstatic final String numeroPosicionAnguloInvalido = "El angulo de la posicion debe estar entre cero y ciento ochenta";[m
[32m+[m
 	static final String breakSinWhile = "Se ha utilizado un break fuera de un bloque while";[m
 	static final String ficheroCiNoExiste = "El fichero del CI no existe";[m
 	static final String existeTipo = "El tipo definido ya existe";[m
[1mdiff --git a/PuntoCodigo.java b/PuntoCodigo.java[m
[1mindex 5539c3d..f2c03d9 100644[m
[1m--- a/PuntoCodigo.java[m
[1m+++ b/PuntoCodigo.java[m
[36m@@ -3,9 +3,14 @@[m
 class PuntoCodigo {[m
 	public static int linea;[m
 	public static String token;[m
[32m+[m	[32mpublic static String titulo;[m
 [m
 	public static void informacionCodigo(int l, String t) {[m
 		linea = l;[m
 		token = t;[m
 	}[m
[32m+[m
[32m+[m	[32mpublic static void agregarTitulo(String t) {[m
[32m+[m		[32mtitulo = t;[m
[32m+[m	[32m}[m
 }[m
\ No newline at end of file[m
[1mdiff --git a/Sintactico.cup b/Sintactico.cup[m
[1mindex a455b5b..9f6644e 100644[m
[1m--- a/Sintactico.cup[m
[1m+++ b/Sintactico.cup[m
[36m@@ -7,6 +7,7 @@[m [maction code {:[m
 [m
 	void inicializar(){[m
 		tabla = new Tabla();[m
[32m+[m		[32mPuntoCodigo.agregarTitulo("Ls2. ");[m
 	}[m
 [m
 	boolean existeSimbolo(String id){[m
[36m@@ -30,7 +31,7 @@[m [mparser code {:[m
 [m
 	// Muestra el texto de un error[m
 	public void report_error(String message) {[m
[31m-		System.err.println(message);[m
[32m+[m		[32mSystem.err.println(PuntoCodigo.titulo + " " + message);[m
 	}[m
 [m
 	// Muestra un error de sintaxis[m
[36m@@ -45,7 +46,7 @@[m [mparser code {:[m
 [m
 	// Muestra el texto de un error irrecuperable[m
 	public void report_fatal_error(String message, Object info) {[m
[31m-		System.err.println(message);[m
[32m+[m		[32mSystem.err.println(PuntoCodigo.titulo + " " + message);[m
 	}[m
 [m
 	// Muestra un mensaje cuando no se puede seguir analizando[m
[36m@@ -74,7 +75,7 @@[m [mterminal 	OPLOGICOS, OPRELACIONALES;[m
 /* Identificadores */[m
 terminal 	TKIDSIMBOLOS, TKIDPARAMETROS;[m
 /* Otros */[m
[31m-terminal 	LINEABLANCO, TKNUM, [m
[32m+[m[32mterminal 	LINEABLANCO, REAL, ENTERO,[m
 		 	COMA, PUNTO, DOSPUNTOS, ABRIRPARENTESIS, CERRARPARENTESIS, OPGENERATRIZ,[m
 		 	OPFUNCIONDEPARAMETRO;[m
 [m
[36m@@ -129,19 +130,39 @@[m [mstart with programa;[m
 [m
 /* PRODUCCIONES */[m
 [m
[31m-programa ::= titulo comentario gramatica;[m
[32m+[m[32mprograma ::= {:[m
[32m+[m[32m                  inicializar();[m
[32m+[m[32m             :}[m
[32m+[m[32m             titulo comentario gramatica;[m
 [m
[31m-titulo ::=  TKTITULO LINEABLANCO;[m
[32m+[m[32mtitulo ::=  TKTITULO:titulo LINEABLANCO[m[41m [m
[32m+[m			[32m{:[m
[32m+[m				[32mPuntoCodigo.agregarTitulo(titulo.toString().replaceAll("Titulo:(\\s)*",""));[m
[32m+[m			[32m:};[m
 [m
 comentario ::= TKCOMENTARIO LINEABLANCO;[m
 [m
 gramatica ::= GRAMATICA parametros_variables ;[m
 [m
 parametros_variables ::= parametro_variable parametros_variables | parametros_funciones; [m
[31m-parametro_variable ::= PARAMETRO TKIDPARAMETROS DOSPUNTOS TIPOREAL VALOR TKNUM;[m
[32m+[m[32mparametro_variable ::= PARAMETRO TKIDPARAMETROS:idP DOSPUNTOS TIPOREAL VALOR REAL[m
[32m+[m						[32m{:[m
[32m+[m							[32mif (existeSimbolo(idP.toString()))[m[41m [m
[32m+[m							[32m    parser.error(MensajesError.parametroRedeclarado);[m
[32m+[m							[32melse[m
[32m+[m							[32m    agregarSimbolo(idP.toString());[m
[32m+[m							[32m// imprimeTS();[m
[32m+[m[41m                [m		[32m:};[m
 [m
 parametros_funciones ::= parametro_funcion parametros_funciones | simbolos;[m
[31m-parametro_funcion ::= PARAMETRO TKIDPARAMETROS DOSPUNTOS TIPOREAL OPFUNCIONDEPARAMETRO expresiones;[m
[32m+[m[32mparametro_funcion ::= PARAMETRO TKIDPARAMETROS:idP DOSPUNTOS TIPOREAL OPFUNCIONDEPARAMETRO expresiones[m
[32m+[m						[32m{:[m
[32m+[m							[32mif (existeSimbolo(idP.toString()))[m[41m [m
[32m+[m							[32m    parser.error(MensajesError.parametroRedeclarado);[m
[32m+[m							[32melse[m
[32m+[m							[32m    agregarSimbolo(idP.toString());[m
[32m+[m							[32m// imprimeTS();[m
[32m+[m[41m                [m		[32m:};[m
 [m
 expresiones ::= expresion expresiones | expresion;[m
 expresion ::= 	expresion SUMA 	  expresion |[m
[36m@@ -151,18 +172,57 @@[m [mexpresion ::= 	expresion SUMA 	  expresion |[m
 				expresion LOG 	  expresion |[m
 				expresion ELEVADO expresion |[m
 				ABRIRPARENTESIS expresion CERRARPARENTESIS |[m
[31m-				TKNUM |[m
[31m-				TKIDPARAMETROS;[m
[32m+[m				[32mREAL |[m
[32m+[m				[32mENTERO |[m
[32m+[m				[32mTKIDPARAMETROS:idP[m
[32m+[m				[32m{:[m
[32m+[m[32m                   if (!existeSimbolo(idP.toString()))[m
[32m+[m[32m                       parser.error(MensajesError.parametroNoDeclarado);[m
[32m+[m[32m                :};[m
 [m
 simbolos ::= simbolo simbolos | LINEABLANCO condiciones;[m
[31m-simbolo ::=  SIMBOLO TKIDSIMBOLOS | [m
[31m-			 SIMBOLO TKIDSIMBOLOS ABRIRPARENTESIS parametros_simbolos CERRARPARENTESIS;[m
[31m-parametros_simbolos ::= TKIDPARAMETROS COMA parametros_simbolos | TKIDPARAMETROS;[m
[32m+[m[32msimbolo ::=  SIMBOLO TKIDSIMBOLOS:idS[m[41m [m
[32m+[m			[32m{:[m
[32m+[m				[32mif (existeSimbolo(idS.toString()))[m[41m [m
[32m+[m				[32m    parser.error(MensajesError.simboloRedeclarado);[m
[32m+[m				[32melse[m
[32m+[m				[32m    agregarSimbolo(idS.toString());[m
[32m+[m				[32m// imprimeTS();[m
[32m+[m[41m    [m		[32m:}|[m[41m [m
[32m+[m			[32m SIMBOLO TKIDSIMBOLOS:idS ABRIRPARENTESIS parametros_simbolos CERRARPARENTESIS[m
[32m+[m			[32m{:[m
[32m+[m				[32mif (existeSimbolo(idS.toString()))[m[41m [m
[32m+[m				[32m    parser.error(MensajesError.simboloRedeclarado);[m
[32m+[m				[32melse[m
[32m+[m				[32m    agregarSimbolo(idS.toString());[m
[32m+[m				[32m// imprimeTS();[m
[32m+[m[41m    [m		[32m:};[m
[32m+[m[32mparametros_simbolos ::= TKIDPARAMETROS:idP COMA parametros_simbolos[m[41m [m
[32m+[m			[32m{:[m
[32m+[m[32m               if (!existeSimbolo(idP.toString()))[m
[32m+[m[32m                   parser.error(MensajesError.parametroNoDeclarado);[m
[32m+[m[32m            :} | TKIDPARAMETROS:idP[m
[32m+[m			[32m{:[m
[32m+[m[32m               if (!existeSimbolo(idP.toString()))[m
[32m+[m[32m                   parser.error(MensajesError.parametroNoDeclarado);[m
[32m+[m[32m            :};[m
 [m
 condiciones ::= CONDICIONES pasos angulo posicion LINEABLANCO matriz;[m
[31m-pasos ::= PASOS TKNUM;[m
[31m-angulo ::= ANGULO TKNUM;[m
[31m-posicion ::= POSICION ABRIRPARENTESIS TKNUM COMA TKNUM CERRARPARENTESIS TKNUM;[m
[32m+[m[32mpasos ::= PASOS ENTERO:entero[m
[32m+[m			[32m{:[m
[32m+[m[41m               [m	[32mif ( Integer.parseInt((String) entero) <= 0)[m
[32m+[m[32m                   parser.error(MensajesError.numeroPasosInvalido);[m
[32m+[m[32m            :};[m
[32m+[m[32mangulo ::= ANGULO ENTERO:entero[m
[32m+[m			[32m{:[m
[32m+[m[41m               [m	[32mif (!(Integer.parseInt((String) entero) >= 0 && Integer.parseInt((String) entero) <= 90))[m
[32m+[m[32m                   parser.error(MensajesError.numeroAnguloInvalido);[m
[32m+[m[32m            :};[m
[32m+[m[32mposicion ::= POSICION ABRIRPARENTESIS REAL COMA REAL CERRARPARENTESIS ENTERO:angulo[m
[32m+[m			[32m{:[m
[32m+[m	[41m           [m	[32mif (!(Integer.parseInt((String) angulo) >= 0 && Integer.parseInt((String) angulo) <= 180))[m
[32m+[m	[32m               parser.error(MensajesError.numeroPosicionAnguloInvalido);[m
[32m+[m[32m            :};[m
 [m
 matriz ::= MATRIZ movimientos LINEABLANCO generatriz;[m
 movimientos ::= NUMERAL TKIDSIMBOLOS lista_lista_expresiones siguiente | [m
[36m@@ -183,8 +243,11 @@[m [mproduccion ::= 	TKIDSIMBOLOS ABRIRPARENTESIS parametros_simbolos CERRARPARENTESI[m
 				TKIDSIMBOLOS OPGENERATRIZ | [m
 				TKIDSIMBOLOS ABRIRPARENTESIS parametros_simbolos CERRARPARENTESIS OPGENERATRIZ | [m
 				TKIDSIMBOLOS DOSPUNTOS expresion_logica OPGENERATRIZ;[m
[31m-expresion_logica ::= TKIDPARAMETROS OPLOGICOS TKNUM | TKIDPARAMETROS OPRELACIONALES TKNUM;[m
[31m-probabilidad ::= PROBABILIDAD TKNUM;[m
[32m+[m[32mexpresion_logica ::= TKIDPARAMETROS OPLOGICOS ENTERO |[m[41m [m
[32m+[m					[32m TKIDPARAMETROS OPLOGICOS REAL |[m[41m [m
[32m+[m					[32m TKIDPARAMETROS OPRELACIONALES ENTERO |[m
[32m+[m					[32m TKIDPARAMETROS OPRELACIONALES REAL;[m
[32m+[m[32mprobabilidad ::= PROBABILIDAD REAL;[m
 [m
 /*[m
 movimientos ::= movimiento movimientos | [m
[1mdiff --git a/Tabla.java b/Tabla.java[m
[1mindex deebf68..59adeca 100644[m
[1m--- a/Tabla.java[m
[1m+++ b/Tabla.java[m
[36m@@ -8,7 +8,7 @@[m [mclass Tabla {[m
 	Tabla() {[m
 		tablaSimbolos = new Vector();[m
 		tablaTipos = new Vector();[m
[31m-		Tipo t = new Tipo(0,"int");[m
[32m+[m		[32mTipo t = new Tipo(0,"Real");[m
 		t.setDimension(1);[m
 		addTipo(t);[m
 	}[m
[1mdiff --git a/inputs/ejemplo1.txt b/inputs/ejemplo1.txt[m
[1mindex e0273f0..e627d31 100644[m
[1m--- a/inputs/ejemplo1.txt[m
[1m+++ b/inputs/ejemplo1.txt[m
[36m@@ -5,14 +5,22 @@[m [mVer el ejemplo 3 en el documento.[m
 [m
 Gramatica [m
 parametro c:Real valor 1.0[m
[31m-parametro c:Real valor 1.0[m
[32m+[m[32mparametro d:Real valor 1.0[m
[32m+[m[32mparametro x:Real valor 1.0[m
[32m+[m[32mparametro p:Real valor 1.0[m
[32m+[m[32mparametro q:Real valor 1.0[m
[32m+[m[32mparametro t:Real valor 1.0[m
[32m+[m[32mparametro x1:Real valor 1.0[m
 parametro h:Real := (p*q)^0.2[m
[32m+[m[32mparametro a:Real := (p*q)^0.2[m
[32m+[m[32mparametro b:Real := (p*q)^0.2[m
 simbolo F(x,t)[m
 simbolo X[m
[31m-simbolo F(x1)[m
[32m+[m[32msimbolo D[m
[32m+[m[32msimbolo C(x1)[m
 [m
 Condiciones[m
[31m-pasos 10[m
[32m+[m[32mpasos 8[m
 angulo 86[m
 posicion (0.0,0.0) 90[m
 [m
