// Esta clase se utiliza para guardar y recuperar informacion de punto del codigo
// fuente por el que se va analizando
class PuntoCodigo {
	public static int linea;
	public static String token;

	public static void informacionCodigo(int l, String t) {
		linea = l;
		token = t;
	}
}