/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.pokos.gui.panels;

import by.bsuir.pokos.database.dao.entity.Cargo;
import by.bsuir.pokos.database.dao.entity.Carrier;
import by.bsuir.pokos.database.dao.entity.Loading;
import by.bsuir.pokos.database.dao.entity.Order;
import by.bsuir.pokos.database.dao.entity.Payment;
import by.bsuir.pokos.database.dao.entity.Stock;
import by.bsuir.pokos.database.dao.entity.Unloading;
import by.bsuir.pokos.message.Message;
import by.bsuir.pokos.utils.Actions;
import by.bsuir.pokos.utils.Checks;
import by.bsuir.pokos.utils.FieldContainsException;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Color;
import java.awt.Component;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

/**
 *
 * @author Gavrilik
 */
public class AddOrderPanel extends javax.swing.JPanel {

    public ObjectOutputStream outputStream;
    public ObjectInputStream inputStream;
    public String workerLogin;
    private int stockID;
    
    public AddOrderPanel(ObjectOutputStream outputStream,ObjectInputStream inputStream,String workerLogin) {
        this.outputStream=outputStream;
        this.inputStream=inputStream;
        this.workerLogin=workerLogin;
        initComponents();
        
        setUnloadingStockData();
        setOrderByForwardeData();
    }

