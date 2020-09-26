package by.bsuir.pokos.gui.frames;

import by.bsuir.pokos.message.Message;
import by.bsuir.pokos.message.ShowAllWorkersDATA;
import by.bsuir.pokos.message.User;
import by.bsuir.pokos.utils.Actions;
import by.bsuir.pokos.utils.Checks;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class AdminFrame extends javax.swing.JFrame {

    public ObjectOutputStream outputStream;
    public ObjectInputStream inputStream;
    private final String workerLogin;
    private DefaultTableModel dm;
    private  ArrayList<User> workers;
    private int choosenUserId;
    private int choosenRoleID;

    public AdminFrame(ObjectOutputStream outputStream, ObjectInputStream inputStream, String login) {
        this.outputStream = outputStream;
        this.inputStream = inputStream;
        this.workerLogin = login;
        initComponents();
        dm = (DefaultTableModel) usersTable.getModel();
        showUsers();
        sort_table();
        loginWorkerField.setText(login);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        usersTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        postComBox = new javax.swing.JComboBox<>();
        nameField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        loginField = new javax.swing.JTextField();
        passwordField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        addUserButton = new javax.swing.JButton();
        deleteUserButton = new javax.swing.JButton();
        changeUserButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        loginWorkerField = new javax.swing.JLabel();
        exitLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(84, 174, 213));

        usersTable.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
        usersTable.setAutoCreateRowSorter(true);
        usersTable.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        usersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Должность", "Имя сотрудника", "Логин", "Пароль"
            }
        ));
        usersTable.setGridColor(new java.awt.Color(0, 0, 0));
        usersTable.setShowGrid(true);
        usersTable.setRowHeight(30);
        usersTable.setSelectionBackground(new java.awt.Color(244, 150, 56));
        usersTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usersTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(usersTable);
        if (usersTable.getColumnModel().getColumnCount() > 0) {
            usersTable.getColumnModel().getColumn(1).setMinWidth(200);
        }
        JTableHeader header = usersTable.getTableHeader();
        header.setFont(new Font("Serif", Font.PLAIN, 16));
        header.setForeground(new Color(55,113,229));
        ((DefaultTableCellRenderer)usersTable.getTableHeader().getDefaultRenderer())
        .setHorizontalAlignment(JLabel.CENTER);
        usersTable.setDefaultEditor(Object.class, null);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Должность:");

        postComBox.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        postComBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Экспедитор", "Управляющий складом", "Администратор" }));

        nameField.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Имя сотрудника:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Логин:");

        loginField.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        passwordField.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Пароль:");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/leaf-icon.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("PT Sans", 1, 32)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(244, 150, 56));
        jLabel1.setText("SllS ");

        jLabel7.setFont(new java.awt.Font("PT Sans", 3, 32)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Application");

        jLabel8.setFont(new java.awt.Font("PT Sans", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("\u00a9 Gavrilik");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Сотрудники");

        addUserButton.setFont(new java.awt.Font("League Gothic", 0, 24)); // NOI18N
        addUserButton.setText("Зарегистрировать");
        addUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserButtonActionPerformed(evt);
            }
        });

        deleteUserButton.setFont(new java.awt.Font("League Gothic", 0, 24)); // NOI18N
        deleteUserButton.setText("Удалить");
        deleteUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteUserButtonActionPerformed(evt);
            }
        });

        changeUserButton.setFont(new java.awt.Font("League Gothic", 0, 24)); // NOI18N
        changeUserButton.setText("Изменить");
        changeUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeUserButtonActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Авторизирован:");

        loginWorkerField.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        loginWorkerField.setForeground(new java.awt.Color(255, 255, 255));
        loginWorkerField.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        exitLabel.setFont(new java.awt.Font("League Gothic", 0, 22)); // NOI18N
        exitLabel.setForeground(new java.awt.Color(255, 255, 255));
        exitLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/exit-icon.png"))); // NOI18N
        exitLabel.setText("Выйти");
        exitLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitLabelMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(126, 126, 126)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(64, 64, 64)
                                        .addComponent(jLabel8)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 23, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(loginField, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(postComBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(deleteUserButton)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(addUserButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(changeUserButton))
                                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loginWorkerField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(301, 301, 301)
                        .addComponent(exitLabel)
                        .addGap(21, 21, 21))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 849, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jLabel7))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(postComBox, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(loginField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addUserButton)
                            .addComponent(changeUserButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteUserButton)
                        .addGap(192, 192, 192)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(loginWorkerField, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(exitLabel)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void sort_table() {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(dm);
        usersTable.setRowSorter(sorter);
    }

    public void showUsers() {

        try {

            workers = new ArrayList<>();
            ShowAllWorkersDATA data=  new ShowAllWorkersDATA();
            
            Message message = new Message(Actions.GetAllWorkers);
            outputStream.writeObject(message);

            message = (Message) inputStream.readObject();
            data = (ShowAllWorkersDATA) message.getStructure();
            workers=data.getWorkers();
            
            DefaultTableModel model = (DefaultTableModel) usersTable.getModel();
            String[] row;

            for (int i = 0; i < workers.size(); i++) {
                row = new String[4];

                outputStream.writeObject(new Message(workers.get(i).getLogin(),Actions.GetWorkerPostByLogin));
                message = (Message) inputStream.readObject();
                switch(message.getMessage()) {
                    case "storekeeper":
                        row[0]="Управляющий складом";break;
                    case "admin":
                        row[0]="Администратор";break;
                    case "forwarder":
                        row[0]="Экспедитор";break;
                }
                
                row[1]=workers.get(i).getName();
                row[2]=workers.get(i).getLogin();
                row[3]=workers.get(i).getPassword();
                model.addRow(row);
            }

        } catch (IOException ex) {
            Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateUsersTable() {
        DefaultTableModel model=(DefaultTableModel) usersTable.getModel();
        model.setRowCount(0);
        showUsers();
    }
    
    private void usersTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersTableMouseClicked

        // display choose row
        int i=usersTable.getSelectedRow();
        TableModel model=usersTable.getModel();

        String post=model.getValueAt(i, 0).toString();
        switch(post){
            case "Продавец": postComBox.setSelectedIndex(0);break;
            case "Управляющий складом":postComBox.setSelectedIndex(1);break;
            case "Администратор":postComBox.setSelectedIndex(2);break;
        }
        nameField.setText(model.getValueAt(i, 1).toString());
        loginField.setText(model.getValueAt(i, 2).toString());
        passwordField.setText(model.getValueAt(i, 3).toString());
        
        choosenUserId=workers.get(i).getId();
        choosenRoleID=workers.get(i).getPostID();
    }//GEN-LAST:event_usersTableMouseClicked

    private void exitLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitLabelMouseClicked
        // TODO add your handling code here:
        this.dispose();
        try {
            outputStream.close();
            inputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(WorkerFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        MainFrame frame =new MainFrame();
        frame.setVisible(true);

    }//GEN-LAST:event_exitLabelMouseClicked

    private void exitLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitLabelMouseEntered
        // TODO add your handling code here:
        exitLabel.setForeground(Color.BLACK);
    }//GEN-LAST:event_exitLabelMouseEntered

    private void exitLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitLabelMouseExited
        // TODO add your handling code here:
        exitLabel.setForeground(Color.WHITE);
    }//GEN-LAST:event_exitLabelMouseExited

    private void addUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserButtonActionPerformed
        try {
            // TODO add your handling code here:
            String name=nameField.getText();
            String login=loginField.getText();
            String password=passwordField.getText();
            int roleID=-1;
            
            switch(postComBox.getSelectedItem().toString()) {
                case "Экспедитор":roleID=3;break;
                case "Управляющий складом":roleID=2;break;
                case "Администратор":roleID=1;break;
            }
            
            if(Checks.isEmpty(name)||Checks.isEmpty(login)||Checks.isEmpty(password)) {
                JOptionPane.showMessageDialog(null, "Пожалуйста,проверье корректность введённых данных","Внимание",JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(postComBox.getSelectedItem().toString().equals("Администратор")) {
                JOptionPane.showMessageDialog(null, "Нельзя зарегистрировать нового администратора","Внимание",JOptionPane.WARNING_MESSAGE);
                return;
            }
            outputStream.writeObject(new Message(login,Actions.GetWorkerID));
            Message message=(Message)inputStream.readObject();
            if(message.getMessage().equals("-1")==false)
            {
                JOptionPane.showMessageDialog(null, "Данный логин занят!!!","Внимание",JOptionPane.WARNING_MESSAGE);
                return;
            }
            outputStream.writeObject(new Message("add worker",Actions.AddWorker,new User(0,roleID,login,password,name)));
            message=(Message)inputStream.readObject();
            if(message.getMessage().equals("Success")) {
                JOptionPane.showMessageDialog(null, "Пользователь успешно добавлен!!!","Регистрация",JOptionPane.INFORMATION_MESSAGE);
                updateUsersTable();
            }
            else 
                JOptionPane.showMessageDialog(null, "Ошибка регистрации!!!","Внимание",JOptionPane.WARNING_MESSAGE);
            
            
        } catch (IOException ex) {
            Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addUserButtonActionPerformed

    private void changeUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeUserButtonActionPerformed
        try {
            // TODO add your handling code here:
            String name=nameField.getText();
            String login=loginField.getText();
            String password=passwordField.getText();
            int roleID=-1;
            switch(postComBox.getSelectedItem().toString()) {
                case "Экспедитор":roleID=3;break;
                case "Управляющий складом":roleID=2;break;
                case "Администратор":roleID=1;break;
            }
            
            if(Checks.isEmpty(name)||Checks.isEmpty(login)||Checks.isEmpty(password)) {
                JOptionPane.showMessageDialog(null, "Пожалуйста,проверье корректность введённых данных","Внимание",JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if(choosenRoleID!=1 && roleID==1) { //пытаемся сделать администратором не администратора
                JOptionPane.showMessageDialog(null, "Данный пользователь не является администратором!!!","Внимание",JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            Message message = new Message("Изменение данных пользователя",Actions.ChangeWorker,new User(choosenUserId, choosenRoleID, login, password, name));
            outputStream.writeObject(message);
            message = (Message) inputStream.readObject();
            
            if (message.getMessage().equals("Success")) {
                JOptionPane.showMessageDialog(null, "Информация о сотруднике изменена", "Изменение", JOptionPane.INFORMATION_MESSAGE);
                updateUsersTable();
            }
            else
                JOptionPane.showMessageDialog(null, "Ошибка!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }//GEN-LAST:event_changeUserButtonActionPerformed

    private void deleteUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteUserButtonActionPerformed
        try {
            // TODO add your handling code here:
            boolean isSelected=false;
            for(int i=0;i<usersTable.getRowCount();i++) {
                if(usersTable.isRowSelected(i)) {
                    isSelected=true;
                     break;
                }
            }
            
            if(isSelected==false) {
                JOptionPane.showMessageDialog(null, "Сотрудник не выбран", "Удаление", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if(choosenRoleID==1) {
                JOptionPane.showMessageDialog(null, "Нельзя удалить администатора", "Удаление", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            Message message = new Message(String.valueOf(choosenUserId),Actions.DeleteWorker);
            outputStream.writeObject(message);
            message = (Message) inputStream.readObject();
            
            if (message.getMessage().equals("Success")) {
                JOptionPane.showMessageDialog(null, "Пользователь удалён", "Удаление", JOptionPane.INFORMATION_MESSAGE);
                updateUsersTable();
            }
            else
                JOptionPane.showMessageDialog(null, "Ошибка!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteUserButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addUserButton;
    private javax.swing.JButton changeUserButton;
    private javax.swing.JButton deleteUserButton;
    private javax.swing.JLabel exitLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField loginField;
    private javax.swing.JLabel loginWorkerField;
    private javax.swing.JTextField nameField;
    private javax.swing.JTextField passwordField;
    private javax.swing.JComboBox<String> postComBox;
    private javax.swing.JTable usersTable;
    // End of variables declaration//GEN-END:variables
}
