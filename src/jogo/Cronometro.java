package jogo;

import telas.Jogo;

public class Cronometro implements Runnable {
    private boolean rodando = false;
	private boolean resetado = false;
    private int horas = 0;
    private int minutos = 0;
    private int segundos = 0;

    public void run() {
        rodando = true;
        while (rodando) {
            Jogo.txtSec.setText(Integer.toString(segundos));
            segundos++;
            if (segundos == 60) {
                segundos = 0;
                minutos++;
            }
            if (minutos == 60) {
                minutos = 0;
                horas++;
            }

            Jogo.txtMin.setText(Integer.toString(minutos));
            Jogo.txtHora.setText(Integer.toString(horas));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                if (resetado) {
                	resetado = false;
                    rodando = false;
                } else {
                	rodando = false;
                }
            }
        }
    }

    public int getHoras() {
		return horas;
	}

	public int getMinutos() {
		return minutos;
	}

	public int getSegundos() {
		return segundos;
	}

	public void iniciar() {
    	rodando = true;
    }

    public void pausar() {
    	rodando = false;
    }

    public void resetar() {
    	resetado = true;
        rodando = false;
        horas = 0;
        minutos = 0;
        segundos = 0;
        Jogo.txtSec.setText(Integer.toString(segundos));
    	Jogo.txtMin.setText(Integer.toString(minutos));
    	Jogo.txtHora.setText(Integer.toString(horas));
    }
    
    public boolean isRodando() {
		return rodando;
	}

}
