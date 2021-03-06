import java.util.Vector;

class Tabla {

	private Vector tablaSimbolos;
	private Vector tablaTipos;

	Tabla() {
		tablaSimbolos = new Vector();
		tablaTipos = new Vector();
		Tipo t = new Tipo(0,"Real");
		t.setDimension(1);
		addTipo(t);
	}

	void imprimir() {
		System.out.println("********** TABLA DE TIPOS **********");
		for(int i=0;i<countTipos();i++) {
			Tipo t = getTipo(i);
			t.imprimir();
			System.out.println("----------");
		}
		System.out.println("************************************");
		System.out.println("******** TABLA DE SIMBOLOS *********");
		for(int j=0;j<countSimbolos();j++) {
			Simbolo s = getSimbolo(j);
			s.imprimir();
			System.out.println("----------");
		}
		System.out.println("************************************");
	}

	boolean existeCampoRegistro(String id) {
		//Como el tipo base de un registro es -1, 
		//eso nos va a indicar cuando hemos llegado al tipo base
		boolean retorno = false;
		int i = countTipos()-1;
		while(i>=0) {
			Tipo t = getTipo(i);
			if(t.getTipoBase()<0) {
				break;
			} else {
				if(t.getId().equals(id)) {
					retorno = true;
					break;
				}
			}
			i--;
		}
		return retorno;
	}

	int posicionCampoRegistro(int t, String id) {
		int pos = -1;
		for(int i = t+1;i<countTipos();i++) {
			if(getTipo(i).getPadre()>-1) {
				if(getTipo(i).getId().equals(id)) {
					pos = i-t-1;
					break;
				} 
			} else {
				break;
			}
		}
		return pos;
	}

	void addTipoCampo(String id) {
		addTipo(id);
		Tipo t = getTipo(id);
		t.setDimension(1);
		t.setTipoBase(0);
		//Como el tipo base de un registro es -1, 
		//eso nos va a indicar cuando hemos llegado al tipo base
		int i = countTipos()-1;
		while(i>=0) {
			Tipo j = getTipo(i);
			if(j.getTipoBase()<0) {
				j.setDimension(j.getDimension()+1);
				t.setPadre(i);
				break;
			}
			i--;
		}
	}

	void addSimbolo(String id) {
		tablaSimbolos.add(new Simbolo(countSimbolos(),id));
	}

	void addTipo(String id) {
		tablaTipos.add(new Tipo(countTipos(),id));
	}

	void addTipo(Tipo t) {
		tablaTipos.add(t);
	}

	void addTipo(String id, int e) {
		Tipo t = new Tipo(countTipos(),id);
		t.setTipoBase(0);
		t.setDimension(e+1);
		t.setMaximo(e);
		tablaTipos.addElement(t);
	}

	int countSimbolos() {
		return tablaSimbolos.size();
	}

	int countTipos() {
		return tablaTipos.size();
	}

	Simbolo getSimbolo(int pos) {
		return (Simbolo)tablaSimbolos.elementAt(pos);
	}

	Tipo getTipo(int pos) {
		return (Tipo)tablaTipos.elementAt(pos);
	}

	Simbolo getSimbolo(String id) {
		Simbolo simbolo = null;
		//La busqueda comienza desde el final hacia el principio
		//Esto se hace asi para cuando tengamos variables locales
		//declaradas con el mismo nombre que las globales. Se
		//buscara primero la local, es decir la ultima en la tabla
		for(int i=countSimbolos()-1;i>=0;i--) {
			simbolo = getSimbolo(i);
			if(simbolo.getId().equals(id)) {
				break;
			} else {
				simbolo = null;
			}
		}
		return simbolo;
	}

	Tipo getTipo(String id) {
		Tipo tipo = null;
		for(int i=0;i<countTipos();i++) {
			tipo = getTipo(i);
			if(tipo.getId().equals(id)) {
				break;
			} else {
				tipo = null;
			}
		}
		return tipo;
	}

	boolean existeSimbolo(String id) {
		if(getSimbolo(id)!=null) {
			return true;
		} else {
			return false;
		}
	}

	boolean existeSimboloAmbito(String id, int a) {
		boolean retorno = false;
		for(int i=countSimbolos()-1;i>=0;i--) {
			Simbolo s = getSimbolo(i);
			if(s.getId().equals(id) && s.getAmbito()==a) {
				retorno = true;
				break;
			}
		}
		return retorno;
	}


	boolean existeTipo(String id) {
		boolean retorno = false;
		for(int i=countTipos()-1;i>=0;i--) {
			Tipo t = getTipo(i);
			if(t.getId().equals(id) && t.getPadre()<0) {
				retorno = true;
				break;
			}
		}
		return retorno;
	}	

	void setSimbolo(Simbolo s) {
		int cod = s.getCod();
		tablaSimbolos.setElementAt(s,cod);
	}

	void setDireccionSimbolo(String id,int d) {
		Simbolo simbolo = getSimbolo(id);
		simbolo.setDireccion(d);
		setSimbolo(simbolo);
	}

	void setTipoSimbolo(String id,int t) {
		Simbolo simbolo = getSimbolo(id);
		simbolo.setTipo(t);
		setSimbolo(simbolo);
	}

	void setCategoriaSimbolo(String id,String c) {
		Simbolo simbolo = getSimbolo(id);
		simbolo.setCategoria(c);
		setSimbolo(simbolo);
	}

	void setCategoriaUltimoSimbolo(String c) {
		Simbolo simbolo = getSimbolo(countSimbolos()-1);
		simbolo.setCategoria(c);
		setSimbolo(simbolo);
	}

	void setAmbitoSimbolo(String id, int a) {
		for(int i=countSimbolos()-1;i>=0;i--) {
			Simbolo s = getSimbolo(i);
			if(s.getId().equals(id)) {
				s.setAmbito(a);
				setSimbolo(s);
				break;
			}
		}
	}	

	void eliminarAmbito(int a) {
		int i = countSimbolos()-1;
		while(i>0) {
			Simbolo s = getSimbolo(i);
			if(s.getAmbito()>=a) {
				tablaSimbolos.removeElementAt(i);
				i--;
			} else {
				break;
			}
		}
	}	

	Simbolo getUltimoSubprograma() {
		Simbolo s = null;
		for(int i=countSimbolos()-1;i>=0;i--) {
			s = getSimbolo(i);
			if(s.getCategoria().equals("funcion") || s.getCategoria().equals("procedimiento")) {
				break;
			} else {
				s = null;
			}
		}
		return s;
	}

	void setParametroUltimoSubprograma(Tipo tp) {
		Simbolo s = getUltimoSubprograma();
		s.setNumeroParametros(s.getNumeroParametros()+1);
		s.addParametro(tp.getCod());
	}

	int getTipoFuncion() {
		int retorno = -1;
		Simbolo s;
		for(int i=countSimbolos()-1;i>=0;i--) {
			s = getSimbolo(i);
			if(s.getAmbito()==0) {
				retorno = s.getTipo();
				break;
			}
		}
		return retorno;
	}

	String getIdFuncion() {
		String retorno = "";
		Simbolo s;
		for(int i=countSimbolos()-1;i>=0;i--) {
			s = getSimbolo(i);
			if(s.getAmbito()==0) {
				retorno = s.getId();
				break;
			}
		}
		return retorno;
	}

	int getDimensionParametros(String id) {
		Simbolo s = getSimbolo(id);
		int suma = 0;
		for(int i=0;i<s.getNumeroParametros()-1;i++) {
			suma = suma + getTipo(s.getParametro(i)).getDimension();
		}
		return suma;
	}
}