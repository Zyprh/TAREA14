
package DAO.Implement;

import Modelo.Empleado;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JTextField;

public interface EmpleadoDAO {

    public boolean crearEmpleado(Empleado empleado);

    public ArrayList<Empleado> listarEmpleados();

    public boolean actualizarEmpleado(Empleado modelo);

    public boolean eliminarEmpleado(String numeroDocumento);

    public boolean buscar(Empleado empleado);

    public void soloNcarc(int numcarac, JTextField tf, KeyEvent e);

    public void soloTodosNum(KeyEvent e);
    
    Empleado obtenerEmpleadoPorId(String idEmpleado);
    
    public void soloLetrasYEspacios(KeyEvent e);
    
    public boolean validarCorreo(String correo);

}
