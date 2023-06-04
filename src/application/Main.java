package application;
	
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.animation.PauseTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.scene.control.cell.PropertyValueFactory; 

public class Main extends Application {
	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) throws SQLException {
		    primaryStage.setTitle("Inscription");
		    //scene1
			BorderPane root = new BorderPane();
			root.getStyleClass().add("root");
			GridPane grid1 = new GridPane();
			Scene scene1 = new Scene(root,500,500);
			grid1.getStyleClass().add("grid");
			grid1.setVgap(20);
			grid1.setHgap(20);
			HBox hbox1 = new HBox(15); 
			
			hbox1.setPadding(new Insets(0,10,40,10));
			
			//scene2 (pour boutton lister)
			VBox vboxLister = new VBox();
			Scene sceneLister = new Scene(vboxLister, 500, 500);
			HBox hboxLister = new HBox(15);
			hboxLister.setPadding(new Insets(30,30,30,20));
			TableView <etudiant> table = new TableView<etudiant>();//create a tableview
			table.getStyleClass().add("table-view");
			
			table.setMaxHeight(500);
			table.setMaxWidth(480);
	
	        etudiantDAO studentDAO = new etudiantDAO();
	        List<etudiant> etudiants = studentDAO.getAllStudents();
			

			//Labels
			Label LabelNom = new Label("Nom:");
			Label LabelPrenom = new Label("Prenom:");
			Label LabelNaissance = new Label("Date de naissance:");
			Label LabelCNE = new Label("CNE:");
			Label LabelCNI = new Label("CNI:");
			
			LabelNom.getStyleClass().add("label");
			LabelPrenom.getStyleClass().add("label");
			LabelNaissance.getStyleClass().add("label");
			LabelCNE.getStyleClass().add("label");
			LabelCNI.getStyleClass().add("label");
			
			
			//textfields
			TextField FieldNom = new TextField();
			TextField FieldPrenom = new TextField();
			TextField FieldNaissance = new TextField();
			TextField FieldCNE = new TextField();
			TextField FieldCNI = new TextField();
			
			
			FieldNom.getStyleClass().add("text-field");
			FieldPrenom.getStyleClass().add("text-field");
		    FieldNaissance.getStyleClass().add("text-field");
			FieldCNE.getStyleClass().add("text-field");
			FieldCNI.getStyleClass().add("text-field");

			
			//buttons
			Button ajouterbtn = new Button("Ajouter");
			
			ajouterbtn.setPrefHeight(30);
			ajouterbtn.getStyleClass().add("button");
			Button listerbtn = new Button("Lister");
			listerbtn.getStyleClass().add("button");
			listerbtn.setPrefHeight(30);
			Button voirProfilbtn = new Button("Voir Profil");
			voirProfilbtn.getStyleClass().add("button");
			voirProfilbtn.setPrefHeight(30);
			
			//adding eventhandler to ajouterbtn
			   ajouterbtn.setOnAction(new EventHandler <ActionEvent>(){

					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						String nom= FieldNom.getText();
						String prenom = FieldPrenom.getText();
						String naissance = FieldNaissance.getText();
						String CNE = FieldCNE.getText();
						String CNI = FieldCNI.getText();
						if (!nom.isEmpty() && !prenom.isEmpty() && !naissance.isEmpty() && !CNE.isEmpty() && !CNI.isEmpty()) {
						try {
							Connection conn2 = Connection_Class.getConnection();
							PreparedStatement stmt2 = conn2.prepareStatement("INSERT INTO etudiants VALUES(?, ?, ?, ?, ?)");
							stmt2.setString(1, nom);
							stmt2.setString(2, prenom);
							stmt2.setString(3, naissance);
							stmt2.setString(4, CNE);
							stmt2.setString(5, CNI);
							
							stmt2.executeUpdate();  // Execute the SQL statement to insert the row
							table.getItems().add(new etudiant(nom, prenom, naissance, CNE, CNI));
							etudiants.add(new etudiant(nom, prenom, naissance, CNE, CNI));
							
							
							
							 // Show success message
				            Alert alert = new Alert(AlertType.INFORMATION);
				            alert.setTitle("Message");
				            alert.setHeaderText(null);
				            alert.setContentText("L'étudiant a été ajouté avec succès!");
				            alert.showAndWait();
				            // Clear the input fields
				            FieldNom.clear();
				            FieldPrenom.clear();
				            FieldNaissance.clear();
				            FieldCNE.clear();
				            FieldCNI.clear();
						}

						 catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
					} 
					}
			   });
			//adding eventhandler to listerbtn
			listerbtn.setOnAction(event->{
				primaryStage.setScene(sceneLister);
			});
				
			// adding the elements to the grid
			grid1.add(LabelNom, 0, 0, 1, 1);
			grid1.add(LabelPrenom, 0, 1, 1, 1);
			grid1.add(LabelNaissance, 0, 2, 1, 1);
			grid1.add(LabelCNE, 0, 3, 1, 1);
			grid1.add(LabelCNI, 0, 4, 1, 1);
			grid1.add(FieldNom, 1, 0, 2, 1);
		    grid1.add(FieldPrenom, 1, 1, 2, 1);
			grid1.add(FieldNaissance, 1, 2, 2, 1);
		    
			grid1.add(FieldCNE, 1, 3, 2, 1);
			grid1.add(FieldCNI, 1, 4, 2, 1);
			root.setCenter(grid1);
			grid1.setAlignment(Pos.CENTER);
			hbox1.getChildren().addAll(ajouterbtn, listerbtn, voirProfilbtn);
			root.setBottom(hbox1);
			hbox1.setAlignment(Pos.BOTTOM_CENTER);
	
			scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			sceneLister.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			
		 
			
			
          
	        
	        //create tablecolumns
	        TableColumn <etudiant, String>  CNEColumn= new TableColumn <etudiant, String>("CNE");
	        CNEColumn.getStyleClass().add("table-Header");
	        TableColumn <etudiant, String>  CNIColumn= new TableColumn <etudiant, String>("CNI");
	        CNIColumn.getStyleClass().add("table-Header");
	        TableColumn <etudiant, String>  NomColumn= new TableColumn <etudiant, String>("Nom");
	        NomColumn.getStyleClass().add("table-Header");
	        TableColumn <etudiant, String>  PrenomColumn= new TableColumn <etudiant, String>("Prenom");
	        PrenomColumn.getStyleClass().add("table-Header");
	        TableColumn <etudiant, String>  NaissanceColumn= new TableColumn <etudiant, String>("Date de naissance");
	        NaissanceColumn.getStyleClass().add("table-Header");
	        
	        
	        
	        CNEColumn.setCellValueFactory(new PropertyValueFactory<>("CNE"));
	        CNIColumn.setCellValueFactory(new PropertyValueFactory<>("CNI"));
	        NomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
	        PrenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
	        NaissanceColumn.setCellValueFactory(new PropertyValueFactory<>("naissance"));
	        CNEColumn.setPrefWidth(100);
	        CNIColumn.setPrefWidth(100);
	        NaissanceColumn.setPrefWidth(165);
	        
	        table.getColumns().addAll(CNEColumn, CNIColumn, NomColumn, PrenomColumn, NaissanceColumn);
	        
            table.getItems().addAll(etudiants);
            
            //Buttons for scene2(listerbtn)
            Button supprimerbtn = new Button("Supprimer");
            supprimerbtn.getStyleClass().add("button");
            supprimerbtn.setPrefHeight(30);
            Button backbtn = new Button("Retourner");
            backbtn.getStyleClass().add("button");
            backbtn.setPrefHeight(30);
            //Eventhandler for supprimerbtn
            //permet de supprimer l'etudiant selectionné
            supprimerbtn.setOnAction(new EventHandler <ActionEvent>(){

				@Override
				public void handle(ActionEvent event) {
					etudiant selectedPerson = table.getSelectionModel().getSelectedItem();
					// TODO Auto-generated method stub
					 if (selectedPerson != null) {
					        try {
					            Connection conn = Connection_Class.getConnection();
					            
					            PreparedStatement stmt = conn.prepareStatement("DELETE FROM etudiants WHERE CNE = ?");
					            stmt.setString(1, selectedPerson.getCNE());
					            stmt.executeUpdate();
				               
				            table.getItems().remove(selectedPerson);
				        } catch (SQLException e) {
				            e.printStackTrace();
				        }
					 }
            	
            }
            }
            );
            //Eventhandler for backbtn
            backbtn.setOnAction(event->{
            	primaryStage.setScene(scene1);
            });
            
            hboxLister.getChildren().addAll( supprimerbtn, backbtn);
            
            vboxLister.getChildren().add(table);
            vboxLister.setAlignment(Pos.CENTER);
            vboxLister.getChildren().add(hboxLister);
			hboxLister.setAlignment(Pos.BOTTOM_CENTER);
			
			
			
	
			//adding eventhandler to voirProfilbtn
	     	voirProfilbtn.setOnAction(e -> {
	     	    // Get the value entered in the "etudiant" TextField
	     	    String etudiantValue = FieldCNE.getText();

	     	    // Perform database lookup to check if the student exists
	     	    boolean studentExists = checkIfStudentExists(etudiantValue); 

	     	    if (studentExists) {
	     	        // Student exists in the database, retrieve and display the data
	     	        etudiant etudiant = retrieveStudentData(etudiantValue); 
		            // Create a new scene to display the retrieved data
	     	        BorderPane rootProfil = new BorderPane();
		            GridPane gridProfil = new GridPane();
		            HBox hboxProfil = new HBox(20);
		            hboxProfil.setPadding(new Insets(0, 20, 50, 20));
		            gridProfil.setPadding(new Insets(20));
		            gridProfil.setVgap(30);
                    gridProfil.setHgap(30);
                    
                    //Labels
		            Label nomLabel = new Label("Nom:");
		            nomLabel.getStyleClass().add("label");
		            Label nomValue = new Label(etudiant.getNom());
		            nomValue.getStyleClass().add("label");
		            gridProfil.addRow(0, nomLabel, nomValue);

		            Label prenomLabel = new Label("Prenom:");
		            prenomLabel.getStyleClass().add("label");
		            Label prenomValue = new Label(etudiant.getPrenom());
		            prenomValue.getStyleClass().add("label");
		            gridProfil.addRow(1, prenomLabel, prenomValue);

		            Label naissanceLabel = new Label("Date de naissance:");
		            naissanceLabel.getStyleClass().add("label");
		            Label naissanceValue = new Label(etudiant.getNaissance());
		            naissanceValue.getStyleClass().add("label");
		            gridProfil.addRow(2, naissanceLabel, naissanceValue);
		            
		            Label CNELabel = new Label("CNE:");
		            CNELabel.getStyleClass().add("label");
		            Label CNEValue = new Label(etudiant.getCNE());
		            CNEValue.getStyleClass().add("label");
		            gridProfil.addRow(3, CNELabel, CNEValue);
		            
		            Label CNILabel = new Label("CNI:");
		            CNILabel.getStyleClass().add("label");
		            Label CNIValue = new Label(etudiant.getCNI());
		            CNIValue.getStyleClass().add("label");
		            gridProfil.addRow(4, CNILabel, CNIValue);
		            
		            
		            
		            //buttons
		            Button modifierbtn = new Button("Modifier");
		            modifierbtn.setPrefHeight(30);
		            Button imprimerbtn = new Button("Imprimer");
		            imprimerbtn.setPrefHeight(30);
		            Button retournerbtn = new Button("Retourner");
		            retournerbtn.setPrefHeight(30);
		            //scene of modification
	                 modifierbtn.setOnAction(event->{

	                	 BorderPane rootmodify = new BorderPane(); 
		            	 GridPane gridModify = new GridPane();
		            	 gridModify.setHgap(30);
		            	 gridModify.setVgap(30);
		            	 Scene sceneModify = new Scene(rootmodify, 500, 500);
		            	 Label modifyNomLabel = new Label("Nom:");
		            	 modifyNomLabel.getStyleClass().add("label");
		                 TextField modifyNomField = new TextField(etudiant.getNom());
		                 gridModify.addRow(0, modifyNomLabel, modifyNomField);

		                 Label modifyPrenomLabel = new Label("Prenom:");
		                 modifyPrenomLabel.getStyleClass().add("label");
		                 TextField modifyPrenomField = new TextField(etudiant.getPrenom());
		                 gridModify.addRow(1, modifyPrenomLabel, modifyPrenomField);

		                 Label modifyNaissanceLabel = new Label("Date de naissance:");
		                 modifyNaissanceLabel.getStyleClass().add("label");
		                 TextField modifyNaissanceField = new TextField(etudiant.getNaissance());
		                 gridModify.addRow(2, modifyNaissanceLabel, modifyNaissanceField);
		          
		                 Label modifyCNELabel = new Label("CNE:");
		                 modifyCNELabel.getStyleClass().add("label");
		                 TextField modifyCNEField = new TextField(etudiant.getCNE());
		                 gridModify.addRow(3, modifyCNELabel, modifyCNEField);

		                 Label modifyCNILabel = new Label("CNI:");
		                 modifyCNILabel.getStyleClass().add("label");
		                 TextField modifyCNIField = new TextField(etudiant.getCNI());
		                 gridModify.addRow(4, modifyCNILabel, modifyCNIField);
		                 Button enregistrerModifBtn = new Button("Enregistrer");
		                 enregistrerModifBtn.getStyleClass().add("button");
		                 
		                 Button retournerBtn = new Button("Retourner");
		                 retournerBtn.getStyleClass().add("button");
		                 
		                 gridModify.add(enregistrerModifBtn, 1, 6, 1, 1);
		                 gridModify.add(retournerBtn, 0, 6, 1, 1);
		                 gridModify.setAlignment(Pos.CENTER);
		                 rootmodify.setCenter(gridModify);

		                 primaryStage.setScene(sceneModify);
		                 sceneModify.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		            
		                 enregistrerModifBtn.setOnAction(saveEvent -> {
		                     // Retrieve the modified values from the input fields
		                     String modifiedNom = modifyNomField.getText();
		                     String modifiedPrenom = modifyPrenomField.getText();
		                     String modifiedNaissance = modifyNaissanceField.getText();
		                     String modifiedCNE = modifyCNEField.getText();
		                     String modifiedCNI = modifyCNIField.getText();
		                     
		                     try {
		                         Connection conn = Connection_Class.getConnection();
		                         PreparedStatement stmt = conn.prepareStatement("UPDATE etudiants SET nom = ?, prenom = ?, naissance = ?, CNE = ?, CNI = ? WHERE CNE = ?");
		                         stmt.setString(1, modifiedNom);
		                         stmt.setString(2, modifiedPrenom);
		                         stmt.setString(3, modifiedNaissance);
		                         stmt.setString(4, modifiedCNE);
		                         stmt.setString(5, modifiedCNI);
		                         stmt.setString(6, etudiant.getCNE());
		                         stmt.executeUpdate();
		                         
		                         // Update the etudiant's data in the TableView
		                         etudiant.setNom(modifiedNom);
		                         etudiant.setPrenom(modifiedPrenom);
		                         etudiant.setNaissance(modifiedNaissance);
		                         etudiant.setCNE(modifiedCNE);
		                         etudiant.setCNI(modifiedCNI);
		 
		                         table.refresh();
								 // Show success message
						            Alert alert = new Alert(AlertType.INFORMATION);
						            alert.setTitle("Message");
						            alert.setHeaderText(null);
						            alert.setContentText("Modification avec succès!");
						            alert.showAndWait();
		                     
		                     } catch (SQLException ex) {
						                ex.printStackTrace();
						                // Handle any potential errors
						                showMessage("Erreur", "Une erreur s'est produite lors de la modification de l'étudiant.");
						            }
		                     

		            });
		                 retournerBtn.setOnAction(evnt->{
		                	primaryStage.setScene(scene1);
		                 });
	                 });
		           
		                 
		          
		            
		            //adding eventhandler for retournerbtn
		            retournerbtn.setOnAction(ev->{
		            	primaryStage.setScene(scene1);
		            });
		            hboxProfil.getChildren().addAll(modifierbtn, imprimerbtn, retournerbtn);
		            // Create a new stage and set the scene
		            rootProfil.setCenter(gridProfil);
		            rootProfil.setBottom(hboxProfil);
		            hboxProfil.setAlignment(Pos.CENTER);
		            gridProfil.setAlignment(Pos.CENTER);
		            Scene dataScene = new Scene(rootProfil, 500, 500);
		            dataScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		           
		            primaryStage.setScene(dataScene); 
		                     
	     	    } else {
	     	        // Student does not exist in the database, show error message
	     	        showMessage("Error","Cet etudiant n'existe pas!");
	     	    }
	     	     FieldNom.clear();
	     	     FieldPrenom.clear();
	     	     FieldNaissance.clear();
	     	     FieldCNE.clear();
	     	     FieldCNI.clear();
	     	});    
	 		
			
			primaryStage.setScene(scene1);
			primaryStage.show();
		/*} catch(Exception e) {
			e.printStackTrace();
		}*/
		
	}
	
	private boolean checkIfStudentExists(String cne) {
		
		
		    boolean studentExists = false;

		    // Assuming you have a connection to the database named "connection"
		    try {
		    	Connection conn = Connection_Class.getConnection();
		        PreparedStatement statement = conn.prepareStatement("SELECT COUNT(*) FROM etudiants WHERE CNE = ?");
		        statement.setString(1, cne);
		        ResultSet resultSet = statement.executeQuery();
		        resultSet.next();
		        int count = resultSet.getInt(1);
		        studentExists = count > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        // Handle any potential errors
		    }

		    return studentExists;
		}
	etudiant retrieveStudentData(String cne) {
	    etudiant etudiant = null;

	    try {
	    	Connection conn = Connection_Class.getConnection(); 
	        PreparedStatement statement = conn.prepareStatement("SELECT * FROM etudiants WHERE CNE = ?");
	        statement.setString(1, cne);
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            
	            String CNE = resultSet.getString("CNE");
	            String CNI = resultSet.getString("CNI");
	            String nom = resultSet.getString("nom");
	            String prenom = resultSet.getString("prenom");
	            String naissance = resultSet.getString("naissance");
	            
	            // Create a new Student object with the retrieved data
	            etudiant = new etudiant( nom, prenom, naissance, CNE, CNI);
	            

	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Handle any potential errors
	    }

	    return etudiant;
	}
	   public static void showMessage(String title, String message) {
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.showAndWait();
	    }

	public static void main(String[] args) {
		launch(args);
	}
}
