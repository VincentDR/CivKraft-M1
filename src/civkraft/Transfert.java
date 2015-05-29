package civkraft;

import java.awt.Color;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import madkit.kernel.AbstractAgent;
import madkit.kernel.Probe;
import civilisation.Civilisation;
import civilisation.Configuration;
import civilisation.ItemPheromone;
import civilisation.SchemaCognitif;
import civilisation.amenagement.TypeAmenagement;
import civilisation.effects.Effect;
import civilisation.individu.decisionMaking.DecisionMaker;
import civilisation.individu.plan.action.Action;
import civilisation.inventaire.Objet;
import civilisation.world.EvoluPatch;
import civilisation.world.Terrain;
import civilisation.world.World;
import civilisation.world.WorldViewer;

public class Transfert implements Serializable {
	
	//Regroupe l'ensemble des donn�es � envoyer

	private String choix;
	
	
	private int hauteurSimu;
	private int largeurSimu;
	

	
	/**
	 * M�thodes
	 */
	 public Transfert(){
		 
	 }
	 
	 /**
	  * Getters and setters
	  */
	
	 public String getChoix(){
		 return choix;
	 }
	 
	 public void setChoix(String c){
		 choix = c;
	 }
	 
	 public int getHauteurSimu(){
		 return hauteurSimu;
	 }
	 
	 public void setHauteurSimu(int hS){
		 hauteurSimu = hS;
	 }
	
	 public int getLargeurSimu(){
		 return largeurSimu;
	 }
	 
	 public void setLargeurSimu(int lS){
		 largeurSimu = lS;
	 }

}

