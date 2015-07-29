package com.flpitu88.fileswitcher.gestorpaths;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PathGuardar implements Serializable {
	
	private String pathPropio;
	private String pathComun;
	
	public String getPathPropio() {
		return pathPropio;
	}
	public void setPathPropio(String pathPropio) {
		this.pathPropio = pathPropio;
	}
	public String getPathComun() {
		return pathComun;
	}
	public void setPathComun(String pathComun) {
		this.pathComun = pathComun;
	}
	
	public PathGuardar(String ini, String fin){
		this.setPathPropio(ini);
		this.setPathComun(fin);
	}

}
