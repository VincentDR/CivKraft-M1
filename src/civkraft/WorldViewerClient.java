package civkraft;

import java.awt.Graphics;

import java.util.List;
import java.util.logging.Level;

import madkit.kernel.AgentAddress;
import madkit.message.ObjectMessage;
import madkit.simulation.viewer.SwingViewer;

import civilisation.Configuration;
import civilisation.DefineConstants;
import civilisation.SchemaCognitif;
import civilisation.world.WorldViewer;
import turtlekit.kernel.TKLauncher;


public class WorldViewerClient extends SwingViewer {

	
	public WorldViewerClient(){
		createGUIOnStartUp();
	}

	
	protected void activate() {
		setLogLevel(Level.OFF);
		createGroupIfAbsent(DefineConstants.RESEAU,DefineConstants.GROUPE_RESEAU_VIEWERS,true);			
		requestRole(DefineConstants.RESEAU,DefineConstants.GROUPE_RESEAU_VIEWERS,DefineConstants.ROLE_CLIENT_VIEWERS);

		super.activate();
	}
	
	protected void live() 
	 {           
		//AgentAddress test = this.getDistantAgentWithRole(DefineConstants.RESEAU,DefineConstants.GROUPE_RESEAU_VIEWERS,DefineConstants.ROLE_SERVEUR_VIEWERS, serveur.getKernelAddress());
		//test.getClass();
	 }

	public boolean sames(List<AgentAddress> un, List<AgentAddress> deux){
		 if(un == null && deux == null){
			 return true;
		 }	
		 if((un == null && deux != null) || (un != null && deux == null)){
			 return false;
		 }
		 if(un.size() == deux.size()){
			 boolean rechercheActuelle =false;
			 for(AgentAddress ad : un){
				 for(AgentAddress adDeux : deux){
					 if(ad.equals(adDeux)){
						 rechercheActuelle = true;
					 }
				 }
				 if(!rechercheActuelle){
					 return false;
				 }
			 }
			 return true;
		 }
		 return false;
	 }

	@Override
	protected void render(Graphics arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
