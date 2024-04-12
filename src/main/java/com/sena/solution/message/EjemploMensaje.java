package com.sena.solution.message;

public class EjemploMensaje {
	private String icon;
	private String title;
	public EjemploMensaje() {
		// TODO Auto-generated constructor stub
	}
	public EjemploMensaje(String icon, String title) {
		super();
		this.icon = icon;
		this.title = title;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "EjemploMensaje [icon=" + icon + ", title=" + title + "]";
	}
	
	
}
