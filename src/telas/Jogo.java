package telas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import jogo.Cronometro;
import jogo.ListaJogadores;
import jogo.Jogador;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Jogo {

	private JFrame frame;
		
	JButton bt1 = new JButton("1");
	JButton bt2 = new JButton("2");
	JButton bt3 = new JButton("3");
	JButton bt4 = new JButton("4");
	JButton bt5 = new JButton("5");
	JButton bt6 = new JButton("6");
	JButton bt7 = new JButton("7");
	JButton bt8 = new JButton("8");
	JButton bt9 = new JButton("9");
	JButton bt10 = new JButton("10");
	JButton bt11 = new JButton("11");
	JButton bt12 = new JButton("12");
	JButton bt13 = new JButton("13");
	JButton bt14 = new JButton("14");
	JButton bt15 = new JButton("15");
	JButton bt16 = new JButton("");
	
	List<Integer> numeros = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16));
    List<JButton> botoes = new ArrayList<>(Arrays.asList(bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt10, 													 bt11, bt12, bt13, bt14, bt15, bt16));
    List<ImageIcon> imagens = new ArrayList<>();
    
	Cronometro cronometro = new Cronometro();
    Thread threadCronometro = new Thread(cronometro);
        
    ListaJogadores listaJogadores = new ListaJogadores();
    /*
   	public void setListaJogadores(ListaJogadores listaJogadores) {
      this.listaJogadores = listaJogadores;
    }*/

    private final JButton btEmaralharImpar = new JButton("Embaralhar Impar");
	public static JTextField txtHora;
	public static JTextField txtMin = new JTextField();
	public static JTextField txtSec = new JTextField();
	private final JLabel lblNewLabel_2_1 = new JLabel(":");
	private final JButton btPlay = new JButton("Play");
	private final JButton btStop = new JButton("Stop");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {	
				try {
					//login login = new login();
					Jogo window = new Jogo(telas.Login.listaJogadores);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	boolean visible = false;
	public void setVisible(boolean b) {
		if(b == true)
			frame.setVisible(true);
	}
	
	public Jogo(ListaJogadores listaJogadores) {
		this.listaJogadores = listaJogadores;
		
		setVisible(visible);
		initialize();	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void ativarTemaCarro(){
		//limpa as imagens se ja houver
		if(imagens.size() > 0)
			imagens.clear();
		
		ImageIcon imagem1 = new ImageIcon("./imagens/carro1-1.png");
		ImageIcon imagem2 = new ImageIcon("./imagens/carro1-2.png");
		ImageIcon imagem3 = new ImageIcon("./imagens/carro1-3.png");
		ImageIcon imagem4 = new ImageIcon("./imagens/carro1-4.png");
		ImageIcon imagem5 = new ImageIcon("./imagens/carro2-1.png");
		ImageIcon imagem6 = new ImageIcon("./imagens/carro2-2.png");
		ImageIcon imagem7 = new ImageIcon("./imagens/carro2-3.png");
		ImageIcon imagem8 = new ImageIcon("./imagens/carro2-4.png");
		ImageIcon imagem9 = new ImageIcon("./imagens/carro3-1.png");
		ImageIcon imagem10 = new ImageIcon("./imagens/carro3-2.png");
		ImageIcon imagem11 = new ImageIcon("./imagens/carro3-3.png");
		ImageIcon imagem12 = new ImageIcon("./imagens/carro3-4.png");
		ImageIcon imagem13 = new ImageIcon("./imagens/carro4-1.png");
		ImageIcon imagem14 = new ImageIcon("./imagens/carro4-2.png");
		ImageIcon imagem15 = new ImageIcon("./imagens/carro4-3.png");
		
		imagens.add(imagem1);
		imagens.add(imagem2);
		imagens.add(imagem3);
		imagens.add(imagem4);
		imagens.add(imagem5);
		imagens.add(imagem6);
		imagens.add(imagem7);
		imagens.add(imagem8);
		imagens.add(imagem9);
		imagens.add(imagem10);
		imagens.add(imagem11);
		imagens.add(imagem12);
		imagens.add(imagem13);
		imagens.add(imagem14);
		imagens.add(imagem15);
		
	}
	
	void permutarBotao(JButton botao1, JButton botao2) {		
		String permutarBotao = botao2.getText();
		JButton btTemp = new JButton("");
		//INICIA O TEMPO ASSIM QUE HOUVER UMA PERMUTA
		if (!threadCronometro.isAlive() || cronometro.isRodando() == false) {			
            iniciarCronometro();
        }
				
		if(permutarBotao == "") {		
			botao2.setText(botao1.getText());
			botao1.setText("");
			
			btTemp.setIcon(botao1.getIcon());
			botao1.setIcon(botao2.getIcon());
			botao2.setIcon(btTemp.getIcon());		
			//metodo de verificar ordem aqui
			estaOrdenado();			
		}
		
	}
	
	//////////METODO VERIFICA SE OS BOTOES ESTAO COM PERMUTACAO PAR///////////
	public boolean permutacaoPar(List<Integer> numeros) {
	    // Verificar se a lista corresponde a uma permutacao par
	    int inversoes = 0;
	    for (int i = 0; i < numeros.size() - 1; i++) {
	        for (int j = i + 1; j < numeros.size(); j++) {
	            if (numeros.get(i) > numeros.get(j)) {
	                inversoes++;
	            }
	        }
	    }
	    return inversoes % 2 == 0;
	}

	//////////METODO VERIFICA SE OS BOTOES ESTAO COM PERMUTACAO IMPAR///////////
	public boolean permutacaoImpar(List<Integer> numeros) {
	    // Verificar se a lista corresponde a uma permutacao impar
	    int inversoes = 0;
	    for (int i = 0; i < numeros.size() - 1; i++) {
	        for (int j = i + 1; j < numeros.size(); j++) {
	            if (numeros.get(i) > numeros.get(j)) {
	                inversoes++;
	            }
	        }
	    }
	    return inversoes % 2 == 1;
	}
	////////METODO EMBARALHA OS BOTOES, PELO MENOS 3 BOTOES
	////////MANTENDO AS CONDIÇOES DE EMBARALHAR IMPAR OU PAR
	public void embaralharBotoes(boolean par) {
		
	    int posicoesAlteradas = 0;
	    
	    while (posicoesAlteradas < 3) {
	        //Troca aleatoriamente dois numeros da lista
	        Random random = new Random();
	        int pos1 = random.nextInt(15);
	        int pos2 = random.nextInt(15);
	        
	        while(pos2 == pos1) {
	            pos2 = random.nextInt(15);
	        }
	        
	        int temp = numeros.get(pos1);
	        numeros.set(pos1, numeros.get(pos2));
	        numeros.set(pos2, temp);
	        
	        if(imagens.size() > 0) {	        	
	        	ImageIcon tempImagem = imagens.get(pos1);
	        	imagens.set(pos1, imagens.get(pos2));
	        	imagens.set(pos2, tempImagem); 
	        }

	        //Verifica se a lista atende as condicoes de "embaralhar impar" ou "embaralhar par"
	        if((par && permutacaoPar(numeros)) || (!par && permutacaoImpar(numeros))) {
	            //Aumenta o contador de posicoes alteradas se a lista n atender as condicoes desejadas
	            posicoesAlteradas++;
	        }
	        
	        for (int i = 0; i < botoes.size(); i++) {
		        JButton button = botoes.get(i);
		        button.setText(Integer.toString(numeros.get(i)));
		        
		        if(i == 15) {
		        	button.setText("");
		        	button.setIcon(null);
		        }else if(imagens.size() > 0)
		        	button.setIcon(imagens.get(i));
		    }
	        	
	    }
	}
		
	private void embaralharBotoesTeste() {
		//este metodo reorganiza os botoes de maneira quase completa
		//para testar as demais funcoes do codigo
		bt1.setText("1");
        bt2.setText("2");
        bt3.setText("3");
        bt4.setText("4");
        bt5.setText("5");
        bt6.setText("6");
        bt7.setText("7");
        bt8.setText("8");
        bt9.setText("9");
        bt10.setText("10");
        bt11.setText("11");
        bt12.setText("12");
        bt13.setText("13");
        bt14.setText("14");
        bt15.setText("");
        bt16.setText("15");
	}
	
	private final JTextArea txtRanking = new JTextArea();
	private int jogadorAtual = 0; //para identificar o indice do jogador na lista
	private void estaOrdenado() {
		while(jogadorAtual < 3) {			
			for (int i = 0; i < 15; i++) {
				//Verifica se a posicao atual do botao corresponde ao numero esperado
				if (botoes.get(i).getText().equals(Integer.toString(i + 1))) {
					//Se sim, continua para a proxima posicao
					continue;
				} else {
					//Se n, sai do metodo sem fazer nada
					return;
				}
			}
			//SE AS POSICOES ESTIVEREM CORRETAS, PARA O TEMPO E MOSTRA UMA MENSAGEM
			pararCronometro();//para o tempo
			pontuacao();//metodo que pega o tempo atual do cronometro e calcula pontuacao do jogador atual
			reiniciarCronometro();//reseto o cronometro para o proximo jogador
			
			JOptionPane.showMessageDialog(null, "PARABÉNS, VOCÊ COMPLETOU O PUZZLE!!", null, 1);
			listaJogadores.listarJogadores(txtRanking, jogadorAtual);;
			//embaralharBotoes(true);//embaralha os botoes novamente para o proximo jogador
			embaralharBotoesTeste();
			jogadorAtual++;
		}
	}
	
	public void pontuacao() {
		System.out.println(jogadorAtual);
		Jogador jogador = listaJogadores.getListaJogadores().get(jogadorAtual);
		//passa o tempo em segundos do jogador para ser calculado a sua pontuacao
		jogador.calcularPontuacao(cronometro.getSegundos() + (cronometro.getMinutos()*60) + (cronometro.getHoras()*120));
		System.out.println(jogador.getApelido());
		System.out.println(jogador.getPontuacao());

	}
	
	@SuppressWarnings("removal")
	public void iniciarCronometro() {
		
		if (!threadCronometro.isAlive()) {			
            cronometro.iniciar();
            threadCronometro.start();
        }
		
		if(cronometro.isRodando() == false || threadCronometro.isInterrupted()) {
			cronometro.iniciar();
			threadCronometro.resume();
		}
	}
	
	@SuppressWarnings("removal")
	public void pararCronometro() {
		
		if(threadCronometro.isAlive()) {
			cronometro.pausar();
			threadCronometro.suspend();
		}
	}
	
	@SuppressWarnings("removal")
	public void reiniciarCronometro() {
		threadCronometro.suspend();
		cronometro.resetar();
	}
	
	public void organizarNumeros() {
	    //Ordena a lista de números em ordem crescente
	    Collections.sort(numeros);

	    //Atualiza os botões com os números ordenados
	    for (int i = 0; i < botoes.size(); i++) {
	        JButton button = botoes.get(i);
	        button.setText(Integer.toString(numeros.get(i)));
	        
	        if(i == 15) 
	            button.setText("");
	    }
	}

	public void organizarImagens() {
		
		Collections.sort(imagens, new Comparator<ImageIcon>() {
		    public int compare(ImageIcon imagem1, ImageIcon imagem2) {
		        //compara as imagens e organiza
		        return imagem1.getDescription().compareTo(imagem2.getDescription());
		    }
		});
		//seta as imagens nos respectivos botoes
		for (int i = 0; i < botoes.size(); i++) {
		    JButton button = botoes.get(i);
		    if (i != 15) {
		        button.setIcon(imagens.get(i));
		    }
		}
		
	}
	
	private void initialize() {
		//embaralha em ''permutacao par'' assim que inicia o programa
		//embaralharBotoes(true);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 770, 458);
		frame.setLocationRelativeTo(null);//inicia a tela no meio
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(2, 40, 330, 65);
		panel.setBorder(new LineBorder(Color.CYAN, 2));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Puzzle Game :)");
		lblNewLabel.setFont(new Font("Segoe Script", Font.PLAIN, 32));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 0, 310, 65);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(2, 116, 330, 303);
		panel_1.setBorder(new LineBorder(Color.CYAN, 2));
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		bt1.setBounds(10, 11, 69, 62);				
		bt1.setFont(new Font("Segoe Script", Font.PLAIN, 22));
		panel_1.add(bt1);
		
		bt2.setBounds(89, 11, 69, 62);				
		bt2.setFont(new Font("Segoe Script", Font.PLAIN, 22));
		panel_1.add(bt2);
		
		bt3.setBounds(168, 11, 69, 62);			
		bt3.setFont(new Font("Segoe Script", Font.PLAIN, 22));
		panel_1.add(bt3);
		
		bt4.setBounds(247, 11, 69, 62);				
		bt4.setFont(new Font("Segoe Script", Font.PLAIN, 22));
		panel_1.add(bt4);
		
		bt5.setBounds(10, 84, 69, 62);
		bt5.setFont(new Font("Segoe Script", Font.PLAIN, 22));
		panel_1.add(bt5);
		
		bt6.setBounds(89, 84, 69, 62);
		bt6.setFont(new Font("Segoe Script", Font.PLAIN, 22));
		panel_1.add(bt6);
		
		bt7.setBounds(168, 84, 69, 62);
		bt7.setFont(new Font("Segoe Script", Font.PLAIN, 22));
		panel_1.add(bt7);
		
		bt8.setBounds(247, 84, 69, 62);
		bt8.setFont(new Font("Segoe Script", Font.PLAIN, 22));
		panel_1.add(bt8);
		
		bt9.setBounds(10, 157, 69, 62);
		bt9.setFont(new Font("Segoe Script", Font.PLAIN, 22));
		panel_1.add(bt9);
		
		bt10.setBounds(89, 157, 69, 62);
		bt10.setFont(new Font("Segoe Script", Font.PLAIN, 22));
		panel_1.add(bt10);
		
		bt11.setBounds(168, 157, 69, 62);	
		bt11.setFont(new Font("Segoe Script", Font.PLAIN, 22));
		panel_1.add(bt11);
		
		bt12.setBounds(247, 157, 69, 62);
		bt12.setFont(new Font("Segoe Script", Font.PLAIN, 22));
		panel_1.add(bt12);
		
		bt13.setBounds(10, 230, 69, 62);
		bt13.setFont(new Font("Segoe Script", Font.PLAIN, 22));
		panel_1.add(bt13);
		
		bt14.setBounds(89, 230, 69, 62);	
		bt14.setFont(new Font("Segoe Script", Font.PLAIN, 22));
		panel_1.add(bt14);
		
		bt15.setBounds(168, 230, 69, 62);			
		bt15.setFont(new Font("Segoe Script", Font.PLAIN, 22));
		panel_1.add(bt15);
		
		bt16.setBounds(247, 230, 69, 62);			
		bt16.setFont(new Font("Segoe Script", Font.PLAIN, 22));
		panel_1.add(bt16);		
		////////CHAMANDO OS METODOS DE PERMUTA DOS BOTOES/////////////
		bt16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				permutarBotao(bt16, bt12);
				permutarBotao(bt16, bt15);			
			}
		});
		
		bt15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				permutarBotao(bt15, bt11);
				permutarBotao(bt15, bt14);
				permutarBotao(bt15, bt16);			
			}
		});
		
		bt14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				permutarBotao(bt14, bt10);
				permutarBotao(bt14, bt13);
				permutarBotao(bt14, bt15);			
			}
		});
		
		bt13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				permutarBotao(bt13, bt9);
				permutarBotao(bt13, bt14);			
			}
		});
		
		bt12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				permutarBotao(bt12, bt8);
				permutarBotao(bt12, bt11);
				permutarBotao(bt12, bt16);			
			}
		});
		
		bt11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				permutarBotao(bt11, bt7);
				permutarBotao(bt11, bt10);
				permutarBotao(bt11, bt12);
				permutarBotao(bt11,bt15);	
			}
		});
		
		bt10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				permutarBotao(bt10, bt6);
				permutarBotao(bt10, bt9);
				permutarBotao(bt10, bt11);
				permutarBotao(bt10, bt14);				
			}
		});
		
		bt9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				permutarBotao(bt9, bt5);
				permutarBotao(bt9, bt10);
				permutarBotao(bt9, bt13);				
			}
		});
		
		bt8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				permutarBotao(bt8, bt4);
				permutarBotao(bt8, bt7);
				permutarBotao(bt8, bt12);			
			}
		});
		
		bt7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				permutarBotao(bt7, bt3);
				permutarBotao(bt7, bt6);
				permutarBotao(bt7, bt8);
				permutarBotao(bt7, bt11);				
			}
		});
		
		bt6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				permutarBotao(bt6, bt2);
				permutarBotao(bt6, bt5);
				permutarBotao(bt6, bt7);
				permutarBotao(bt6, bt10);								
			}
		});
		
		bt5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				permutarBotao(bt5, bt1);
				permutarBotao(bt5, bt6);
				permutarBotao(bt5, bt9);								
			}
		});
		
		bt4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				permutarBotao(bt4, bt3);
				permutarBotao(bt4, bt8);							
			}
		});
		
		bt3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				permutarBotao(bt3, bt2);
				permutarBotao(bt3, bt4);
				permutarBotao(bt3, bt7);								
			}
		});
		
		bt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				permutarBotao(bt2, bt1);
				permutarBotao(bt2, bt3);
				permutarBotao(bt2, bt6);				
			}
		});
		
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				permutarBotao(bt1, bt2);
				permutarBotao(bt1, bt5);				
			}
		});
		///////////////////////////////////////////////////////////////////
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(342, 40, 410, 379);
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new LineBorder(Color.CYAN, 2));
		frame.getContentPane().add(panel_1_1);
		
		JButton btResolver = new JButton("Resolver");
		btResolver.setFont(new Font("Segoe Script", Font.BOLD, 14));
		btResolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				organizarNumeros();
				
				if(imagens.size() > 0)
					organizarImagens();

		        JOptionPane.showMessageDialog(null, "Você ganhou!!", "Puzzle Game", 1);;
		        
			}
		});
		
		btResolver.setBounds(10, 110, 181, 23);
		panel_1_1.add(btResolver);
		
		
		JLabel lblNewLabel_1 = new JLabel("Ranking");
		lblNewLabel_1.setFont(new Font("Segoe Script", Font.BOLD, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(10, 186, 160, 23);
		panel_1_1.add(lblNewLabel_1);
		
		JButton btEmbaralharPar = new JButton("Embaralhar Par");
		btEmbaralharPar.setFont(new Font("Segoe Script", Font.BOLD, 14));
		btEmbaralharPar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				embaralharBotoes(true);
			}
		});
		btEmbaralharPar.setBounds(219, 76, 181, 23);
		panel_1_1.add(btEmbaralharPar);
		btEmaralharImpar.setFont(new Font("Segoe Script", Font.BOLD, 14));
		
		btEmaralharImpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				embaralharBotoes(false);
			}
		});
		///BOTAO IMPAR DECLARADO LA EM CIMA...///
		btEmaralharImpar.setBounds(219, 110, 181, 23);	
		panel_1_1.add(btEmaralharImpar);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tempo >>");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setFont(new Font("Segoe Script", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(10, 42, 86, 23);
		panel_1_1.add(lblNewLabel_1_1);
		
		txtHora = new JTextField();
		txtHora.setHorizontalAlignment(SwingConstants.CENTER);
		txtHora.setText("00");
		txtHora.setBounds(84, 43, 29, 20);
		panel_1_1.add(txtHora);
		txtHora.setColumns(10);
		
		txtMin.setText("00");
		txtMin.setHorizontalAlignment(SwingConstants.CENTER);
		txtMin.setColumns(10);
		txtMin.setBounds(123, 43, 29, 20);
		panel_1_1.add(txtMin);
		
		txtSec.setText("00");
		txtSec.setHorizontalAlignment(SwingConstants.CENTER);
		txtSec.setColumns(10);
		txtSec.setBounds(162, 43, 40, 20);
		panel_1_1.add(txtSec);
		
		JLabel lblNewLabel_2 = new JLabel(":");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Segoe Script", Font.BOLD, 14));
		lblNewLabel_2.setBounds(104, 46, 29, 14);
		panel_1_1.add(lblNewLabel_2);
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Segoe Script", Font.BOLD, 14));
		lblNewLabel_2_1.setBounds(143, 46, 29, 14);
		panel_1_1.add(lblNewLabel_2_1);
		
		JButton btReiniciar = new JButton("Reiniciar");
		btReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reiniciarCronometro();
			}
		});
		btReiniciar.setFont(new Font("Segoe Script", Font.BOLD, 14));
		btReiniciar.setBounds(10, 76, 181, 23);
		panel_1_1.add(btReiniciar);
		
		btStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pararCronometro();			
			}
		});
		btStop.setFont(new Font("Segoe Script", Font.BOLD, 14));
		btStop.setBounds(310, 42, 90, 23);
		panel_1_1.add(btStop);
		
		btPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarCronometro();				
			}
		});
		btPlay.setFont(new Font("Segoe Script", Font.BOLD, 14));
		btPlay.setBounds(219, 42, 86, 23);
		panel_1_1.add(btPlay);
		
		JButton btLimparRanking = new JButton("Limpar");
		btLimparRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaJogadores.getListaJogadores().clear();//limpa a lista
				txtRanking.setText("");
				
				frame.dispose();//fecha a tela jogo e abre a de login novamente
				Login l1 = new Login();
				l1.setVisible(true);
			}
		});
		btLimparRanking.setFont(new Font("Segoe Script", Font.BOLD, 14));
		btLimparRanking.setBounds(189, 186, 116, 23);
		panel_1_1.add(btLimparRanking);
		txtRanking.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		
		txtRanking.setBounds(10, 220, 295, 125);
		panel_1_1.add(txtRanking);
		
		JMenuBar jMenuBar = new JMenuBar();
		jMenuBar.setBounds(0, 0, 754, 24);
		frame.getContentPane().add(jMenuBar);
		
		JMenu mnTrocarTema = new JMenu("Arquivo");
		mnTrocarTema.setMnemonic('A');
		jMenuBar.add(mnTrocarTema);
		
		JMenu mnTemas = new JMenu("Temas");
		mnTrocarTema.add(mnTemas);
		
		JMenuItem menuTemaCarro = new JMenuItem("Carro");
		menuTemaCarro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				ativarTemaCarro();
			}
		});
		mnTemas.add(menuTemaCarro);
	}
}
