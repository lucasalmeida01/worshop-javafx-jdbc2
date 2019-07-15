package gui;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;
import model.entities.Seller;
import model.services.SellerService;

public class SellerListController implements Initializable{
	
	//dependence
	private SellerService service;
	
	@FXML
	private TableView<Seller> tableViewSeller;
	
	@FXML
	private TableColumn<Seller, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Seller, String> tableColumnName;

	@FXML
	private TableColumn<Seller, String> tableColumnEmail;
	
	@FXML
	private TableColumn<Seller, Date> tableColumnBirthDate;
	
	@FXML
	private TableColumn<Seller, Double> tableColumnBaseSalary;
	
	@FXML
	private TableColumn<Seller, Department> tableColumnDepartmentId;
	
	@FXML 
	private Button btNew;
	
	@FXML
	private ObservableList<Seller> obsList;
	
	@FXML
	public void onBtNewAction() {
		System.out.println("obBtNewAction");
	}
	
	//Inje��o de depend�ncie
	public void setSellerService(SellerService service) {
		this.service = service;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodes();
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewSeller.prefHeightProperty().bind(stage.heightProperty()); //a tableview preenche a tela	
	}
	
	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("Id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
		tableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
		tableColumnBaseSalary.setCellValueFactory(new PropertyValueFactory<>("BaseSalary"));
		initializeDepartmentIdColumn();
		initializeBirthDateColumn();
		
	}
	
	 public void updateTableView() {
	        if (service == null) {
	            throw new IllegalStateException("Service was null");
	        } else {
	            List<Seller> list = service.findAll();
	            obsList = FXCollections.observableArrayList(list);
	            tableViewSeller.setItems(obsList);
	        }
	    }
	 private void initializeDepartmentIdColumn() {
		 tableColumnDepartmentId.setCellValueFactory(new PropertyValueFactory<>("department"));
	     
		 tableColumnDepartmentId.setCellFactory(param -> new TableCell<>() {
	         @Override
	         protected void updateItem(Department department, boolean b) {
	             super.updateItem(department, b);
	             
	             if (department == null) {
	                 setText(null);
	             } else {
	                 setText(String.valueOf(department.getId()));
	             }
	         }
	     });
	 }
	 
	 private void initializeBirthDateColumn() {
		 tableColumnBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
	        
	        tableColumnBirthDate.setCellFactory(param -> new TableCell<>() {
	            private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	            
	            @Override
	            public void updateItem(Date date, boolean empty) {
	                if (date == null) {
	                    setText(null);
	                } else {
	                    setText(sdf.format(date));
	                }
	            }
	        });
	 }
	 
}