    public void setOrderByForwardeData() {
        try {
            Message message = new Message(Actions.GetAllForwarders);
            outputStream.writeObject(message);
            message =(Message) inputStream.readObject();
            
            ArrayList<String> list= (ArrayList<String>)message.getStructure();
            for(int i=0;i<list.size();i++)
            {
                orderBYforwarderComboBox.addItem(list.get(i));
            }
            
        } catch (IOException ex) {
            Logger.getLogger(AddOrderPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddOrderPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void setUnloadingStockData() {
        
        try {
            Message message=new Message(unloadingStockComboBox.getSelectedItem().toString(),Actions.GetStock); //получаем выбраный склад
            outputStream.writeObject(message);
            
            message=(Message) inputStream.readObject();
            Stock stock=(Stock)message.getStructure();
            
            //устаналиваем поля склада выгрзуки
            unloadingStockNameTextField.setText(stock.getStockName());
            unloadingStockAdressTextField.setText(stock.getStockAdress());
            unloadingStockCityTextField.setText(stock.getStockCity());
            unloadingStockCountryTextField.setText(stock.getStockCountry());
            unloadingStockPostalCodeTextField.setText(stock.getStockPostalCode());
            
            stockID=stock.getStockID();
            
        } catch (IOException ex) {
            Logger.getLogger(AddOrderPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddOrderPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        carrierCompanyNameField = new javax.swing.JTextField();
        carrierContactField = new javax.swing.JTextField();
        carrierTelephoneField = new javax.swing.JTextField();
        carrierElMailField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cargoDescriptionTextArea = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cargoDocumentComboBox = new javax.swing.JComboBox<>();
        cargoWeightField = new javax.swing.JTextField();
        cargoCountField = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        Calendar c = Calendar.getInstance();
        loadingDateChooser = new com.toedter.calendar.JDateChooser(c.getTime());
        loadingTimeSpinner = new javax.swing.JSpinner();
        jLabel14 = new javax.swing.JLabel();
        Calendar c3 = Calendar.getInstance();
        c3.add(Calendar.DAY_OF_YEAR,14);
        unloadingDateChooser = new com.toedter.calendar.JDateChooser(c3.getTime());
        unloadingTimeSpinner = new javax.swing.JSpinner();
        jLabel15 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        loadingCompanyNameTextField = new javax.swing.JTextField();
        loadingAdressTextField = new javax.swing.JTextField();
        loadingCountryTextField = new javax.swing.JTextField();
        loadingPostalCodeTextField = new javax.swing.JTextField();
        loadingCityTextField = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        paymentPeriodComboBox = new javax.swing.JComboBox<>();
        unloadingStockCountryTextField = new javax.swing.JTextField();
        unloadingCityTextField = new javax.swing.JTextField();
        unloadingStockCityTextField = new javax.swing.JTextField();
        unloadingStockAdressTextField = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        unloadingClientTextField = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        unloadingCountryTextField = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        unloadingStockNameTextField = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        carrierForeignRegNumberField = new javax.swing.JTextField();
        carrierDriverTelephoneNumberField = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        freightCostTextField = new javax.swing.JTextField();
        unloadingStockComboBox = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        confirmOrderButton = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        unloadingStockPostalCodeTextField = new javax.swing.JTextField();
        orderBYforwarderComboBox = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("League Gothic", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(61, 139, 212));
        jLabel2.setText("Заказ на загрузку");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText(">> Международные перевозки");

        jSeparator1.setForeground(new java.awt.Color(204, 204, 204));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setText("Перевозчик");

        jSeparator2.setForeground(new java.awt.Color(204, 204, 204));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Название компании");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Контактное лицо");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Телефон");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Регистрационный номер");

        carrierCompanyNameField.setColumns(12);
        carrierCompanyNameField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        carrierContactField.setColumns(12);
        carrierContactField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        carrierTelephoneField.setColumns(12);
        carrierTelephoneField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        carrierElMailField.setColumns(5);
        carrierElMailField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel8.setText("Груз");

        jSeparator3.setForeground(new java.awt.Color(204, 204, 204));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setText("Описание товара");

        cargoDescriptionTextArea.setColumns(20);
        cargoDescriptionTextArea.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cargoDescriptionTextArea.setRows(5);
        jScrollPane1.setViewportView(cargoDescriptionTextArea);

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("Вес товара[кг]");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel11.setText("Дата загрузки");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setText("Документ");

        cargoDocumentComboBox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cargoDocumentComboBox.setMaximumRowCount(5);
        cargoDocumentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EX", "BRAK", "T1 PA", "T1 PT", "TIR" }));

        cargoWeightField.setColumns(5);
        cargoWeightField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cargoCountField.setColumns(5);
        cargoCountField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jSeparator4.setForeground(new java.awt.Color(204, 204, 204));

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setText("Количество");

        Date date = new Date();
        SpinnerDateModel sm = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        loadingTimeSpinner = new javax.swing.JSpinner(sm);
        JSpinner.DateEditor de = new JSpinner.DateEditor(loadingTimeSpinner, "HH:mm:ss");
        loadingTimeSpinner.setEditor(de);

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setText("Срок поставки");

        JTextFieldDateEditor editor1 = (JTextFieldDateEditor) unloadingDateChooser.getDateEditor();
        editor1.setEditable(false);

        Date date1 = new Date();
        SpinnerDateModel sm1 =   new SpinnerDateModel(date1, null, null, Calendar.HOUR_OF_DAY);
        unloadingTimeSpinner = new javax.swing.JSpinner(sm1);
        JSpinner.DateEditor de1 = new JSpinner.DateEditor(unloadingTimeSpinner, "HH:mm:ss");
        unloadingTimeSpinner.setEditor(de1);

        jLabel15.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel15.setText("Адресс загрузки");

        jSeparator5.setForeground(new java.awt.Color(204, 204, 204));

        jLabel16.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel16.setText("Адресс разгрузки");

        jLabel17.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel17.setText("Название компании");

        jLabel18.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Адресс");

        jLabel19.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel19.setText("Страна");

        jLabel20.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel20.setText("Почтовый индекс");

        jLabel21.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel21.setText("Город");

        loadingCompanyNameTextField.setColumns(5);
        loadingCompanyNameTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        loadingAdressTextField.setColumns(5);
        loadingAdressTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        loadingCountryTextField.setColumns(5);
        loadingCountryTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        loadingPostalCodeTextField.setColumns(5);
        loadingPostalCodeTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        loadingCityTextField.setColumns(5);
        loadingCityTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("Название склада ");

        jLabel23.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("Выбери склад");

        jLabel24.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("Адрес склада ");

        jLabel25.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel25.setText("Почтовый индекс");

        jLabel26.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel26.setText("Страна");

        paymentPeriodComboBox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        paymentPeriodComboBox.setMaximumRowCount(5);
        paymentPeriodComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "7 дней", "14 дней", "21 день", "30 дней", "45 дней" }));

        unloadingStockCountryTextField.setColumns(5);
        unloadingStockCountryTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        unloadingCityTextField.setColumns(5);
        unloadingCityTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        unloadingStockCityTextField.setColumns(5);
        unloadingStockCityTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        unloadingStockAdressTextField.setColumns(5);
        unloadingStockAdressTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel27.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel27.setText("Клиент");

        unloadingClientTextField.setColumns(5);
        unloadingClientTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel28.setText("Город");

        jLabel29.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel29.setText("Страна");
        jLabel29.setToolTipText("ана");

        unloadingCountryTextField.setColumns(5);
        unloadingCountryTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel30.setText("Экспедитор BY");
        jLabel30.setToolTipText("");

        unloadingStockNameTextField.setColumns(5);
        unloadingStockNameTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jSeparator6.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator6.setPreferredSize(new java.awt.Dimension(0, 615));

        jLabel31.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel31.setText("Электронная почта");

        jLabel32.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel32.setText("Телефон водителя");

        carrierForeignRegNumberField.setColumns(12);
        carrierForeignRegNumberField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        carrierDriverTelephoneNumberField.setColumns(12);
        carrierDriverTelephoneNumberField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel33.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel33.setText("Ставка/Фрахт");

        jSeparator7.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator7.setPreferredSize(new java.awt.Dimension(0, 615));

        jLabel34.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel34.setText("Сумма(EUR)");

        jLabel35.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel35.setText("Срок оплаты");

        freightCostTextField.setColumns(5);
        freightCostTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        unloadingStockComboBox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        unloadingStockComboBox.setMaximumRowCount(5);
        unloadingStockComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SERPANTIN LOGISTIC SP.Z O. O.", "HATRANS" }));
        unloadingStockComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                unloadingStockComboBoxItemStateChanged(evt);
            }
        });

        confirmOrderButton.setBackground(new java.awt.Color(61, 139, 212));
        confirmOrderButton.setFont(new java.awt.Font("League Gothic", 0, 24)); // NOI18N
        confirmOrderButton.setForeground(new java.awt.Color(255, 255, 255));
        confirmOrderButton.setText("Подтвердить");
        confirmOrderButton.setBorderPainted(false);
        confirmOrderButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        confirmOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmOrderButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addComponent(confirmOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(confirmOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel36.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel36.setText("Город");

        unloadingStockPostalCodeTextField.setColumns(5);
        unloadingStockPostalCodeTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel20)
                                .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(loadingPostalCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(loadingCountryTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                            .addComponent(loadingCompanyNameTextField)
                            .addComponent(loadingAdressTextField)
                            .addComponent(loadingCityTextField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel36)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(unloadingStockCityTextField)
                            .addComponent(unloadingStockAdressTextField)
                            .addComponent(unloadingStockNameTextField)
                            .addComponent(unloadingStockComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(unloadingStockPostalCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(unloadingStockCountryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(111, 111, 111)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel30)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(unloadingCityTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                            .addComponent(unloadingClientTextField)
                            .addComponent(unloadingCountryTextField)
                            .addComponent(orderBYforwarderComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(62, 62, 62))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 903, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel13))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cargoWeightField, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                                            .addComponent(cargoCountField))
                                        .addGap(117, 117, 117)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cargoDocumentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(loadingDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(loadingTimeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(110, 110, 110)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(unloadingDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(unloadingTimeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(carrierCompanyNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(carrierContactField, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(163, 163, 163)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel31)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(carrierElMailField))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(carrierTelephoneField, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(93, 93, 93)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(carrierForeignRegNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(carrierDriverTelephoneNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 304, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addComponent(jSeparator2)
                            .addComponent(jSeparator5)
                            .addComponent(jSeparator6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel1))
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel33)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel34)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(freightCostTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(56, 56, 56)
                                        .addComponent(jLabel35)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(paymentPeriodComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(548, 548, 548)
                                        .addComponent(jLabel16)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 1691, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(carrierCompanyNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(carrierTelephoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(carrierForeignRegNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(carrierContactField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(carrierElMailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel31)
                    .addComponent(carrierDriverTelephoneNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cargoWeightField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(cargoDocumentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cargoCountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(unloadingDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(loadingTimeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(unloadingTimeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loadingDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(loadingCompanyNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(loadingAdressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(loadingPostalCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(loadingCountryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(loadingCityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addGap(55, 55, 55)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(jLabel35)
                            .addComponent(freightCostTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(paymentPeriodComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel23)
                                    .addComponent(unloadingStockComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel22)
                                    .addComponent(unloadingStockNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel24)
                                    .addComponent(unloadingStockAdressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel25)
                                    .addComponent(unloadingStockCountryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26)
                                    .addComponent(unloadingStockPostalCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel27)
                                    .addComponent(unloadingClientTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28)
                                    .addComponent(unloadingCityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel29)
                                    .addComponent(unloadingCountryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel30)
                                    .addComponent(orderBYforwarderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(unloadingStockCityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36))))
                .addContainerGap(101, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(116, 116, 116)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(894, Short.MAX_VALUE)))
        );

        JTextFieldDateEditor editor = (JTextFieldDateEditor) loadingDateChooser.getDateEditor();
        editor.setEditable(false);
        JFormattedTextField tf = ((JSpinner.DefaultEditor) loadingTimeSpinner.getEditor()).getTextField();
        tf.setEditable(false);
        tf.setBackground(Color.white);
        JFormattedTextField tf1 = ((JSpinner.DefaultEditor) unloadingTimeSpinner.getEditor()).getTextField();
        tf1.setEditable(false);
        tf1.setBackground(Color.white);
        unloadingStockCountryTextField.setEditable(false);
        unloadingStockCityTextField.setEditable(false);
        unloadingStockAdressTextField.setEditable(false);
        unloadingStockNameTextField.setEditable(false);
        unloadingStockPostalCodeTextField.setEditable(false);
    }// </editor-fold>//GEN-END:initComponents

    private Order isDataCorrect() {
        
        try {
        String carrierCompanyName=carrierCompanyNameField.getText();
        String carrierContact=carrierContactField.getText();
        String carrierTelephone=carrierTelephoneField.getText(); //regex
        String carrierElMail=carrierElMailField.getText();
        String carrierForeignRegNumber=carrierForeignRegNumberField.getText();
        String carrierDriverTelephoneNumber=carrierDriverTelephoneNumberField.getText(); //regex
        
        String cargoDescription=cargoDescriptionTextArea.getText();
        int cargoWeight=Integer.parseInt(cargoWeightField.getText());  
        int cargoCount=Integer.parseInt(cargoCountField.getText());    
        String cargoDocument=cargoDocumentComboBox.getSelectedItem().toString(); //dont check
        
        String loadingCompanyName=loadingCompanyNameTextField.getText();
        String loadingAdress=loadingAdressTextField.getText();
        String loadingPostalCode=loadingPostalCodeTextField.getText(); //regex;  
        String loadingCity=loadingCityTextField.getText();
        String loadingCountry=loadingCountryTextField.getText();
        java.sql.Date loadingDate= new java.sql.Date(loadingDateChooser.getDate().getTime());   
        Time loadingTime= new Time(((Date)loadingTimeSpinner.getValue()).getTime());
        
        int unloadingStockID=stockID;
        String unloadingClient=unloadingClientTextField.getText();
        String unloadingCity=unloadingCityTextField.getText();
        String unloadingCountry=unloadingCountryTextField.getText();
        java.sql.Date unloadingDate= new java.sql.Date(unloadingDateChooser.getDate().getTime());   
        Time unloadingTime= new Time(((Date)unloadingTimeSpinner.getValue()).getTime());
        String orderBYforwarder=String.valueOf(orderBYforwarderComboBox.getSelectedItem());  //must be unloadingForwaderID (int)
        
        float freightCost=Float.parseFloat(freightCostTextField.getText()); 
        String paymentPeriod=String.valueOf(paymentPeriodComboBox.getSelectedItem());//dont check
        
        //проверки
        Pattern emailPattern=Pattern.compile(Checks.EMAIL_REGEX);
        Matcher matcher=emailPattern.matcher(carrierElMail);
        if(matcher.matches()==false)
           throw new FieldContainsException();
        
        Pattern telephonePattern=Pattern.compile(Checks.TELEPHONE_REGEX);
        matcher= telephonePattern.matcher(carrierDriverTelephoneNumber);
        if(matcher.matches()==false)
            throw new FieldContainsException();
        
        matcher= telephonePattern.matcher(carrierTelephone);
        if(matcher.matches()==false)
            throw new FieldContainsException();    
        
        Pattern regNumberPattern=Pattern.compile(Checks.CAPITALS_AND_NUMBERS_REGEX);
        matcher=regNumberPattern.matcher(carrierForeignRegNumber);
        if(matcher.matches()==false)
            throw new FieldContainsException(); 
        
        Pattern postalCodePattern=Pattern.compile(Checks.POSTAL_CODE_REGEX);
        matcher=postalCodePattern.matcher(String.valueOf(loadingPostalCode));
        if(matcher.matches()==false)
            throw new FieldContainsException(); 
        
        if(cargoCount<=0 || cargoWeight<=0 || freightCost<=0)
            throw new FieldContainsException();
        
        if(Checks.isEmpty(carrierCompanyName)||
                Checks.isEmpty(carrierContact)||
                Checks.isEmpty(cargoDescription)||
                Checks.isEmpty(loadingCompanyName)||
                Checks.isEmpty(loadingAdress)||
                Checks.isEmpty(loadingCity)||
                Checks.isEmpty(loadingCountry)||
                Checks.isEmpty(unloadingCity) ||
                Checks.isEmpty(unloadingCountry)||
                Checks.isEmpty(unloadingClient)
                )
            throw new FieldContainsException();
        
        //orderPLforwaderID
        Message message=new Message(this.workerLogin,Actions.GetWorkerID); // 
        outputStream.writeObject(message);
        message=(Message) inputStream.readObject();
        int orderPLforwaderID=Integer.parseInt(message.getMessage());
        //orderBYforwarderID
        message=new Message(orderBYforwarder,Actions.GetWorkerID); // 
        outputStream.writeObject(message);
        message=(Message) inputStream.readObject();
        int orderBYforwaderID=Integer.parseInt(message.getMessage());
        //orderStatus
        String orderStatus="IsInImplementation";
        //numberOfOrder
        outputStream.writeObject(new Message(Actions.GenerateNewOrderNumber));
        message=(Message) inputStream.readObject();
        String numberOfOrder= message.getMessage();
        //dateOfOrder
        java.sql.Date dateOfOrder = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        //carrierID
        final Carrier carrier= new Carrier.CarrierBuilder(0,carrierCompanyName, carrierContact, carrierTelephone, carrierElMail,
                carrierForeignRegNumber, carrierDriverTelephoneNumber).createCarrier();
        outputStream.writeObject(new Message("Add carrier",Actions.AddCarrier,carrier));
        inputStream.readObject();
        outputStream.writeObject(new Message("get carrier id",Actions.GetCarrierID,carrier));
        message=(Message) inputStream.readObject();
        int carrierID= Integer.parseInt(message.getMessage());
        //cargoID
        final Cargo cargo = new Cargo.CargoBuilder(0,cargoDescription, cargoWeight, cargoCount, cargoDocument).createCargo();
        outputStream.writeObject(new Message("Add cargo",Actions.AddCargo,cargo));
        inputStream.readObject();
        outputStream.writeObject(new Message("get cargo id",Actions.GetCargoID,cargo));
        message=(Message) inputStream.readObject();
        int cargoID= Integer.parseInt(message.getMessage());
        //loadingID
        final Loading loading = new Loading.LoadingBuilder(0,loadingCompanyName, loadingAdress, loadingPostalCode, loadingCity,
                loadingCountry, loadingDate, loadingTime).createLoading();
        outputStream.writeObject(new Message("Add loading",Actions.AddLoading,loading));
        inputStream.readObject();
        outputStream.writeObject(new Message("get loading id",Actions.GetLoadingID,loading));
        message=(Message) inputStream.readObject();
        int loadingID= Integer.parseInt(message.getMessage());
        //unloading
        final Unloading unloading = new Unloading.UnloadingBuilder(0,unloadingStockID, unloadingClient, unloadingCity, unloadingCountry,
                unloadingDate,unloadingTime).createUnloading();
        outputStream.writeObject(new Message("Add unloading",Actions.AddUnloading,unloading));
        inputStream.readObject();
        outputStream.writeObject(new Message("get unloading id",Actions.GetUnloadingID,unloading));
        message=(Message) inputStream.readObject();
        int unloadingID= Integer.parseInt(message.getMessage());
        //payment
        final Payment payment= new Payment.PaymentBuilder(freightCost, paymentPeriod).createPayment();
        
        //create Order by Builders
        Order order= new Order.OrderBuilder(
                0,
                dateOfOrder,
                numberOfOrder,
                orderStatus,
                orderBYforwaderID,
                orderPLforwaderID,
                carrierID, 
                unloadingID,
                unloadingID,
                cargoID,
                payment).createOrder();
       
        return order;

        } catch (FieldContainsException ex) {
            return null; //Ошибка ввода данных
        } catch (IOException | ClassNotFoundException|NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Проверьте правильность введённых данных: вес товара,количество,ставка!!!","Ошибка",JOptionPane.ERROR_MESSAGE);
        }
         return null;   
    }
    
    private void confirmOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmOrderButtonActionPerformed
        try {
            
            Order order=isDataCorrect();
            if(order==null) {
                JOptionPane.showMessageDialog(null, "Проверьте правильность введённых данных!!!"+"\n"+"Номера телефонов должны быть международном формате(+код XXX XXX....)"
                    + "\n"+"Все поля должны быть заполнены","Ошибка",JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Message message= new Message("AddOrder",Actions.AddOrder,order);
            outputStream.writeObject(message);
            message=(Message) inputStream.readObject();
            
            if(message.getMessage().equals("Success"))
                JOptionPane.showMessageDialog(null, "Заказа на загрузку успешно добавлен!", "Добавление загрузки", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Невозможно добавить загрузку!", "Ошибка!!!", JOptionPane.ERROR_MESSAGE);
            
            this.сlear();
            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(AddOrderPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_confirmOrderButtonActionPerformed

    private void unloadingStockComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_unloadingStockComboBoxItemStateChanged
        // TODO add your handling code here:
        setUnloadingStockData();
    }//GEN-LAST:event_unloadingStockComboBoxItemStateChanged
        
    //clear all fiels of panel
    public  void сlear() {
        for(Component control : this.getComponents())
        {
            if(control instanceof JTextField)
            {
                JTextField ctrl = (JTextField) control;
                ctrl.setText("");
            }
            else if (control instanceof JComboBox)
            {
                JComboBox ctr = (JComboBox) control;
                ctr.setSelectedIndex(0);
            }
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cargoCountField;
    private javax.swing.JTextArea cargoDescriptionTextArea;
    private javax.swing.JComboBox<String> cargoDocumentComboBox;
    private javax.swing.JTextField cargoWeightField;
    private javax.swing.JTextField carrierCompanyNameField;
    private javax.swing.JTextField carrierContactField;
    private javax.swing.JTextField carrierDriverTelephoneNumberField;
    private javax.swing.JTextField carrierElMailField;
    private javax.swing.JTextField carrierForeignRegNumberField;
    private javax.swing.JTextField carrierTelephoneField;
    private javax.swing.JButton confirmOrderButton;
    private javax.swing.JTextField freightCostTextField;
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
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTextField loadingAdressTextField;
    private javax.swing.JTextField loadingCityTextField;
    private javax.swing.JTextField loadingCompanyNameTextField;
    private javax.swing.JTextField loadingCountryTextField;
    private com.toedter.calendar.JDateChooser loadingDateChooser;
    private javax.swing.JTextField loadingPostalCodeTextField;
    private javax.swing.JSpinner loadingTimeSpinner;
    private javax.swing.JComboBox<String> orderBYforwarderComboBox;
    private javax.swing.JComboBox<String> paymentPeriodComboBox;
    private javax.swing.JTextField unloadingCityTextField;
    private javax.swing.JTextField unloadingClientTextField;
    private javax.swing.JTextField unloadingCountryTextField;
    private com.toedter.calendar.JDateChooser unloadingDateChooser;
    private javax.swing.JTextField unloadingStockAdressTextField;
    private javax.swing.JTextField unloadingStockCityTextField;
    private javax.swing.JComboBox<String> unloadingStockComboBox;
    private javax.swing.JTextField unloadingStockCountryTextField;
    private javax.swing.JTextField unloadingStockNameTextField;
    private javax.swing.JTextField unloadingStockPostalCodeTextField;
    private javax.swing.JSpinner unloadingTimeSpinner;
    // End of variables declaration//GEN-END:variables
}
