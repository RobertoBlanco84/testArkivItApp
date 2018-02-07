package com.arkivit.view;

import javafx.scene.control.Label;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class ExcelAppGUIFX{



	/*public static void main(String[] args) {
		launch(args);

	}*/

	/*ExcelAppGUIFX(String args){
		launch(args);
	}*/

	private GridPane grid,gridSecondScene;// = new GridPane();
	//Text scenetitle = new Text("Welcome");
	private Scene secondScene;
	private Label BAL;
	private TextField BALtxt;

	private Label AK;
	private TextField AKtxt;

	private Label OA;
	private TextField OAtxt;

	private Label LM;
	private TextField LMtxt;

	private Label OLM;
	private TextField OLMtxt;

	private Label SK;
	private TextField SKtxt;

	private Label KFL;
	private TextField KFLtxt;

	private Label TTK;
	private TextField TTKtxt ;

	private Label EK;
	private TextField EKtxt;

	private Label AN;
	private TextField ANtxt;

	private Label SN;
	private TextField SNtxt;

	private Label UD;
	private DatePicker datePicker;
	private TextField UDtxt;

	private Label KOM;
	private TextField KOMtxt; 

	private Button saveButton;
	private VBox root = new VBox();
	private Scene firstScene;
	private Button btnOpenFile;
	private Button btnSaveAs;
	private Button btnConvert;
	private TextField openTxtField;// = new TextField();
	private TextField saveTxtField; //= new TextField();
	private Label dirLabel;// = new Label("Directory");
	private Label outputLabel;// = new Label("Output");
	private Label mapLabel;// = new Label("Map");
	private CheckBox checkBox;// = new CheckBox("");

	private DirectoryChooser directoryChooser = new DirectoryChooser();


	public void start() {
		//primaryStage.setTitle("ArkivIt");

		BAL = new Label("Beskrivning av leverans");
		AK = new Label("Arkivbildare");
		OA = new Label("Organisationsnummer arkivbildare");
		LM = new Label("Levererande myndighet");
		Label OLM = new Label("Organisationsnummer levererande myndighet");
		KFL = new Label("Kontaktperson för leverans");
		SK = new Label("Servicebyrå/Konsult");
		TTK = new Label("Telefonnummer till kontaktperson");
		EK = new Label("E-post-adress till kontakperson");
		AN = new Label("Arkivets namn");
		SN = new Label("Systemts namn");
		UD = new Label("Uttagsdatum:");
		KOM = new Label("Kommentar");
		saveButton = new Button("SAVE");
		BALtxt = new TextField();

		AKtxt = new TextField();

		OAtxt = new TextField();

		LMtxt = new TextField();

		OLMtxt = new TextField();

		SKtxt = new TextField();

		KFLtxt = new TextField();

		TTKtxt = new TextField();

		EKtxt = new TextField();

		ANtxt = new TextField();

		SNtxt = new TextField();

		//UDtxt = new TextField();
		datePicker = new DatePicker();

		KOMtxt = new TextField();


		//setUpGridPane(grid);
		//  scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		//grid.add(scenetitle, 0, 0, 2, 1);
		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		grid.add(BAL, 0, 1);
		grid.add(BALtxt, 1, 1);

		grid.add(AK, 0, 2);
		grid.add(AKtxt, 1, 2);

		grid.add(OA, 0, 3);
		grid.add(OAtxt, 1, 3);

		grid.add(LM, 0, 4);
		grid.add(LMtxt, 1, 4);

		grid.add(OLM, 0, 5);
		grid.add(OLMtxt, 1, 5);

		grid.add(SK, 0, 6);
		grid.add(SKtxt, 1, 6);

		grid.add(KFL, 0, 7);
		grid.add(KFLtxt, 1, 7);

		grid.add(TTK, 0, 8);
		grid.add(TTKtxt, 1, 8);

		grid.add(EK, 0, 9);
		grid.add(EKtxt, 1, 9);

		grid.add(AN, 0, 10);
		grid.add(ANtxt, 1, 10);

		grid.add(SN, 0, 11);
		grid.add(SNtxt, 1, 11);

		grid.add(UD, 0, 12);
		grid.add(datePicker, 1, 12);
		datePicker.setEditable(false);

		grid.add(KOM, 0, 13);
		grid.add(KOMtxt, 1, 13);

		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(saveButton);
		grid.add(hbBtn, 1, 14);

		root.getChildren().add(grid);
		//root.getChildren().add(hbBtn);

		firstScene = new Scene(root, 800, 600);



	}

	/*private void setUpGridPane(GridPane grid) {
		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

	}*/

	public void startSecondScene()
	{
		VBox root2 = new VBox();
		btnOpenFile = new Button("Select file...");
		btnSaveAs = new Button("Save As...");
		btnConvert = new Button("Create");
		openTxtField = new TextField();
		saveTxtField = new TextField();
		dirLabel = new Label("Directory");
		outputLabel = new Label("Output");
		mapLabel = new Label("Map");
		checkBox = new CheckBox("");

		//setUpGridPane(gridSecondScene);

		gridSecondScene = new GridPane();
		gridSecondScene.setAlignment(Pos.CENTER);
		gridSecondScene.setHgap(10);
		gridSecondScene.setVgap(10);
		gridSecondScene.setPadding(new Insets(200, 200, 200, 200));

		//Open dir components
		gridSecondScene.add(dirLabel, 0, 0);
		gridSecondScene.add(openTxtField, 1, 0);
		gridSecondScene.add(btnOpenFile, 2, 0);

		//mapp
		gridSecondScene.add(mapLabel, 0, 1);
		gridSecondScene.add(checkBox, 1, 1);

		//Out dir components
		gridSecondScene.add(outputLabel, 0, 2);
		gridSecondScene.add(saveTxtField, 1, 2);
		gridSecondScene.add(btnSaveAs, 2, 2);

		//Create Excel button

		gridSecondScene.add(btnConvert, 1, 3);
		
		root2.getChildren().add(gridSecondScene);
		secondScene = new Scene(root2, 800, 600);
	}





	public Button getBtnOpenFile() {
		return btnOpenFile;
	}

	public void setBtnOpenFile(Button btnOpenFile) {
		this.btnOpenFile = btnOpenFile;
	}

	public Button getBtnSaveAs() {
		return btnSaveAs;
	}

	public void setBtnSaveAs(Button btnSaveAs) {
		this.btnSaveAs = btnSaveAs;
	}

	public Button getBtnConvert() {
		return btnConvert;
	}

	public void setBtnConvert(Button btnConvert) {
		this.btnConvert = btnConvert;
	}

	public TextField getOpenTxtField() {
		return openTxtField;
	}

	public void setOpenTxtField(TextField openTxtField) {
		this.openTxtField = openTxtField;
	}

	public TextField getSaveTxtField() {
		return saveTxtField;
	}

	public void setSaveTxtField(TextField saveTxtField) {
		this.saveTxtField = saveTxtField;
	}

	public void setSaveButton(Button saveButton) {
		this.saveButton = saveButton;
	}

	public DirectoryChooser getDirectoryChooser() {
		return directoryChooser;
	}

	public Scene getSecondScene() {
		return secondScene;
	}

	public Scene getScene() {
		return firstScene;
	}

	public TextField getBALtxt() {
		return BALtxt;
	}

	public TextField getAKtxt() {
		return AKtxt;
	}

	public TextField getOAtxt() {
		return OAtxt;
	}

	public TextField getLMtxt() {
		return LMtxt;
	}

	public TextField getOLMtxt() {
		return OLMtxt;
	}

	public TextField getSKtxt() {
		return SKtxt;
	}

	public TextField getKFLtxt() {
		return KFLtxt;
	}

	public TextField getTTKtxt() {
		return TTKtxt;
	}

	public TextField getEKtxt() {
		return EKtxt;
	}

	public TextField getANtxt() {
		return ANtxt;
	}


	public TextField getSNtxt() {
		return SNtxt;
	}
	
	public DatePicker getDatePicker() {
		return datePicker;
	}

	public TextField getUDtxt() {
		return UDtxt;
	}


	public TextField getKOMtxt() {
		return KOMtxt;
	}

	public Button getSaveButton() {
		return saveButton;
	}

}