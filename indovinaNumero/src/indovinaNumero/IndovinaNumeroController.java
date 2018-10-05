/**
 * Sample Skeleton for 'Indovina Numero.fxml' Controller Class
 */

package indovinaNumero;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class IndovinaNumeroController {
	
	private int NMax=100; // costate numero max
	private int TMax=7;// costante numero di tentativi
	
	private int segreto; // numero da indovinare
	private int tentativi; // tentativi gia fatti
	
	private boolean inGame = false; // stato del gioco;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxdiGioco"
    private HBox boxdiGioco; // Value injected by FXMLLoader

    @FXML // fx:id="txtcurrunt"
    private TextField txtcurrunt; // Value injected by FXMLLoader

    @FXML // fx:id="txtMax"
    private TextField txtMax; // Value injected by FXMLLoader

    @FXML // fx:id="txttentativo"
    private TextField txttentativo; // Value injected by FXMLLoader

    @FXML // fx:id="txtLog"
    private TextArea txtLog; // Value injected by FXMLLoader

    @FXML // fx:id="btnNuova"
    private Button btnNuova; // Value injected by FXMLLoader

    @FXML
    //Nuova Partita abilita e disabilita determinati elementi
    void handerNuova(ActionEvent event) {
    	/*il metodo random mi restituisce un numero DOUBLE casuale tra
    	 * 0 e 1 per ricevere un numero casuale tra 0 e N effettuare
    	 * il prodotto tra random e N dopodichè effettuare il cast 
    	 * se il numero che mi serve è un numero intero inserisco +1
    	 * dato che il numero casuale verrà generato da 0 a N-1;
    	 */
    	this.segreto = (int)(Math.random()*NMax)+1;
    	
    	this.tentativi = 0;
    	this.inGame=true;
    	
    	/*
    	 * attenzione alla doppia negazione 
    	 */
    	btnNuova.setDisable(true);//disabilitare il bottone nuova (disabilito=vero)
    	boxdiGioco.setDisable(false);//abilitare la box di gioco (disabilito=falso)
    	//tentativi è un intero e lo trasformo in stringa in questo modo:
    	txtcurrunt.setText(String.format("%d", this.tentativi));
    	txtMax.setText(String.format("%d", this.TMax));
    	txtLog.clear();
    	txttentativo.clear();
    	
    	txtLog.setText(String.format("Indovina un  umero ta %d e %d\n", 1, NMax));
    }

    @FXML
    void handelProva(ActionEvent event) {
    	
    	String numS = txttentativo.getText();//prelevo il tentativo dalla casella di testo
    	
    	if(numS.length()==0) {
    		txtLog.appendText("Devi inserire un numero\n");
    		return ;//inserendo return esco dalla situazione e mi ritorna il messaggio RICORDATI DI INSERIRLO
    	}
    	try {
    		//trasformo una stringa in numero attenzione bisogna gestire una ECCEZIONE
    		int num = Integer.parseInt(numS);
    		if ((num<1)||(num>NMax)){
    			txtLog.appendText("Valore fuori range\n");
    			return;
    		}
    		
    		
    		if(num==this.segreto) {
    			//ha indovinato
    			txtLog.appendText("HAI VINTO!");
    			
    			//chiudere la partita (disabilitare l'area di gioco e riabilitare il pulsante nuova partita
    			boxdiGioco.setDisable(true);
    			btnNuova.setDisable(false);
    			this.inGame=false;
    			
    		}else {
    			//ha sbagliato
    			this.tentativi++;
    			txtcurrunt.setText(String.format("%d", this.tentativi));
    			
    			if(this.tentativi==this.TMax) {
    				//ha perso
    				txtLog.appendText(String.format("Hai perso!, il numero era: %d\n", 
    						this.segreto));
    				//chiudere la partita (disabilitare l'area di gioco e riabilitare il pulsante nuova partita
        			boxdiGioco.setDisable(true);
        			btnNuova.setDisable(false);
        			this.inGame=false;
    			}else {
    				//sono ancora in gioco
    				if(num<segreto) {
    					//troppo basso
    					txtLog.appendText("Troppo Basso\n");
    				}else {
    					//troppo alto
    					txtLog.appendText("Troppo Alto\n");
    				}
    			}
    		}
    		
    	}catch (NumberFormatException ex) {
    		txtLog.appendText("il dato inserito non è numerico\n");
    		return ;//inserendo return esco dalla situazione RICORDATI DI INSERIRLO
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxdiGioco != null : "fx:id=\"boxdiGioco\" was not injected: check your FXML file 'Indovina Numero.fxml'.";
        assert txtcurrunt != null : "fx:id=\"txtcurrunt\" was not injected: check your FXML file 'Indovina Numero.fxml'.";
        assert txtMax != null : "fx:id=\"txtMax\" was not injected: check your FXML file 'Indovina Numero.fxml'.";
        assert txttentativo != null : "fx:id=\"txttentativo\" was not injected: check your FXML file 'Indovina Numero.fxml'.";
        assert txtLog != null : "fx:id=\"txtLog\" was not injected: check your FXML file 'Indovina Numero.fxml'.";
        assert btnNuova != null : "fx:id=\"btnNuova\" was not injected: check your FXML file 'Indovina Numero.fxml'.";

    }
}
