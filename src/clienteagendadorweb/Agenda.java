/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clienteagendadorweb;
import java.sql.Timestamp;
/**
 *
 * @author Eder
 */
public class Agenda {

    private int id;
    private Timestamp dataHora;
    private String status;
    private String ventilador;
    private String luzDaSala;
    private String tv;
    private String luzDoQuarto;
    
   
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVentilador() {
        return ventilador;
    }

    public void setVentilador(String ventilador) {
        this.ventilador = ventilador;
    }

    public String getLuzDaSala() {
        return luzDaSala;
    }

    public void setLuzDaSala(String luzDaSala) {
        this.luzDaSala = luzDaSala;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }

    public String getLuzDoQuarto() {
        return luzDoQuarto;
    }

    public void setLuzDoQuarto(String luzDoQuarto) {
        this.luzDoQuarto = luzDoQuarto;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    
}
