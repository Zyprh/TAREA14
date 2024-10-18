package Controlador;

import Modelo.Empleado;
import DAO.Implement.EmpleadoDAO;
import VIEW.Frame1;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CrtlEmpleado implements ActionListener {

    private final Empleado modelo;
    private final EmpleadoDAO dao;
    private final Frame1 vista;
    private DefaultTableModel modeloTabla;

    public CrtlEmpleado(Empleado modelo, EmpleadoDAO dao, Frame1 vista) {
        this.modelo = modelo;
        this.dao = dao;
        this.vista = vista;

        // Carga de icono usando getResource
        ImageIcon iconAgregar = new ImageIcon(getClass().getResource("/img/agregar.png"));
        Image imgAgregar = iconAgregar.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Cambia 20, 20 por el tamaño que desees
        this.vista.btnAgregar.setIcon(new ImageIcon(imgAgregar));

        // Repite para los demás botones
        ImageIcon iconModificar = new ImageIcon(getClass().getResource("/img/perfil.png"));
        Image imgModificar = iconModificar.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        this.vista.btnModificar.setIcon(new ImageIcon(imgModificar));

        ImageIcon iconEliminar = new ImageIcon(getClass().getResource("/img/basura.png"));
        Image imgEliminar = iconEliminar.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        this.vista.btnEliminar.setIcon(new ImageIcon(imgEliminar));

        ImageIcon iconLimpiar = new ImageIcon(getClass().getResource("/img/limpiar.png"));
        Image imgLimpiar = iconLimpiar.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        this.vista.btnLimpiar.setIcon(new ImageIcon(imgLimpiar));

        ImageIcon iconBuscar = new ImageIcon(getClass().getResource("/img/lupa.png"));
        Image imgBuscar = iconBuscar.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        this.vista.btnBuscar.setIcon(new ImageIcon(imgBuscar));

        /*\*/
        // Asignamos los listeners a los botones
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);

        inicializarTabla();
        // Cargar los datos al inicializar
        cargarEmpleados();
    }

    private void inicializarTabla() {
        // Agregar las nuevas columnas "Tipo de Pago" y "Años de Experiencia"
        String[] columnas = {"ID", "Nombre", "Apellido", "N° Documento", "Sexo", "Cargo", "Departamento", "Salario",
            "F.Nacimiento", "F.Ingreso", "Estado", "T.Pago", "A. Experiencia"};

        // Inicializar el modelo de la tabla
        modeloTabla = new DefaultTableModel(columnas, 0);

        // Asignar el modelo de tabla a la tabla de la vista
        vista.Templeados.setModel(modeloTabla);
        modeloTabla.setRowCount(0);

        // Ocultar la columna "ID"
        vista.Templeados.getColumnModel().getColumn(0).setMinWidth(0);
        vista.Templeados.getColumnModel().getColumn(0).setMaxWidth(0);
        vista.Templeados.getColumnModel().getColumn(0).setWidth(0);
    }

