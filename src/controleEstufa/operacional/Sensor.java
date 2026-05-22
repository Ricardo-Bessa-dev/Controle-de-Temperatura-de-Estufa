package controleEstufa.operacional;

import controleEstufa.exception.LeituraIncosistenteExc;

public class Sensor {
    public double lerTemp(double resultadoSensor) throws LeituraIncosistenteExc{ //Metodo de leitura do sensor
        if(resultadoSensor > 60 || resultadoSensor < -10){
            throw new LeituraIncosistenteExc("ERRO: resultado incosistente do sensor (" + resultadoSensor + "°C).");
        }
        return resultadoSensor;
    }
}