package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Ellipse;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    public ComboBox orbitedObject;
    public TextField apoapsis;
    public TextField periapsis;
    public Ellipse orbit;
    public Label center;
    public Button apply;

    public List objects = new ArrayList<String>();
    public List masses = new ArrayList<String>();
    public List radii = new ArrayList<String>();

    public void initialize(){
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader("Objects"));
            String line = reader.readLine();
            objects.add(line.split(",")[0]);
            masses.add(line.split(",")[1]);
            radii.add(line.split(",")[2]);

            while (line != null) {
                line = reader.readLine();
                try{
                    objects.add(line.split(",")[0]);
                } catch (java.lang.NullPointerException e) {

                }
                try{
                    masses.add(line.split(",")[1]);
                } catch (java.lang.ArrayIndexOutOfBoundsException e) {

                } catch (java.lang.NullPointerException e) {

                }
                try{
                    radii.add(line.split(",")[2]);
                } catch (java.lang.NullPointerException e) {

                } catch (java.lang.ArrayIndexOutOfBoundsException e) {

                }
            }

            //objects.remove(objects.size()-1);

        } catch (IOException e) {
            e.printStackTrace();
        }
        orbitedObject.getItems().addAll(objects);
    }

    public void applyChanges(ActionEvent actionEvent) {
        String objectName = orbitedObject.getValue().toString();
        String mass = getMass(objectName);
        System.out.println(mass);
        String Apoapsis = apoapsis.getText();
        String Periapsis = periapsis.getText();
        String Radius = getRadius(objectName);
        //orbit.setRadiusY((Double.parseDouble(Apoapsis) + Double.parseDouble(Periapsis) + Double.parseDouble(Radius)) / 40);

        center.setText(objectName);
    }

    public String getMass(String objectName){
        BufferedReader reader;
        int x = 0;

        while (objects.get(x) != objectName){
            x++;
        }

        return masses.get(x).toString();
    }

    public String getRadius(String objectName){
        BufferedReader reader;
        int x = 0;

        while (objects.get(x) != objectName){
            x++;
        }

        return radii.get(x).toString();
    }

    public void newName(ActionEvent actionEvent) {
        center.setText(orbitedObject.getValue().toString());
    }
}
