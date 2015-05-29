package civkraft;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import turtlekit.kernel.TurtleKit;
import civilisation.Configuration;
import civilisation.SchemaCognitif;
import madkit.kernel.Agent;
import madkit.kernel.AgentAddress;
import madkit.kernel.KernelAddress;
import madkit.message.ObjectMessage;

public class FenetreEnvoiScCognitif extends JFrame implements MouseListener{
	
	private Client dad;
	
	private AgentAddress serveurCible;
	
	private JPanel listeSchemas = new JPanel();
	private JComboBox schemas = new JComboBox();
	private JLabel explications = new JLabel();
	
	
	private JPanel boutons = new JPanel();
	private JButton annuler =  new JButton();
	private JButton envoiScCognitif = new JButton();
	
	public FenetreEnvoiScCognitif(AgentAddress ka, Client d){
		dad = d;
		serveurCible = ka;
		
		this.setSize(512, 200);
		this.setTitle("CIVKRAFT");
		this.setVisible(true);
		
		listeSchemas = new JPanel();
		
		explications = new JLabel("<html><center>Veuillez s�lectionnez le sch�ma cognitif <br> que vous d�sirez envoyer au serveur d'adresse <br>" + ka +"</center></html>",JLabel.CENTER);
		Font font = new Font("Arial", Font.BOLD, 12);
		explications.setFont(font);
		explications.setVisible(true);
		
		Object[] data = new Object[Configuration.SchemasCognitifs.size()];
		
		for(int i =0; i < Configuration.SchemasCognitifs.size(); i++){
			data[i] = Configuration.SchemasCognitifs.get(i);
		}
		schemas = new JComboBox(data);
		
		listeSchemas.add(explications, BorderLayout.NORTH);
		listeSchemas.add(schemas, BorderLayout.SOUTH);
		listeSchemas.setVisible(true);
		
		this.add(listeSchemas);
		
		boutons = new JPanel();
		
		annuler = new JButton("Annuler");
		annuler.addMouseListener((MouseListener) this);
		
		envoiScCognitif = new JButton("Envoi Sch�ma Cognitif");
		envoiScCognitif.addMouseListener((MouseListener) this);
		
		boutons.add(annuler, BorderLayout.CENTER);
		boutons.add(envoiScCognitif, BorderLayout.CENTER);
		//boutons.add(connection, BorderLayout.WEST);
		boutons.setVisible(true);
		
		this.add(boutons, BorderLayout.SOUTH);
		
		//Mettre la fenetre au centre
		this.setLocationRelativeTo(null);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource().equals(envoiScCognitif)){
			int cpt =0;
			while(!Configuration.SchemasCognitifs.get(cpt).getNom().equals(schemas.getSelectedItem().toString())){
				cpt++;
			}
			
			//AgentEnvoiScCognitif a = new AgentEnvoiScCognitif();
			ObjectMessage<SchemaCognitif> message = new ObjectMessage<SchemaCognitif>(Configuration.SchemasCognitifs.get(cpt));
			dad.sendMessage(serveurCible, message);
			
			this.dispose();
		}
		if(arg0.getSource().equals(annuler)){
			this.dispose();
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
