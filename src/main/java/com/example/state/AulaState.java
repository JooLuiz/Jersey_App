package com.example.state;

import com.example.models.Aula;

public class AulaState implements IState<Aula> {

    public AulaState() {
    }

    public Aula iniciarAula(Aula aula) {
        String situacaoAula = aula.getSituacao();
        if (situacaoAula.equals("P")) {
            Aula aula3 = nextState(aula);
            aula.setSituacao(aula3.getSituacao());
        }
        return aula;
    }

    public Aula concluirAula(Aula aula) {
        String situacaoAula = aula.getSituacao();
        if (situacaoAula.equals("A")) {
            Aula aula3 = nextState(aula);
            aula.setSituacao(aula3.getSituacao());
        }
        return aula;

    }

    @Override
    public Aula nextState(Aula aula) {
        String situacao;
        String situacaoAula = aula.getSituacao();

        switch (situacaoAula) {
        case "P":
            situacao = "A";
            break;
        case "A":
            situacao = "C";
            break;
        default:
            situacao = aula.getSituacao();
        }
        Aula aula2 = new Aula();
        aula2.setSituacao(situacao);
        return aula2;
    };
}
