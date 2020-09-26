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
import by.bsuir.pokos.database.dao.entity.Stock;
import by.bsuir.pokos.database.dao.entity.Unloading;
import by.bsuir.pokos.message.Message;
import by.bsuir.pokos.utils.Actions;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Gavrilik
 */
public class OrderPanel extends javax.swing.JPanel {

    public ObjectOutputStream outputStream;
    public ObjectInputStream inputStream;
    public String workerLogin;
    public Order order;
    public Map statusOfOrder;
    
    public OrderPanel(ObjectOutputStream outputStream,ObjectInputStream inputStream,String workerLogin) {
        this.outputStream=outputStream;
        this.inputStream=inputStream;
        this.workerLogin=workerLogin;
        initComponents();
        statusOfOrder = new HashMap<String,String>();
        statusOfOrder.put("Нет на складе","IsInImplementation");
        statusOfOrder.put("Находится на складе","IsInStock");
        statusOfOrder.put("Готов к отправке клиенту","IsLoaded");
        statusOfOrder.put("Доставлен клиенту","IsUnloaded");
    }

    public void showOrder(int orderID) {
        
        try {
            
            outputStream.writeObject(new Message(String.valueOf(orderID),Actions.GetOrderByID));
            Message message=(Message)inputStream.readObject();
            order=(Order)message.getStructure();
            //cargo
            outputStream.writeObject(new Message(String.valueOf(order.getCargoID()),Actions.GetCargoByID));
            message= (Message)inputStream.readObject();
            Cargo cargo = (Cargo)message.getStructure();
            //BYforwarder
            outputStream.writeObject(new Message(String.valueOf(order.getOrderBYforwarderID()),Actions.GetWorkerNameByID));
            message=(Message)inputStream.readObject();
            String BYforwarder=message.getMessage();
            //PLforwarder    
            outputStream.writeObject(new Message(String.valueOf(order.getOrderPLforwarderID()),Actions.GetWorkerNameByID));
            message=(Message)inputStream.readObject();
            String PLforwarder=message.getMessage();
            //unloading
            outputStream.writeObject(new Message(String.valueOf(order.getUnloadingID()),Actions.GetUnloadingByID));
            message= (Message)inputStream.readObject();
            Unloading unloading = (Unloading)message.getStructure();
            //loading
            outputStream.writeObject(new Message(String.valueOf(order.getLoadingID()),Actions.GetLoadingByID));
            message= (Message)inputStream.readObject();
            Loading loading = (Loading)message.getStructure();
            //carrier
            outputStream.writeObject(new Message(String.valueOf(order.getCarrierID()),Actions.GetCarrierByID));
            message= (Message)inputStream.readObject();
            Carrier carrier = (Carrier)message.getStructure();
            //stock
            outputStream.writeObject(new Message(String.valueOf(unloading.getUnloadingStockID()),Actions.GetStockByID));
            message= (Message)inputStream.readObject();
            Stock stock = (Stock)message.getStructure();
            
            
            //fill fields 
            numberOfOrderLabel.setText(order.getNumberOfOrder());
            orderStatusField.setText(carrier.getCarrierForeignRegNumber());
            dateOfOrderField.setText(order.getDateOfOrder().toString());
            carrierCompanyNameField1.setText(carrier.getCarrierCompanyName());
            carrierContactField.setText(carrier.getCarrierContact());
            carrierTelephoneField.setText(carrier.getCarrierTelephone());
            carrierElMailField.setText(carrier.getCarrierElMail());
            carrierForeignRegNumberField.setText(carrier.getCarrierForeignRegNumber());
            carrierForeignRegNumberFieldHigh1.setText(carrier.getCarrierForeignRegNumber());
            carrierDriverTelephoneNumberField1.setText(carrier.getCarrierDriverTelephoneNumber());
            cargoDescriptionField.setText(cargo.getCargoDescription());
            cargoWeightField.setText(String.valueOf(cargo.getCargoWeight()));
            cargoCountField.setText(String.valueOf(cargo.getCargoCount()));
            cargoDocumentField1.setText(cargo.getCargoDocument());
            loadingDateField1.setText(loading.getLoadingDate().toString());
            loadingTimeField.setText(loading.getLoadingTime().toString());
            loadingCompanyNameTextField1.setText(loading.getLoadingCompanyName());
            loadingAdressTextField.setText(loading.getLoadingAdress());
            loadingCountryTextField.setText(loading.getLoadingCountry());
            loadingPostalCodeTextField.setText(loading.getLoadingPostalCode());
            loadingCityTextField.setText(loading.getLoadingCity());
            unloadingDateField.setText(unloading.getUnloadingDate().toString());
            unloadingTimeField.setText(unloading.getUnloadingTime().toString());
            unloadingClientTextField.setText(unloading.getUnloadingCity());
            unloadingCityTextField.setText(unloading.getUnloadingCity());
            unloadingCountryTextField.setText(unloading.getUnloadingCountry());
            orderBYforwarderField1.setText(BYforwarder);
            freightCostTextField1.setText(String.valueOf(order.getPayment().getFreightCost()));
            paymentPeriodField.setText(order.getPayment().getPaymentPeriod());
            orderPLforwarderField.setText(PLforwarder);
            unloadingStockNameField.setText(stock.getStockName());
            unloadingStockAdressTextField.setText((stock.getStockAdress()));
            unloadingStockCityTextField.setText((stock.getStockCity()));
            unloadingStockPostalCodeTextField.setText(stock.getStockPostalCode());
            unloadingStockCountryTextField.setText(stock.getStockCountry());
            
            switch(order.getOrderStatus()) {
                case "IsInImplementation":
                    orderStatusField.setText("Нет на складе");
                    break;
                case "IsInStock":
                    orderStatusField.setText("Находится на складе");
                    break;
                case "IsLoaded":
                    orderStatusField.setText("Готов к отправке клиенту");
                    break;
                case "IsUnloaded":
                    orderStatusField.setText("Доставлен клиенту");
                    changedStatusOfOrderComboBox.setEnabled(false);
                    createCMRButton.setEnabled(false);
                    break;
            }
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(OrderPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderPanel.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lLabel123 = new javax.swing.JLabel();
        numberOfOrderLabel = new javax.swing.JLabel();
        orderStatusField = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        dateOfOrderField = new javax.swing.JTextField();
        carrierContactField = new javax.swing.JTextField();
        carrierTelephoneField = new javax.swing.JTextField();
        carrierElMailField = new javax.swing.JTextField();
        carrierForeignRegNumberField = new javax.swing.JTextField();
        cargoDescriptionField = new javax.swing.JTextField();
        carrierDriverTelephoneNumberField1 = new javax.swing.JTextField();
        cargoWeightField = new javax.swing.JTextField();
        cargoCountField = new javax.swing.JTextField();
        loadingTimeField = new javax.swing.JTextField();
        cargoDocumentField1 = new javax.swing.JTextField();
        loadingDateField1 = new javax.swing.JTextField();
        unloadingStockNameField = new javax.swing.JTextField();
        loadingAdressTextField = new javax.swing.JTextField();
        loadingCountryTextField = new javax.swing.JTextField();
        loadingPostalCodeTextField = new javax.swing.JTextField();
        loadingCityTextField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        unloadingDateField = new javax.swing.JTextField();
        unloadingTimeField = new javax.swing.JTextField();
        loadingCompanyNameTextField1 = new javax.swing.JTextField();
        unloadingStockAdressTextField = new javax.swing.JTextField();
        unloadingStockCityTextField = new javax.swing.JTextField();
        unloadingStockPostalCodeTextField = new javax.swing.JTextField();
        unloadingStockCountryTextField = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        unloadingClientTextField = new javax.swing.JTextField();
        unloadingCityTextField = new javax.swing.JTextField();
        unloadingCountryTextField = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        orderPLforwarderField = new javax.swing.JTextField();
        paymentPeriodField = new javax.swing.JTextField();
        freightCostTextField1 = new javax.swing.JTextField();
        orderBYforwarderField1 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        carrierCompanyNameField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        changedStatusOfOrderComboBox = new javax.swing.JComboBox<>();
        createCMRButton = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        carrierForeignRegNumberFieldHigh1 = new javax.swing.JLabel();
        jchangeStatusOfOrderButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("Дата создания:");

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(61, 139, 212));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Заказ №: ");

        lLabel123.setBackground(new java.awt.Color(255, 255, 255));
        lLabel123.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lLabel123.setForeground(new java.awt.Color(212, 109, 23));
        lLabel123.setText("Перевозчик:");

        numberOfOrderLabel.setBackground(new java.awt.Color(255, 255, 255));
        numberOfOrderLabel.setFont(new java.awt.Font("League Gothic", 0, 30)); // NOI18N
        numberOfOrderLabel.setForeground(new java.awt.Color(61, 139, 212));

        orderStatusField.setFont(new java.awt.Font("League Gothic", 0, 30)); // NOI18N
        orderStatusField.setForeground(new java.awt.Color(61, 139, 212));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Перевозчик:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("<html>Регистрационный<br>номер</html>");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Тел. водителя:");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("О товаре:");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Вес груза:");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Количество:");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Документ:");
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Срок поставки:");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Адресс загрузки:");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        dateOfOrderField.setEditable(false);
        dateOfOrderField.setBackground(new java.awt.Color(255, 255, 255));
        dateOfOrderField.setColumns(12);
        dateOfOrderField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        dateOfOrderField.setForeground(new java.awt.Color(153, 153, 153));
        dateOfOrderField.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        dateOfOrderField.setSelectionColor(new java.awt.Color(255, 255, 255));

        carrierContactField.setEditable(false);
        carrierContactField.setBackground(new java.awt.Color(255, 255, 255));
        carrierContactField.setColumns(12);
        carrierContactField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        carrierContactField.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        carrierContactField.setSelectionColor(new java.awt.Color(255, 255, 255));

        carrierTelephoneField.setEditable(false);
        carrierTelephoneField.setBackground(new java.awt.Color(255, 255, 255));
        carrierTelephoneField.setColumns(12);
        carrierTelephoneField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        carrierTelephoneField.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        carrierTelephoneField.setSelectionColor(new java.awt.Color(255, 255, 255));

        carrierElMailField.setEditable(false);
        carrierElMailField.setBackground(new java.awt.Color(255, 255, 255));
        carrierElMailField.setColumns(5);
        carrierElMailField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        carrierElMailField.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        carrierElMailField.setSelectionColor(new java.awt.Color(255, 255, 255));

        carrierForeignRegNumberField.setEditable(false);
        carrierForeignRegNumberField.setBackground(new java.awt.Color(255, 255, 255));
        carrierForeignRegNumberField.setColumns(12);
        carrierForeignRegNumberField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        cargoDescriptionField.setEditable(false);
        cargoDescriptionField.setBackground(new java.awt.Color(255, 255, 255));
        cargoDescriptionField.setColumns(12);
        cargoDescriptionField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        carrierDriverTelephoneNumberField1.setEditable(false);
        carrierDriverTelephoneNumberField1.setBackground(new java.awt.Color(255, 255, 255));
        carrierDriverTelephoneNumberField1.setColumns(12);
        carrierDriverTelephoneNumberField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        cargoWeightField.setEditable(false);
        cargoWeightField.setBackground(new java.awt.Color(255, 255, 255));
        cargoWeightField.setColumns(5);
        cargoWeightField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        cargoCountField.setEditable(false);
        cargoCountField.setBackground(new java.awt.Color(255, 255, 255));
        cargoCountField.setColumns(5);
        cargoCountField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        loadingTimeField.setEditable(false);
        loadingTimeField.setBackground(new java.awt.Color(255, 255, 255));
        loadingTimeField.setColumns(5);
        loadingTimeField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        cargoDocumentField1.setEditable(false);
        cargoDocumentField1.setBackground(new java.awt.Color(255, 255, 255));
        cargoDocumentField1.setColumns(5);
        cargoDocumentField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        loadingDateField1.setEditable(false);
        loadingDateField1.setBackground(new java.awt.Color(255, 255, 255));
        loadingDateField1.setColumns(5);
        loadingDateField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        unloadingStockNameField.setEditable(false);
        unloadingStockNameField.setBackground(new java.awt.Color(255, 255, 255));
        unloadingStockNameField.setColumns(5);
        unloadingStockNameField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        loadingAdressTextField.setEditable(false);
        loadingAdressTextField.setBackground(new java.awt.Color(255, 255, 255));
        loadingAdressTextField.setColumns(5);
        loadingAdressTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        loadingCountryTextField.setEditable(false);
        loadingCountryTextField.setBackground(new java.awt.Color(255, 255, 255));
        loadingCountryTextField.setColumns(5);
        loadingCountryTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        loadingPostalCodeTextField.setEditable(false);
        loadingPostalCodeTextField.setBackground(new java.awt.Color(255, 255, 255));
        loadingPostalCodeTextField.setColumns(5);
        loadingPostalCodeTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        loadingCityTextField.setEditable(false);
        loadingCityTextField.setBackground(new java.awt.Color(255, 255, 255));
        loadingCityTextField.setColumns(5);
        loadingCityTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Адресс разгрузки:");
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Дата загрузки:");
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        unloadingDateField.setEditable(false);
        unloadingDateField.setBackground(new java.awt.Color(255, 255, 255));
        unloadingDateField.setColumns(5);
        unloadingDateField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        unloadingTimeField.setEditable(false);
        unloadingTimeField.setBackground(new java.awt.Color(255, 255, 255));
        unloadingTimeField.setColumns(5);
        unloadingTimeField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        loadingCompanyNameTextField1.setEditable(false);
        loadingCompanyNameTextField1.setBackground(new java.awt.Color(255, 255, 255));
        loadingCompanyNameTextField1.setColumns(5);
        loadingCompanyNameTextField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        unloadingStockAdressTextField.setEditable(false);
        unloadingStockAdressTextField.setBackground(new java.awt.Color(255, 255, 255));
        unloadingStockAdressTextField.setColumns(5);
        unloadingStockAdressTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        unloadingStockCityTextField.setEditable(false);
        unloadingStockCityTextField.setBackground(new java.awt.Color(255, 255, 255));
        unloadingStockCityTextField.setColumns(5);
        unloadingStockCityTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        unloadingStockPostalCodeTextField.setEditable(false);
        unloadingStockPostalCodeTextField.setBackground(new java.awt.Color(255, 255, 255));
        unloadingStockPostalCodeTextField.setColumns(5);
        unloadingStockPostalCodeTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        unloadingStockCountryTextField.setEditable(false);
        unloadingStockCountryTextField.setBackground(new java.awt.Color(255, 255, 255));
        unloadingStockCountryTextField.setColumns(5);
        unloadingStockCountryTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Статус груза:");
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        unloadingClientTextField.setEditable(false);
        unloadingClientTextField.setBackground(new java.awt.Color(255, 255, 255));
        unloadingClientTextField.setColumns(5);
        unloadingClientTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        unloadingCityTextField.setEditable(false);
        unloadingCityTextField.setBackground(new java.awt.Color(255, 255, 255));
        unloadingCityTextField.setColumns(5);
        unloadingCityTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        unloadingCountryTextField.setEditable(false);
        unloadingCountryTextField.setBackground(new java.awt.Color(255, 255, 255));
        unloadingCountryTextField.setColumns(5);
        unloadingCountryTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Экспедитор BY:");
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Сумма(EUR):");
        jLabel18.setToolTipText("");
        jLabel18.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Срок оплаты:");
        jLabel19.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("Заказчик:");
        jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        orderPLforwarderField.setEditable(false);
        orderPLforwarderField.setBackground(new java.awt.Color(255, 255, 255));
        orderPLforwarderField.setColumns(5);
        orderPLforwarderField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        paymentPeriodField.setEditable(false);
        paymentPeriodField.setBackground(new java.awt.Color(255, 255, 255));
        paymentPeriodField.setColumns(5);
        paymentPeriodField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        freightCostTextField1.setEditable(false);
        freightCostTextField1.setBackground(new java.awt.Color(255, 255, 255));
        freightCostTextField1.setColumns(5);
        freightCostTextField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        orderBYforwarderField1.setEditable(false);
        orderBYforwarderField1.setBackground(new java.awt.Color(255, 255, 255));
        orderBYforwarderField1.setColumns(5);
        orderBYforwarderField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        carrierCompanyNameField1.setEditable(false);
        carrierCompanyNameField1.setBackground(new java.awt.Color(255, 255, 255));
        carrierCompanyNameField1.setColumns(12);
        carrierCompanyNameField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        carrierCompanyNameField1.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        carrierCompanyNameField1.setSelectionColor(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText(",");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText(",");

        jLabel21.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("Клиент:");
        jLabel21.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        changedStatusOfOrderComboBox.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        changedStatusOfOrderComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Находится на складе", "Готов к отправке клиенту", "Доставлен клиенту" }));

        createCMRButton.setBackground(new java.awt.Color(61, 139, 212));
        createCMRButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        createCMRButton.setForeground(new java.awt.Color(255, 255, 255));
        createCMRButton.setText("Создать CMR");

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(61, 139, 212));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("Статус груза:");

        carrierForeignRegNumberFieldHigh1.setFont(new java.awt.Font("League Gothic", 0, 30)); // NOI18N
        carrierForeignRegNumberFieldHigh1.setForeground(new java.awt.Color(212, 109, 23));

        jchangeStatusOfOrderButton1.setBackground(new java.awt.Color(61, 139, 212));
        jchangeStatusOfOrderButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jchangeStatusOfOrderButton1.setForeground(new java.awt.Color(255, 255, 255));
        jchangeStatusOfOrderButton1.setText("Изменить статус");
        jchangeStatusOfOrderButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jchangeStatusOfOrderButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(225, 225, 225)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(unloadingStockAdressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(unloadingStockCountryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(unloadingStockCityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(unloadingStockPostalCodeTextField))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(unloadingDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(unloadingTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(unloadingStockNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(unloadingCountryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(unloadingCityTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                                            .addComponent(unloadingClientTextField))))))
                        .addGap(765, 765, 765))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(756, 756, 756)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(carrierTelephoneField, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(carrierElMailField, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(carrierCompanyNameField1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(carrierContactField, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(521, 521, 521)
                                                .addComponent(dateOfOrderField, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cargoWeightField, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cargoDescriptionField, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(carrierDriverTelephoneNumberField1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(orderBYforwarderField1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(freightCostTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(paymentPeriodField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(orderPLforwarderField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(284, 284, 284)
                                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(changedStatusOfOrderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(338, 338, 338)
                                                .addComponent(jchangeStatusOfOrderButton1)
                                                .addGap(151, 151, 151)
                                                .addComponent(createCMRButton))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(numberOfOrderLabel)
                                        .addGap(556, 556, 556)
                                        .addComponent(lLabel123)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(carrierForeignRegNumberFieldHigh1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel22))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cargoCountField, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(orderStatusField, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1096, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1092, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(carrierForeignRegNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator2)
                            .addComponent(jSeparator7))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(loadingCountryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(loadingPostalCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(loadingCompanyNameTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(loadingDateField1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(loadingTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(cargoDocumentField1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(loadingAdressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(loadingCityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(orderStatusField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(carrierForeignRegNumberFieldHigh1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lLabel123)
                            .addComponent(numberOfOrderLabel))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(carrierCompanyNameField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(carrierContactField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateOfOrderField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(carrierTelephoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(carrierElMailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(carrierForeignRegNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(carrierDriverTelephoneNumberField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cargoDescriptionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cargoWeightField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cargoCountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cargoDocumentField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(loadingDateField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(loadingTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(unloadingTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(unloadingDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator5)
                    .addComponent(jSeparator4))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(loadingCompanyNameTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)
                        .addComponent(unloadingStockNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(unloadingStockAdressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loadingAdressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(loadingCountryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(unloadingStockCityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(unloadingStockPostalCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(loadingPostalCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel15)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(unloadingStockCountryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loadingCityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(unloadingClientTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(unloadingCityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(unloadingCountryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(orderBYforwarderField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(changedStatusOfOrderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(freightCostTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(paymentPeriodField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(orderPLforwarderField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jchangeStatusOfOrderButton1)
                        .addComponent(createCMRButton)))
                .addGap(11, 11, 11))
        );

        unloadingStockAdressTextField.setEditable(false);
        unloadingStockCityTextField.setEditable(false);
        unloadingStockPostalCodeTextField.setEditable(false);
        unloadingStockCountryTextField.setEditable(false);
    }// </editor-fold>//GEN-END:initComponents

    private void jchangeStatusOfOrderButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchangeStatusOfOrderButton1ActionPerformed
        // TODO add your handling code here:
         try {
            // TODO add your handling code here:
            outputStream.writeObject(new Message(this.workerLogin,Actions.GetWorkerPostByLogin));
            Message message= (Message)inputStream.readObject();
            
            if(message.getMessage().equals("storekeeper")==false) {
                JOptionPane.showMessageDialog(null, "У вас нет прав для для данной операции!!!","Ошибка",JOptionPane.ERROR_MESSAGE);
            }else {
               if(canStatusOfOrderBeChanged(changedStatusOfOrderComboBox.getSelectedItem().toString()))
               {
                   //"новый статус+IDзаказа"
                   String changeOrderStatusDATA=((String) statusOfOrder.get(changedStatusOfOrderComboBox.getSelectedItem()))+" "+String.valueOf(order.getID());
                   outputStream.writeObject(new Message(changeOrderStatusDATA,Actions.ChangtStatusOfOrder));
                   message= (Message)inputStream.readObject();
                   if(message.getMessage().equals("Success")) {
                        JOptionPane.showMessageDialog(null, "Статус груза успешно изменён!!!","Изменение статуса груза",JOptionPane.INFORMATION_MESSAGE);  
                        orderStatusField.setText(changedStatusOfOrderComboBox.getSelectedItem().toString());
                   }
                   else
                        JOptionPane.showMessageDialog(null, "Ошибка!!!","Ошибка",JOptionPane.ERROR_MESSAGE);  
               }
               
            }
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(OrderPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jchangeStatusOfOrderButton1ActionPerformed

    public boolean canStatusOfOrderBeChanged(String newStatus){
        switch(orderStatusField.getText()) {
            case "Нет на складе":
                if(newStatus.equals("Находится на складе")==false) {
                    JOptionPane.showMessageDialog(null, "Груз ещё не доставлен на склад!!!","Ошибка",JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                break;
            case "Находится на складе":
                if(newStatus.equals("Готов к отправке клиенту")==false) {
                    JOptionPane.showMessageDialog(null, "Груз уже находится на складе!!!","Ошибка",JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                break;
            case "Готов к отправке клиенту":
                if(newStatus.equals("Доставлен клиенту")==false) {
                    JOptionPane.showMessageDialog(null, "!!!","Груз уже загружен для отправки к заказчику",JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                break;
        }
        
        return true;
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cargoCountField;
    private javax.swing.JTextField cargoDescriptionField;
    private javax.swing.JTextField cargoDocumentField1;
    private javax.swing.JTextField cargoWeightField;
    private javax.swing.JTextField carrierCompanyNameField1;
    private javax.swing.JTextField carrierContactField;
    private javax.swing.JTextField carrierDriverTelephoneNumberField1;
    private javax.swing.JTextField carrierElMailField;
    private javax.swing.JTextField carrierForeignRegNumberField;
    private javax.swing.JLabel carrierForeignRegNumberFieldHigh1;
    private javax.swing.JTextField carrierTelephoneField;
    private javax.swing.JComboBox<String> changedStatusOfOrderComboBox;
    private javax.swing.JButton createCMRButton;
    private javax.swing.JTextField dateOfOrderField;
    private javax.swing.JTextField freightCostTextField1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JButton jchangeStatusOfOrderButton1;
    private javax.swing.JLabel lLabel123;
    private javax.swing.JTextField loadingAdressTextField;
    private javax.swing.JTextField loadingCityTextField;
    private javax.swing.JTextField loadingCompanyNameTextField1;
    private javax.swing.JTextField loadingCountryTextField;
    private javax.swing.JTextField loadingDateField1;
    private javax.swing.JTextField loadingPostalCodeTextField;
    private javax.swing.JTextField loadingTimeField;
    private javax.swing.JLabel numberOfOrderLabel;
    private javax.swing.JTextField orderBYforwarderField1;
    private javax.swing.JTextField orderPLforwarderField;
    private javax.swing.JLabel orderStatusField;
    private javax.swing.JTextField paymentPeriodField;
    private javax.swing.JTextField unloadingCityTextField;
    private javax.swing.JTextField unloadingClientTextField;
    private javax.swing.JTextField unloadingCountryTextField;
    private javax.swing.JTextField unloadingDateField;
    private javax.swing.JTextField unloadingStockAdressTextField;
    private javax.swing.JTextField unloadingStockCityTextField;
    private javax.swing.JTextField unloadingStockCountryTextField;
    private javax.swing.JTextField unloadingStockNameField;
    private javax.swing.JTextField unloadingStockPostalCodeTextField;
    private javax.swing.JTextField unloadingTimeField;
    // End of variables declaration//GEN-END:variables
}
