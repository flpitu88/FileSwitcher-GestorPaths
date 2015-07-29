package com.flpitu88.fileswitcher.gestorpaths;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PathUsuario implements Serializable {

	private String usuario;
	private String pathGuardar;
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPathGuardar() {
		return pathGuardar;
	}

	public void setPathGuardar(String pathGuardar) {
		this.pathGuardar = pathGuardar;
	}
	
	
	public PathUsuario(String user, String path){
		this.setUsuario(user);
		this.setPathGuardar(path);
	}



	
	
}
