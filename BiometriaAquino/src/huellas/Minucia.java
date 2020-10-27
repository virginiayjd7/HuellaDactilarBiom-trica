package huellas;

/**
 * Clase Minucia
 */
public class Minucia {
	
	private int x;		/** posiciÃ³n en eje X que ocupa la minucia */
	private int y;		/** posiciÃ³n en eje Y que ocupa la minucia */
	private int tipo;	/** entero que representa el tipo de minucia segÃºn su Crossing Number
						  *		0 -> Punto Aislado
						  *		1 -> Corte (DE INTERÃ‰S EN BIOMETRÃ�A)
						  *		2 -> ContinuaciÃ³n
						  *		3 -> BifurcaciÃ³n (DE INTERÃ‰S EN BIOMETRÃ�A)
						  *		4 -> Punto de cruce
						  */
	
	/**
	 * Constructor paramÃ©trico
	 * @param x posiciÃ³n en eje X
	 * @param y posiciÃ³n en eje Y
	 * @param tipo el tipo de minucia segÃºn su Crossing Number
	 */
	public Minucia( int x , int y , int tipo ){
		this.x = x;
		this.y = y;
		this.tipo = tipo;
	}
	
	/**
	 * Devuelve la posiciÃ³n en X de la minucia
	 * @return entero que representa la posiciÃ³n en X
	 */
	public int getX() {
		return x;
	}

	/**
	 * Fija la posiciÃ³n en X de la minucia
	 * @param x entero que representa la posiciÃ³n en X
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Devuelve la posiciÃ³n en Y de la minucia
	 * @return entero que representa la posiciÃ³n en Y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Fija la posiciÃ³n en Y de la minucia
	 * @param x entero que representa la posiciÃ³n en Y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Devuelve el tipo de la minucia segÃºn su Crossing Number
	 * @return entero que representa el tipo de la minucia
	 */
	public int getTipo() {
		return tipo;
	}
	
	/**
	 * Fija el tipo de la minucia segÃºn su Crossing Number
	 * @param entero que representa el tipo de la minucia
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

}