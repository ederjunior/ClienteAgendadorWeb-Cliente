package util;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;

import java.util.List;

/**
 *
 * @author Gustavo
 */
public class RXTXUtil {

    private OutputStream serialOut;
    private SerialPort serialPort;

     public CommPortIdentifier ConectarPortaAuto() {       
        // TODO - RXTX Listar Portas
        Enumeration<CommPortIdentifier> identificadoresDasPortas
                = CommPortIdentifier.getPortIdentifiers();
        
        List<CommPortIdentifier> listaDePortas
                = new ArrayList<CommPortIdentifier>();
        
        CommPortIdentifier porta = null;
        while (identificadoresDasPortas.hasMoreElements()) {
            porta = identificadoresDasPortas.nextElement();
            
        }
        return porta;
    }
    

    public void conectar(CommPortIdentifier porta) throws PortInUseException, IOException, UnsupportedCommOperationException{
        // TODO - RXTX Conectar
        serialPort = (SerialPort) porta.open("comunicação serial", 9600);
        serialOut = serialPort.getOutputStream();
        
        serialPort.setSerialPortParams(9600,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE);
    }

    public void desconectar() throws IOException{
        // TODO - RXTX Desconectar
        serialPort.close();
        serialOut.close();
    }

    public void enviarDados(String mensagem) throws IOException {
        // TODO - RXTX EnviarDados
        serialOut.write(mensagem.getBytes());
    }
}
