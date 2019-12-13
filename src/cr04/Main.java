// CodeReview 04

package cr04;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage)throws Exception{
        primaryStage.setTitle("::: Set action prices :::");

        ObservableList<Products> items = FXCollections.observableArrayList(
                new Products(
                        "Pfeffer", "1 Stk.",
                        "Schwarzer Pfeffer verleiht Ihren Speisen eine pikante Schärfe, besonders wenn er länger mitgekocht wird.",
                        3.49,2.79,
                        "Resources/pfeffer__600x600.jpg"),
                new Products(
                        "Schafmilchkäse", "200 gr",
                        "Hier gibt es keine Beschreibung, weil unsere Handelskette kennst sich nur bedingt damit aus, wie man eine Werbebeschreibung schreibt.",
                        3.59,1.99,
                        "Resources/cheese_salakis__600x600.jpg"),
                new Products(
                        "Vöslauer", "1.5 l",
                        "Spritziges Vöslauer Mineralwasser.",
                        0.75,0.49,
                        "Resources/voslauer__600x600.jpg"),
                new Products(
                        "Zucker", "500 gr",
                        "Natürliches Gelieren wird durch Apfelpektin unterstützt, welches im richtigen Verhältnis mit Zitronensäure und Kristallzucker abgemischt wurde.",
                        1.39,0.89,
                        "Resources/zucker__600x600.jpg")
        );

        ListView<Products> list = new ListView<>();
        list.setItems(items);

        // ::: TEXTFELDER, TEXTAREA (Desc)
        TextField prodTitleField =  new TextField("");
        prodTitleField.setEditable(false);
        prodTitleField.setDisable(false);
        Label prodTitelLabel = new Label("Name:        ");

        TextField prodQuantField = new TextField("");
        prodQuantField.setEditable(false);
        prodQuantField.setDisable(false);
        Label prodQuantLabel = new Label("Quantity:   ");

        TextArea prodDescField = new TextArea();
        prodDescField.setWrapText(true);
        prodDescField.setEditable(false);
        prodDescField.setDisable(false);

        TextField oldPrField = new TextField("");
        Label oldPrLabel = new Label("Old Price:   ");
        Label eur1 = new Label("EUR");

        TextField newPrField = new TextField("");
        newPrField.setStyle("-fx-text-inner-color: red;");
        Label newPrLabel = new Label("New Price: ");
        Label eur2 = new Label("EUR");

        // ::: BILDER
        ImageView imageView = new ImageView();
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        HBox imgBox = new HBox(imageView);
        imgBox.setPadding(new Insets(10,10,10,10));

        // ::: BUTTONS
        /*Button addItem = new Button("Add");*/
        Button updateItem = new Button("Update");
        Button clearFields = new Button("Clear");
        Button printReport = new Button("Report");
        Button deleteItem = new Button("Delete");

        // ::: LAYOUT
        HBox prodTitle = new HBox(prodTitelLabel,prodTitleField);
        prodTitle.setSpacing(5);
        prodTitle.setAlignment(Pos.TOP_LEFT);

        HBox prodDesc = new HBox(prodDescField);
        prodDesc.setSpacing(5);

        HBox prodQuant = new HBox(prodQuantLabel,prodQuantField);
        prodQuant.setSpacing(5);

        HBox oldPr = new HBox(oldPrLabel,oldPrField, eur1);
        oldPr.setSpacing(5);

        HBox newPr = new HBox(newPrLabel,newPrField,eur2);
        newPr.setSpacing(5);

        HBox buttons = new HBox(updateItem, clearFields, printReport, deleteItem); // addItem button
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(15);

        VBox mainProdBox = new VBox(prodTitle, prodQuant, oldPr,newPr, imgBox, prodDesc, buttons);
        mainProdBox.setSpacing(7);
        mainProdBox.setPrefWidth(300);

        VBox listBox = new VBox(list);
        listBox.setPrefWidth(300);

        HBox mainBox = new HBox(mainProdBox, listBox);
        mainBox.setSpacing(5);
        mainBox.setPadding(new Insets(1,2,5,5));

        Scene scene = new Scene(mainBox, 620,440);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

        // ::: CONTROL :::
        // ---> IMG View
        list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            String oldP = Double.toString(newValue.getoldPr());
            String newP = Double.toString(newValue.getnewPr());
            prodTitleField.setText(newValue.getprodTitle());
            prodQuantField.setText(newValue.getprodQuant());
            prodDescField.setText(newValue.getprodDesc());
            oldPrField.setText(oldP);
            newPrField.setText(newP);
            imageView.setImage(new Image(this.getClass().getResourceAsStream(newValue.getimgPath())));
        });

        // ---> Action e add
       /* addItem.setOnAction(ActionEvent -> {
            System.out.println("Add new clicked");
            int addIdx = list.getSelectionModel().getSelectedIndex();
            Double newAddP = Double.valueOf(newPrField.getText());
            if (addIdx != -1) {
                String prodTitleFieldText = prodTitleField.getText();
                String prodQuantFieldText = prodQuantField.getText();
                String prodDescFieldText = prodDescField.getText();
                String oldPrText = oldPrField.getText();
                String newPrText = newPrField.getText();
                list.getItems().get(addIdx).setnewPr(newAddP);
                list.refresh();
                add();
            }else {
                System.out.println("ERROR: User have to select a new item!");
            }
        });
*/
        // ---> Action e update
        updateItem.setOnAction(actionEvent ->  {
            System.out.println("User clicked on update button!");
            int selIdx = list.getSelectionModel().getSelectedIndex();
            Double newP = Double.valueOf(newPrField.getText());
            Double oldP = Double.valueOf(oldPrField.getText());
            if (selIdx != -1) {
                String prodTitleFieldText = prodTitleField.getText();
                String prodQuantFieldText = prodQuantField.getText();
                String prodDescFieldText = prodDescField.getText();
                String oldPrText = oldPrField.getText();
                String newPrText = newPrField.getText();
                list.getItems().get(selIdx).setoldPr(oldP);
                list.getItems().get(selIdx).setnewPr(newP);
                list.refresh();
                upd();
            }});

        // ---> Action e clear
        clearFields.setOnAction(actionEvent ->  {
            System.out.println("User clicked on clear button!");
            prodTitleField.setText("");
            prodQuantField.setText("");
            prodDescField.setText("");
            oldPrField.setText("");
            newPrField.setText("");
            imageView.setImage(new Image(this.getClass().getResourceAsStream("")));

        });

        // ---> Action e delete
        deleteItem.setOnAction(ActionEvent -> {
                    System.out.println("User clicked on delete button!");
                    int deleteIdx = list.getSelectionModel().getSelectedIndex();
                    if(deleteIdx != -1) {
                        list.getItems().remove(deleteIdx);
                        list.refresh();
                        delete();
        }});

        // ---> Action e reporting
        printReport.setOnAction(report -> {
            System.out.println("User clicked on report button!");
            try {
                writeReport(items);
                writeReport();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    // ::: write Report into a .txt file
    private void writeReport(ObservableList <Products> items) throws IOException {
        FileWriter fileWriter = new FileWriter("./src/report.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        items.forEach(data ->{
            printWriter.println(data.getprodTitle());
            printWriter.println(data.getprodQuant());
            printWriter.println(data.getprodDesc());
            printWriter.println("The old price was " + data.getoldPr() + "€");
            printWriter.println("The new price is " + data.getnewPr() + "€");
            printWriter.println("");
        });
        printWriter.close();
    }

    // ::: Alerts
    /*private void add(){
        Alert upd = new Alert(Alert.AlertType.INFORMATION);
        upd.setTitle("ADDING AN ITEM");
        upd.setHeaderText(null);
        upd.setContentText("Adding finished!");
        upd.showAndWait();
    }*/

    private void upd(){
        Alert upd = new Alert(Alert.AlertType.INFORMATION);
        upd.setTitle("UPDATE");
        upd.setHeaderText(null);
        upd.setContentText("Update finished!");
        upd.showAndWait();
    }

    private void writeReport(){
        Alert writeReport = new Alert(Alert.AlertType.INFORMATION);
        writeReport.setTitle("REPORTING SYSTEM");
        writeReport.setHeaderText("Report creation is done!");
        writeReport.setContentText("Please find it in the report.txt file!");
        writeReport.showAndWait();
    }

    private void delete(){
        Alert delete = new Alert(Alert.AlertType.WARNING);
        delete.setTitle("DELETE");
        delete.setHeaderText(null);
        delete.setContentText("The selected item has been deleted!");
        delete.showAndWait();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
