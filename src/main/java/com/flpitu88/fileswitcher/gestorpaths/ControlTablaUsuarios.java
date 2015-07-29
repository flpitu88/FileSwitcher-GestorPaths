package com.flpitu88.fileswitcher.gestorpaths;

public class ControlTablaUsuarios {

	/** El modelo de la tabla */
    private TablaUsuarios modelo = null;
	
	/**
     * Constructor. Se le pasa el modelo, al que a�ade varios elementos y
     * se lo guarda.
     */
    public ControlTablaUsuarios(TablaUsuarios modelo){       
    	this.modelo = modelo;
    }
    
    /**
     * A�ade una fila en el modelo, al final del mismo
     */
    public void addFila(){
        // Crea un nuevo Path
        PathUsuario dato = new PathUsuario("Inserte nombre de usuario","Inserte path donde guardar sus archivos");
    	modelo.addPathUsuario(dato);
    }
    
    /** Elimina la primera fila del modelo */
    public void borraFila (int fila){
        if (modelo.getRowCount() > 0)
           modelo.borrarPathUsuario(fila);
    }
    
    public TablaUsuarios getModelo(){
    	return this.modelo;
    }
}
