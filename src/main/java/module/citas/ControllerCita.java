package module.citas;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import jfxtras.scene.control.agenda.Agenda;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ControllerCita implements Initializable {
    @FXML
    AnchorPane otro_anchorpane;
    @FXML
    TextArea text_area_nueva_cita;
    @FXML
    Button boton_guardar_cita;
    @FXML
    TextField text_paciente;
    @FXML
    ComboBox<String> combox_medico;
    @FXML
    TextField text_mascota;
    @FXML
    TextField text_hora;
    @FXML
    TextField text_minuto;
    @FXML
    TextField text_hora_f;
    @FXML
    TextField text_minuto_f;
    @FXML
    DatePicker fecha_picker;
    @FXML
    DatePicker fecha_picker_f;
    Agenda agenda = new Agenda();
    @FXML
    public void citaAdd(){ // guardar todas las variables en BD !!! por el momento solo lo escribe en text area
        try {

            String paciente,mascota;
            int hora,horaF,minuto,minutoF;

            LocalDate fechaCita;
            LocalDate fechaCitaF;

            paciente = text_paciente.getText().toString();
            mascota = text_mascota.getText().toString();

            hora = Integer.valueOf(text_hora.getText().toString());
            minuto = Integer.valueOf(text_minuto.getText().toString());
            horaF = Integer.valueOf(text_hora_f.getText().toString());
            minutoF = Integer.valueOf(text_minuto_f.getText().toString());

            fechaCita = fecha_picker.getValue();
            fechaCitaF = fecha_picker_f.getValue();

            text_area_nueva_cita.setVisible(true);
            text_area_nueva_cita.setText("Paciente: "+paciente+"\n"+
                    "Fecha: "+ fechaCita.toString()); // te devuelve la string, y hay que tratarla

            crearCita(paciente,fechaCita,hora,minuto,fechaCitaF,horaF,minutoF);

        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Recuerde completar datos correctamente...\n" +e.toString());
            alert.showAndWait();
        }

    }
    @FXML
    public void reAgendar(){ // solo imprime datos una vez que se re agenda, esto imprime los nuevos datos
        try {
            for (Agenda.Appointment a: agenda.appointments()) {
                System.out.println("Summary: "+a.getSummary().toString());
                System.out.println("Start date: "+a.getStartLocalDateTime());
                System.out.println("End date: "+a.getEndLocalDateTime());
                System.out.println("Start time H: "+a.getStartLocalDateTime().getHour());
                System.out.println("Start time M: "+a.getStartLocalDateTime().getMinute());
                System.out.println("End time H: "+a.getEndLocalDateTime().getHour());
                System.out.println("End time M: "+a.getEndLocalDateTime().getMinute());
                System.out.println("=======================================");
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText(e.toString());
            alert.showAndWait();
        }
        //text_area_nueva_cita.setText("");
    }
    public void crearCita(String paciente, LocalDate fechaCita, int hora, int minuto, LocalDate fechaCitaF, int horaF, int minutoF){

        Agenda.Appointment appointment = new Agenda.AppointmentImplLocal();
        appointment.setStartLocalDateTime(LocalDate.of(fechaCita.getYear(),fechaCita.getMonth(),fechaCita.getDayOfMonth()).atTime(hora,minuto));
        appointment.setEndLocalDateTime(LocalDate.of(fechaCitaF.getYear(),fechaCitaF.getMonth(),fechaCitaF.getDayOfMonth()).atTime(horaF,minutoF));
        appointment.setSummary(paciente);
        agenda.appointments().add(appointment);
        agenda.getAllowResize();
        otro_anchorpane.getChildren().setAll(agenda);
        //  AQUI ACTUALIZAR LA BASE DE DATOS
        text_paciente.setText("");
        text_mascota.setText("");
        text_hora.setText("");
        text_minuto.setText("");
        text_hora_f.setText("");
        text_minuto_f.setText("");
        fecha_picker.getEditor().clear();
        fecha_picker_f.getEditor().clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        combox_medico.setItems(FXCollections.observableArrayList( //esto se va llenar con el query de doctores y su id
                "Many Rivera, 125889",
                "Doctor House, 8974405",
                "Doctor Who, 4538400",
                "Doctor Goku, 0000000"
        ));
    }
}


/* *
* http://jfxtras.org/doc/8.0/jfxtras-agenda/jfxtras/scene/control/agenda/Agenda.html
* */
 /*agenda.appointments().addAll(
                new Agenda.AppointmentImplLocal()
                        .withStartLocalDateTime(LocalDate.now().atTime(4, 00))
                        .withEndLocalDateTime(LocalDate.now().atTime(15, 30))
                        .withDescription("It's time")
                        .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1")) // you should use a map of AppointmentGroups
        );*/

        /*agenda.newAppointmentCallbackProperty().set( (localDateTimeRange) -> { // con el muose puede mover
            return new Agenda.AppointmentImplLocal()
                    .withStartLocalDateTime(localDateTimeRange.getStartLocalDateTime())
                    .withEndLocalDateTime(localDateTimeRange.getEndLocalDateTime())
                    .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1")); // it is better to have a map of appointment groups to get from
        });*/