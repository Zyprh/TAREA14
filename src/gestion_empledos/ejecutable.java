package gestion_empledos;

import Controlador.CrtlEmpleado;
import Modelo.Empleado;
import DAO.EmpleadoDAOImpl;
import VIEW.Frame1;
import javax.swing.SwingUtilities;

public class ejecutable {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            Empleado modelo = new Empleado();
            EmpleadoDAOImpl dao = new EmpleadoDAOImpl();
            Frame1 vista = new Frame1();

            CrtlEmpleado controlador = new CrtlEmpleado(modelo, dao, vista);

            controlador.iniciar();

            // Mostrar la vista
            vista.setVisible(true);

        });
    }

}
