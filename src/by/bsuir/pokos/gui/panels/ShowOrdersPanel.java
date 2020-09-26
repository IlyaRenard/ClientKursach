/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.pokos.gui.panels;

import by.bsuir.pokos.database.dao.entity.Cargo;
import by.bsuir.pokos.database.dao.entity.Loading;
import by.bsuir.pokos.database.dao.entity.Order;
import by.bsuir.pokos.database.dao.entity.Unloading;
import by.bsuir.pokos.gui.frames.WorkerFrame;
import by.bsuir.pokos.message.Message;
import by.bsuir.pokos.utils.Actions;
import com.sun.glass.ui.Cursor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.List;
import java.awt.Point;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Gavrilik
 */
public class ShowOrdersPanel extends javax.swing.JPanel {

    public ObjectOutputStream outputStream;
    public ObjectInputStream inputStream;
    public OrderPanel orderPanel;
    public WorkerFrame frame;
    
    public int[] idArray;
    
    public ShowOrdersPanel(ObjectOutputStream outputStream,ObjectInputStream inputStream,WorkerFrame frame) {
        
        this.outputStream=outputStream;
        this.inputStream=inputStream;
        this.frame=frame;
        
        initComponents();
        showAllOrderInRealization();
        sort_table();
        
    }
    
    //custom design of Table
    public static class CustomRenderer extends DefaultTableCellRenderer {
        private ArrayList<Color> desiredColors = new ArrayList<>();

