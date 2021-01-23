package service;

import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import java.net.ServerSocket;






public class MainServer {
    public static void main(String[] args){
        MiServidor marcoprincipal = new MiServidor();
        marcoprincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class MiServidor extends JFrame implements Runnable{

    private JTextArea areatexto;

    public MiServidor() {
        setBounds(1200,300,200,350);
        JPanel marco = new JPanel();
        marco.setLayout(new BorderLayout());
        areatexto = new JTextArea();
        marco.add(areatexto, BorderLayout.CENTER);
        add(marco);
        setVisible(true);
        Thread hilo = new Thread(this);
        hilo.start();
    }

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(9000);
            areatexto.append("\n" + "Esperando...");
            Socket miserver = server.accept();
            areatexto.append("\n" + "Conectado");
            DataExportCPU cpuUsageService = new DataExportCPU();
            areatexto.append("\n" + "EL porcentaje de uso de la CPU SERVIDOR es:");
            while (true){
                Thread.sleep(1000);
                double j = cpuUsageService.loadAndGetCpuData();
                areatexto.append("\n"+ j);
                DataOutputStream info = new DataOutputStream(miserver.getOutputStream());
                info.writeDouble(j);

            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
