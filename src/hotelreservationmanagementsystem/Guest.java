package hotelreservationmanagementsystem;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import net.proteanit.sql.DbUtils;

public class Guest extends javax.swing.JFrame {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String validationUsername = null;

    String validationEmail;
    String tableName = null;

    PreparedStatement pstCount = null;
    ResultSet rsCount = null;

    double roomTotal;
    double roomAvailable;
    double roomReserved;
    double roomOccupied;

    //parameterless constructor is used for testing purposes
    public Guest() {
        validationEmail = "angel@mail.com";
        toInitialize();
    }

    //parameterized is invoked when the user register/login with a guest account
    public Guest(String email, String name) {

        this.validationEmail = email;
        System.out.println(email);
        toInitialize();

        // txtGreet.setText("Welcome to Evermore Hotel " + name+"!");
    }

    //method that contains codes needed to be initialized in the constructor
    void toInitialize() {
        initComponents();
        txtPhoneRes.setText("");
        txtAddressRes.setText("");

        con = DbConnection.ConnectDb();
        updateRequest();
        autoFill();
        pHome.setVisible(true);
        pReservation.setVisible(false);
        Home();
        txtPriceRes.setEditable(false);
        requestTbl.setDefaultEditor(Object.class, null);
        Calendar cal = Calendar.getInstance();
        //txtCheckInRes.setDate((cal.getTime()));

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(165, 89, 40));
        headerRenderer.setForeground(new Color(255, 255, 255));
        requestTbl.getTableHeader().setDefaultRenderer(headerRenderer);

        requestTbl.getTableHeader().setOpaque(false);
        requestTbl.setIntercellSpacing(new Dimension(0, 0));
        requestTbl.getTableHeader().setPreferredSize(new Dimension(30, 40));
        requestTbl.setGridColor(new Color(24, 71, 58));
        requestTbl.setSelectionBackground(new Color(230, 166, 124));
        requestTbl.getTableHeader().setBackground(new Color(165, 89, 40));

        spRequest.getViewport().setBackground(new Color(239, 233, 221));
        requestTbl.setRowHeight(29);
        requestTbl.setShowGrid(false);