        public void setColors(Color incomingColor)
        {
            desiredColors.add(incomingColor);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            for (int i = 0; i < desiredColors.size(); i++) {
                
                if(row == i){
                   // cellComponent.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                    cellComponent.setForeground(Color.BLACK);
                    cellComponent.setBackground(desiredColors.get(i));
                    if(column==4) {
                        Map<TextAttribute, Integer> fontAttributes = new HashMap<>();
                        fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);    
                        cellComponent.setFont(new Font("PT Sans", 0, 16).deriveFont(fontAttributes)); 
                        cellComponent.setForeground(new Color(61,139,212));
                    }
                }
            
        }
        return cellComponent;
        }
    }

    private void sort_table(){
        DefaultTableModel model=(DefaultTableModel) ordersInRealizationTable.getModel();
        TableRowSorter<DefaultTableModel> sorter =new TableRowSorter<>(model);
        ordersInRealizationTable.setRowSorter(sorter);
    }
    
    private void search(String query) {
        DefaultTableModel model=(DefaultTableModel) ordersInRealizationTable.getModel();
        TableRowSorter<DefaultTableModel> sorter =new TableRowSorter<>(model);
        ordersInRealizationTable.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(query));
    }
    
    public void showAllOrderInRealization() {
        try {
            //this.repaint();
            
            outputStream.writeObject(new Message(Actions.GetOrdersInRealization));
            Message message = (Message) inputStream.readObject();
             
            ArrayList<Order> result = (ArrayList<Order>) message.getStructure();
            DefaultTableModel model=(DefaultTableModel) ordersInRealizationTable.getModel();
            String[] row;
            idArray= new int[result.size()];
            CustomRenderer colouringTable = new CustomRenderer();
            System.out.println(result.size());
            for(int i=0;i<result.size();i++)
            {
                row= new String[12];
                outputStream.writeObject(new Message(String.valueOf(result.get(i).getCargoID()),Actions.GetCargoByID));
                message= (Message)inputStream.readObject();
                Cargo cargo = (Cargo)message.getStructure();
                row[7]=String.valueOf(cargo.getCargoCount());
                row[8]=String.valueOf(cargo.getCargoWeight());
                row[10]=cargo.getCargoDocument();
                
                outputStream.writeObject(new Message(String.valueOf(result.get(i).getOrderBYforwarderID()),Actions.GetWorkerNameByID));
                message=(Message)inputStream.readObject();
                row[3]=message.getMessage();
                
                outputStream.writeObject(new Message(String.valueOf(result.get(i).getOrderPLforwarderID()),Actions.GetWorkerNameByID));
                message=(Message)inputStream.readObject();
                row[11]=message.getMessage();
                
                row[4]=result.get(i).getNumberOfOrder();
                
                outputStream.writeObject(new Message(String.valueOf(result.get(i).getUnloadingID()),Actions.GetUnloadingByID));
                message= (Message)inputStream.readObject();
                Unloading unloading = (Unloading)message.getStructure();
                row[0]=unloading.getUnloadingClient();
                row[1]=unloading.getUnloadingCountry();
                row[2]=unloading.getUnloadingCity();
                
                outputStream.writeObject(new Message(String.valueOf(result.get(i).getLoadingID()),Actions.GetLoadingByID));
                message= (Message)inputStream.readObject();
                Loading loading = (Loading)message.getStructure();
                row[5]=loading.getLoadingCountry();
                row[6]=loading.getLoadingCity();
                row[9]=loading.getLoadingDate().toString();
                
                idArray[i]=result.get(i).getID();
                model.addRow(row);
                
                switch(result.get(i).getOrderStatus()){
                    case "IsInImplementation": 
                        colouringTable.setColors(Color.YELLOW); 
                        break;
                    case "IsInStock":
                        colouringTable.setColors(Color.GREEN); 
                        break;
                    case "IsLoaded":
                        colouringTable.setColors(Color.RED);
                        break;
                    case "IsUnloaded":
                        colouringTable.setColors(Color.WHITE);
                        break;
                }
                
            }
            
            //paint row
            for(int i = 0;i<model.getColumnCount();i++) {
                ordersInRealizationTable.getColumnModel().getColumn(i).setCellRenderer(colouringTable); 
            }
            
            System.out.println("sdfsdf");
        } catch (IOException ex) {
            Logger.getLogger(ShowOrdersPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShowOrdersPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ordersInRealizationTable = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        searchField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(234, 247, 247));

        ordersInRealizationTable.setBorder(new MatteBorder(1, 1, 1, 1, Color.gray));
        ordersInRealizationTable.setFont(new java.awt.Font("PT Sans", 0, 16)); // NOI18N
        ordersInRealizationTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Клиент", "Страна", "Город назачения", "Экспедитор BY", "№ Заказа", "Страна", "Город загрузки", "Количество", "Весс[кг]", "Поставка", "Док", "Экспедитор PL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ordersInRealizationTable.setToolTipText("");
        ordersInRealizationTable.setGridColor(new java.awt.Color(204, 204, 204));
        ordersInRealizationTable.setRowHeight(38);
        ordersInRealizationTable.setRowSelectionAllowed(false);
        ordersInRealizationTable.setSelectionBackground(new java.awt.Color(255, 255, 255));
        ordersInRealizationTable.getTableHeader().setReorderingAllowed(false);
        ordersInRealizationTable.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                ordersInRealizationTableMouseMoved(evt);
            }
        });
        ordersInRealizationTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ordersInRealizationTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(ordersInRealizationTable);
        JTableHeader header = ordersInRealizationTable.getTableHeader();
        header.setFont(new Font("Serif", Font.PLAIN, 16));
        header.setForeground(new Color(55,113,229));
        ((DefaultTableCellRenderer)ordersInRealizationTable.getTableHeader().getDefaultRenderer())
        .setHorizontalAlignment(JLabel.CENTER);

        ordersInRealizationTable.setRowSelectionAllowed(false);
        ordersInRealizationTable.setShowGrid(true);
        //ordersInRealizationTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        //ordersInRealizationTable.setCellSelectionEnabled(true);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/question (1).png"))); // NOI18N
        jLabel5.setToolTipText("<html>\n<div style=\"background-color: white; font-size: 12px;\">\n<font color=\"#ffff1a\">Заказы в реализации</font><br>\n<font color=\"green\">На складе</font><br>\n<font color=\"red\">Загружены для отправки клиенту</font><br>\n</div>\n</html>");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1681, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 804, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("League Gothic", 0, 26)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(61, 139, 212));
        jLabel2.setText("Транспортные заказы");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText(">> Мониторинг грузов");

        jSeparator1.setForeground(new java.awt.Color(204, 204, 204));

        searchField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        searchField.setToolTipText("Поиск");
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchFieldKeyReleased(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 16, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ordersInRealizationTableMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ordersInRealizationTableMouseMoved
          Point p = evt.getPoint ();
          if (ordersInRealizationTable.columnAtPoint (p) == 4)
            ordersInRealizationTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
          else
            ordersInRealizationTable.setCursor (new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_ordersInRealizationTableMouseMoved

    private void ordersInRealizationTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ordersInRealizationTableMouseClicked
        // TODO add your handling code here:
        Point point =  evt.getPoint();
        int col = ordersInRealizationTable.columnAtPoint(point);
        if (evt.getClickCount() == 1 || col==4) {
            
            int i=ordersInRealizationTable.getSelectedRow();
            int orderID=idArray[i];
            
            this.frame.addOrderPanel.setVisible(false);
            this.frame.showOrdersPanel.setVisible(false);
            this.frame.orderPanel.showOrder(orderID);
            this.frame.orderPanel.setVisible(true);
            
        }
    }//GEN-LAST:event_ordersInRealizationTableMouseClicked

    private void searchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyReleased
        // TODO add your handling code here:
        search(searchField.getText());
    }//GEN-LAST:event_searchFieldKeyReleased

    public void update_table()
    {
        DefaultTableModel model=(DefaultTableModel) ordersInRealizationTable.getModel();
        model.setRowCount(0);
        showAllOrderInRealization();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable ordersInRealizationTable;
    private javax.swing.JTextField searchField;
    // End of variables declaration//GEN-END:variables

}
