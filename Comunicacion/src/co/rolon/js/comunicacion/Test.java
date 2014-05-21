package co.rolon.js.comunicacion;

import co.rolon.js.estructuras.CustomHashMap;

public class Test {
	public static void main(String[] args) throws Throwable {
		System.out.println(Comunicacion.send("auth", new CustomHashMap().put("id", "juan").put("password", "juan"), 1));
	}
}
