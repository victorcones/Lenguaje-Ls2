import java.util.ArrayList;

class Probabilidades {
	public static ArrayList<String> simbolos_producciones = new ArrayList<String>();
	public static ArrayList<Float> valor = new ArrayList<Float>();
	//public static Stack<Object> pila = new Stack<Object>();

	/*public static void agregarPila(Object o) {
		pila.push(o);
		if(pila.size()==2){
			Float f = (Float) pila.pop();
			String s = (String) pila.pop();
			Simbolo.agregarSimbolo(s,f);
		}
	}*/

	public static void agregarSimbolo(String t) {
		 simbolos_producciones.add(new String(t));
	}
	public static void agregarValor(Float t) {
		 valor.add(new Float(t));
	}
	public static boolean verificarSimbolo(String t) {
		System.out.println("tama*o "+simbolos_producciones.size());
		for(int i=0;i<simbolos_producciones.size();i++){
                    if(simbolos_producciones.get(i).equals(t)){
			return true;
                    }
                }
		return false;
	}
	public static void verificarProbabilidad(String t) {
	
		/*if(verificarSimbolo(t)){
			System.out.println("existe");	
		}*/

		 //simbolos_producciones.size();
	}

	public static void sumarProbabilidades(){

		Float suma = 0f;
		for(int i=0;i<valor.size();i++){
			System.out.println(valor.get(i));
			suma+=valor.get(i);
                }
		System.out.println(suma);
	}

}
