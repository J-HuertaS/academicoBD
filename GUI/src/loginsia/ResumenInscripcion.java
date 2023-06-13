/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package loginsia;

import com.academico.login.conexion.SQLConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import static loginsia.SIA.Ventana;

/**
 *
 * @author HUERTAS
 */
public class ResumenInscripcion extends javax.swing.JInternalFrame {
    
    
    private DefaultTableModel resumen;
    private String programa;
    private String usuario;
    private String contrasena;
    SQLConnection cc = new SQLConnection();
    private Connection conexion;
    private ResultSet rs;
    /**
     * Creates new form ResumenInscripcion
     */
    public ResumenInscripcion(String usuario,String contrasena,String programa) {
        initComponents();
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.programa = programa;
        
        this.conexion = cc.conexion(usuario,contrasena);
        
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui1 = (BasicInternalFrameUI) this.getUI();
        ui1.setNorthPane(null);
        
        this.resumen = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int filas,int columnas){
                return false;
                
            }
        };
        resumen.addColumn("Asignatura");
        resumen.addColumn("Grupo");
        resumen.addColumn("Componente");
        resumen.addColumn("Horario");
        ResumenTable.setModel(resumen);
        ResumenTable.setFillsViewportHeight(true);
        
        try {
            String sql = "{CALL sp_AsignaturasInscritas(?,?)}";
            CallableStatement callableStatement = conexion.prepareCall(sql);
            
            callableStatement.setString(1,this.usuario);
            callableStatement.setString(2,this.programa);

            // Recorrer los resultados
            ResultSet resultSet = callableStatement.executeQuery();
            String[] datos = new String[4];
            while(resultSet.next()){
                datos[0] = resultSet.getString("asignatura");
                datos[1] = String.valueOf(resultSet.getInt("grupo"));
                datos[2] = resultSet.getString("componente");
                datos[3] = resultSet.getString("espacio");
                resumen.addRow(datos);
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(Historia.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ResumenTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setText("Resumen de inscripción");

        ResumenTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(ResumenTable);

        jButton1.setBackground(new java.awt.Color(96, 25, 25));
        jButton1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Confirmar inscripción");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1076, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(453, 453, 453)
                        .addComponent(jButton1)))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButton1)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        JOptionPane.showMessageDialog(null,"Proceso de inscripción efectuado correctamente.");
        Welcome wl = new Welcome();
        Ventana.removeAll();
        Ventana.add(wl);
        wl.setVisible(true);
    }//GEN-LAST:event_jButton1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ResumenTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
