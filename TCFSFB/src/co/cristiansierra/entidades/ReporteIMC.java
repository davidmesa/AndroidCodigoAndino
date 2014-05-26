package co.cristiansierra.entidades;

import java.sql.Date;

public class ReporteIMC {

	//-------------------------------------------------------------------------
	//  Atributos
	//-------------------------------------------------------------------------
	/**
	 * Peso de la persona en kilogramos
	 */
	private double peso;

	/**
	 * Altura del paciente
	 */
	private double altura;

	/**
	 * IMC del reporte del paciente
	 */
	private double IMC;

	/**
	 * Fecha del Reporte de IMC
	 */
	private Date fechaReporte;

	//-------------------------------------------------------------------------
	//  Constructor
	//-------------------------------------------------------------------------
	public ReporteIMC( double paramPeso, double paramAltura, Date fecha)
	{
		peso=paramPeso;
		altura=paramAltura;
		IMC=peso/((altura/100)*(altura/100));
		fechaReporte=fecha;
	}

	//-------------------------------------------------------------------------
	//  Metodos
	//-------------------------------------------------------------------------

	/**
	 * Retorna el IMC reportado
	 * @return IMC reportado
	 */
	public double getIMC() {
		return IMC;
	}

	/**
	 * Retorna el Peso reportado
	 * @return Peso reportado
	 */
	public double getPeso() {
		return peso;
	}

	/**
	 * Retorna la Altura reportada
	 * @return Altura reportada
	 */
	public double getAltura() {
		return altura;
	}

	/**
	 * Retorna la Fecha en la que se realizo el Reporte
	 * @return Fecha Reporte
	 */
	public Date getFechaReporte() {
		return fechaReporte;
	}

	public String getFechaString()
	{
		return fechaReporte.toString();
	}

}
