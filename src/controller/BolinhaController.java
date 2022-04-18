package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

import javax.swing.JButton;
import javax.swing.JLabel;

public class BolinhaController implements ActionListener{
	private JLabel lblBolinha1;
	private JLabel lblBolinha2;
	private JLabel lblBolinha3;
	private JButton btnIniciar;
	
	public BolinhaController(JLabel lblBolinha1,JLabel lblBolinha2, JLabel lblBolinha3, JButton btnIniciar) {
		this.lblBolinha1 = lblBolinha1;
		this.lblBolinha2 = lblBolinha2;
		this.lblBolinha3 = lblBolinha3;
		this.btnIniciar = btnIniciar;
	}	
	
	private void botaoBolinha(){
		
		Semaphore vencedor = new Semaphore(1);
		
		Thread t1 = new ThreadBolinha(1,lblBolinha1, btnIniciar, vencedor);
		Thread t2 = new ThreadBolinha(2,lblBolinha2, btnIniciar, vencedor);
		Thread t3 = new ThreadBolinha(3,lblBolinha3, btnIniciar, vencedor);
		
		t1.start();
		t2.start();
		t3.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		botaoBolinha();
	}
}