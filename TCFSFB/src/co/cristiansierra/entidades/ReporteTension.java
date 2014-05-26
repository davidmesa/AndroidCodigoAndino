package co.cristiansierra.entidades;

import java.sql.Date;

public class ReporteTension {
	//-------------------------------------------------------------------------
	//  Atributos
	//-------------------------------------------------------------------------
	/**
	 * Diastole de la Presion Arterial
	 */
	private int diastole;

	/**
	 * Siastole de la Presion Arterial
	 */
	private int siastole;

	/**
	 * Pulsaciones de la Presion Arterial
	 */
	private int pulso;

	/**
	 * Fecha de realizacion del reporte
	 */
	private Date fechaReporte;

	//-------------------------------------------------------------------------
	//  Constructor
	//-------------------------------------------------------------------------
	public ReporteTension(int paramDiastole, int paramSiastole, int paramPulsaciones, Date fecha) {
		diastole=paramDiastole;
		siastole=paramSiastole;
		pulso=paramPulsaciones;
		fechaReporte=fecha;
	}

	//-------------------------------------------------------------------------
	//  Getters and Setters
	//-------------------------------------------------------------------------
	/**
	 * Retorna el valor de la Diastole
	 * @return diastole de la presion arterial
	 */
	public int getDiastole() {
		return diastole;
	}

	/**
	 * Retorna el valor de la Siastole
	 * @return siastole de la presion arterial
	 */
	public int getSiastole() {
		return siastole;
	}

	/**
	 * Retorna las pulsaciones
	 * @return pulsaciones de la presin arterial
	 */
	public int getPulso() {
		return pulso;
	}

	/**
	 * Retorna la fecha del reporte
	 * @return Date, que indica la hora "exacta" de realizacion del examen
	 */
	public Date getFechaReporte() {
		return fechaReporte;
	}

	public String getFechaString()
	{
		return fechaReporte.toString();
	}
}
