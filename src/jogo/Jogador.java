package jogo;

public class Jogador {
	
	private String apelido;
	private int pontuacao;
	
	
	public Jogador(String apelido) {
		super();
		this.apelido = apelido;
		this.pontuacao = 0;
	}
	
	public String getApelido() {
		return apelido;
	}
	
	/*
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	*/
	public int getPontuacao() {
		return pontuacao;
	}
	
	//metodo de pontos corrigindo a pontuacao
	public void calcularPontuacao(int tempo) {
	    int pontos = 0;
	    
	    //Define a pontuação inicial como 1000 pontos
	    pontos = 1000;
	    
	    //Se o tempo for menor que 60 segundos, subtrai 50 pontos por segundo
	    if (tempo < 60) {
	        pontos -= 50 * tempo;
	    } 
	    //Se o tempo for maior ou igual a 60 segundos, mas menor que 10 minutos (600 segundos),
	    //subtrai 6 pontos por segundo
	    else if (tempo < 600) {
	        pontos -= 6 * tempo;
	    } 
	    //Se o tempo for maior ou igual a 10 minutos, subtrai 1 ponto por cada 10 minutos
	    else {
	        pontos -= tempo / 600;
	    }
	    this.pontuacao = pontos;
	}

	/*
	public void calcularPontuacao(int tempo) {
	    int pontos = 0;
	    
	    if (tempo < 60) {
	        pontos = 1000 + (50 * tempo / 3);
	    } else if (tempo < 600) {
	        pontos = 100 + (6 * tempo);
	    } else {
	        pontos = 10 + (tempo / 600);
	    }
	    this.pontuacao = pontos;
	}
	*/

	
}
