package com.flpitu88.fileswitcher.gestorpaths;

public class ControlTablaPaths {
	
	  /** El modelo de la tabla */
    private TablaBaseDatos modelo = null;
	
	/**
     * Constructor. Se le pasa el modelo, al que a�ade varios elementos y
     * se lo guarda.
     */
    public ControlTablaPaths(TablaBaseDatos modelo){       
    	this.modelo = modelo;
    }
    
    /**
     * A�ade una fila en el modelo, al final del mismo
     */
    public void addFila(){
        // Crea un nuevo Path
        PathGuardar dato = new PathGuardar ("Inserte path propio de cada PC","Inserte path comun del arbol de directorios");
    	modelo.addPathGuardar (dato);
    }
    
    /** Elimina la primera fila del modelo */
    public void borraFila (int fila){
        if (modelo.getRowCount() > 0)
           modelo.borraPathGuardar (fila);
    }
    
    public TablaBaseDatos getModelo(){
    	return this.modelo;
    }
     
}
