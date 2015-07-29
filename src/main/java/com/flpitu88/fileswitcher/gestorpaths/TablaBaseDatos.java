package com.flpitu88.fileswitcher.gestorpaths;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.flpitu88.fileSwitcher.utilitarios.Archivo;

public class TablaBaseDatos implements TableModel {
	
//	static final String fichero = "C:/Users/FlavioLeonel/Documents/Desarrollos/File Switcher/App/pathCfg.fss";
	static String fichero = null;
	
	// Lista de paths que se guardan
    private LinkedList<PathGuardar> datosPaths = new LinkedList<PathGuardar>();
    
    // Lista de suscriptores
    private LinkedList<Object> listeners = new LinkedList<Object>();
	
    public TablaBaseDatos(){
    	fichero = MenuGestor.obtenerConfiguracion().getProperty("archGuardado");
    	// Cargo el estado de las listas
    	this.cargarObjeto();
    }
    
    public int getColumnCount() {
        return 2;
    }
    
    public int getRowCount() {
        return datosPaths.size();
    }
    
    /** Returns the value for the cell at <code>columnIndex</code> and
     * <code>rowIndex</code>.
     *
     * @param	rowIndex	the row whose value is to be queried
     * @param	columnIndex 	the column whose value is to be queried
     * @return	the value Object at the specified cell
     *
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        PathGuardar aux;
        
        // Se obtiene la persona de la fila indicada
        aux = (PathGuardar)(datosPaths.get(rowIndex));
        
        // Se obtiene el campo apropiado seg�n el valor de columnIndex
        switch (columnIndex){
            case 0:
                return aux.getPathPropio();
            case 1:
                return aux.getPathComun();
            default:
                return null;
        }
    }
    
    // Eliminar el path indicado
    public void borraPathGuardar (int fila){
        // Se borra la fila 
        datosPaths.remove(fila); 
        // Y se avisa a los suscriptores, creando un TableModelEvent...
        TableModelEvent evento = new TableModelEvent (this, fila, fila, 
        TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);
        // ... y pas�ndoselo a los suscriptores
        avisaSuscriptores (evento);
    }
    
    //Anadir paths al final de la tabla
    public void addPathGuardar (PathGuardar nuevoPath){
        // A�ade la persona al modelo 
        datosPaths.add (nuevoPath);
        // Avisa a los suscriptores creando un TableModelEvent...
        TableModelEvent evento;
        evento = new TableModelEvent (this, this.getRowCount()-1,this.getRowCount()-1, TableModelEvent.ALL_COLUMNS,TableModelEvent.INSERT);
        // ... y avisando a los suscriptores
        avisaSuscriptores(evento);
    }
    
    // Anadir un listener a la lista de suscriptores
    public void addTableModelListener(TableModelListener l){
        // A�ade el suscriptor a la lista de suscriptores
        listeners.add(l);
    }
    
    // Retorna la clase de las columnas
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int columnIndex){
        // Devuelve la clase que hay en cada columna.
        switch (columnIndex){
            case 0:
                // La columna cero contiene el path propio
                return String.class;
            case 1:
                // La columna uno contiene el path comun
                return String.class;
            default:
                // Devuelve una clase Object por defecto.
                return Object.class;
        }
    }
    
    //Retorna nombre de la columna
    public String getColumnName(int columnIndex){
        // Devuelve el nombre de cada columna. Este texto aparecer� en la
        // cabecera de la tabla.
        switch (columnIndex){
            case 0:
                return "Path propio";
            case 1:
                return "Path comun";
            default:
                return null;
        }
    }
    
    // Retorna true si la celda es editable
    public boolean isCellEditable(int rowIndex, int columnIndex){
        // Permite que la celda sea editable.
        return true;
    }
    
    // Elimina un listener de la lista
    public void removeTableModelListener(TableModelListener l){
        // Elimina los suscriptores.
        listeners.remove(l);
    }
    
    // Setea el valor en la celda especificada
    public void setValueAt(Object aValue, int rowIndex, int columnIndex){
        // Obtiene la persona de la fila indicada
        PathGuardar aux;
        aux = (PathGuardar)(datosPaths.get(rowIndex));
        
        // Cambia el campo de Path que indica columnIndex, poniendole el 
        // aValue que se nos pasa.
        switch (columnIndex){
            case 0:
                aux.setPathPropio((String)aValue);
                break;
            case 1:
                aux.setPathComun((String)aValue);
                break;
            default:
                break;
        }
        
        // Avisa a los suscriptores del cambio, creando un TableModelEvent ...
        TableModelEvent evento = new TableModelEvent (this, rowIndex, rowIndex, 
            columnIndex);

        // ... y pas�ndoselo a los suscriptores.
        avisaSuscriptores(evento);
    }
    
    // Paso evento a los suscriptores
    private void avisaSuscriptores(TableModelEvent evento){
        int i;
        
        // Bucle para todos los suscriptores en la lista, se llama al metodo
        // tableChanged() de los mismos, pas�ndole el evento.
        for (i=0; i<listeners.size(); i++)
            ((TableModelListener)listeners.get(i)).tableChanged(evento);
    }
    
    // Guardar el estado del objeto en un archivo para persistirlo
    public void guardarObjeto(){
    	try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero));
			oos.writeObject(datosPaths);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    // Cargar el estado del objeto guardado
    @SuppressWarnings("unchecked")
	public void cargarObjeto(){
    	try {
    		Archivo archCargar = new Archivo(fichero);
    		if (archCargar.existe()){
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero));
				datosPaths = (LinkedList<PathGuardar>) ois.readObject();
				ois.close();
    		}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
        
}
