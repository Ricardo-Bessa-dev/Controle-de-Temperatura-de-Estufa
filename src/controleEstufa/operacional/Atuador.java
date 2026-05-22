package controleEstufa.operacional;

import controleEstufa.exception.FalhaNoAtuadorExc;

public class Atuador {
    private String nome;
    private boolean funcionando;
    private boolean ligado;

    public Atuador(String nome, boolean funcionando){
        this.nome = nome;
        this.funcionando = funcionando;
        this.ligado = false;
    }

    public void ligar() throws FalhaNoAtuadorExc{
        if(!funcionando){
            throw new FalhaNoAtuadorExc("ERRO: " + nome + " nao respondeu.");
        }
        this.ligado = true;
        System.out.println(nome + " ligado.");
    }

    public void desligar(){
        this.ligado = false;
    }

    public String getNome(){
        return this.nome;
    }

    public boolean getLigado(){
        return this.ligado;
    }
}
