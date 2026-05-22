package controleEstufa;

import java.util.Scanner;

import controleEstufa.exception.FalhaNoAtuadorExc;
import controleEstufa.exception.LeituraIncosistenteExc;
import controleEstufa.operacional.Atuador;
import controleEstufa.operacional.Gerenciamento;

public class Main {
    public static void main(String args[]) {

        Scanner scan = new Scanner(System.in);

        int op=1;

        while(op!=0) {
            System.out.println("\n--- Controle da Estufa - Caso Possiveis---");
            System.out.println("1. Temperatura normal");
            System.out.println("2. Temperatura alta, ventilador liga");
            System.out.println("3. Temperatura baixa, aquecedor liga");
            System.out.println("4. Leitura inconsistente alta");
            System.out.println("5. Leitura inconsistente baixa");
            System.out.println("6. Falha no ventilador");
            System.out.println("7. Falha no aquecedor");
            System.out.println("0. Sair");
            System.out.print("-> Escolha: ");
            op = scan.nextInt();

            switch (op) {
                case 1:
                    verificarCenario(25, true, true);
                    break;
                case 2:
                    verificarCenario(35, true, true);
                    break;
                case 3:
                    verificarCenario(10, true, true);
                    break;
                case 4:
                    verificarCenario(999, true, true);
                    break;
                case 5:
                    verificarCenario(-500, true, true);
                    break;
                case 6:
                    verificarCenario(35, false, true);
                    break;
                case 7:
                    verificarCenario(10, true, false);
                    break;
                case 0:
                    System.out.println("Programa Finalizado!");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        }
    }

    public static void verificarCenario(double temp, boolean funcVentilador, boolean funcAquecedor){
        Atuador ventilador = new Atuador("Ventilador", funcVentilador);
        Atuador aquecedor = new Atuador("Aquecedor", funcAquecedor);
        Gerenciamento gerenciamento = new Gerenciamento(ventilador, aquecedor);

        System.out.println("\nTemperatura testada: " + temp + "C");
        try{
            gerenciamento.verificarEstufa(temp);
        }catch(LeituraIncosistenteExc | FalhaNoAtuadorExc e){
            System.out.println(e.getMessage());
        }finally{
            gerenciamento.atualizarLog(temp);
        }
    }
}
