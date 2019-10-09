import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Fenetre extends JFrame{
	
	private JPanel container = new JPanel();
	
	private JLabel ecran = new JLabel("Test");
	private JLabel screen = new JLabel("Vous êtes ici :");
	private Dimension dim1 = new Dimension(50,40);
	private Dimension dim2 = new Dimension(50,31);
	String[] name = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
	private JButton[] bouton = new JButton[name.length];
	private Controleur control;
	public Fenetre(Controleur control) {
		
		this.control = control;
		this.setTitle("Ascenseur Virtuel");
		this.setSize(250, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		
		initContainer();
		
		this.setContentPane(container);
		this.setVisible(true);
	}
	
	public void initContainer() {
		
		Font police = new Font("Arial", Font.BOLD, 20);
		ecran.setFont(police);
		ecran.setHorizontalAlignment(JLabel.RIGHT);
		ecran.setPreferredSize(new Dimension(220,20));
		
		screen.setFont(police);
		screen.setHorizontalAlignment(JLabel.RIGHT);
		screen.setPreferredSize(new Dimension(220,20));
		
		JPanel panEcran = new JPanel();
		panEcran.setPreferredSize(new Dimension(220,30));

		JPanel panScreen = new JPanel();
		panScreen.setPreferredSize(new Dimension(220,30));
		JPanel chiffre = new JPanel();
		chiffre.setPreferredSize(new Dimension(165,225));
		
		for(int i = 0; i < name.length; i++) {
			bouton[i] = new JButton(name[i]);
			bouton[i].setPreferredSize(dim1);
			chiffre.add(bouton[i]);
			bouton[i].addActionListener(new EtageListener());
		}
		panEcran.add(ecran);
		panEcran.setBorder(BorderFactory.createLineBorder(Color.black));
		
		panScreen.add(screen);
		panScreen.setBorder(BorderFactory.createLineBorder(Color.black));
		container.add(panEcran, BorderLayout.NORTH);
		container.add(chiffre, BorderLayout.CENTER);
		container.add(panScreen, BorderLayout.SOUTH);

	}
	
	public class EtageListener implements ActionListener{
			
		public void actionPerformed(ActionEvent e) {
			
			String text = ((JButton)e.getSource()).getText();
			ecran.setText(text);
			control.request((Integer.valueOf(text)));
			
		}
	}
}
