//UNIVERSIDAD NACIONAL EXPERIMENTAL DE GUAYANA
//INGENIERIA EN INFORMATICA
//UC: LENGUAJES Y COMPILADORES - 2016-2
//Integrantes:
//Arzola Jose 24001005
//Cones Victor 22820595
//Larez Hector 24890347
//Mendoza Carlos 22587983
//Navas Angel 24482546
// SECCION DE CODIGO DE USUARIO
import java.lang.System;
class Lexico{
    public static void main (String argv[]) throws java.io.IOException {
        Yylex yy = new Yylex(System.in);
        while (yy.yylex() != null) ; 
    }
}
class Yytoken{
    Yytoken(){}
}    
// SECCION DE DIRECTIVAS


class Yylex {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 256;
	private final int YY_EOF = 257;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int COMENTLINE = 2;
	private final int COMENTCOMPLETE = 1;
	private final int yy_state_dtrans[] = {
		0,
		94,
		95
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NOT_ACCEPT,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NOT_ACCEPT,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NOT_ACCEPT,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NOT_ACCEPT,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NOT_ACCEPT,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NOT_ACCEPT,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NOT_ACCEPT,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NOT_ACCEPT,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NOT_ACCEPT,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NOT_ACCEPT,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NOT_ACCEPT,
		/* 73 */ YY_NOT_ACCEPT,
		/* 74 */ YY_NOT_ACCEPT,
		/* 75 */ YY_NOT_ACCEPT,
		/* 76 */ YY_NOT_ACCEPT,
		/* 77 */ YY_NOT_ACCEPT,
		/* 78 */ YY_NOT_ACCEPT,
		/* 79 */ YY_NOT_ACCEPT,
		/* 80 */ YY_NOT_ACCEPT,
		/* 81 */ YY_NOT_ACCEPT,
		/* 82 */ YY_NOT_ACCEPT,
		/* 83 */ YY_NOT_ACCEPT,
		/* 84 */ YY_NOT_ACCEPT,
		/* 85 */ YY_NOT_ACCEPT,
		/* 86 */ YY_NOT_ACCEPT,
		/* 87 */ YY_NOT_ACCEPT,
		/* 88 */ YY_NOT_ACCEPT,
		/* 89 */ YY_NOT_ACCEPT,
		/* 90 */ YY_NOT_ACCEPT,
		/* 91 */ YY_NOT_ACCEPT,
		/* 92 */ YY_NOT_ACCEPT,
		/* 93 */ YY_NOT_ACCEPT,
		/* 94 */ YY_NOT_ACCEPT,
		/* 95 */ YY_NOT_ACCEPT,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NOT_ACCEPT,
		/* 100 */ YY_NOT_ACCEPT,
		/* 101 */ YY_NOT_ACCEPT,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NO_ANCHOR,
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NO_ANCHOR,
		/* 125 */ YY_NO_ANCHOR,
		/* 126 */ YY_NO_ANCHOR,
		/* 127 */ YY_NO_ANCHOR,
		/* 128 */ YY_NO_ANCHOR,
		/* 129 */ YY_NO_ANCHOR,
		/* 130 */ YY_NO_ANCHOR,
		/* 131 */ YY_NO_ANCHOR,
		/* 132 */ YY_NO_ANCHOR,
		/* 133 */ YY_NO_ANCHOR,
		/* 134 */ YY_NO_ANCHOR,
		/* 135 */ YY_NO_ANCHOR,
		/* 136 */ YY_NO_ANCHOR,
		/* 137 */ YY_NO_ANCHOR,
		/* 138 */ YY_NO_ANCHOR,
		/* 139 */ YY_NO_ANCHOR,
		/* 140 */ YY_NO_ANCHOR,
		/* 141 */ YY_NO_ANCHOR,
		/* 142 */ YY_NO_ANCHOR,
		/* 143 */ YY_NO_ANCHOR,
		/* 144 */ YY_NO_ANCHOR,
		/* 145 */ YY_NO_ANCHOR,
		/* 146 */ YY_NO_ANCHOR,
		/* 147 */ YY_NO_ANCHOR,
		/* 148 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,258,
"46:9,19,21,46,19,20,46:18,8,46:2,41:2,46,41,46,43,44,32:2,42,33,11,34,10:10" +
",7,46,35,31,36,46,41,40:2,12,40:2,45,22,40:10,30,40,1,40:6,46,37,46,32,46:2" +
",16,25,23,26,14,9,28,9,2,9:2,5,13,15,6,27,9,17,18,3,4,24,9:3,29,41,19,41,46" +
":46,39,46:21,38,46:61,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,149,
"0,1,2,3,4,5,6,1,7,1,8,1:9,5,1,3:5,1,3,1,3:2,1:2,3,1:2,9,10,11,12,13,14,15,1" +
"6,17,13,18,19,20,21,1,22,23,24,1,3,25,26,27,28,29,30,31,32,33,34,35,36,37,3" +
"8,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,6" +
"3,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,8" +
"8,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109," +
"110,111,112,113,114,115,116")[0];

	private int yy_nxt[][] = unpackFromString(117,47,
"1,2,3:3,97,3,4,5,3,6,7,38,130,3:2,131,3,137,5,112,5,47,3,121,3:2,122,146,3," +
"53,8,9,43,50,42,49,10,44,51,58,11,12,13,14,96,51,-1:49,37,-1:7,58,-1:37,3:6" +
",-1:2,3:2,-1,3:7,-1:3,3:9,-1:9,3,-1:4,3,-1:32,15,-1:23,5,-1:10,5:3,-1:35,6," +
"46,-1:70,55,-1:45,18,-1:15,62,-1:49,52,-1:3,58,-1:37,3:6,-1:2,3:2,-1,3:7,-1" +
":3,3:6,56,3:2,-1:9,3,-1:4,3,-1:9,5,-1:10,5:2,20,-1:35,41,-1:67,45,-1:48,16," +
"-1:2,17,-1:49,18,-1:43,18,-1:20,58,-1:6,57,-1:30,3:6,-1:2,3:2,-1,3:5,22,3,-" +
"1:3,3:9,-1:9,3,-1:4,3,-1:32,55,-1:52,18,-1:22,64,-1,66,-1:41,58,-1:3,99,-1:" +
"33,3:6,-1:2,3:2,-1,3:6,23,-1:3,3:9,-1:9,3,-1:4,3,-1:17,68,-1:40,58,-1:37,3:" +
"6,-1:2,3:2,-1,3:7,-1:3,3:7,24,3,-1:9,3,-1:4,3,-1:16,19,-1:32,3:5,25,-1:2,3:" +
"2,-1,3:7,-1:3,3:9,-1:9,3,-1:4,3,-1:5,72,-1:43,3:5,26,-1:2,3:2,-1,3:7,-1:3,3" +
":9,-1:9,3,-1:4,3,-1:15,73,-1:33,3:6,-1:2,3:2,-1,3:3,28,3:3,-1:3,3:9,-1:9,3," +
"-1:4,3,-1:27,74,-1:21,3:5,30,-1:2,3:2,-1,3:7,-1:3,3:9,-1:9,3,-1:4,3,-1:14,7" +
"5,-1:34,3:6,-1:2,3:2,-1,3:7,-1:3,3:7,31,3,-1:9,3,-1:4,3,-1:6,21,-1:42,3:6,-" +
"1:2,3:2,-1,3:7,-1:3,3:4,34,3:4,-1:9,3,-1:4,3,-1:6,76,-1:56,77,-1:33,78,-1:6" +
"0,100,-1:36,79,-1:43,80,-1:66,81,-1:30,82,-1:55,83,-1:32,84,-1:45,82:6,-1,8" +
"2:3,27,82:7,-1:3,82:9,-1:9,82,-1:4,82,-1:18,86,-1:35,87,-1:63,88,-1:25,89,-" +
"1:59,90,-1:47,29,-1:36,91,-1:54,92,-1:50,93,-1:46,32,-1:29,93:6,-1,93:3,33," +
"93:19,-1:9,93,-1:4,93,-1,1,35:19,-1:2,35:25,1,36:19,-1:2,36:25,-1:2,60,-1:7" +
",58,-1:37,3:5,39,-1:2,3:2,-1,3:7,-1:3,3:9,-1:9,3,-1:4,3,-1:9,5,-1:10,5,40,5" +
",-1:41,70,-1:33,101,-1:45,85,-1:45,3:5,48,-1:2,3:2,-1,3:7,-1:3,3:9,-1:9,3,-" +
"1:4,3,-1:2,3:5,54,-1:2,3:2,-1,3:7,-1:3,3:9,-1:9,3,-1:4,3,-1:2,3,59,3:4,-1:2" +
",3:2,-1,3:7,-1:3,3:9,-1:9,3,-1:4,3,-1:2,3:4,61,3,-1:2,3:2,-1,3:7,-1:3,3:9,-" +
"1:9,3,-1:4,3,-1:2,3:4,63,3,-1:2,3:2,-1,3:7,-1:3,3:9,-1:9,3,-1:4,3,-1:2,3:5," +
"65,-1:2,3:2,-1,3:7,-1:3,3:9,-1:9,3,-1:4,3,-1:2,3:6,-1:2,3:2,-1,3:5,67,3,-1:" +
"3,3:9,-1:9,3,-1:4,3,-1:2,3,69,3:4,-1:2,3:2,-1,3:7,-1:3,3:9,-1:9,3,-1:4,3,-1" +
":2,3:6,-1:2,3:2,-1,3:4,71,3:2,-1:3,3:9,-1:9,3,-1:4,3,-1:2,3:4,102,3,-1:2,3:" +
"2,-1,3:7,-1:3,3:9,-1:9,3,-1:4,3,-1:9,5,-1:10,5:2,98,-1:26,3:6,-1:2,3:2,-1,3" +
":5,139,103,-1:3,3:9,-1:9,3,-1:4,3,-1:2,3:6,-1:2,3:2,-1,3:5,104,3,-1:3,3:9,-" +
"1:9,3,-1:4,3,-1:2,3:3,105,3:2,-1:2,3:2,-1,3:7,-1:3,3:9,-1:9,3,-1:4,3,-1:2,3" +
":5,106,-1:2,3:2,-1,3:7,-1:3,3:9,-1:9,3,-1:4,3,-1:2,3,107,3:4,-1:2,3:2,-1,3:" +
"7,-1:3,3:9,-1:9,3,-1:4,3,-1:2,3:2,108,3:3,-1:2,3:2,-1,3:7,-1:3,3:9,-1:9,3,-" +
"1:4,3,-1:2,3:6,-1:2,3:2,-1,3:5,109,3,-1:3,3:9,-1:9,3,-1:4,3,-1:2,3:6,-1:2,3" +
":2,-1,3:7,-1:3,3:4,110,3:4,-1:9,3,-1:4,3,-1:2,3:6,-1:2,3:2,-1,3:4,111,3:2,-" +
"1:3,3:9,-1:9,3,-1:4,3,-1:2,3:5,138,-1:2,3:2,-1,3:4,113,148,3,-1:3,3:9,-1:9," +
"3,-1:4,3,-1:2,3:2,114,3:3,-1:2,3:2,-1,3:7,-1:3,3:9,-1:9,3,-1:4,3,-1:2,3:6,-" +
"1:2,3:2,-1,3:7,-1:3,3:6,115,3:2,-1:9,3,-1:4,3,-1:2,3:6,-1:2,3:2,-1,3:7,-1:3" +
",3:3,116,3:5,-1:9,3,-1:4,3,-1:2,3:6,-1:2,3:2,-1,3:7,-1:3,3,117,3:7,-1:9,3,-" +
"1:4,3,-1:2,3:6,-1:2,3:2,-1,3:2,118,3:4,-1:3,3:9,-1:9,3,-1:4,3,-1:2,3:2,119," +
"3:3,-1:2,3:2,-1,3:7,-1:3,3:9,-1:9,3,-1:4,3,-1:2,3,120,3:4,-1:2,3:2,-1,3:7,-" +
"1:3,3:9,-1:9,3,-1:4,3,-1:2,3:6,-1:2,3:2,-1,3:4,123,3:2,-1:3,3:9,-1:9,3,-1:4" +
",3,-1:2,3:6,-1:2,3:2,-1,3:3,124,3:3,-1:3,3:9,-1:9,3,-1:4,3,-1:2,3:6,-1:2,3:" +
"2,-1,3,125,3:5,-1:3,3:9,-1:9,3,-1:4,3,-1:2,3,126,3:4,-1:2,3:2,-1,3:7,-1:3,3" +
":9,-1:9,3,-1:4,3,-1:2,3:6,-1:2,3:2,-1,3,127,3:5,-1:3,3:9,-1:9,3,-1:4,3,-1:2" +
",3:6,-1:2,3:2,-1,3:4,128,3:2,-1:3,3:9,-1:9,3,-1:4,3,-1:2,3:4,129,3,-1:2,3:2" +
",-1,3:7,-1:3,3:9,-1:9,3,-1:4,3,-1:2,3,132,3:4,-1:2,3:2,-1,3:7,-1:3,3:9,-1:9" +
",3,-1:4,3,-1:2,3:6,-1:2,3:2,-1,3:6,133,-1:3,3:9,-1:9,3,-1:4,3,-1:2,3:6,-1:2" +
",3:2,-1,3:4,134,3:2,-1:3,3:9,-1:9,3,-1:4,3,-1:2,3:6,-1:2,3:2,-1,3:5,135,3,-" +
"1:3,3:9,-1:9,3,-1:4,3,-1:2,3,136,3:4,-1:2,3:2,-1,3:7,-1:3,3:9,-1:9,3,-1:4,3" +
",-1:2,3:6,-1:2,3:2,-1,3:2,140,3:4,-1:3,3:9,-1:9,3,-1:4,3,-1:2,3:6,-1:2,3:2," +
"-1,3:7,-1:3,3:3,141,3:5,-1:9,3,-1:4,3,-1:2,3:6,-1:2,3:2,-1,3:3,142,3:3,-1:3" +
",3:9,-1:9,3,-1:4,3,-1:2,3:6,-1:2,3:2,-1,3:4,143,3:2,-1:3,3:9,-1:9,3,-1:4,3," +
"-1:2,3:6,-1:2,3:2,-1,3:2,144,3:4,-1:3,3:9,-1:9,3,-1:4,3,-1:2,3:6,-1:2,3:2,-" +
"1,3:7,-1:3,3:3,145,3:5,-1:9,3,-1:4,3,-1:2,3:5,147,-1:2,3:2,-1,3:7,-1:3,3:9," +
"-1:9,3,-1:4,3,-1");

	public Yytoken yylex ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

    {System.out.printf("Final del Archivo\n");
    System.exit(0);}
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{
    return new Symbol(sym.TkIdSimbolos);
}
					case -3:
						break;
					case 3:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -4:
						break;
					case 4:
						{
    return new Symbol(sym.DOSPUNTOS);
}
					case -5:
						break;
					case 5:
						{}
					case -6:
						break;
					case 6:
						{
   return new Symbol(sym.TkNum);
}
					case -7:
						break;
					case 7:
						{
    return new Symbol(sym.PUNTO);
}
					case -8:
						break;
					case 8:
						{
   return new Symbol(sym.OPRELACIONALES);
}
					case -9:
						break;
					case 9:
						{
   return new Symbol(sym.OPARITMETICOS);
}
					case -10:
						break;
					case 10:
						{
    System.out.println("Error lexico initial en <" + yyline + " , " + yychar + 
        "> no se reconoce " + yytext());
    yychar = 0;
}
					case -11:
						break;
					case 11:
						{
    return new Symbol(sym.OPMOVIMIENTO);
}
					case -12:
						break;
					case 12:
						{
    return new Symbol(sym.COMA);
}
					case -13:
						break;
					case 13:
						{
    return new Symbol(sym.ABRIRPARENTESIS);
}
					case -14:
						break;
					case 14:
						{
    return new Symbol(sym.CERRARPARENTESIS);
}
					case -15:
						break;
					case 15:
						{
   return new Symbol(sym.OPFUNCIONdePARAMETRO);
}
					case -16:
						break;
					case 16:
						{
    return new Symbol(sym.DOBLERAYA);
}
					case -17:
						break;
					case 17:
						{
   return new Symbol(sym.OPGENERATRIZ);
}
					case -18:
						break;
					case 18:
						{
  return new Symbol(sym.OPLOGICOS);
}
					case -19:
						break;
					case 19:
						{
    return new Symbol(sym.FIN);
}
					case -20:
						break;
					case 20:
						{
   return new Symbol(sym.LineaBlanco);
}
					case -21:
						break;
					case 21:
						{
   return new Symbol(sym.TIPOREAL);
}
					case -22:
						break;
					case 22:
						{
   return new Symbol(sym.VALOR);
}
					case -23:
						break;
					case 23:
						{
   return new Symbol(sym.PASOS);
}
					case -24:
						break;
					case 24:
						{
   return new Symbol(sym.MATRIZ);
}
					case -25:
						break;
					case 25:
						{
   return new Symbol(sym.ANGULO);
}
					case -26:
						break;
					case 26:
						{
   return new Symbol(sym.SIMBOLO);
}
					case -27:
						break;
					case 27:
						{
    return new Symbol(sym.TKTITULO);
}
					case -28:
						break;
					case 28:
						{
   return new Symbol(sym.POSICION);
}
					case -29:
						break;
					case 29:
						{
   return new Symbol(sym.GRAMATICA);
}
					case -30:
						break;
					case 30:
						{
    return new Symbol(sym.PARAMETRO);
}
					case -31:
						break;
					case 31:
						{
   return new Symbol(sym.GENERATRIZ);
}
					case -32:
						break;
					case 32:
						{
   return new Symbol(sym.CONDICIONES);
}
					case -33:
						break;
					case 33:
						{
   return new Symbol(sym.TKCOMENTARIO);
}
					case -34:
						break;
					case 34:
						{
   return new Symbol(sym.PROBABILIDAD);
}
					case -35:
						break;
					case 35:
						{
    System.out.println("Error lexico en estado comentario completo no se cerro las llaves <" + yyline + " , " + yychar + 
        "> no se reconoce " + yytext());
    yychar = 0;
}
					case -36:
						break;
					case 36:
						{
    System.out.println("Error lexico en estado linea <" + yyline + " , " + yychar + 
        "> no se reconoce " + yytext());
    yychar = 0;
}
					case -37:
						break;
					case 38:
						{
    return new Symbol(sym.TkIdSimbolos);
}
					case -38:
						break;
					case 39:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -39:
						break;
					case 40:
						{}
					case -40:
						break;
					case 41:
						{
   return new Symbol(sym.TkNum);
}
					case -41:
						break;
					case 42:
						{
   return new Symbol(sym.OPRELACIONALES);
}
					case -42:
						break;
					case 43:
						{
   return new Symbol(sym.OPARITMETICOS);
}
					case -43:
						break;
					case 44:
						{
    System.out.println("Error lexico initial en <" + yyline + " , " + yychar + 
        "> no se reconoce " + yytext());
    yychar = 0;
}
					case -44:
						break;
					case 45:
						{
  return new Symbol(sym.OPLOGICOS);
}
					case -45:
						break;
					case 47:
						{
    return new Symbol(sym.TkIdSimbolos);
}
					case -46:
						break;
					case 48:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -47:
						break;
					case 49:
						{
   return new Symbol(sym.OPRELACIONALES);
}
					case -48:
						break;
					case 50:
						{
   return new Symbol(sym.OPARITMETICOS);
}
					case -49:
						break;
					case 51:
						{
    System.out.println("Error lexico initial en <" + yyline + " , " + yychar + 
        "> no se reconoce " + yytext());
    yychar = 0;
}
					case -50:
						break;
					case 53:
						{
    return new Symbol(sym.TkIdSimbolos);
}
					case -51:
						break;
					case 54:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -52:
						break;
					case 55:
						{
   return new Symbol(sym.OPRELACIONALES);
}
					case -53:
						break;
					case 56:
						{
   return new Symbol(sym.OPARITMETICOS);
}
					case -54:
						break;
					case 58:
						{
    return new Symbol(sym.TkIdSimbolos);
}
					case -55:
						break;
					case 59:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -56:
						break;
					case 61:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -57:
						break;
					case 63:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -58:
						break;
					case 65:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -59:
						break;
					case 67:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -60:
						break;
					case 69:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -61:
						break;
					case 71:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -62:
						break;
					case 96:
						{
    return new Symbol(sym.TkIdSimbolos);
}
					case -63:
						break;
					case 97:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -64:
						break;
					case 98:
						{}
					case -65:
						break;
					case 102:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -66:
						break;
					case 103:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -67:
						break;
					case 104:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -68:
						break;
					case 105:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -69:
						break;
					case 106:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -70:
						break;
					case 107:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -71:
						break;
					case 108:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -72:
						break;
					case 109:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -73:
						break;
					case 110:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -74:
						break;
					case 111:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -75:
						break;
					case 112:
						{}
					case -76:
						break;
					case 113:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -77:
						break;
					case 114:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -78:
						break;
					case 115:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -79:
						break;
					case 116:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -80:
						break;
					case 117:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -81:
						break;
					case 118:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -82:
						break;
					case 119:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -83:
						break;
					case 120:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -84:
						break;
					case 121:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -85:
						break;
					case 122:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -86:
						break;
					case 123:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -87:
						break;
					case 124:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -88:
						break;
					case 125:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -89:
						break;
					case 126:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -90:
						break;
					case 127:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -91:
						break;
					case 128:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -92:
						break;
					case 129:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -93:
						break;
					case 130:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -94:
						break;
					case 131:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -95:
						break;
					case 132:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -96:
						break;
					case 133:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -97:
						break;
					case 134:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -98:
						break;
					case 135:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -99:
						break;
					case 136:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -100:
						break;
					case 137:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -101:
						break;
					case 138:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -102:
						break;
					case 139:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -103:
						break;
					case 140:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -104:
						break;
					case 141:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -105:
						break;
					case 142:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -106:
						break;
					case 143:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -107:
						break;
					case 144:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -108:
						break;
					case 145:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -109:
						break;
					case 146:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -110:
						break;
					case 147:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -111:
						break;
					case 148:
						{
    return new Symbol(sym.TkIdParametros);
}
					case -112:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
