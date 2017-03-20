import java.util.HashMap;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Iterator;
import java.util.Map;

class ProbabilidadSimbolo {

	public static HashMap<String, ProbabilidadSimbolo> simbolos = new HashMap();
	public static Stack<Object> pila = new Stack();

	String nombre;
	ArrayList<Float> probabilidades = new ArrayList();
	boolean tieneProbabilidad;

	// Probabilidad

	public ProbabilidadSimbolo(String nombre, boolean probabilidad){
		this.nombre = nombre;
		this.tieneProbabilidad = probabilidad;
	}

	public void agregarProbabilidad(Float f){
		probabilidades.add(f);
	}

	public Float sumatoria(){
		Float sumatoria = 0f;
		for(Float f: probabilidades){
			sumatoria += f;
		}

		return sumatoria;
	}

	// Simbolo

	public static void agregarSimbolo(String nombre, boolean b){
		simbolos.put(nombre, new ProbabilidadSimbolo(nombre, b));
	}

	public static ProbabilidadSimbolo obtenerSimbolo(String nombre){
		return simbolos.get(nombre);
	}

	public static void agregarProbabilidad(String nombre, Float f){
		simbolos.get(nombre).agregarProbabilidad(f);
	}

	public static void validarProbabilidades(){
		Iterator it = simbolos.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();

	        ProbabilidadSimbolo elemento = (ProbabilidadSimbolo) pair.getValue();
	       	
	        if(elemento.sumatoria() != 1f && elemento.tieneProbabilidad){
	        	System.out.println("Error Semantico, la sumatoria de las probabilidades tiene" +
	        		"que ser igual a uno.");
	        }

	        it.remove(); // avoids a ConcurrentModificationException
	    }
	}

	// Pila
	public static void agregarPila(Object o){
		// Meto el elemento a la pila
		pila.push(o);
		//System.out.println("Metiendo en la pila elemento " + o);
		// Si la pila es igual a 2
		if(pila.size() == 2){
			//System.out.println("La pila es mayor que dos, evaluando: " + o);
			// Obtengo el elemento tope de la pila
			Object t = pila.pop();
			// Si el elemento tope de la pila es un float
			if(t instanceof Float){
				//System.out.println(t + " es un Float, por lo tanto, es una probabilidad");
				// Agrego esa probabilidad en el simbolo del elemento restante
				// en la pila.
				Float f = (Float) t;
				String s = (String) pila.pop();

				if(obtenerSimbolo(s) != null){
					//System.out.println(s + " no existe en el mapa, creando y agregando probabilidad " + f);
					agregarProbabilidad(s,f);
				}else{
					//System.out.println(s + " existe en el mapa, agregando probabilidad " + f);
					agregarSimbolo(s, true);
					agregarProbabilidad(s, f);
				}
			}else{
				//System.out.println(t + " es un string, por lo tanto, es un simbolo");
				/* Si en vez de ser un float es un String, obtengo el Simbolo del fondo
				de la pila y lo agrego a la mapa de simbolos. */
				String s = (String) pila.pop();
				if(obtenerSimbolo(s) == null){
					//System.out.println("Metiendo en el mapa " + s + " que no tiene probabilidad");
					agregarSimbolo(s, false);
				}
				//System.out.println("Reinsertando en la pila " + t);
				pila.push(t);
			}
		}
	}
}