// Método para cargar empleados en la tabla
    private void cargarEmpleados() {
        modeloTabla.setRowCount(0);
        List<Empleado> empleados = dao.listarEmpleados(); // Asegúrate de que este método está retornando correctamente la lista de empleados

        for (Empleado empleado : empleados) {
            modeloTabla.addRow(new Object[]{
                empleado.getId(),
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getNumDocumento(),
                empleado.getSexo(),
                empleado.getCargo(),
                empleado.getDepartamento(),
                empleado.getSalario(),
                empleado.getFechaNacimiento(),
                empleado.getFechaIngreso(),
                empleado.isEstado() ? "Activo" : "Inactivo", // Convertir estado boolean a texto
                empleado.getTipoPago(), // Añadir el campo tipo_pago
                empleado.getAnosExperiencia() // Añadir el campo años de experiencia
            });
        }
    }

    public void iniciar() {
        vista.setTitle("Gestión de Empleados");
        vista.setLocationRelativeTo(null);
        vista.txtId.setVisible(false);

        // Limpiar los ítems de los JComboBox antes de agregar nuevos
        vista.ComboTipoDocu.removeAllItems();
        vista.ComboSexo.removeAllItems();
        vista.ComboDepartamento.removeAllItems();

        // Agregar ítems a ComboTipoDocu
        vista.ComboTipoDocu.addItem("--SELECCIONE--");
        vista.ComboTipoDocu.addItem("DNI");
        vista.ComboTipoDocu.addItem("Pasaporte");
        vista.ComboTipoDocu.addItem("Carnet de Extranjería");

        // Agregar ítems a ComboSexo
        vista.ComboSexo.addItem("--SELECCIONE--");
        vista.ComboSexo.addItem("Masculino");
        vista.ComboSexo.addItem("Femenino");

        // Agregar ítems a ComboDepartamento
        vista.ComboDepartamento.addItem("--SELECCIONE--");
        vista.ComboDepartamento.addItem("Ventas");
        vista.ComboDepartamento.addItem("Recursos Humanos");
        vista.ComboDepartamento.addItem("IT");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //---------------------BOTON AGREGAR--------------------------
        if (e.getSource() == vista.btnAgregar) {
            modelo.setNombre(vista.txtNombre.getText());
            modelo.setApellido(vista.txtApellido.getText());
            modelo.setTipoDocumento(vista.ComboTipoDocu.getSelectedItem().toString());  // ComboBox
            modelo.setNumDocumento(vista.txtNumDocu.getText());
            modelo.setDireccion(vista.txtDireccion.getText());
            modelo.setTelefono(vista.txtTelefono.getText());
            modelo.setCorreo(vista.txtCorreo.getText());
            modelo.setSexo(vista.ComboSexo.getSelectedItem().toString()); // ComboBox
            modelo.setCargo(vista.txtCargo.getText());
            modelo.setDepartamento(vista.ComboDepartamento.getSelectedItem().toString()); // ComboBox

            // Obtener y establecer el salario
            try {
                String salarioStr = vista.txtSalario.getText();
                BigDecimal salario = new BigDecimal(salarioStr); // Convertir a BigDecimal
                modelo.setSalario(salario);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "El salario ingresado no es válido. Por favor, ingresa un número válido.");
                return; // Detener la ejecución si el salario no es válido
            }

            // Obtener fechas de los JDateChooser
            java.util.Date fechaNacimiento = vista.dcFechaNacimiento.getDate();
            java.util.Date fechaIngreso = vista.dcFechaIngreso.getDate();

            // Verificar que las fechas no sean nulas
            if (fechaNacimiento != null) {
                modelo.setFechaNacimiento(new java.sql.Date(fechaNacimiento.getTime())); // Establecer fecha de nacimiento
            } else {
                JOptionPane.showMessageDialog(null, "Por favor selecciona una fecha de nacimiento.");
                return; // Detener ejecución si no hay fecha
            }

            if (fechaIngreso != null) {
                modelo.setFechaIngreso(new java.sql.Date(fechaIngreso.getTime())); // Establecer fecha de ingreso
            } else {
                JOptionPane.showMessageDialog(null, "Por favor selecciona una fecha de ingreso.");
                return; // Detener ejecución si no hay fecha
            }

            // Estado Civil (RadioButtons)
            if (vista.rbCasado.isSelected()) {
                modelo.setEstadoCivil("Casado");
            } else if (vista.rbSoltero.isSelected()) {
                modelo.setEstadoCivil("Soltero");
            }

            // METODO DE PAGO  (RadioButtons)
            if (vista.rbEfectivo.isSelected()) {
                modelo.setTipoPago("Efectivo");
            } else if (vista.rbTransferencia.isSelected()) {
                modelo.setTipoPago("Transferencia");
            }

            // Estado (Activo o Inactivo)
            modelo.setEstado(vista.chkActivo.isSelected()); // Solo una línea
            // Obtener años de experiencia del JSpinner
            modelo.setAnosExperiencia((Integer) vista.SExperiencia.getValue());

            String correo = vista.txtCorreo.getText().trim(); // Obtener el correo
            if (!dao.validarCorreo(correo)) {
                JOptionPane.showMessageDialog(vista, "El correo debe tener al menos un carácter antes de '@' y terminar en @gmail.com o @hotmail.com", "Error de Correo", JOptionPane.ERROR_MESSAGE);
                return; // Salir si el correo no es válido
            }

            // Crear empleado en la base de datos
            if (dao.crearEmpleado(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar");
            }

            // Cargar empleados en la tabla
            cargarEmpleados();
        }
        //---------------------BOTON MODIFICAR--------------------------
        if (e.getSource() == vista.btnModificar) {
            String idEmpleado = vista.txtId.getText();
            if (idEmpleado.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El ID del empleado no puede estar vacío.");
                return; // Detiene la ejecución
            }

            modelo.setId(idEmpleado);
            modelo.setNombre(vista.txtNombre.getText());
            modelo.setApellido(vista.txtApellido.getText());
            modelo.setTipoDocumento(vista.ComboTipoDocu.getSelectedItem().toString());
            modelo.setNumDocumento(vista.txtNumDocu.getText());
            modelo.setDireccion(vista.txtDireccion.getText());
            modelo.setTelefono(vista.txtTelefono.getText());
            modelo.setCorreo(vista.txtCorreo.getText());
            modelo.setSexo(vista.ComboSexo.getSelectedItem().toString());
            modelo.setCargo(vista.txtCargo.getText());
            modelo.setDepartamento(vista.ComboDepartamento.getSelectedItem().toString());

            // Obtener y validar el salario
            String salarioStr = vista.txtSalario.getText();
            if (salarioStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El salario no puede estar vacío.");
                return; // Detiene la ejecución
            }

            try {
                BigDecimal salario = new BigDecimal(salarioStr);
                modelo.setSalario(salario);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "El salario ingresado no es válido. Por favor, ingresa un número válido.");
                return; // Detiene la ejecución
            }

            // Establecer fechas
            java.util.Date fechaNacimiento = vista.dcFechaNacimiento.getDate();
            java.util.Date fechaIngreso = vista.dcFechaIngreso.getDate();

            if (fechaNacimiento != null) {
                modelo.setFechaNacimiento(new java.sql.Date(fechaNacimiento.getTime()));
            }
            if (fechaIngreso != null) {
                modelo.setFechaIngreso(new java.sql.Date(fechaIngreso.getTime()));
            }

            // Establecer estado civil
            if (vista.rbCasado.isSelected()) {
                modelo.setEstadoCivil("Casado");
            } else {
                modelo.setEstadoCivil("Soltero");
            }

            // Establecer estado
            modelo.setEstado(vista.chkActivo.isSelected());

            // Obtener tipo de pago desde los RadioButtons
            if (vista.rbEfectivo.isSelected()) {
                modelo.setTipoPago("Efectivo");
            } else if (vista.rbTransferencia.isSelected()) {
                modelo.setTipoPago("Transferencia");
            }

            // Obtener años de experiencia desde el JSpinner
            modelo.setAnosExperiencia((Integer) vista.SExperiencia.getValue());  // JSpinner para años de experiencia

            String correo = vista.txtCorreo.getText().trim(); // Obtener el correo
            if (!dao.validarCorreo(correo)) {
                JOptionPane.showMessageDialog(vista, "El correo debe tener al menos un carácter antes de '@' y terminar en @gmail.com o @hotmail.com", "Error de Correo", JOptionPane.ERROR_MESSAGE);
                return; // Salir si el correo no es válido
            }

            // Actualizar el empleado en la base de datos
            if (dao.actualizarEmpleado(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro Modificado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Modificar");
            }

            cargarEmpleados(); // Refrescar la tabla
        }

        //---------------------BOTON ELIMINAR--------------------------
        if (e.getSource() == vista.btnEliminar) {
            String numeroDocumento = vista.txtNumDocu.getText();  // Eliminar por numero_documento

            // Confirmación de eliminación
            int confirmacion = JOptionPane.showConfirmDialog(null,
                    "¿Está seguro de que desea eliminar este registro?",
                    "Confirmar Eliminación",
                    JOptionPane.YES_NO_OPTION);

            // Si el usuario selecciona "Sí"
            if (confirmacion == JOptionPane.YES_OPTION) {
                if (dao.eliminarEmpleado(numeroDocumento)) {
                    JOptionPane.showMessageDialog(null, "Registro Eliminado");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Eliminar");
                }
            } else {
                JOptionPane.showMessageDialog(null, "El registro no ha sido eliminado."); // Mensaje de cancelación
            }

            // Cargar empleados en la tabla
            cargarEmpleados();
        }

        //---------------------BOTON BUSCAR--------------------------
        if (e.getSource() == vista.btnBuscar) {
            String numeroDocumento = vista.txtBuscar.getText().trim();  // Buscar por numero_documento
            if (numeroDocumento.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El documento no puede estar vacío.");
                return; // Detiene la ejecución
            }

            modelo.setNumDocumento(numeroDocumento);
            if (dao.buscar(modelo)) {
                vista.txtId.setText(String.valueOf(modelo.getId()));
                vista.txtNombre.setText(modelo.getNombre());
                vista.txtApellido.setText(modelo.getApellido());
                vista.ComboTipoDocu.setSelectedItem(modelo.getTipoDocumento());  // Seleccionamos el valor en ComboBox
                vista.txtNumDocu.setText(modelo.getNumDocumento());
                vista.txtDireccion.setText(modelo.getDireccion());
                vista.txtTelefono.setText(modelo.getTelefono());
                vista.txtCorreo.setText(modelo.getCorreo());
                vista.ComboSexo.setSelectedItem(modelo.getSexo());  // Seleccionamos el valor en ComboBox
                vista.txtCargo.setText(modelo.getCargo());
                vista.ComboDepartamento.setSelectedItem(modelo.getDepartamento());  // Seleccionamos el valor en ComboBox
                vista.txtSalario.setText(modelo.getSalario().toString());

                // Años de experiencia (suponiendo que tienes un método getAñosExperiencia en el modelo)
                vista.SExperiencia.setValue(modelo.getAnosExperiencia());

                // Establecer tipo de pago (radio buttons)
                if ("Efectivo".equals(modelo.getTipoPago())) {
                    vista.rbEfectivo.setSelected(true);
                } else if ("Transferencia".equals(modelo.getTipoPago())) {
                    vista.rbTransferencia.setSelected(true);
                }

                // Establecer las fechas en los JDateChooser
                if (modelo.getFechaNacimiento() != null) {
                    vista.dcFechaNacimiento.setDate(modelo.getFechaNacimiento());
                }
                if (modelo.getFechaIngreso() != null) {
                    vista.dcFechaIngreso.setDate(modelo.getFechaIngreso());
                }

                // Estado Civil
                if ("Casado".equals(modelo.getEstadoCivil())) {
                    vista.rbCasado.setSelected(true);
                } else if ("Soltero".equals(modelo.getEstadoCivil())) {
                    vista.rbSoltero.setSelected(true);
                }

                // Estado (Activo o Inactivo)
                vista.chkActivo.setSelected(modelo.isEstado());
                vista.chkInactivo.setSelected(!modelo.isEstado());
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró registro");
                limpiar();
            }
        }
        //---------------------BOTON LIMPIAR--------------------------
        if (e.getSource() == vista.btnLimpiar) {
            limpiar();
        }
    }

    // Método para limpiar los campos de la vista
    public void limpiar() {
        vista.txtNombre.setText(null);
        vista.txtApellido.setText(null);
        vista.ComboTipoDocu.setSelectedIndex(0);
        vista.txtNumDocu.setText(null);
        vista.txtDireccion.setText(null);
        vista.txtTelefono.setText(null);
        vista.txtCorreo.setText(null);
        vista.ComboSexo.setSelectedIndex(0);
        vista.txtCargo.setText(null);
        vista.ComboDepartamento.setSelectedIndex(0);
        vista.txtSalario.setText(null);
        vista.dcFechaNacimiento.setDate(null); // Limpiar JDateChooser
        vista.dcFechaIngreso.setDate(null); // Limpiar JDateChooser
        vista.txtId.setText(null);
        vista.txtBuscar.setText(null);

        vista.estadoCivilGroup.clearSelection(); // Limpia el ButtonGroup de estado civil
        vista.tipoPagoGroup.clearSelection();    // Limpia el ButtonGroup de tipo de pago

        // Limpiar los CheckBoxes
        vista.chkActivo.setSelected(false);
        vista.chkInactivo.setSelected(false);

        // Limpiar el Spinner
        vista.SExperiencia.setValue(0);
    }

}
