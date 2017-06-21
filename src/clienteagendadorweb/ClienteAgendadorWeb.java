/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteagendadorweb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import util.RXTXUtil;

/**
 *
 * @author Eder
 */
public class ClienteAgendadorWeb {

    static Date dt;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Agenda> list;
                Date horaAtual = null;
                Date horaDoBanco = null;
                RXTXUtil rxtxUtil;
                
                String ventilador;
                String luzSala;
                String tv;
                String luzQuarto;
                
                while (true) {
                    try {
                        list = getListaDeCliente();

                        for (Agenda list1 : list) {
                            Timestamp ts = new Timestamp(new Date().getTime());

                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                            try {
                                horaAtual = format.parse(ts.toString());
                                horaDoBanco = format.parse(list1.getDataHora().toString());
                            } catch (ParseException ex) {
                                System.out.println("Erro de formatação SimpleDateFormat!");
                            }

                            if (horaDoBanco.compareTo(horaAtual) == 0) {
                                System.out.println("Acender led!!!");

                                if("ligado".equals(list1.getVentilador())){
                                    ventilador = "1";
                                }else{
                                    ventilador = "2";
                                }
                                if("ligado".equals(list1.getLuzDaSala())){
                                    luzSala = "3";
                                }else{
                                    luzSala = "4";
                                }
                                if("ligado".equals(list1.getTv())){
                                    tv = "5";
                                }else{
                                    tv = "6";
                                }
                                if("ligado".equals(list1.getLuzDoQuarto())){
                                    luzQuarto = "7";
                                }else{
                                    luzQuarto = "8";
                                }
                                
                                rxtxUtil = new RXTXUtil();
                                try {
                                    rxtxUtil.conectar(rxtxUtil.ConectarPortaAuto());
                                    rxtxUtil.enviarDados(ventilador);
                                    rxtxUtil.enviarDados(luzSala);
                                    rxtxUtil.enviarDados(tv);
                                    rxtxUtil.enviarDados(luzQuarto);
                                    rxtxUtil.desconectar();                              
                                } catch (Exception ex) {
                                    System.out.println("Erro conexão RXTX!");
                                }
                                list1.setStatus("Desativado");
                                putAgenda(list1);
                                Thread.sleep(60000);
                                
                            }
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ClienteAgendadorWeb.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        thread.start();
    }

    public static List<Agenda> getListaDeCliente() {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/AgendadorWeb");
        Response response = target.path("/agendaWS/getListaAgenda").request().get(Response.class);
        List<Agenda> list = response.readEntity(new GenericType<List<Agenda>>() {
        });
        return list;
    }

    public static void putAgenda(Agenda agenda){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/AgendadorWeb");
        Entity<Agenda> entity = Entity.entity(agenda, MediaType.APPLICATION_JSON);
        Response response = target.path("/agendaWS/editarAgenda").request().put(entity);
        System.out.println(response.readEntity(String.class)); 
        System.out.println("Put");
        System.out.println(response.getStatus());
    }
}
