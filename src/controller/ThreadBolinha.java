package controller;
import java.awt.Rectangle;
import java.util.concurrent.Semaphore;

import javax.swing.JButton;
import javax.swing.JLabel;


public class ThreadBolinha extends Thread {
	
	private JLabel lblBolinha;
	private JButton btnIniciar;
	private Semaphore vencedor;
	private int id;
	
	public ThreadBolinha(int id,JLabel lblBolinha, JButton btnIniciar, Semaphore vencedor) {
		this.id = id;
		this.lblBolinha = lblBolinha;
		this.btnIniciar = btnIniciar;
		this.vencedor = vencedor;
	}
	
	private void mexeBolinha() {
		
		btnIniciar.setEnabled(false);
		Rectangle posicao;
		posicao = lblBolinha.getBounds();
		lblBolinha.setBounds(posicao);
		//int contadorDeMov = 0;
		
		while(posicao.x<=400) {
			try {
				vencedor.acquire();
				posicao.x = posicao.x + aleatorioEntre(0,15);
				lblBolinha.setBounds(posicao);
				vencedor.release();
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			vencedor.acquire();
			System.out.println("A bolinha "+id+" venceu!!!");
		} catch (InterruptedException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		btnIniciar.setEnabled(true);
	}
	
	@Override
	public void run() {
		mexeBolinha();
	}
	
	private int aleatorioEntre(int inicio, int fim) {
		
		fim = fim-inicio;
		fim++;

		int numero = (int)((Math.random()*fim)+inicio);
		
		return numero;
	}
}