package controleEstufa.operacional;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.io.File;

import controleEstufa.exception.FalhaNoAtuadorExc;
import controleEstufa.exception.LeituraIncosistenteExc;

public class Gerenciamento {
    private Sensor sensor = new Sensor();
    private Atuador ventilador;
    private Atuador aquecedor;
    private String statusTentativa = "Nenhuma tentativa registrada.";

    public Gerenciamento(Atuador ventilador, Atuador aquecedor){
        this.ventilador = ventilador;
        this.aquecedor = aquecedor;
    }

    public void verificarEstufa(double temp) throws LeituraIncosistenteExc, FalhaNoAtuadorExc{
        statusTentativa = "Tentativa iniciada.";

        double tempLida;

        try{
            tempLida = sensor.lerTemp(temp);
        }catch(LeituraIncosistenteExc e){
            statusTentativa = "Falha na leitura do sensor.";
            throw e;
        }

        System.out.println("Temperatura lida: " + tempLida + "°C");

        if(tempLida > 30){
            statusTentativa = "Tentou ligar o ventilador.";
            aquecedor.desligar();
            ventilador.ligar();
            statusTentativa = "Ventilador acionado.";
        }else if(tempLida < 15){
            statusTentativa = "Tentou ligar o aquecedor.";
            ventilador.desligar();
            aquecedor.ligar();
            statusTentativa = "Aquecedor acionado.";
        }else{
            ventilador.desligar();
            aquecedor.desligar();
            statusTentativa = "Temperatura normal, atuadores desligados.";
            System.out.println("Temperatura normal.");
        }
    }

    public void atualizarLog(double temp){
        try{
            File arquivoLog = new File("log_estufa.txt");
            FileWriter arquivo = new FileWriter(arquivoLog, true);
            arquivo.write(LocalDateTime.now() + " / temperatura: " + temp + "°C / " + statusTentativa + "\n");
            arquivo.close();
            System.out.println("Log atualizado em: " + arquivoLog.getAbsolutePath());
        }catch(IOException e){
            System.out.println("Nao foi possivel atualizar o log.");
        }
    }
}