        requestTbl.setShowHorizontalLines(true);
        btnSave.setVisible(false);

    }

    void Home() {
        System.out.println(validationEmail);
        //below are statements for retrieving the total number of rooms as well as the number of occupied, reserved, and available rooms 
        try {
            pst = con.prepareStatement("Select COUNT(roomNo) from room");
            rs = pst.executeQuery();
            while (rs.next()) {

                roomTotal = rs.getInt(1);
                lblTotal.setText(String.valueOf((int) roomTotal));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            pst = con.prepareStatement("Select COUNT(roomNo) from room where status ='Available' ");
            rs = pst.executeQuery();
            while (rs.next()) {
                roomAvailable = rs.getInt(1);
                lblAvailable.setText(String.valueOf((int) roomAvailable));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        try {

            pst = con.prepareStatement("Select COUNT(roomNo) from room where status ='Reserved' ");
            rs = pst.executeQuery();
            while (rs.next()) {
                roomReserved = rs.getInt(1);
                lblReserve.setText(String.valueOf((int) roomReserved));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            pst = con.prepareStatement("Select COUNT(roomNo) from room where status ='Occupied' ");
            rs = pst.executeQuery();
            while (rs.next()) {
                roomOccupied = rs.getInt(1);
                lblOccupied.setText(String.valueOf((int) roomOccupied));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            pst = con.prepareStatement("Select COUNT(roomNo) from room where bed='single'");
            rs = pst.executeQuery();
            while (rs.next()) {
                sTotal.setText(String.valueOf("Total: " + rs.getInt(1)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            pst = con.prepareStatement("Select COUNT(roomNo) from room where status ='Occupied' and bed='single'");
            rs = pst.executeQuery();
            while (rs.next()) {
                sOccupied.setText(String.valueOf("Occupied: " + rs.getInt(1)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            pst = con.prepareStatement("Select COUNT(roomNo) from room where status ='Reserved' and bed='single'");
            rs = pst.executeQuery();
            while (rs.next()) {
                sReserved.setText(String.valueOf("Reserved: " + rs.getInt(1)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            pst = con.prepareStatement("Select COUNT(roomNo) from room where status ='Available' and bed='single'");
            rs = pst.executeQuery();
            while (rs.next()) {
                sAvailable.setText(String.valueOf("Available: " + rs.getInt(1)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            pst = con.prepareStatement("Select COUNT(roomNo) from room where bed='double'");
            rs = pst.executeQuery();
            while (rs.next()) {
                dTotal.setText(String.valueOf("Total: " + rs.getInt(1)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            pst = con.prepareStatement("Select COUNT(roomNo) from room where status ='Occupied' and bed='double'");
            rs = pst.executeQuery();
            while (rs.next()) {
                dOccupied.setText(String.valueOf("Occupied: " + rs.getInt(1)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            pst = con.prepareStatement("Select COUNT(roomNo) from room where status ='Reserved' and bed='double'");
            rs = pst.executeQuery();
            while (rs.next()) {
                dReserved.setText(String.valueOf("Reserved: " + rs.getInt(1)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            pst = con.prepareStatement("Select COUNT(roomNo) from room where status ='Available' and bed='double'");
            rs = pst.executeQuery();
            while (rs.next()) {
                dAvailable.setText(String.valueOf("Available: " + rs.getInt(1)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            pst = con.prepareStatement("Select COUNT(roomNo) from room where bed='triple'");
            rs = pst.executeQuery();
            while (rs.next()) {
                tTotal.setText(String.valueOf("Total: " + rs.getInt(1)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            pst = con.prepareStatement("Select COUNT(roomNo) from room where status ='Occupied' and bed='triple'");
            rs = pst.executeQuery();
            while (rs.next()) {
                tOccupied.setText(String.valueOf("Occupied: " + rs.getInt(1)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            pst = con.prepareStatement("Select COUNT(roomNo) from room where status ='Reserved' and bed='triple'");
            rs = pst.executeQuery();
            while (rs.next()) {
                tReserved.setText(String.valueOf("Reserved: " + rs.getInt(1)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            pst = con.prepareStatement("Select COUNT(roomNo) from room where status ='Available' and bed='triple'");
            rs = pst.executeQuery();
            while (rs.next()) {
                tAvailable.setText(String.valueOf("Available: " + rs.getInt(1)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        panelImage("Single.jpg", "single");
        panelImage("Double.jpg", "double");
        panelImage("Triple.jpg", "triple");

    }
    
     //method that adds an icon  to a label
    void panelImage(String filename, String room) {
        ImageIcon imageName = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(filename)));
        Image imageFile = imageName.getImage();
        ImageIcon image = new ImageIcon(imageFile.getScaledInstance(sPicture.getWidth(), sPicture.getHeight(), Image.SCALE_SMOOTH));
        if (room.equals("single")) {
            sPicture.setIcon(image);
        } else if (room.equals("double")) {
            dPicture.setIcon(image);
        } else if (room.equals("triple")) {
            tPicture.setIcon(image);
        }
    }

    //method to get the available Room Number and its corresponding price for the combo box in Reservation Section
    public void roomDetailsReservation() {
        String bed, roomType;
        comboRoomNoRes.removeAllItems();
        txtPriceRes.setText("");
        bed = (String) comboBedRes.getSelectedItem();
        roomType = (String) comboRoomTypeRes.getSelectedItem();

        try {
            pst = con.prepareStatement("SELECT * FROM room WHERE bed = ? and roomType = ? and status = 'Available'");
            pst.setString(1, bed);
            pst.setString(2, roomType);
            rs = pst.executeQuery();

            while (rs.next()) {
                comboRoomNoRes.addItem(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    //method that automatically provides information of the name and email text field
    void autoFill() {
        try {
            pst = con.prepareStatement("Select * from guestInformation where  email=?");
            pst.setString(1, validationEmail);
            rs = pst.executeQuery();
            while (rs.next()) {
                txtNameRes.setText(rs.getString("firstname"));
                txtNameRes1.setText(rs.getString("lastname"));

                txtEmail.setText(rs.getString("email"));
                txtPhoneRes.setText(rs.getString("phone"));
                txtEmail.setText(rs.getString("email"));
                txtAddressRes.setText(rs.getString("address"));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }

    }

    //method to update the table in reservation Section
    private void updateRequest() {
        try {
            pst = con.prepareStatement("SELECT reservation.reserve_id AS ID, CONCAT( guestinformation.firstName, ' ' ,guestinformation.lastName ) As Name, guestinformation.email AS Email, guestinformation.address AS Address,  guestinformation.phone As Phone, reservation.roomNo As RoomNumber,room.bed AS Bed, room.roomType AS RoomType,room.price AS Price, reservation.checkIn AS CheckIn, reservation.status AS Status\n"
                    + "FROM reservation  \n"
                    + "JOIN guestinformation ON guestinformation.guest_id = reservation.person_id \n"
                    + "JOIN room ON room.roomNo = reservation.roomNo where email =  ?");

            pst.setString(1, validationEmail);
            rs = pst.executeQuery();
            requestTbl.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pMenu = new javax.swing.JPanel();
        pcHome = new javax.swing.JPanel();
        btnHome = new javax.swing.JLabel();
        pcReservation = new javax.swing.JPanel();
        btnReservation = new javax.swing.JLabel();
        pcLogout = new javax.swing.JPanel();
        btnLogout = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        pReservation = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        txtNameRes = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtAddressRes = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtPhoneRes = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        comboBedRes = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        comboRoomTypeRes = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        comboRoomNoRes = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        txtPriceRes = new javax.swing.JTextField();
        spRequest = new javax.swing.JScrollPane();
        requestTbl = new javax.swing.JTable();
        btnCancelRes = new javax.swing.JButton();
        btnConfirmRes = new javax.swing.JButton();
        txtEmail = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        cbbReservation = new javax.swing.JComboBox<>();
        txtSearchGuest = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtCheckInRes = new com.toedter.calendar.JDateChooser();
        txtNameRes1 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        pHome = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        tReserved = new javax.swing.JLabel();
        tOccupied = new javax.swing.JLabel();
        tAvailable = new javax.swing.JLabel();
        tTotal = new javax.swing.JLabel();
        sReserved = new javax.swing.JLabel();
        sOccupied = new javax.swing.JLabel();
        sAvailable = new javax.swing.JLabel();
        dReserved = new javax.swing.JLabel();
        dOccupied = new javax.swing.JLabel();
        dAvailable = new javax.swing.JLabel();
        sTotal = new javax.swing.JLabel();
        dTotal = new javax.swing.JLabel();
        tPicture = new javax.swing.JLabel();
        dPicture = new javax.swing.JLabel();
        sPicture = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lblReserve1 = new javax.swing.JLabel();
        lblReserve = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblAvailable1 = new javax.swing.JLabel();
        lblAvailable = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblOccupied1 = new javax.swing.JLabel();
        lblOccupied = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblTotal = new javax.swing.JLabel();
        lblTotal1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1920, 1080));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pMenu.setBackground(new java.awt.Color(25, 71, 58));
        pMenu.setForeground(new java.awt.Color(239, 233, 221));
        pMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pcHome.setBackground(new java.awt.Color(239, 233, 221));

        btnHome.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 36)); // NOI18N
        btnHome.setForeground(new java.awt.Color(24, 71, 58));
        btnHome.setText("Home");
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHomeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pcHomeLayout = new javax.swing.GroupLayout(pcHome);
        pcHome.setLayout(pcHomeLayout);
        pcHomeLayout.setHorizontalGroup(
            pcHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pcHomeLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(btnHome)
                .addContainerGap(209, Short.MAX_VALUE))
        );
        pcHomeLayout.setVerticalGroup(
            pcHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pcHomeLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pMenu.add(pcHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, 360, 70));

        pcReservation.setBackground(new java.awt.Color(24, 71, 58));

        btnReservation.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 36)); // NOI18N
        btnReservation.setForeground(new java.awt.Color(255, 255, 255));
        btnReservation.setText("Reservation");
        btnReservation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReservationMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pcReservationLayout = new javax.swing.GroupLayout(pcReservation);
        pcReservation.setLayout(pcReservationLayout);
        pcReservationLayout.setHorizontalGroup(
            pcReservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pcReservationLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(btnReservation)
                .addContainerGap(98, Short.MAX_VALUE))
        );
        pcReservationLayout.setVerticalGroup(
            pcReservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pcReservationLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnReservation, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pMenu.add(pcReservation, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 530, 360, -1));

        pcLogout.setBackground(new java.awt.Color(24, 71, 58));
        pcLogout.setForeground(new java.awt.Color(24, 71, 58));

        btnLogout.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 36)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("Logout");
        btnLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLogoutMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pcLogoutLayout = new javax.swing.GroupLayout(pcLogout);
        pcLogout.setLayout(pcLogoutLayout);
        pcLogoutLayout.setHorizontalGroup(
            pcLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pcLogoutLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(btnLogout)
                .addContainerGap(192, Short.MAX_VALUE))
        );
        pcLogoutLayout.setVerticalGroup(
            pcLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pcLogoutLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pMenu.add(pcLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 980, -1, -1));

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotelreservationmanagementsystem/MenuLogo.jpg"))); // NOI18N
        pMenu.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 22, 300, -1));

        getContentPane().add(pMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 1080));

        pReservation.setBackground(new java.awt.Color(239, 233, 221));
        pReservation.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(23, 68, 55));
        jLabel26.setText("Last Name:");
        pReservation.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, -1, 30));

        txtNameRes.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        txtNameRes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(23, 68, 55), 2, true));
        txtNameRes.setEnabled(false);
        txtNameRes.setPreferredSize(new java.awt.Dimension(35, 35));
        pReservation.add(txtNameRes, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 250, -1));

        jLabel27.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(23, 68, 55));
        jLabel27.setText("Address: (Optional)");
        pReservation.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 150, -1, 30));

        txtAddressRes.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        txtAddressRes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(23, 68, 55), 2, true));
        txtAddressRes.setEnabled(false);
        txtAddressRes.setPreferredSize(new java.awt.Dimension(35, 35));
        pReservation.add(txtAddressRes, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 180, 250, -1));

        jLabel28.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(23, 68, 55));
        jLabel28.setText("Phone Number: (Optional)");
        pReservation.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 150, -1, 30));

        txtPhoneRes.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        txtPhoneRes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(23, 68, 55), 2, true));
        txtPhoneRes.setEnabled(false);
        txtPhoneRes.setPreferredSize(new java.awt.Dimension(35, 35));
        pReservation.add(txtPhoneRes, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 180, 250, -1));

        jLabel29.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(23, 68, 55));
        jLabel29.setText("Check In Date:");
        pReservation.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, -1, 30));

        jLabel30.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(23, 68, 55));
        jLabel30.setText("Search:");
        pReservation.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 430, -1, 35));

        comboBedRes.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        comboBedRes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Single", "Double", "Triple" }));
        comboBedRes.setBorder(null);
        comboBedRes.setPreferredSize(new java.awt.Dimension(35, 35));
        comboBedRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBedResActionPerformed(evt);
            }
        });
        pReservation.add(comboBedRes, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 250, 250, -1));

        jLabel31.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(23, 68, 55));
        jLabel31.setText("Room Type:");
        pReservation.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 220, -1, 30));

        comboRoomTypeRes.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        comboRoomTypeRes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "AC", "Non-AC" }));
        comboRoomTypeRes.setBorder(null);
        comboRoomTypeRes.setPreferredSize(new java.awt.Dimension(35, 35));
        comboRoomTypeRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboRoomTypeResActionPerformed(evt);
            }
        });
        pReservation.add(comboRoomTypeRes, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 250, 250, -1));

        jLabel32.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(23, 68, 55));
        jLabel32.setText("Room Number:");
        pReservation.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 220, -1, 30));

        comboRoomNoRes.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        comboRoomNoRes.setBorder(null);
        comboRoomNoRes.setPreferredSize(new java.awt.Dimension(35, 35));
        comboRoomNoRes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboRoomNoResMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                comboRoomNoResMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                comboRoomNoResMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                comboRoomNoResMouseReleased(evt);
            }
        });
        comboRoomNoRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboRoomNoResActionPerformed(evt);
            }
        });
        pReservation.add(comboRoomNoRes, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 250, 250, -1));

        jLabel33.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(23, 68, 55));
        jLabel33.setText("Price:");
        pReservation.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 220, -1, 30));

        txtPriceRes.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        txtPriceRes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(23, 68, 55), 2, true));
        txtPriceRes.setPreferredSize(new java.awt.Dimension(35, 35));
        pReservation.add(txtPriceRes, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 250, 250, -1));

        spRequest.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(165, 89, 40), 3));

        requestTbl.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        requestTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Email Address", "Address", "Phone Number", "Check-In Date", "Bed Type", "Room Type", "Room Number", "Price", "Status"
            }
        ));
        requestTbl.setFocusable(false);
        requestTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                requestTblMouseClicked(evt);
            }
        });
        spRequest.setViewportView(requestTbl);

        pReservation.add(spRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 480, 1410, 450));

        btnCancelRes.setBackground(new java.awt.Color(165, 89, 40));
        btnCancelRes.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 18)); // NOI18N
        btnCancelRes.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelRes.setText("Cancel Reservation");
        btnCancelRes.setBorder(null);
        btnCancelRes.setPreferredSize(new java.awt.Dimension(50, 50));
        btnCancelRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelResActionPerformed(evt);
            }
        });
        pReservation.add(btnCancelRes, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 960, 250, -1));

        btnConfirmRes.setBackground(new java.awt.Color(165, 89, 40));
        btnConfirmRes.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 18)); // NOI18N
        btnConfirmRes.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirmRes.setText("Confirm");
        btnConfirmRes.setBorder(null);
        btnConfirmRes.setPreferredSize(new java.awt.Dimension(50, 50));
        btnConfirmRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmResActionPerformed(evt);
            }
        });
        btnConfirmRes.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                btnConfirmResPropertyChange(evt);
            }
        });
        pReservation.add(btnConfirmRes, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 310, 250, -1));

        txtEmail.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        txtEmail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(23, 68, 55), 2, true));
        txtEmail.setEnabled(false);
        txtEmail.setPreferredSize(new java.awt.Dimension(35, 35));
        pReservation.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 180, 250, -1));

        jLabel38.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(23, 68, 55));
        jLabel38.setText("Email:");
        pReservation.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 150, 160, 30));

        cbbReservation.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        cbbReservation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Reservation", "Request" }));
        cbbReservation.setBorder(null);
        cbbReservation.setPreferredSize(new java.awt.Dimension(35, 35));
        cbbReservation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbReservationActionPerformed(evt);
            }
        });
        pReservation.add(cbbReservation, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 430, 250, -1));

        txtSearchGuest.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        txtSearchGuest.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(23, 68, 55), 2, true));
        txtSearchGuest.setPreferredSize(new java.awt.Dimension(35, 35));
        txtSearchGuest.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchGuestKeyReleased(evt);
            }
        });
        pReservation.add(txtSearchGuest, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 430, 350, -1));

        jLabel34.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(23, 68, 55));
        jLabel34.setText("Bed:");
        pReservation.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, -1, 30));

        txtCheckInRes.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        pReservation.add(txtCheckInRes, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 250, 35));

        txtNameRes1.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        txtNameRes1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(23, 68, 55), 2, true));
        txtNameRes1.setEnabled(false);
        txtNameRes1.setPreferredSize(new java.awt.Dimension(35, 35));
        pReservation.add(txtNameRes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 180, 250, -1));

        jLabel37.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(23, 68, 55));
        jLabel37.setText("First Name:");
        pReservation.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, -1, 30));

        btnSave.setBackground(new java.awt.Color(165, 89, 40));
        btnSave.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 18)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("Save");
        btnSave.setBorder(null);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        pReservation.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 310, 250, 50));

        btnUpdate.setBackground(new java.awt.Color(165, 89, 40));
        btnUpdate.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 18)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Update");
        btnUpdate.setBorder(null);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        pReservation.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 310, 250, 50));

        jButton4.setBackground(new java.awt.Color(165, 89, 40));
        jButton4.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Clear");
        jButton4.setBorder(null);
        jButton4.setBorderPainted(false);
        jButton4.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        pReservation.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 310, 130, 50));

        jLabel25.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 36)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(23, 68, 55));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Reservation");
        pReservation.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1520, -1));

        getContentPane().add(pReservation, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 1520, 1080));

        pHome.setBackground(new java.awt.Color(239, 233, 221));
        pHome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel36.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 36)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(24, 71, 58));
        jLabel36.setText("Room Status:");
        pHome.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, -1, -1));

        tReserved.setFont(new java.awt.Font("Noto Sans Medium", 0, 18)); // NOI18N
        tReserved.setForeground(new java.awt.Color(255, 255, 255));
        tReserved.setText("Triple Reserve");
        pHome.add(tReserved, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 930, -1, 40));

        tOccupied.setFont(new java.awt.Font("Noto Sans Medium", 0, 18)); // NOI18N
        tOccupied.setForeground(new java.awt.Color(255, 255, 255));
        tOccupied.setText("Triple Occupied");
        pHome.add(tOccupied, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 890, -1, 40));

        tAvailable.setFont(new java.awt.Font("Noto Sans Medium", 0, 18)); // NOI18N
        tAvailable.setForeground(new java.awt.Color(255, 255, 255));
        tAvailable.setText("Triple Available");
        pHome.add(tAvailable, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 910, -1, 40));

        tTotal.setFont(new java.awt.Font("Noto Sans Medium", 0, 18)); // NOI18N
        tTotal.setForeground(new java.awt.Color(255, 255, 255));
        tTotal.setText("Triple Total");
        pHome.add(tTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 870, -1, 40));

        sReserved.setFont(new java.awt.Font("Noto Sans Medium", 0, 18)); // NOI18N
        sReserved.setForeground(new java.awt.Color(255, 255, 255));
        sReserved.setText("Single Reserve");
        pHome.add(sReserved, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 930, -1, 40));

        sOccupied.setFont(new java.awt.Font("Noto Sans Medium", 0, 18)); // NOI18N
        sOccupied.setForeground(new java.awt.Color(255, 255, 255));
        sOccupied.setText("Single Occupied");
        pHome.add(sOccupied, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 890, -1, 40));

        sAvailable.setFont(new java.awt.Font("Noto Sans Medium", 0, 18)); // NOI18N
        sAvailable.setForeground(new java.awt.Color(255, 255, 255));
        sAvailable.setText("Single Available");
        pHome.add(sAvailable, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 910, -1, 40));

        dReserved.setFont(new java.awt.Font("Noto Sans Medium", 0, 18)); // NOI18N
        dReserved.setForeground(new java.awt.Color(255, 255, 255));
        dReserved.setText("DoubleReserve");
        pHome.add(dReserved, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 930, -1, 40));

        dOccupied.setFont(new java.awt.Font("Noto Sans Medium", 0, 18)); // NOI18N
        dOccupied.setForeground(new java.awt.Color(255, 255, 255));
        dOccupied.setText("Double Occupied");
        pHome.add(dOccupied, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 890, -1, 40));

        dAvailable.setFont(new java.awt.Font("Noto Sans Medium", 0, 18)); // NOI18N
        dAvailable.setForeground(new java.awt.Color(255, 255, 255));
        dAvailable.setText("Double Available");
        pHome.add(dAvailable, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 910, -1, 40));

        sTotal.setFont(new java.awt.Font("Noto Sans Medium", 0, 18)); // NOI18N
        sTotal.setForeground(new java.awt.Color(255, 255, 255));
        sTotal.setText("Single Total");
        pHome.add(sTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 870, -1, 40));

        dTotal.setFont(new java.awt.Font("Noto Sans Medium", 0, 18)); // NOI18N
        dTotal.setForeground(new java.awt.Color(255, 255, 255));
        dTotal.setText("Double Total");
        pHome.add(dTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 870, -1, 40));

        tPicture.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        pHome.add(tPicture, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 380, 410, 600));

        dPicture.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        pHome.add(dPicture, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 380, 410, 600));

        sPicture.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        pHome.add(sPicture, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 410, 600));

        jPanel7.setBackground(new java.awt.Color(165, 89, 40));
        jPanel7.setForeground(new java.awt.Color(165, 89, 40));

        lblReserve1.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 24)); // NOI18N
        lblReserve1.setForeground(new java.awt.Color(255, 255, 255));
        lblReserve1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReserve1.setText("Reserved");

        lblReserve.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 72)); // NOI18N
        lblReserve.setForeground(new java.awt.Color(255, 255, 255));
        lblReserve.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReserve.setText("2");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblReserve1, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                    .addComponent(lblReserve, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(lblReserve, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblReserve1)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pHome.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 150, 270, 190));

        jPanel6.setBackground(new java.awt.Color(165, 89, 40));
        jPanel6.setForeground(new java.awt.Color(165, 89, 40));

        lblAvailable1.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 24)); // NOI18N
        lblAvailable1.setForeground(new java.awt.Color(255, 255, 255));
        lblAvailable1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAvailable1.setText("Available");

        lblAvailable.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 72)); // NOI18N
        lblAvailable.setForeground(new java.awt.Color(255, 255, 255));
        lblAvailable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAvailable.setText("3");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAvailable1, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
            .addComponent(lblAvailable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(lblAvailable, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAvailable1)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pHome.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 150, 270, 190));

        jPanel5.setBackground(new java.awt.Color(165, 89, 40));
        jPanel5.setForeground(new java.awt.Color(165, 89, 40));

        lblOccupied1.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 24)); // NOI18N
        lblOccupied1.setForeground(new java.awt.Color(255, 255, 255));
        lblOccupied1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOccupied1.setText("Occupied");

        lblOccupied.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 72)); // NOI18N
        lblOccupied.setForeground(new java.awt.Color(255, 255, 255));
        lblOccupied.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOccupied.setText("1");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblOccupied1, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                    .addComponent(lblOccupied, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(129, 129, 129))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(lblOccupied, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblOccupied1)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pHome.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 150, 270, 190));

        jPanel2.setBackground(new java.awt.Color(165, 89, 40));
        jPanel2.setForeground(new java.awt.Color(165, 89, 40));

        lblTotal.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 72)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(255, 255, 255));
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotal.setText("0");

        lblTotal1.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 24)); // NOI18N
        lblTotal1.setForeground(new java.awt.Color(255, 255, 255));
        lblTotal1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotal1.setText("Total");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTotal1, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                    .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(85, 85, 85))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotal1)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pHome.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 270, 190));

        jPanel1.setBackground(new java.awt.Color(165, 89, 40));
        jPanel1.setForeground(new java.awt.Color(165, 89, 40));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        pHome.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 390, 410, 600));

        jPanel9.setBackground(new java.awt.Color(165, 89, 40));
        jPanel9.setForeground(new java.awt.Color(165, 89, 40));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        pHome.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 390, -1, -1));

        jPanel10.setBackground(new java.awt.Color(165, 89, 40));
        jPanel10.setForeground(new java.awt.Color(165, 89, 40));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        pHome.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 390, -1, -1));

        jPanel4.setBackground(new java.awt.Color(24, 71, 58));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1360, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );

        pHome.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 1360, 130));

        getContentPane().add(pHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 1580, 1080));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboBedResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBedResActionPerformed
        roomDetailsReservation();
    }//GEN-LAST:event_comboBedResActionPerformed

    private void comboRoomTypeResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboRoomTypeResActionPerformed
        roomDetailsReservation();
    }//GEN-LAST:event_comboRoomTypeResActionPerformed

    private void comboRoomNoResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboRoomNoResActionPerformed

    }//GEN-LAST:event_comboRoomNoResActionPerformed

    private void requestTblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_requestTblMouseClicked

    }//GEN-LAST:event_requestTblMouseClicked

    private void btnCancelResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelResActionPerformed
        int row = requestTbl.getSelectedRow();
        String selectedRow = requestTbl.getModel().getValueAt(row, 5).toString();
        try {
            if (JOptionPane.showConfirmDialog(this, "Are you sure you want to cancel your reservation?", "",
                    JOptionPane.YES_NO_OPTION) == 0) {
                //checks if the reservation is already approved, if not the it can still be canceled
                pst = con.prepareStatement("SELECT  g.email, a.roomNo, a.status FROM guestInformation g INNER JOIN reservation a ON(g.guest_id = a.person_id) where  email = ? and status ='Reserved' and roomNo = ?");

                pst.setString(1, validationEmail);
                pst.setString(2, selectedRow);
                rs = pst.executeQuery();
                if (rs.next()) {
                    cbbReservation.setSelectedIndex(0);
                    JOptionPane.showMessageDialog(this, "Reservation cannot be cancelled.", "Failed!", JOptionPane.ERROR_MESSAGE);
                } else {
                    pst = con.prepareStatement("DELETE  a FROM guestInformation g INNER JOIN reservation a ON(g.guest_id = a.person_id) where  email = ? and roomNo = ?");
                    pst.setString(1, validationEmail);
                    pst.setString(2, selectedRow);
                    pst.execute();
                    JOptionPane.showMessageDialog(this, "Your reservation has been cancelled.", "Successful!", JOptionPane.INFORMATION_MESSAGE);
                    updateRequest();
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
    }//GEN-LAST:event_btnCancelResActionPerformed

    private void btnConfirmResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmResActionPerformed

        String name = txtNameRes.getText();
        String email = txtEmail.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String checkIn = sdf.format(txtCheckInRes.getDate());
        String bed = (String) comboBedRes.getSelectedItem();
        String roomType = (String) comboRoomTypeRes.getSelectedItem();
        String roomNo = (String) comboRoomNoRes.getSelectedItem();
        //check if fields are empty
        if (name.equals("") || email.equals("") || bed == null || checkIn == null || roomType == null || roomNo == null) {

            JOptionPane.showMessageDialog(this, "Please fill up the required fields!.", "Failed!", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                String selected = (String) comboRoomNoRes.getSelectedItem();
                pst = con.prepareStatement("SELECT  g.email, a.roomNo, a.status FROM guestInformation g INNER JOIN reservation a ON(g.guest_id = a.person_id) where  email = ? and roomNo =?");
                pst.setString(1, validationEmail);
                pst.setString(2, selected);

                rs = pst.executeQuery();
                if (!rs.next()) {

                    String guestId = null;
                    //gets guest id
                    pst = con.prepareStatement("SELECT * FROM guestinformation WHERE email = ?");
                    pst.setString(1, validationEmail);
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        guestId = rs.getString("guest_id");
                        pst = con.prepareStatement("INSERT INTO reservation (person_id, roomNo, checkIn, status) VALUES (?, ?, ?, 'Pending')");

                        pst.setString(3, checkIn);
                        pst.setString(1, guestId);

                        pst.setString(2, roomNo);

                        int j = pst.executeUpdate();
                        if (j == 1) {
                            updateRequest();
                            JOptionPane.showMessageDialog(this, "Added!");
                            txtCheckInRes.setCalendar(null);
                            comboBedRes.setSelectedIndex(0);
                            comboRoomTypeRes.setSelectedIndex(0);
                            comboRoomNoRes.setSelectedIndex(0);
                            txtPriceRes.setText("");
                        } else {
                            JOptionPane.showMessageDialog(this, "Adding Failed!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "You already reserve this room.", "Failed!", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_btnConfirmResActionPerformed

    private void btnHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseClicked
        //changes the background and foreground color of a button in the menu 
        pcHome.setBackground(new Color(239, 233, 221));
        pcReservation.setBackground(new Color(24, 71, 58));
        btnHome.setForeground(new Color(24, 71, 58));
        btnReservation.setForeground(new Color(255, 255, 255));
        pHome.setVisible(true);
        pReservation.setVisible(false);

    }//GEN-LAST:event_btnHomeMouseClicked

    private void btnReservationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReservationMouseClicked

        autoFill();
        btnUpdate.setVisible(true);
        btnSave.setVisible(false);
        pcHome.setBackground(new Color(24, 71, 58));
        pcReservation.setBackground(new Color(239, 233, 221));
        btnHome.setForeground(new Color(255, 255, 255));
        btnReservation.setForeground(new Color(24, 71, 58));
        pHome.setVisible(false);
        pReservation.setVisible(true);
        //txtAddressRes.setText("");
        txtSearchGuest.setText("");
        txtCheckInRes.setCalendar(null);
//        comboBedRes.setSelectedIndex(0);
//        comboRoomTypeRes.setSelectedIndex(0);
//        comboRoomNoRes.setSelectedIndex(0);
//        txtPriceRes.setText("");
//        cbbReservation.setSelectedIndex(0);
    }//GEN-LAST:event_btnReservationMouseClicked

    private void btnLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseClicked
        //confirmation if user want to logout
        btnLogout.setBackground(new Color(24, 71, 58));
        btnLogout.setForeground(new Color(255, 255, 255));
        if (JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "",
                JOptionPane.YES_NO_OPTION) == 0) {
            setVisible(false);
            new Account().setVisible(true);
        }

    }//GEN-LAST:event_btnLogoutMouseClicked

    private void cbbReservationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbReservationActionPerformed
        String selection = cbbReservation.getSelectedItem().toString();
        //filtering table using combobox
        try {
            if (selection.equals("All")) {
                updateRequest();

            } else if (selection.equals("Reservation")) {
                pst = con.prepareStatement("SELECT reservation.reserve_id As ID, CONCAT( guestinformation.firstName, ' ' ,guestinformation.lastName ) As Name, guestinformation.email As Enail, guestinformation.address As Address,  guestinformation.phone As Phone, reservation.roomNo As RoomNumber,room.bed As Bed, room.roomType AS RoomType,room.price AS Price, reservation.checkIn AS CheckIn, reservation.status As Status\n"
                        + "FROM reservation  \n"
                        + "JOIN guestinformation ON guestinformation.guest_id = reservation.person_id \n"
                        + "JOIN room ON room.roomNo = reservation.roomNo where email =  ? and reservation.status= 'Reserved'");

                pst.setString(1, validationEmail);
            } else if (selection.equals("Request")) {
                pst = con.prepareStatement("SELECT reservation.reserve_id As ID, CONCAT( guestinformation.firstName, ' ' ,guestinformation.lastName ) As Name, guestinformation.email AS Email, guestinformation.address AS Address,  guestinformation.phone AS Phone, reservation.roomNo AS RoomNumber,room.bed As Bed, room.roomType As RoomType,room.price As Price, reservation.checkIn AS CheckIn, reservation.status AS Status\n"
                        + "FROM reservation  \n"
                        + "JOIN guestinformation ON guestinformation.guest_id = reservation.person_id \n"
                        + "JOIN room ON room.roomNo = reservation.roomNo where email =  ? and reservation.status= 'Pending'");
                pst.setString(1, validationEmail);
            }
            rs = pst.executeQuery();
            requestTbl.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_cbbReservationActionPerformed

    private void comboRoomNoResMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboRoomNoResMouseClicked
        try {
            //show price of selected room in textfield
            String roomNo = (String) comboRoomNoRes.getSelectedItem();
            pst = con.prepareStatement("SELECT * FROM room WHERE roomNo = ?");
            pst.setString(1, roomNo);
            rs = pst.executeQuery();

            while (rs.next()) {
                txtPriceRes.setText(rs.getString(4));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }

    }//GEN-LAST:event_comboRoomNoResMouseClicked

    private void comboRoomNoResMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboRoomNoResMouseReleased

    }//GEN-LAST:event_comboRoomNoResMouseReleased

    private void comboRoomNoResMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboRoomNoResMousePressed

    }//GEN-LAST:event_comboRoomNoResMousePressed

    private void comboRoomNoResMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboRoomNoResMouseEntered

    }//GEN-LAST:event_comboRoomNoResMouseEntered

    private void btnConfirmResPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_btnConfirmResPropertyChange

    }//GEN-LAST:event_btnConfirmResPropertyChange

    private void txtSearchGuestKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchGuestKeyReleased
        //query for searching a record in the table
        String search = txtSearchGuest.getText();
        if (!search.equals("")) {
            try {

                pst = con.prepareStatement("SELECT reservation.reserve_id As ID, CONCAT( guestinformation.firstName, ' ' ,guestinformation.lastName ) As Name, guestinformation.email AS Email, guestinformation.address AS Address,  guestinformation.phone AS Phone, reservation.roomNo AS RoomNumber,room.bed AS Bed, room.roomType AS RoomType,room.price AS Price, reservation.checkIn AS CheckIn, reservation.status AS Status\n"
                        + "FROM reservation  \n"
                        + "JOIN guestinformation ON guestinformation.guest_id = reservation.person_id \n"
                        + "JOIN room ON room.roomNo = reservation.roomNo WHERE  reservation.roomNo like '%" + search + "%' and email = ?");
                pst.setString(1, validationEmail);
                rs = pst.executeQuery();
                requestTbl.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        } else {
            updateRequest();
        }
    }//GEN-LAST:event_txtSearchGuestKeyReleased

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        txtAddressRes.setEnabled(true);
        txtNameRes.setEnabled(true);
        txtNameRes1.setEnabled(true);
        txtPhoneRes.setEnabled(true);
        //txtEmail.setEnabled(true);
        btnSave.setVisible(true);
        btnUpdate.setVisible(false);

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        String name = txtNameRes.getText();
        String name1 = txtNameRes1.getText();
        String email = txtEmail.getText();
        String address = txtAddressRes.getText();
        String phone = txtPhoneRes.getText();

        try {
            if (phone.length() == 11 || phone.equals("")) {
                pst = con.prepareStatement("Update guestinformation set firstName = ?, lastName=?, address=?, phone=? where email=?");
                pst.setString(1, name);
                pst.setString(2, name1);
                pst.setString(3, address);
                pst.setString(4, phone);
                pst.setString(5, email);
                int k = pst.executeUpdate();

                if (k == 1) {

                    JOptionPane.showMessageDialog(this, "Updated successfully.");
                    txtAddressRes.setEnabled(false);
                    txtNameRes.setEnabled(false);
                    txtNameRes1.setEnabled(false);
                    txtPhoneRes.setEnabled(false);
                    //txtEmail.setEnabled(true);
                    btnSave.setVisible(false);
                    updateRequest();
                    btnUpdate.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid phone number.", "Failed!", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);

        }


    }//GEN-LAST:event_btnSaveActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        txtCheckInRes.setCalendar(null);
        comboBedRes.setSelectedIndex(0);
        comboRoomTypeRes.setSelectedIndex(0);
        comboRoomNoRes.setSelectedIndex(0);
        txtPriceRes.setText("");
        cbbReservation.setSelectedIndex(0);
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(Guest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Guest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Guest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Guest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Guest().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelRes;
    private javax.swing.JButton btnConfirmRes;
    private javax.swing.JLabel btnHome;
    private javax.swing.JLabel btnLogout;
    private javax.swing.JLabel btnReservation;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbbReservation;
    private javax.swing.JComboBox<String> comboBedRes;
    private javax.swing.JComboBox<String> comboRoomNoRes;
    private javax.swing.JComboBox<String> comboRoomTypeRes;
    private javax.swing.JLabel dAvailable;
    private javax.swing.JLabel dOccupied;
    private javax.swing.JLabel dPicture;
    private javax.swing.JLabel dReserved;
    private javax.swing.JLabel dTotal;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lblAvailable;
    private javax.swing.JLabel lblAvailable1;
    private javax.swing.JLabel lblOccupied;
    private javax.swing.JLabel lblOccupied1;
    private javax.swing.JLabel lblReserve;
    private javax.swing.JLabel lblReserve1;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotal1;
    private javax.swing.JPanel pHome;
    private javax.swing.JPanel pMenu;
    private javax.swing.JPanel pReservation;
    private javax.swing.JPanel pcHome;
    private javax.swing.JPanel pcLogout;
    private javax.swing.JPanel pcReservation;
    private javax.swing.JTable requestTbl;
    private javax.swing.JLabel sAvailable;
    private javax.swing.JLabel sOccupied;
    private javax.swing.JLabel sPicture;
    private javax.swing.JLabel sReserved;
    private javax.swing.JLabel sTotal;
    private javax.swing.JScrollPane spRequest;
    private javax.swing.JLabel tAvailable;
    private javax.swing.JLabel tOccupied;
    private javax.swing.JLabel tPicture;
    private javax.swing.JLabel tReserved;
    private javax.swing.JLabel tTotal;
    private javax.swing.JTextField txtAddressRes;
    private com.toedter.calendar.JDateChooser txtCheckInRes;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNameRes;
    private javax.swing.JTextField txtNameRes1;
    private javax.swing.JTextField txtPhoneRes;
    private javax.swing.JTextField txtPriceRes;
    private javax.swing.JTextField txtSearchGuest;
    // End of variables declaration//GEN-END:variables
}
