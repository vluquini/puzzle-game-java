package jogo;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;

public class ListaJogadores {
	
	private List<Jogador> listaJogadores;

	
	public ListaJogadores() {
		this.listaJogadores=new ArrayList<Jogador>();
	}
	
	public List<Jogador> getListaJogadores() {
		return listaJogadores;
	}
	
	public void addJogador(Jogador jogador) {
		if(jogador.getApelido() != "") {			
			this.listaJogadores.add(jogador);
			System.out.println("Jogador add com sucesso!");
		}
	}
	
	public void listarJogadores(JTextArea txtRanking, int indice) {
	    StringBuilder string = new StringBuilder();	    

	    for (Jogador jogador : listaJogadores.subList(indice, indice+1)) {
	        string.append(jogador.getApelido() + " >> " + jogador.getPontuacao());
	    }    
	    txtRanking.setText(txtRanking.getText() + "\n" + string.toString());
	}
	
	public boolean existeJogador(String apelido) {
		for (Jogador jogador : listaJogadores) {
			if (jogador.getApelido().equalsIgnoreCase(apelido)) {
				return true;
			}
		}
		return false;
	}

}
