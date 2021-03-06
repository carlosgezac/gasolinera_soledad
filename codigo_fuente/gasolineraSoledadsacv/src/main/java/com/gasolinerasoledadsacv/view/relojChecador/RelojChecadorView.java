package com.gasolinerasoledadsacv.view.relojChecador;

import com.gasolinerasoledadsacv.controller.ChecadorJpaController;
import com.gasolinerasoledadsacv.controller.ChecadorviewJpaController;
import com.gasolinerasoledadsacv.controller.EmpleadoJpaController;
import com.gasolinerasoledadsacv.entity.Checador;
import com.gasolinerasoledadsacv.entity.Checadorview;
import com.gasolinerasoledadsacv.entity.Empleado;
import com.gasolinerasoledadsacv.enums.PersistenceEnum;
import com.gasolinerasoledadsacv.util.DateUtil;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

public class RelojChecadorView extends javax.swing.JFrame {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PersistenceEnum.PERSISTENCE_UNIT_NAME.getValue());
    private EmpleadoJpaController empleadoJpaController;
    private ChecadorviewJpaController checadorviewJpaController;
    private ChecadorJpaController checadorJpaController;
    private List<Empleado> listaEmpleados;

    public RelojChecadorView() {
        initComponents();
        this.llenarTabla();
    }

    public void llenarTabla() {
        //Llenar tabla
        try {
            checadorviewJpaController = new ChecadorviewJpaController(emf);
            List<Checadorview> listaChecadorview = checadorviewJpaController.findChecadorviewEntities(10, 0);
            DefaultTableModel model = (DefaultTableModel) tablaRegistros.getModel();
            model.setRowCount(0);
            listaChecadorview.forEach(checador -> {
                model.addRow(new Object[]{DateUtil.formatDefault(checador.getFechaHora()), DateUtil.getClockHour(checador.getFechaHora()).getHour(), checador.getTipo(), checador.getNumeroEmpleado(), checador.getNombreEmpleado()});
            });
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se pudo cargar la información del checador.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaRegistros = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        comboBoxBuscarEmpleado = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanelRelo1 = new com.gasolinerasoledadsacv.view.relojChecador.JPanelReloj();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reloj Checador");
        setResizable(false);

        tablaRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Día", "Hora", "Tipo", "No. Empleado", "Nombre Completo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaRegistros.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tablaRegistros);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setText("Ultimos 10 registros:");

        comboBoxBuscarEmpleado.setEditable(true);
        comboBoxBuscarEmpleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { ""}));
        comboBoxBuscarEmpleado.getEditor().getEditorComponent().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                comboBoxBuscarEmpleadoKeyReleased(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check.png"))); // NOI18N
        jButton1.setText("Checar Hora");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Entrada");
        jRadioButton1.setActionCommand("Entrada");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jRadioButton2.setText("Salida");
        jRadioButton2.setActionCommand("Salida");

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reloj-de-pared (2).png"))); // NOI18N
        jLabel2.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(125, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jPanelRelo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(120, 120, 120))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jPanelRelo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setText("Buscar Empleado:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(comboBoxBuscarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2))
                        .addGap(33, 33, 33)
                        .addComponent(jButton1)))
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(comboBoxBuscarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jRadioButton1)
                            .addGap(18, 18, 18)
                            .addComponent(jRadioButton2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String strEmpleado = comboBoxBuscarEmpleado.getEditor().getItem().toString();
        strEmpleado = strEmpleado.split("[\\s,]+")[0];
        if (!Pattern.matches("[a-zA-Z_0-9]|-", strEmpleado) || strEmpleado.length() > 10) {
            JOptionPane.showMessageDialog(null, "Empleado incorrecto.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        empleadoJpaController = new EmpleadoJpaController(emf);
        checadorJpaController = new ChecadorJpaController(emf);
        Empleado empleado = empleadoJpaController.findByNumeroEmpleado(strEmpleado);
        if (empleado != null) {
            try {
                Checador checador = new Checador();
                checador.setIdEmpleado(empleado);
                checador.setFechaHora(new Date());
                checador.setTipo(buttonGroup1.getSelection().getActionCommand());
                checadorJpaController.create(checador);
                comboBoxBuscarEmpleado.removeAllItems();
                DefaultTableModel model = (DefaultTableModel) tablaRegistros.getModel();
                if (model.getRowCount() == 10) {
                    model.removeRow(9);
                }
                model.insertRow(0, new Object[]{DateUtil.formatDefault(checador.getFechaHora()), DateUtil.getClockHour(checador.getFechaHora()).getHour(), checador.getTipo(), empleado.getNumeroEmpleado(), empleado.getNombre() + " " + empleado.getApellidoPaterno() + " " + empleado.getApellidoMaterno()});
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "No se pudo checar la hora.\nIntentelo más tarde.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void comboBoxBuscarEmpleadoKeyReleased(java.awt.event.KeyEvent evt) {
        int c = evt.getKeyCode();
        if (c == KeyEvent.VK_UP || c == KeyEvent.VK_DOWN || c == KeyEvent.VK_LEFT || c == KeyEvent.VK_RIGHT || c == KeyEvent.VK_ENTER) {
            return;
        }
        String coincidencia = comboBoxBuscarEmpleado.getEditor().getItem().toString();
        comboBoxBuscarEmpleado.hidePopup();
        if (coincidencia.length() >= 1) {
            empleadoJpaController = new EmpleadoJpaController(emf);
            listaEmpleados = empleadoJpaController.findByEstatusAndNumEmpleadoOrNombreCompleto("Activo", coincidencia, 5);
            if (listaEmpleados.size() > 0) {
                comboBoxBuscarEmpleado.removeAllItems();
            } else {
                return;
            }

            listaEmpleados.forEach(empleado -> {
                comboBoxBuscarEmpleado.addItem(empleado.getNumeroEmpleado() + " - " + empleado.getNombre() + " " + empleado.getApellidoPaterno() + " " + empleado.getApellidoMaterno());
            });

            comboBoxBuscarEmpleado.showPopup();
        }
        comboBoxBuscarEmpleado.setSelectedItem(coincidencia);
        //Remover la seleccion de texto y evitar reemplazar el texto del campo del jcombobox
        JTextComponent editor = ((JTextField) comboBoxBuscarEmpleado.getEditor().getEditorComponent());
        editor.setCaretPosition(coincidencia.length());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> comboBoxBuscarEmpleado;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private com.gasolinerasoledadsacv.view.relojChecador.JPanelReloj jPanelRelo1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaRegistros;
    // End of variables declaration//GEN-END:variables
}
