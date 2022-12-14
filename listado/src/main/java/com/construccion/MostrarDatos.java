package com.construccion;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import org.opentest4j.IncompleteExecutionException;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.image.BufferedImage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 *
 * @author vmend
 */
public class MostrarDatos extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    DefaultTableModel tableModel = new DefaultTableModel() {
        public Class getColumnClass(int column) {
            return getValueAt(0, column).getClass();
        }
    };
    
    SystemApp systemApp;
    ArrayList<Employee> employeeList;
    
    public MostrarDatos() throws JsonMappingException, JsonProcessingException {
        systemApp = new SystemApp("jsonFile/", "employee.json");
        initComponents();
        setModel();
        employeeList = systemApp.getEmployeeObjects();
        setDatos(employeeList);
    }

    private void setModel(){
        employeeTable.setRowHeight(100);
        String[] header = {"Id°","First name","Last name", "photo"};
        tableModel.setColumnIdentifiers(header);
        employeeTable.setModel(tableModel);
    }
    
    private void setDatos(ArrayList<Employee> employeeList){
        Object[] data = new Object[tableModel.getColumnCount()];
        int i=1;
        tableModel.setRowCount(0);

        for(Employee employee : employeeList){
            data[0] = employee.getId();
            data[1] = employee.getFirstName();
            data[2] = employee.getLastName();
            data[3] = changeURLtoImage(employee.getPhoto());
            
            i++;
            tableModel.addRow(data);
        }
        employeeTable.setModel(tableModel);
    }

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) throws MalformedURLException{
        try{
           
            if(txtFirstName.getText().isEmpty()|| txtLastName.getText().isEmpty() || txtId.getText().isEmpty() || txtPhoto.getText().isEmpty()){
                throw new IncompleteExecutionException();
            }

            Employee employee = new Employee();
            String id=txtId.getText();
            boolean flag=true;

            for (int j = 0; j < employeeList.size(); j++) {
                if (employeeList.get(j).getId().equals(id)){
                    flag=false;
                }
            }

            if (flag && Integer.parseInt(id)>0) {
                employee.setId(id);
                employee.setFirstName(txtFirstName.getText());
                employee.setLastName(txtLastName.getText());
                employee.setPhoto(txtPhoto.getText());
                
                
                employeeList.add(employee);
                setDatos(employeeList);
                systemApp.modifyJsonFile(employeeList);
            }
            else{
                JOptionPane.showMessageDialog(null, "El id seleccionado ya existe o no puede ser menor a 1");
    
            }
        }
        catch(IndexOutOfBoundsException | JsonProcessingException e){
            JOptionPane.showMessageDialog(null, "El id seleccionado no existe");
        }
        catch(IncompleteExecutionException e){
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
        }
    }

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt){
        String id = txtId.getText();
        try{
            int indexObject = systemApp.searchEmployeeIndex(employeeList, id);
            employeeList.remove(indexObject);
            setDatos(employeeList);
            systemApp.modifyJsonFile(employeeList);
        }
        catch(IndexOutOfBoundsException | JsonProcessingException e){
            JOptionPane.showMessageDialog(null, "El id seleccionado no existe");
        }
        
    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {                                         
        try{
            String id = txtId.getText();
            int indexObject = systemApp.searchEmployeeIndex(employeeList, id);
            txtFirstName.setText(employeeList.get(indexObject).getFirstName());
            txtLastName.setText(employeeList.get(indexObject).getLastName());
            txtPhoto.setText(employeeList.get(indexObject).getPhoto());
            }
        catch(IndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, "El id seleccionado no existe");
        }
    }     

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {  

        try{

            if(txtFirstName.getText().isEmpty()|| txtLastName.getText().isEmpty() || txtId.getText().isEmpty() || txtPhoto.getText().isEmpty()){
                throw new RuntimeException();
            }
            
            String id = txtId.getText();
            int indexObject = systemApp.searchEmployeeIndex(employeeList, id);                                           
            Employee employee = employeeList.get(indexObject);
            employee.setFirstName(txtFirstName.getText());
            employee.setLastName(txtLastName.getText());
            employee.setPhoto(txtPhoto.getText());

            setDatos(employeeList);
        }
        catch( IndexOutOfBoundsException e ){
            JOptionPane.showMessageDialog(null, "El id seleccionado no existe");
        }
        catch(RuntimeException e){
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
        }

        try {
            systemApp.modifyJsonFile(employeeList);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
          // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        employeeTable = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        lblId = new javax.swing.JLabel();
        lblFirstName = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        lblLastName = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        lblPhoto = new javax.swing.JLabel();
        txtPhoto = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        employeeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(employeeTable);

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnAgregarActionPerformed(evt);
                } 
                catch (MalformedURLException | RuntimeException e) {
                    JOptionPane.showMessageDialog(null, "URL invalida");
                }
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try{
                    btnModificarActionPerformed(evt);
                }
                catch (RuntimeException e) {
                    JOptionPane.showMessageDialog(null, "URL invalida");
                }
            }
        });
        btnEliminar.setText("Eliminar");

        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        lblId.setText("id: ");

        lblFirstName.setText("First name: ");

        lblLastName.setText("LastName: ");

        lblPhoto.setText("Photo: ");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(41, 41, 41))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblFirstName)
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(45, 45, 45)
                                        .addComponent(btnBuscar))
                                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(lblPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(lblLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(btnAgregar)
                        .addGap(107, 107, 107)
                        .addComponent(btnModificar)
                        .addGap(113, 113, 113)
                        .addComponent(btnEliminar)))
                .addContainerGap(121, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblId)
                            .addComponent(btnBuscar))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFirstName)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLastName)
                            .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPhoto)
                            .addComponent(txtPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar))
                .addGap(38, 38, 38))
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
    }// </editor-fold>                     

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MostrarDatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MostrarDatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MostrarDatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MostrarDatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MostrarDatos().setVisible(true);
                } catch (JsonProcessingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }

    private Icon changeURLtoImage(String url){
        try {
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(new URL(url)));
            Image image = getScaledImage(imageIcon.getImage(), 100, 100);
            imageIcon = new ImageIcon(image);
            Icon icon = (Icon) imageIcon ;
            return icon;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JTable employeeTable;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFirstName;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblLastName;
    private javax.swing.JLabel lblPhoto;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtPhoto;
    // End of variables declaration                   
}