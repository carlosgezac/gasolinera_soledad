package com.gasolinerasoledadsacv.view.empleados;

import com.gasolinerasoledadsacv.view.validation.LimitadorCaracteres;
import com.gasolinerasoledadsacv.controller.EmpleadoJpaController;
import com.gasolinerasoledadsacv.entity.Contacto;
import com.gasolinerasoledadsacv.entity.Direccion;
import com.gasolinerasoledadsacv.entity.Empleado;
import com.gasolinerasoledadsacv.enums.PersistenceEnum;
import com.gasolinerasoledadsacv.enums.vo;
import com.gasolinerasoledadsacv.util.DateUtil;
import com.gasolinerasoledadsacv.util.PathUtil;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class NuevoEmpleadoFrame extends javax.swing.JFrame {

    final JFileChooser fileChooser = new JFileChooser();
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PersistenceEnum.PERSISTENCE_UNIT_NAME.getValue());
    private EmpleadoJpaController empleadoJpaController;
    private Empleado empleado = new Empleado();
    private Empleado oldEmpleado = new Empleado();
    private BufferedImage foto;
    private boolean cambioFoto = false;
    private ListadoEmpleadosView listadoEmpleadosView;

    /**
     * Creates new form EmpleadoFrame
     *
     * @param listadoEmpleadosView
     */
    public NuevoEmpleadoFrame(ListadoEmpleadosView listadoEmpleadosView) {
        this.llamarMetodosGenerales(listadoEmpleadosView);
    }

    public NuevoEmpleadoFrame(String numEmpleado, boolean editar, ListadoEmpleadosView listadoEmpleadosView) {
        this.llamarMetodosGenerales(listadoEmpleadosView);
        if (editar) {
            this.llenarCampos(numEmpleado);
        }
    }

    private void llamarMetodosGenerales(ListadoEmpleadosView listadoEmpleadosView) {
        this.listadoEmpleadosView = listadoEmpleadosView;
        FileFilter imageFilter = new FileNameExtensionFilter(
                "*.jpg, *.png)", "jpg", "jpeg", "png");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(imageFilter);
        initComponents();
        this.definirCampos();
        this.llenarComboBox();
    }

    private void llenarComboBox() {
        for (String estatus : vo.estatus) {
            jComboBoxEstatus.addItem(estatus);
        }
        for (String genero : vo.generos) {
            jComboBoxGenero.addItem(genero);
        }
        for (String escolaridad : vo.escolaridades) {
            jComboBoxEscolaridad.addItem(escolaridad);
        }
        for (String estado : vo.estados) {
            jComboBoxEstado.addItem(estado);
        }
        jComboBoxEstado.setSelectedIndex(31);
    }

    private void definirCampos() {
        txtFieldNoEmpleado.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) {
                if (!Pattern.matches("[a-zA-Z_0-9]*|-", str) || (getLength() + str.length()) > 10) {
                    java.awt.Toolkit.getDefaultToolkit().beep();
                    return;
                }
                try {
                    super.insertString(offs, str, a);
                } catch (BadLocationException ex) {
                }
            }
        });
        txtFieldNombres.setDocument(new LimitadorCaracteres(50));
        txtFieldApellidoP.setDocument(new LimitadorCaracteres(50));
        txtFieldApellidoM.setDocument(new LimitadorCaracteres(50));
        txtFieldPuesto.setDocument(new LimitadorCaracteres(50));
        txtFieldCurp.setDocument(new LimitadorCaracteres(18));
        txtFieldRfc.setDocument(new LimitadorCaracteres(13));
        txtFieldNss.setDocument(new LimitadorCaracteres(45));
        txtFielEnfermedad.setDocument(new LimitadorCaracteres(50));
        textAreaIncidentes.setDocument(new LimitadorCaracteres(200));
        textAreaCursos.setDocument(new LimitadorCaracteres(200));
        txtAreaDatosAdicionales.setDocument(new LimitadorCaracteres(200));
        //Direccion
        txtFieldCalle.setDocument(new LimitadorCaracteres(50));
        txtFieldNumInt.setDocument(new LimitadorCaracteres(10));
        txtFieldNumExt.setDocument(new LimitadorCaracteres(10));
        txtFieldColonia.setDocument(new LimitadorCaracteres(50));
        txtFieldMunicipio.setDocument(new LimitadorCaracteres(50));
        //Contacto
        txtFieldTelCasa.setDocument(new LimitadorCaracteres(20));
        txtFieldCel.setDocument(new LimitadorCaracteres(20));
        txtFieldCorreo.setDocument(new LimitadorCaracteres(255));
    }

    private void llenarCampos(String numEmpleado) {
        empleadoJpaController = new EmpleadoJpaController(emf);
        empleado = empleadoJpaController.findByNumeroEmpleado(numEmpleado);
        try {
            oldEmpleado = (Empleado) empleado.clone();
        } catch (CloneNotSupportedException ex) {
        }
        txtFieldNoEmpleado.setText(empleado.getNumeroEmpleado());
        txtFieldNombres.setText(empleado.getNombre());
        txtFieldApellidoP.setText(empleado.getApellidoPaterno());
        txtFieldApellidoM.setText(empleado.getApellidoMaterno());
        txtFieldPuesto.setText(empleado.getPuesto());
        jComboBoxEscolaridad.setSelectedItem(empleado.getEscolaridad());
        txtFieldCurp.setText(empleado.getCurp());
        jComboBoxEstatus.setSelectedItem(empleado.getEstatus());
        jdcFechaNacimiento.setCalendar(DateUtil.toCalendar(empleado.getFechaNacimiento()));
        jComboBoxGenero.setSelectedItem(empleado.getGenero());
        jdcFechaIngreso.setCalendar(DateUtil.toCalendar(empleado.getFechaIngreso()));
        txtFieldRfc.setText(empleado.getRfc());
        txtFieldNss.setText(empleado.getNssImss());
        txtFielEnfermedad.setText(empleado.getEnfermedad());
        textAreaIncidentes.setText(empleado.getIncidentesLaborales());
        textAreaCursos.setText(empleado.getCursosCapacitacion());
        txtAreaDatosAdicionales.setText(empleado.getObservaciones());
        try {
            String dir = PathUtil.getImagesPath() + empleado.getRutaFoto();
            this.foto = ImageIO.read(new File(dir));
            jPanelFoto.repaintImage(this.foto);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo cargar la fotografía.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        //Direccion
        txtFieldCalle.setText(empleado.getDireccion().getCalle());
        txtFieldNumInt.setText(empleado.getDireccion().getNumeroInterior());
        txtFieldNumExt.setText(empleado.getDireccion().getNumeroExterior());
        txtFieldColonia.setText(empleado.getDireccion().getColonia());
        txtFieldMunicipio.setText(empleado.getDireccion().getMunicipio());
        jComboBoxEstado.setSelectedItem(empleado.getDireccion().getEstado());
        //Contacto
        txtFieldTelCasa.setText(empleado.getContacto().getTelefonoCasa());
        txtFieldCel.setText(empleado.getContacto().getCelular());
        txtFieldCorreo.setText(empleado.getContacto().getEmail());
    }

    private boolean verificarCampos() {
        boolean correcto = true;
        //Border error
        Border errorBorder = BorderFactory.createLineBorder(Color.RED, 2);
        //Obtener borde por default
        Border defaultBorder = new JTextField().getBorder();
        if (txtFieldNoEmpleado.getText().isEmpty()) {
            txtFieldNoEmpleado.setBorder(errorBorder);
            correcto = false;
        } else {
            txtFieldNoEmpleado.setBorder(defaultBorder);
        }
        if (txtFieldNombres.getText().isEmpty()) {
            txtFieldNombres.setBorder(errorBorder);
            correcto = false;
        } else {
            txtFieldNombres.setBorder(defaultBorder);
        }
        if (txtFieldApellidoP.getText().isEmpty()) {
            correcto = false;
            txtFieldApellidoP.setBorder(errorBorder);
        } else {
            txtFieldApellidoP.setBorder(defaultBorder);
        }
        if (txtFieldApellidoM.getText().isEmpty()) {
            correcto = false;
            txtFieldApellidoM.setBorder(errorBorder);
        } else {
            txtFieldApellidoM.setBorder(defaultBorder);
        }
        if (txtFieldPuesto.getText().isEmpty()) {
            correcto = false;
            txtFieldPuesto.setBorder(errorBorder);
        } else {
            txtFieldPuesto.setBorder(defaultBorder);
        }
        if (jdcFechaIngreso.getDate() == null) {
            correcto = false;
            jdcFechaIngreso.setBorder(errorBorder);
        } else {
            jdcFechaIngreso.setBorder(defaultBorder);
        }
        if (jdcFechaNacimiento.getDate() == null) {
            correcto = false;
            jdcFechaNacimiento.setBorder(errorBorder);
        } else {
            jdcFechaNacimiento.setBorder(defaultBorder);
        }
        if (txtFieldCurp.getText().isEmpty()) {
            correcto = false;
            txtFieldCurp.setBorder(errorBorder);
        } else {
            txtFieldCurp.setBorder(defaultBorder);
        }
        if (txtFieldRfc.getText().isEmpty()) {
            correcto = false;
            txtFieldRfc.setBorder(errorBorder);
        } else {
            txtFieldRfc.setBorder(defaultBorder);
        }
        if (txtFieldNss.getText().isEmpty()) {
            correcto = false;
            txtFieldNss.setBorder(errorBorder);
        } else {
            txtFieldNss.setBorder(defaultBorder);
        }
        //Direccion
        if (txtFieldCalle.getText().isEmpty()) {
            correcto = false;
            txtFieldCalle.setBorder(errorBorder);
        } else {
            txtFieldCalle.setBorder(defaultBorder);
        }
        if (txtFieldNumExt.getText().isEmpty()) {
            correcto = false;
            txtFieldNumExt.setBorder(errorBorder);
        } else {
            txtFieldNumExt.setBorder(defaultBorder);
        }
        if (txtFieldColonia.getText().isEmpty()) {
            correcto = false;
            txtFieldColonia.setBorder(errorBorder);
        } else {
            txtFieldColonia.setBorder(defaultBorder);
        }
        if (txtFieldMunicipio.getText().isEmpty()) {
            correcto = false;
            txtFieldMunicipio.setBorder(errorBorder);
        } else {
            txtFieldMunicipio.setBorder(defaultBorder);
        }
        if (txtFieldMunicipio.getText().isEmpty()) {
            correcto = false;
            txtFieldMunicipio.setBorder(errorBorder);
        } else {
            txtFieldMunicipio.setBorder(defaultBorder);
        }
        //Contacto
        if (txtFieldCel.getText().isEmpty()) {
            correcto = false;
            txtFieldCel.setBorder(errorBorder);
        } else {
            txtFieldCel.setBorder(defaultBorder);
        }
        //foto
        if (foto == null) {
            correcto = false;
            jPanelFoto.setBorder(errorBorder);
        } else {
            jPanelFoto.setBorder(null);
        }
        return correcto;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jButtonGuardar = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaDatosAdicionales = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabDatosGen = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txtFieldNombres = new javax.swing.JTextField();
        txtFieldApellidoP = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtFieldApellidoM = new javax.swing.JTextField();
        txtFieldCurp = new javax.swing.JTextField();
        butonBuscarFoto = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtFieldCalle = new javax.swing.JTextField();
        txtFieldNumInt = new javax.swing.JTextField();
        txtFieldNumExt = new javax.swing.JTextField();
        txtFieldColonia = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtFieldMunicipio = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jComboBoxEstado = new javax.swing.JComboBox<>();
        jPanelFoto = new com.gasolinerasoledadsacv.view.empleados.JPanelFoto();
        jComboBoxEscolaridad = new javax.swing.JComboBox<>();
        txtFieldNoEmpleado = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jComboBoxGenero = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtFieldTelCasa = new javax.swing.JTextField();
        txtFieldCel = new javax.swing.JTextField();
        txtFieldCorreo = new javax.swing.JTextField();
        jComboBoxEstatus = new javax.swing.JComboBox<>();
        jdcFechaNacimiento = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jdcFechaIngreso = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        txtFieldPuesto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtFieldRfc = new javax.swing.JTextField();
        txtFieldNss = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtFielEnfermedad = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaIncidentes = new javax.swing.JTextArea();
        labelIncidentes = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        textAreaCursos = new javax.swing.JTextArea();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Empleado ");
        setLocation(new java.awt.Point(0, 0));

        jButtonGuardar.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButtonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/guardar.png"))); // NOI18N
        jButtonGuardar.setText("Guardar");
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel21.setText("Datos Adicionales");

        txtAreaDatosAdicionales.setColumns(20);
        txtAreaDatosAdicionales.setRows(5);
        txtAreaDatosAdicionales.setMaximumSize(new java.awt.Dimension(164, 94));
        txtAreaDatosAdicionales.setMinimumSize(new java.awt.Dimension(164, 94));
        txtAreaDatosAdicionales.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAreaDatosAdicionalesKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(txtAreaDatosAdicionales);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setLabelFor(txtFieldNoEmpleado);
        jLabel1.setText("No. Empleado");
        jLabel1.setToolTipText("");
        jLabel1.setAlignmentY(0.0F);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setText("Nombres");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setText("Apellido Paterno");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setText("Apellido Materno");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel5.setText("CURP");

        butonBuscarFoto.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        butonBuscarFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/buscarFoto.png"))); // NOI18N
        butonBuscarFoto.setText("Buscar Fotografía");
        butonBuscarFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butonBuscarFotoActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dirección", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N
        jPanel2.setToolTipText("");
        jPanel2.setName(""); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel15.setText("Calle");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel17.setText("Num. Exterior");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel16.setText("Num. Interior");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel18.setText("Colonia");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel19.setText("Municipio");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel20.setText("Estado");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel18)
                    .addComponent(txtFieldColonia, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addComponent(jLabel15)
                    .addComponent(txtFieldCalle))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(txtFieldNumInt, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(txtFieldNumExt, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFieldMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))))
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFieldCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFieldColonia, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFieldNumExt, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFieldNumInt, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFieldMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanelFoto.setBackground(new java.awt.Color(255, 255, 255));
        jPanelFoto.setBorder(null
        );

        javax.swing.GroupLayout jPanelFotoLayout = new javax.swing.GroupLayout(jPanelFoto);
        jPanelFoto.setLayout(jPanelFotoLayout);
        jPanelFotoLayout.setHorizontalGroup(
            jPanelFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 324, Short.MAX_VALUE)
        );
        jPanelFotoLayout.setVerticalGroup(
            jPanelFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 262, Short.MAX_VALUE)
        );

        txtFieldNoEmpleado.setAlignmentY(0);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel7.setText("Escolaridad");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel11.setText("Estatus");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel12.setText("Fecha de Nacimiento");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel13.setText("Genero");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contacto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel22.setText("Telefono Casa");
        jLabel22.setToolTipText("");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel23.setText("Celular");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel24.setText("Correo Electrónico");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(txtFieldTelCasa, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFieldCel, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(txtFieldCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFieldTelCasa, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFieldCel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFieldCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jdcFechaNacimiento.setDateFormatString("dd/MM/yyyy");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jComboBoxEscolaridad, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(txtFieldNoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtFieldApellidoP, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtFieldApellidoM, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtFieldNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(65, 65, 65)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel11)
                                    .addComponent(jComboBoxEstatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel12)
                                    .addComponent(jdcFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBoxGenero, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtFieldCurp, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(60, 60, 60)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(butonBuscarFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelFoto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFieldNoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFieldNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFieldApellidoP, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFieldApellidoM, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxEscolaridad, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFieldCurp, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(134, 134, 134))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(butonBuscarFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabDatosGen.addTab("Datos Generales", jPanel1);

        jdcFechaIngreso.setDateFormatString("dd/MM/yyyy");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel10.setText("Fecha de Ingreso");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel8.setText("Cargo/Puesto");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setText("RFC");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel9.setText("NSS ");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel14.setText("Enfermedad/Padecimiento");

        textAreaIncidentes.setColumns(20);
        textAreaIncidentes.setRows(5);
        textAreaIncidentes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textAreaIncidentesKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(textAreaIncidentes);

        labelIncidentes.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        labelIncidentes.setText("Incidentes Laborales");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel25.setText("Cursos de Capacitación");

        textAreaCursos.setColumns(20);
        textAreaCursos.setRows(5);
        textAreaCursos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textAreaCursosKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(textAreaCursos);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel25)
                    .addComponent(labelIncidentes)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFieldPuesto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdcFechaIngreso, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFieldRfc)
                            .addComponent(jLabel9)
                            .addComponent(txtFieldNss, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(txtFielEnfermedad, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFieldPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFieldRfc, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFielEnfermedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel14))
                        .addGap(42, 42, 42)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFieldNss, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelIncidentes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(269, Short.MAX_VALUE))
        );

        tabDatosGen.addTab("RRHH", jPanel4);

        jScrollPane5.setViewportView(tabDatosGen);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addGap(15, 15, 15)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtAreaDatosAdicionalesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAreaDatosAdicionalesKeyPressed
        int c = evt.getKeyCode();
        if (c == KeyEvent.VK_ENTER || c == KeyEvent.VK_TAB) {
            evt.consume();  // ignore event
        }
    }//GEN-LAST:event_txtAreaDatosAdicionalesKeyPressed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        if (!this.verificarCampos()) {
            return;
        }
        this.jButtonGuardar.setEnabled(false);
        //Validar que no esten vacios ciertos campos
        empleado.setNumeroEmpleado(txtFieldNoEmpleado.getText());
        empleado.setNombre(txtFieldNombres.getText());
        empleado.setApellidoPaterno(txtFieldApellidoP.getText());
        empleado.setApellidoMaterno(txtFieldApellidoM.getText());
        empleado.setPuesto(txtFieldPuesto.getText());
        empleado.setEscolaridad(jComboBoxEscolaridad.getSelectedItem().toString());
        empleado.setCurp(txtFieldCurp.getText());
        empleado.setEstatus(jComboBoxEstatus.getSelectedItem().toString());
        empleado.setFechaNacimiento(jdcFechaNacimiento.getDate());
        empleado.setGenero(jComboBoxGenero.getSelectedItem().toString());
        empleado.setFechaIngreso(jdcFechaIngreso.getDate());
        empleado.setRfc(txtFieldRfc.getText());
        empleado.setNssImss(txtFieldNss.getText());
        empleado.setEnfermedad(txtFielEnfermedad.getText());
        empleado.setIncidentesLaborales(textAreaIncidentes.getText());
        empleado.setCursosCapacitacion(textAreaCursos.getText());
        empleado.setObservaciones(txtAreaDatosAdicionales.getText());
        Calendar calendar = Calendar.getInstance();
        if (empleado.getIdEmpleado() == null) { //Nuevo
            empleado.setRutaFoto(txtFieldNoEmpleado.getText() + "_" + calendar.getTimeInMillis() + ".jpg");
        }
        //Direccion
        Direccion direccion;
        if (empleado.getDireccion() == null) {//Nuevo
            direccion = new Direccion();
        } else {//Edicion
            direccion = empleado.getDireccion();
        }
        direccion.setCalle(txtFieldCalle.getText());
        direccion.setNumeroInterior(txtFieldNumInt.getText());
        direccion.setNumeroExterior(txtFieldNumExt.getText());
        direccion.setColonia(txtFieldColonia.getText());
        direccion.setMunicipio(txtFieldMunicipio.getText());
        direccion.setEstado(jComboBoxEstado.getSelectedItem().toString());
        //Contacto
        Contacto contacto;
        if (empleado.getContacto() == null) {//Nuevo
            contacto = new Contacto();
        } else {//Edicion
            contacto = empleado.getContacto();
        }
        contacto.setTelefonoCasa(txtFieldTelCasa.getText());
        contacto.setCelular(txtFieldCel.getText());
        contacto.setEmail(txtFieldCorreo.getText());
        empleadoJpaController = new EmpleadoJpaController(emf);
        if (empleado.getIdEmpleado() == null) {
            try {
                //Nuevo registro
                empleadoJpaController.create(empleado, direccion, contacto);
                this.guardarFoto();
            } catch (PersistenceException ex) {
                JOptionPane.showMessageDialog(null, "No se pudo registrar el empleado.\nEl No. de Empleado debe ser unico.", "Error", JOptionPane.ERROR_MESSAGE);
                this.jButtonGuardar.setEnabled(true);
                return;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "No se pudo registrar el empleado.\nIntentelo más tarde.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {//Edicion de registro
            try {
                if (!this.oldEmpleado.equals(empleado)) {
                    empleadoJpaController.edit(empleado);
                }
                if (this.cambioFoto) {
                    this.guardarFoto();
                }

            } catch (RollbackException ex) {
                JOptionPane.showMessageDialog(null, "No se pudo editar el empleado.\nEl No. de Empleado debe ser unico.", "Error", JOptionPane.ERROR_MESSAGE);
                this.jButtonGuardar.setEnabled(true);
                return;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "No se pudo editar el empleado.\nIntentelo más tarde.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        listadoEmpleadosView.llenarTabla(listadoEmpleadosView.getjComboBoxEstatus().getSelectedItem().toString());
        this.dispose();
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void textAreaCursosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textAreaCursosKeyPressed
        int c = evt.getKeyCode();
        if (c == KeyEvent.VK_ENTER || c == KeyEvent.VK_TAB) {
            evt.consume();  // ignore event
        }
    }//GEN-LAST:event_textAreaCursosKeyPressed

    private void textAreaIncidentesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textAreaIncidentesKeyPressed
        int c = evt.getKeyCode();
        if (c == KeyEvent.VK_ENTER || c == KeyEvent.VK_TAB) {
            evt.consume();  // ignore event
        }
    }//GEN-LAST:event_textAreaIncidentesKeyPressed

    private void butonBuscarFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butonBuscarFotoActionPerformed
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                foto = ImageIO.read(file);
                jPanelFoto.repaintImage(foto);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "No se pudo cargar la fotografia.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            this.cambioFoto = true;
        } else {

        }
    }//GEN-LAST:event_butonBuscarFotoActionPerformed

    private void guardarFoto() {
        try {
            File outputfile = new File(PathUtil.getImagesPath() + empleado.getRutaFoto());
            ImageIO.write(foto, "jpg", outputfile);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo guardar la fotografía.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butonBuscarFoto;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JComboBox<String> jComboBoxEscolaridad;
    private javax.swing.JComboBox<String> jComboBoxEstado;
    private javax.swing.JComboBox<String> jComboBoxEstatus;
    private javax.swing.JComboBox<String> jComboBoxGenero;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private com.gasolinerasoledadsacv.view.empleados.JPanelFoto jPanelFoto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private com.toedter.calendar.JDateChooser jdcFechaIngreso;
    private com.toedter.calendar.JDateChooser jdcFechaNacimiento;
    private javax.swing.JLabel labelIncidentes;
    private javax.swing.JTabbedPane tabDatosGen;
    private javax.swing.JTextArea textAreaCursos;
    private javax.swing.JTextArea textAreaIncidentes;
    private javax.swing.JTextArea txtAreaDatosAdicionales;
    private javax.swing.JTextField txtFielEnfermedad;
    private javax.swing.JTextField txtFieldApellidoM;
    private javax.swing.JTextField txtFieldApellidoP;
    private javax.swing.JTextField txtFieldCalle;
    private javax.swing.JTextField txtFieldCel;
    private javax.swing.JTextField txtFieldColonia;
    private javax.swing.JTextField txtFieldCorreo;
    private javax.swing.JTextField txtFieldCurp;
    private javax.swing.JTextField txtFieldMunicipio;
    private javax.swing.JTextField txtFieldNoEmpleado;
    private javax.swing.JTextField txtFieldNombres;
    private javax.swing.JTextField txtFieldNss;
    private javax.swing.JTextField txtFieldNumExt;
    private javax.swing.JTextField txtFieldNumInt;
    private javax.swing.JTextField txtFieldPuesto;
    private javax.swing.JTextField txtFieldRfc;
    private javax.swing.JTextField txtFieldTelCasa;
    // End of variables declaration//GEN-END:variables
}
