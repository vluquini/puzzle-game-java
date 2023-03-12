package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;

import jogo.ListaJogadores;
import jogo.Jogador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Login {

	private JFrame frmPuzzleGame;
	private JTextField txtJogador1;
	private JTextField txtJogador2;
	private JTextField txtJogador3;
	
	static ListaJogadores listaJogadores = new ListaJogadores();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmPuzzleGame.setVisible(true);
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
			frmPuzzleGame.setVisible(true);
	}
	
	public Login() {		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void verificarApelido(String apelido) {
		if(!apelido.isEmpty() || apelido.matches("[0-9]*")) {
			Jogador jogador = new Jogador(apelido);
			listaJogadores.addJogador(jogador);
		}else
			JOptionPane.showMessageDialog(null, "Não pode cadastrar jogador com nome vazio!", null, 0);
	}
	
	public ListaJogadores getListaJogadores() {
	    return listaJogadores;
	}
	
	
	private void initialize() {

		frmPuzzleGame = new JFrame();
		frmPuzzleGame.setTitle("Puzzle Game");
		frmPuzzleGame.setBounds(100, 100, 450, 300);
		frmPuzzleGame.setLocationRelativeTo(null);//inicia a tela no meio
		frmPuzzleGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPuzzleGame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Jogador1:");
		lblNewLabel.setFont(new Font("Segoe Script", Font.PLAIN, 12));
		lblNewLabel.setBounds(92, 71, 63, 14);
		frmPuzzleGame.getContentPane().add(lblNewLabel);

		JButton btCadastrar = new JButton("Cadastrar");
		btCadastrar.setFont(new Font("Segoe Script", Font.PLAIN, 12));
		btCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String apelido1 = txtJogador1.getText();
				String apelido2 = txtJogador2.getText();
				String apelido3 = txtJogador3.getText();
				
				//verifica se ja ha jogador com o mesmo apelido na lista
				if(listaJogadores.existeJogador(apelido1)) {
					JOptionPane.showMessageDialog(null, "Já há um jogador com este apelido!!", null, 0);
					//verifica se esta cadastrando pelo menos um jogador
				}else if(apelido1.isEmpty() && apelido2.isEmpty() && apelido3.isEmpty()) { 
					JOptionPane.showMessageDialog(null, "Cadastre pelo menos um jogador!!", null, 0);
					
				}else {//verifica o apelido dos jogadores
					if(!apelido1.isBlank())
						verificarApelido(apelido1);
					
					if(!apelido2.isBlank())
						verificarApelido(apelido2);
					
					if(!apelido3.isBlank())
						verificarApelido(apelido3);
				}
				//se pelo menos 1 jogador for cadastrado, fecha a tela e inicia a tela do puzzle
				if(!(apelido1.isBlank() && apelido2.isBlank() && apelido3.isBlank())) {
					frmPuzzleGame.dispose();
					Jogo j1 = new Jogo(listaJogadores);
					j1.setVisible(true);
				}
		}});
		btCadastrar.setBounds(92, 178, 110, 23);
		frmPuzzleGame.getContentPane().add(btCadastrar);

		JButton btEntrar = new JButton("Entrar");
		btEntrar.setFont(new Font("Segoe Script", Font.PLAIN, 12));
		btEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				frmPuzzleGame.dispose();
				Jogo j1 = new Jogo(listaJogadores);
				j1.setVisible(true);
			}
		});
		btEntrar.setBounds(241, 178, 110, 23);
		frmPuzzleGame.getContentPane().add(btEntrar);

		txtJogador1 = new JTextField();
		txtJogador1.setBounds(165, 68, 105, 20);
		frmPuzzleGame.getContentPane().add(txtJogador1);
		txtJogador1.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Jogador2:");
		lblNewLabel_1.setFont(new Font("Segoe Script", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(92, 102, 63, 14);
		frmPuzzleGame.getContentPane().add(lblNewLabel_1);

		txtJogador2 = new JTextField();
		txtJogador2.setColumns(10);
		txtJogador2.setBounds(165, 99, 105, 20);
		frmPuzzleGame.getContentPane().add(txtJogador2);

		JLabel lblJogador = new JLabel("Jogador3:");
		lblJogador.setFont(new Font("Segoe Script", Font.PLAIN, 12));
		lblJogador.setBounds(92, 133, 63, 14);
		frmPuzzleGame.getContentPane().add(lblJogador);

		txtJogador3 = new JTextField();
		txtJogador3.setColumns(10);
		txtJogador3.setBounds(165, 130, 105, 20);
		frmPuzzleGame.getContentPane().add(txtJogador3);
		
		JLabel lblNewLabel_2 = new JLabel("Tela Inicial ");
		lblNewLabel_2.setFont(new Font("Segoe Script", Font.BOLD, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(92, 22, 259, 23);
		frmPuzzleGame.getContentPane().add(lblNewLabel_2);
	}
}
