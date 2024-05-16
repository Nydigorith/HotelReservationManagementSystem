package hotelreservationmanagementsystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import java.util.Date;

public class Employee extends javax.swing.JFrame {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    PreparedStatement pstCount = null;
    ResultSet rsCount = null;

    String validationEmail = null;
    String validationName = null;
    String tableName = null;

    double roomTotal;
    double roomAvailable;
    double roomReserved;
    double roomOccupied;
    double reservedPercentage;
    double TotalPercentage;
    double availablePercentage;
    double occupiedPercentage;

    //parameterless constructor is used for testing purposes
    public Employee() {
        toInitialize();
        btnAccount.setVisible(true);
    }

    //parameterized is invoked when the user login with an register/login account
    public Employee(String email, String name) {
        validationEmail = email;
        System.out.println(validationEmail);
        toInitialize();
        userType();
    }

    //method that contains codes needed to be initialized in the constructor     
    void toInitialize() {
        initComponents();
        con = DbConnection.ConnectDb();

        txtInDateIn.setEditable(false);
        txtPriceCheckIn.setEditable(false);

        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        txtInDateIn.setText(myFormat.format(cal.getTime()));

        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);

        txtPhoneRes.setText("");
        txtAddressRes.setText("");
        txtPhoneIn.setText("");
        txtAddressIn.setText("");
        //txtCheckInRes.setDate((cal.getTime()));

        this.setBounds(0, 0, 1920, 1080);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        updateTables();

        pHome.setVisible(true);
        pAdmin.setVisible(false);
        pManage.setVisible(false);
        pCheckIn.setVisible(false);
        pCheckOut.setVisible(false);
        pRequest.setVisible(false);
        pReservation.setVisible(false);
        pCheckOutConfirm.setVisible(false);
        txtPriceRes.setEditable(false);
        btnAccount.setVisible(false);

        //for Manager Table Formatting
        manageTbl.setDefaultEditor(Object.class, null);

        //changes the color of table header 
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(165, 89, 40));
        headerRenderer.setForeground(new Color(255, 255, 255));
        manageTbl.getTableHeader().setDefaultRenderer(headerRenderer);
        manageTbl.getTableHeader().setOpaque(false);
        manageTbl.setIntercellSpacing(new Dimension(0, 0));
        manageTbl.getTableHeader().setPreferredSize(new Dimension(30, 40));
        spManage.getViewport().setBackground(new Color(239, 233, 221));
        manageTbl.setGridColor(new Color(24, 71, 58));
        manageTbl.setSelectionBackground(new Color(230, 166, 124));
        manageTbl.getTableHeader().setBackground(new Color(165, 89, 40));
        manageTbl.setRowHeight(29);
        manageTbl.setShowGrid(false);
        manageTbl.setShowHorizontalLines(true);

        checkInTbl.setDefaultEditor(Object.class, null);
        checkInTbl.getTableHeader().setDefaultRenderer(headerRenderer);
        checkInTbl.getTableHeader().setOpaque(false);
        checkInTbl.setIntercellSpacing(new Dimension(0, 0));
        checkInTbl.getTableHeader().setPreferredSize(new Dimension(30, 40));
        checkInTbl.setGridColor(new Color(24, 71, 58));
        checkInTbl.setSelectionBackground(new Color(230, 166, 124));
        checkInTbl.getTableHeader().setBackground(new Color(165, 89, 40));
        spCheckIn.getViewport().setBackground(new Color(239, 233, 221));
        checkInTbl.setRowHeight(29);
        checkInTbl.setShowGrid(false);
        checkInTbl.setShowHorizontalLines(true);
        checkOutTbl.setDefaultEditor(Object.class, null);

        checkOutTbl.getTableHeader().setDefaultRenderer(headerRenderer);
        checkOutTbl.getTableHeader().setOpaque(false);
        checkOutTbl.setIntercellSpacing(new Dimension(0, 0));
        checkOutTbl.getTableHeader().setPreferredSize(new Dimension(30, 40));
        checkOutTbl.setGridColor(new Color(24, 71, 58));
        checkOutTbl.setSelectionBackground(new Color(230, 166, 124));
        checkOutTbl.getTableHeader().setBackground(new Color(165, 89, 40));
        spCheckOut.getViewport().setBackground(new Color(239, 233, 221));
        checkOutTbl.setRowHeight(29);
        checkOutTbl.setShowGrid(false);
        checkOutTbl.setShowHorizontalLines(true);

        tblRequest.setDefaultEditor(Object.class, null);
        tblRequest.getTableHeader().setDefaultRenderer(headerRenderer);
        tblRequest.getTableHeader().setOpaque(false);
        tblRequest.setIntercellSpacing(new Dimension(0, 0));
        tblRequest.getTableHeader().setPreferredSize(new Dimension(30, 40));
        tblRequest.setGridColor(new Color(24, 71, 58));
        tblRequest.setSelectionBackground(new Color(230, 166, 124));
        spRequest.getViewport().setBackground(new Color(239, 233, 221));
        tblRequest.getTableHeader().setBackground(new Color(165, 89, 40));
        tblRequest.setRowHeight(29);
        tblRequest.setShowGrid(false);
        tblRequest.setShowHorizontalLines(true);

        reservationTbl.setDefaultEditor(Object.class, null);
        reservationTbl.getTableHeader().setDefaultRenderer(headerRenderer);
        reservationTbl.getTableHeader().setOpaque(false);
        reservationTbl.setIntercellSpacing(new Dimension(0, 0));
        reservationTbl.getTableHeader().setPreferredSize(new Dimension(30, 40));
        reservationTbl.setGridColor(new Color(24, 71, 58));
        reservationTbl.setSelectionBackground(new Color(230, 166, 124));
        spReservation.getViewport().setBackground(new Color(239, 233, 221));
        reservationTbl.getTableHeader().setBackground(new Color(165, 89, 40));
        reservationTbl.setRowHeight(29);
        reservationTbl.setShowGrid(false);
        reservationTbl.setShowHorizontalLines(true);
        btnCheckOutConfirm.setVisible(false);
        btnCancel.setVisible(false);

        tblAdmin.setDefaultEditor(Object.class, null);
        tblAdmin.getTableHeader().setDefaultRenderer(headerRenderer);
        tblAdmin.getTableHeader().setOpaque(false);
        tblAdmin.setIntercellSpacing(new Dimension(0, 0));
        tblAdmin.getTableHeader().setPreferredSize(new Dimension(30, 40));
        tblAdmin.setGridColor(new Color(24, 71, 58));
        tblAdmin.setSelectionBackground(new Color(230, 166, 124));
        tblAdmin.getTableHeader().setBackground(new Color(165, 89, 40));
        spAdmin.getViewport().setBackground(new Color(239, 233, 221));
        tblAdmin.setRowHeight(29);
        tblAdmin.setShowGrid(false);
        tblAdmin.setShowHorizontalLines(true);

        Home();
        Admin();
    }

    // method that updates all table at once
    void updateTables() {
        updateCheckOutWith();
        updateCheckInWithout();
        updateManage();
        updateRequestList();
        updateReservationWith();
        updateCheckInWith();
        updateAdminTable();
    }

    //clear textfileds
    void clearFields() {

        txtNameIn.setText("");
        txtAddressIn.setText("");
        txtPhoneIn.setText("");
        //txtInDateIn.setText("");

        txtEmailIn.setText("");
        txtSearchIn.setText("");
        txtNameIn1.setText("");

        txtSearchOut.setText("");

        txtNameRes.setText("");
        txtAddressRes.setText("");
        txtPhoneRes.setText("");

        txtPriceRes.setText("");
        txtEmail.setText("");
        txtSearchRes.setText("");
        txtNameRes1.setText("");

        txtCheckInRes.setCalendar(null);

        txtSearchReq.setText("");

        txtSearchManage.setText("");
        txtPrice.setText("");
        txtRoom.setText("");
        
        txtNameOut.setText("");
        txtCheckInOut.setText("");
        txtCheckOutOut.setText("");
        txtNoStayOutt.setText("");
        txtRoomNoOut.setText("");
        txtPriceOut.setText("");
        txtTotalOut.setText("");
        btnCancel.setVisible(false);
        btnCheckOutConfirm.setVisible(false);

        tfSearch.setText("");
        tableCounter();

    }

    void tableCounter() {
        try {
            //without
            pst = con.prepareStatement("SELECT Count(transaction.transaction_id)  FROM transaction JOIN visitorinformation ON visitorinformation.visitor_id = transaction.person_id JOIN room ON room.roomNo = transaction.roomNo where transaction.checkout is NULL");
            rs = pst.executeQuery();

            while (rs.next()) {
                checkInWithoutCounter.setText(String.valueOf("Without Account: " + rs.getInt(1)));
                checkOutWithoutCounter.setText(String.valueOf("Without Account: " + rs.getInt(1)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            //with
            pst = con.prepareStatement("SELECT Count(transaction.transaction_id) FROM transaction JOIN guestinformation ON guestinformation.guest_id = transaction.person_id JOIN room ON room.roomNo = transaction.roomNo where transaction.checkout is NULL");
            rs = pst.executeQuery();
            while (rs.next()) {
                checkInWithCounter.setText(String.valueOf("With Account: " + rs.getInt(1)));
                checkOutWithCounter.setText(String.valueOf("With Account: " + rs.getInt(1)));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            //with
            pst = con.prepareStatement("SELECT  count(reservation.reserve_id) FROM reservation  \n"
                    + "JOIN guestinformation ON guestinformation.guest_id = reservation.person_id \n"
                    + "JOIN room ON room.roomNo = reservation.roomNo where reservation.status= 'Reserved'");
            rs = pst.executeQuery();

            while (rs.next()) {
                reservationWithCounter.setText(String.valueOf("With Account: " + rs.getInt(1)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            //without
            pst = con.prepareStatement("SELECT  Count(reservation.reserve_id)FROM reservation  \n"
                    + "JOIN visitorinformation ON visitorinformation.visitor_id = reservation.person_id \n"
                    + "JOIN room ON room.roomNo = reservation.roomNo where reservation.status= 'Reserved'");
            rs = pst.executeQuery();

            while (rs.next()) {
                reservationWithoutCounter.setText(String.valueOf("Without Account: " + rs.getInt(1)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            //request
            pst = con.prepareStatement("SELECT  Count(reservation.reserve_id) FROM reservation  \n"
                    + "JOIN guestinformation ON guestinformation.guest_id = reservation.person_id \n"
                    + "JOIN room ON room.roomNo = reservation.roomNo where reservation.status= 'Pending'");
            rs = pst.executeQuery();

            while (rs.next()) {
                requestCounter.setText(String.valueOf("Total: " + rs.getInt(1)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void Home() {
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

    //adds an icon  to a label 
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

    //method is invoke when user click the block/unblock b
    void adminTableClick(String selectedRow, int attempt) {
        int dialogResult;
        try {
            pst = con.prepareStatement("Select from employeeInformation where username=?");
            pst.setString(1, selectedRow);
            if (attempt < 3) {
                dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to block " + selectedRow + "?", "Warning!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (dialogResult == JOptionPane.YES_OPTION) {

                    pst = con.prepareStatement("Update " + tableName + " set attempt=? where username=?");
                    pst.setInt(1, 3);
                    pst.setString(2, selectedRow);

                    pst.executeUpdate();
                }
            } else {
                dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to unblock " + selectedRow + "?", "Warning!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (dialogResult == JOptionPane.YES_OPTION) {

                    pst = con.prepareStatement("Update " + tableName + " set attempt=? where username=?");
                    pst.setInt(1, 0);
                    pst.setString(2, selectedRow);
                    pst.executeUpdate();
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
    }

    //method for Admin Panel
    void Admin() {
        try {
            pstCount = con.prepareStatement("SELECT information.username, COUNT(username) FROM (SELECT username FROM guestInformation UNION ALL SELECT username FROM employeeInformation) AS information where not username = 'administrator'");
            rsCount = pstCount.executeQuery();
            if (rsCount.next()) {
                if (Integer.parseInt(rsCount.getString("count(username)")) > 1) {
                    lblAll.setText("Total: " + rsCount.getString("count(username)") + " Accounts");
                } else {
                    lblAll.setText("Total: " + rsCount.getString("count(username)") + " Account");
                }

                if (Integer.parseInt(rsCount.getString("count(username)")) > 1) {
                    lblFilter.setText("Result: " + rsCount.getString("count(username)") + " Accounts");
                } else {
                    lblFilter.setText("Total: " + rsCount.getString("count(username)") + " Account");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            System.out.print(e);
        }
        updateAdminTable();
    }

    //method is invoke when an admin click the combobox, it is used to filter the table.
    void filter() {
        String selectionAttempt = cbAccountStatus.getSelectedItem().toString();
        String selectionUser = cdTableFilter.getSelectedItem().toString();
        try {
            if (selectionUser.equals("All")) {
                if (selectionAttempt.equals("All")) {
                    pst = con.prepareStatement("SELECT CONCAT(firstName,' ',lastName) As Name ,username As Username,email As Email FROM (SELECT  firstName,lastName  ,username,email FROM guestInformation UNION SELECT  firstName,lastName  ,username,email FROM employeeInformation) unioned where not username = 'administrator'");
                    btnBlock.setEnabled(true);
                    btnUnblock.setEnabled(true);
                } else if (selectionAttempt.equals("Deactivated")) {
                    pst = con.prepareStatement("select CONCAT(firstName,' ',lastName) As Name  ,username As Username, email as Email from guestInformation where attempt >=3 UNION select CONCAT(firstName,' ',lastName) As Name   ,username As Username, email as Email from employeeInformation where attempt >=3 and not username = 'administrator'");
                    btnBlock.setEnabled(false);
                    btnUnblock.setEnabled(true);
                } else if (selectionAttempt.equals("Activated")) {
                    pst = con.prepareStatement("select CONCAT(firstName,' ',lastName) As Name  ,username As Username, email as Email from guestInformation where attempt <3 UNION select CONCAT(firstName,' ',lastName) As Name  ,username As Username, email as Email from employeeInformation where attempt <3 and not username = 'administrator'");
                    btnBlock.setEnabled(true);
                    btnUnblock.setEnabled(false);
                }
            } else if (selectionUser.equals("Guest")) {
                tableName = "guestInformation";
                if (selectionAttempt.equals("All")) {
                    pst = con.prepareStatement("select CONCAT(firstName,' ',lastName) As Name  ,username As Username, email as Email from guestInformation where not username = 'administrator'");
                    btnBlock.setEnabled(true);
                    btnUnblock.setEnabled(true);
                } else if (selectionAttempt.equals("Deactivated")) {
                    pst = con.prepareStatement("select CONCAT(firstName,' ',lastName)AS Name,username As Username, email as Email from guestInformation where attempt >=3 and not username = 'administrator'");
                    btnBlock.setEnabled(false);
                    btnUnblock.setEnabled(true);
                } else if (selectionAttempt.equals("Activated")) {
                    pst = con.prepareStatement("select CONCAT(firstName,' ',lastName) AS Name,username As Username, email as Email from guestInformation where attempt <3 and not username = 'administrator'");
                    btnBlock.setEnabled(true);
                    btnUnblock.setEnabled(false);
                }
            } else if (selectionUser.equals("Employee")) {

                if (selectionAttempt.equals("All")) {
                    pst = con.prepareStatement("select CONCAT(firstName,' ',lastName) AS Name,username As Username, email as Email from employeeInformation where not username = 'administrator'");
                    btnBlock.setEnabled(true);
                    btnUnblock.setEnabled(true);
                } else if (selectionAttempt.equals("Deactivated")) {
                    pst = con.prepareStatement("select CONCAT(firstName,' ',lastName)  AS Name,username As Username, email as Email from employeeInformation where attempt >=3 and not username = 'administrator'");
                    btnBlock.setEnabled(false);
                    btnUnblock.setEnabled(true);
                } else if (selectionAttempt.equals("Activated")) {
                    pst = con.prepareStatement("select CONCAT(firstName,' ',lastName)  AS Name,username As Username, email as Email from employeeInformation where attempt <3 and not username = 'administrator'");
                    btnBlock.setEnabled(true);
                    btnUnblock.setEnabled(false);
                }
            }
            rs = pst.executeQuery();
            tblAdmin.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
        }
    }

    //method that updates the records shown on the table
    void updateAdminTable() {
        try {
            pst = con.prepareStatement("select CONCAT(firstName,' ',lastName) As Name  ,username As Username, email as Email  from guestInformation UNION select CONCAT(firstName,' ',lastName) As Name ,username, email from employeeInformation where not username = 'administrator'");
            rs = pst.executeQuery();
            tblAdmin.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
        tableCounter();
    }

    //method for blocking or unblocking one or more users depending on the records shown on the table
    void permission(String message, int value) {
        String selectionAttempt = cbAccountStatus.getSelectedItem().toString();
        String selectionUser = cdTableFilter.getSelectedItem().toString();
        int dialogResult = JOptionPane.showConfirmDialog(null, message, "Warning!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (dialogResult == JOptionPane.YES_OPTION) {
            try {
                if (selectionUser.equals("All")) {
                    if (selectionAttempt.equals("All")) {

                        pst = con.prepareStatement("Update guestInformation,employeeInformation set guestInformation.attempt = ?, employeeInformation.attempt = ?");

                    } else if (selectionAttempt.equals("Deactivated")) {
                        pst = con.prepareStatement("Update guestInformation,employeeInformation set guestInformation.attempt = ?, employeeInformation.attempt = ? where guestInformation.attempt >=3 or employeeInformation.attempt >=3 ");

                    } else if (selectionAttempt.equals("Activated")) {
                        pst = con.prepareStatement("Update guestInformation,employeeInformation set guestInformation.attempt = ?, employeeInformation.attempt = ? where guestInformation.attempt <3 or employeeInformation.attempt <3 ");

                    }
                    pst.setInt(1, value);
                    pst.setInt(2, value);

                } else if (selectionUser.equals("Guest")) {
                    tableName = "guestInformation";

                    if (selectionAttempt.equals("All")) {
                        pst = con.prepareStatement("Update guestInformation set attempt = ?");

                    } else if (selectionAttempt.equals("Deactivated")) {
                        pst = con.prepareStatement("Update guestInformation set attempt = ? where attempt >=3");

                    } else if (selectionAttempt.equals("Activated")) {
                        pst = con.prepareStatement("Update guestInformation set attempt = ? where attempt <3");

                    }
                    pst.setInt(1, value);
                } else if (selectionUser.equals("Employee")) {
                    tableName = "employeeInformation";

                    if (selectionAttempt.equals("All")) {
                        pst = con.prepareStatement("Update employeeInformation set attempt = ?");

                    } else if (selectionAttempt.equals("Deactivated")) {
                        pst = con.prepareStatement("Update employeeInformation set attempt = ? where attempt >=3");

                    } else if (selectionAttempt.equals("Activated")) {
                        pst = con.prepareStatement("Update employeeInformation set attempt = ? where attempt <3");

                    }
                    pst.setInt(1, value);
                }

                pst.executeUpdate();
                tblAdmin.setModel(DbUtils.resultSetToTableModel(rs));
                filter();
                //updateAdminTable();
            } catch (SQLException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    //determines if the employee is a staff or an administrator, if the employee is an administrator the account button will be shown in the menu
    public void userType() {
        String employeeType = null;
        try {

            pst = con.prepareStatement("Select * from employeeInformation where email=?");
            pst.setString(1, validationEmail);
            System.out.println(validationEmail);
            rs = pst.executeQuery();

            if (rs.next()) {
                employeeType = rs.getString("type");
                if (employeeType.equals("Administrator")) {
                    btnAccount.setVisible(true);
                } else if (employeeType.equals("Staff")) {
                    btnAccount.setVisible(false);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Method to get the available Room Number and its corresponding price for the combo box in Check-In Section
    public void roomDetailsCheckIn() {
        String bed, roomType;
        comboRoomNoIn.removeAllItems();
        txtPriceCheckIn.setText("");
        bed = (String) comboBedIn.getSelectedItem();
        roomType = (String) comboRoomtypeIn.getSelectedItem();
        try {
            pst = con.prepareStatement("SELECT * FROM room WHERE bed = ? and roomType = ? and status = 'Available'"); //Execute a query where the system will get the value of column bed 
            pst.setString(1, bed);                                                                                    //and roomType with condition status must contain a 'Not Booked' Value
            pst.setString(2, roomType);
            rs = pst.executeQuery();

            while (rs.next()) {
                comboRoomNoIn.addItem(rs.getString(1)); //After getting the value, it will be stored to this combo box
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Method to get the available Room Number and its corresponding price for the combo box in Reservation Section
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
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Method to update the table in Manage Room Section
    private void updateManage() {
        try {
            pst = con.prepareStatement("SELECT roomNo As RoomNumber, roomType AS RoomType, bed AS Bed, price AS Price, status AS Status FROM room ORDER BY length(roomNo),roomNo");//Query to get all the value from room database table
            rs = pst.executeQuery();
            manageTbl.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
        tableCounter();
    }

    //Methods to update the table in Check-In Section
    private void updateCheckInWithout() {
        try {
            pst = con.prepareStatement("SELECT transaction.transaction_id As ID, CONCAT( visitorinformation.firstName, ' ' ,visitorinformation.lastName ) As Name, visitorinformation.email As Email, visitorinformation.address As Address,  visitorinformation.phone As Phone, transaction.roomNo As RoomNumber, room.bed As Bed, room.roomType As RoomType, room.price As Price,transaction.checkIn As CheckIn FROM transaction JOIN visitorinformation ON visitorinformation.visitor_id = transaction.person_id JOIN room ON room.roomNo = transaction.roomNo where transaction.checkout is NULL");
            rs = pst.executeQuery();
            checkInTbl.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
        tableCounter();
    }

    private void updateCheckInWith() {
        try {
            pst = con.prepareStatement("SELECT transaction.transaction_id As ID , CONCAT( guestinformation.firstName, ' ' ,guestinformation.lastName ) As Name, guestinformation.email As Email, guestinformation.address As Address,  guestinformation.phone As Phone, transaction.roomNo As RoomNumber, room.bed As Bed, room.roomType As RoomType, room.price As Price,transaction.checkIn As CheckIn FROM transaction JOIN guestinformation ON guestinformation.guest_id = transaction.person_id JOIN room ON room.roomNo = transaction.roomNo where transaction.checkout is NULL");
            rs = pst.executeQuery();
            checkInTbl.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
        tableCounter();
    }

    //Methods to update the table in Check-Out Section
    private void updateCheckOutWithout() {
        try {
            pst = con.prepareStatement("SELECT transaction.transaction_id As ID , CONCAT( visitorinformation.firstName, ' ' ,visitorinformation.lastName ) As Name, visitorinformation.email As Email, visitorinformation.address As Address,  visitorinformation.phone As Phone, transaction.roomNo As RoomNumber, room.bed As Bed, room.roomType As RoomType, room.price As Price,transaction.checkIn As CheckIn FROM transaction JOIN visitorinformation ON visitorinformation.visitor_id = transaction.person_id JOIN room ON room.roomNo = transaction.roomNo where transaction.checkout is NULL");
            rs = pst.executeQuery();
            checkOutTbl.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
        tableCounter();
    }

    private void updateCheckOutWith() {
        try {
            pst = con.prepareStatement("SELECT transaction.transaction_id As ID , CONCAT( guestinformation.firstName, ' ' ,guestinformation.lastName ) As Name, guestinformation.email As Email, guestinformation.address As Address,  guestinformation.phone As Phone, transaction.roomNo As RoomNumber, room.bed As Bed, room.roomType As RoomType, room.price As Price,transaction.checkIn As CheckIn FROM transaction JOIN guestinformation ON guestinformation.guest_id = transaction.person_id JOIN room ON room.roomNo = transaction.roomNo where transaction.checkout is NULL");
            rs = pst.executeQuery();
            checkOutTbl.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
        tableCounter();
    }

    //Method to update the table in Reques List Section
    private void updateRequestList() {
        try {
            pst = con.prepareStatement("SELECT  reservation.reserve_id As ID, CONCAT( guestinformation.firstName, ' ' ,guestinformation.lastName ) As Name, guestinformation.email As Email, guestinformation.address As Address,  guestinformation.phone As Phone, reservation.roomNo As RoomNumber, room.bed As Bed, room.roomType As RoomType, room.price As Price,reservation.checkIn As CheckIn, reservation.status As Status\n"
                    + "FROM reservation  \n"
                    + "JOIN guestinformation ON guestinformation.guest_id = reservation.person_id \n"
                    + "JOIN room ON room.roomNo = reservation.roomNo where reservation.status= 'Pending'");
            rs = pst.executeQuery();
            tblRequest.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
        tableCounter();
    }

    //Method to update the table in Reservation Section
    private void updateReservationWithout() {
        try {
            pst = con.prepareStatement("SELECT  reservation.reserve_id As ID , visitorinformation.visitor_id As PersonID, CONCAT( visitorinformation.firstName, ' ' ,visitorinformation.lastName ) As Name, visitorinformation.email As Email, visitorinformation.address As Address,  visitorinformation.phone As Phone, reservation.roomNo As RoomNumber, room.bed As Bed, room.roomType As RoomType, room.price As Price,reservation.checkIn As CheckIn, reservation.status As Status\n"
                    + "FROM reservation  \n"
                    + "JOIN visitorinformation ON visitorinformation.visitor_id = reservation.person_id \n"
                    + "JOIN room ON room.roomNo = reservation.roomNo where reservation.status= 'Reserved'");
            rs = pst.executeQuery();
            reservationTbl.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
        tableCounter();
    }

    private void updateReservationWith() {
        try {
            pst = con.prepareStatement("SELECT  reservation.reserve_id As ID , guestinformation.guest_id As PersonID, CONCAT( guestinformation.firstName, ' ' ,guestinformation.lastName ) As Name, guestinformation.email As Email, guestinformation.address As Address,  guestinformation.phone As Phone, reservation.roomNo As RoomNumber, room.bed As Bed, room.roomType As RoomType, room.price As Price,reservation.checkIn As CheckIn, reservation.status As Status\n"
                    + "FROM reservation  \n"
                    + "JOIN guestinformation ON guestinformation.guest_id = reservation.person_id \n"
                    + "JOIN room ON room.roomNo = reservation.roomNo where reservation.status= 'Reserved'");
            rs = pst.executeQuery();
            reservationTbl.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
        tableCounter();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pRequest = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        spRequest = new javax.swing.JScrollPane();
        tblRequest = new javax.swing.JTable();
        btnDelte = new javax.swing.JButton();
        btnApprove = new javax.swing.JButton();
        txtSearchReq = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        requestCounter = new javax.swing.JLabel();
        pManage = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtRoom = new javax.swing.JTextField();
        btnDeleteRoom = new javax.swing.JButton();
        btnAddroom = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        comboBed = new javax.swing.JComboBox<>();
        comboRoom = new javax.swing.JComboBox<>();
        jLabel40 = new javax.swing.JLabel();
        spManage = new javax.swing.JScrollPane();
        manageTbl = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtSearchManage = new javax.swing.JTextField();
        pReservation = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
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
        spReservation = new javax.swing.JScrollPane();
        reservationTbl = new javax.swing.JTable();
        btnConfirmRes = new javax.swing.JButton();
        txtEmail = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        btnDelte1 = new javax.swing.JButton();
        btntransfer = new javax.swing.JButton();
        txtSearchRes = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        txtNameRes1 = new javax.swing.JTextField();
        cbbReservationFilter = new javax.swing.JComboBox<>();
        jLabel45 = new javax.swing.JLabel();
        txtCheckInRes = new com.toedter.calendar.JDateChooser();
        jButton4 = new javax.swing.JButton();
        reservationWithoutCounter = new javax.swing.JLabel();
        reservationWithCounter = new javax.swing.JLabel();
        pHome = new javax.swing.JPanel();
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
        jLabel36 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        pCheckOut = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtSearchOut = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        spCheckOut = new javax.swing.JScrollPane();
        checkOutTbl = new javax.swing.JTable();
        cbbCheckoutFilter = new javax.swing.JComboBox<>();
        checkOutWithoutCounter = new javax.swing.JLabel();
        checkOutWithCounter = new javax.swing.JLabel();
        pCheckOutConfirm = new javax.swing.JPanel();
        btnCheckOutConfirm = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtNameOut = new javax.swing.JLabel();
        txtCheckInOut = new javax.swing.JLabel();
        txtCheckOutOut = new javax.swing.JLabel();
        txtNoStayOutt = new javax.swing.JLabel();
        txtRoomNoOut = new javax.swing.JLabel();
        txtPriceOut = new javax.swing.JLabel();
        txtTotalOut = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        pAdmin = new javax.swing.JPanel();
        cbAccountStatus = new javax.swing.JComboBox<>();
        lblFilter = new javax.swing.JLabel();
        tfSearch = new javax.swing.JTextField();
        btnBlock = new javax.swing.JButton();
        btnUnblock = new javax.swing.JButton();
        cdTableFilter = new javax.swing.JComboBox<>();
        lblAll = new javax.swing.JLabel();
        spAdmin = new javax.swing.JScrollPane();
        tblAdmin = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        FDS = new javax.swing.JLabel();
        FDS1 = new javax.swing.JLabel();
        FDS2 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        pCheckIn = new javax.swing.JPanel();
        checkInWithoutCounter = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNameIn = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtAddressIn = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtPhoneIn = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtInDateIn = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        comboBedIn = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        comboRoomtypeIn = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        comboRoomNoIn = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        txtPriceCheckIn = new javax.swing.JTextField();
        btnAllot = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        spCheckIn = new javax.swing.JScrollPane();
        checkInTbl = new javax.swing.JTable();
        jLabel39 = new javax.swing.JLabel();
        txtEmailIn = new javax.swing.JTextField();
        txtSearchIn = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        txtNameIn1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cbbCheckinFilter = new javax.swing.JComboBox<>();
        checkInWithCounter = new javax.swing.JLabel();
        pMenu = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        pcHome = new javax.swing.JPanel();
        btnHome = new javax.swing.JLabel();
        pcCheckin = new javax.swing.JPanel();
        btnCheckin = new javax.swing.JLabel();
        pcCheckout = new javax.swing.JPanel();
        btnCheckout = new javax.swing.JLabel();
        pcReservation = new javax.swing.JPanel();
        btnReservation = new javax.swing.JLabel();
        pcLogout = new javax.swing.JPanel();
        btnLogout = new javax.swing.JLabel();
        pcAccount = new javax.swing.JPanel();
        btnAccount = new javax.swing.JLabel();
        pcRequest = new javax.swing.JPanel();
        btnReques = new javax.swing.JLabel();
        pcRoom = new javax.swing.JPanel();
        btnRoom = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pRequest.setBackground(new java.awt.Color(239, 233, 221));
        pRequest.setPreferredSize(new java.awt.Dimension(1210, 632));
        pRequest.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 36)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(23, 68, 55));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("Reservation Request");
        pRequest.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 70, 1520, -1));

        spRequest.setBackground(new java.awt.Color(239, 233, 221));
        spRequest.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(165, 89, 40), 3));

        tblRequest.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        tblRequest.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customber ID", "Name", "Address", "Phone Number", "Check-In Date", "Room Number", "Bed Type", "Room Type", "Price Per Day", "Number of Days Stay", "Total Amount", "Check-Out Date"
            }
        ));
        tblRequest.setFocusable(false);
        tblRequest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRequestMouseClicked(evt);
            }
        });
        spRequest.setViewportView(tblRequest);

        pRequest.add(spRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 1410, 690));

        btnDelte.setBackground(new java.awt.Color(165, 89, 40));
        btnDelte.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 18)); // NOI18N
        btnDelte.setForeground(new java.awt.Color(255, 255, 255));
        btnDelte.setText("Delete");
        btnDelte.setBorder(null);
        btnDelte.setPreferredSize(new java.awt.Dimension(50, 50));
        btnDelte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelteActionPerformed(evt);
            }
        });
        pRequest.add(btnDelte, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 970, 250, -1));

        btnApprove.setBackground(new java.awt.Color(165, 89, 40));
        btnApprove.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 18)); // NOI18N
        btnApprove.setForeground(new java.awt.Color(255, 255, 255));
        btnApprove.setText("Approve");
        btnApprove.setBorder(null);
        btnApprove.setPreferredSize(new java.awt.Dimension(50, 50));
        btnApprove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApproveActionPerformed(evt);
            }
        });
        pRequest.add(btnApprove, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 970, 250, -1));

        txtSearchReq.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        txtSearchReq.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(23, 68, 55), 2, true));
        txtSearchReq.setPreferredSize(new java.awt.Dimension(35, 35));
        txtSearchReq.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchReqKeyReleased(evt);
            }
        });
        pRequest.add(txtSearchReq, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 350, -1));

        jLabel41.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(23, 68, 55));
        jLabel41.setText("Search:");
        pRequest.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, 35));

        requestCounter.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        requestCounter.setForeground(new java.awt.Color(24, 71, 58));
        requestCounter.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        requestCounter.setText("13 Accounts");
        pRequest.add(requestCounter, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 910, 280, 40));

        getContentPane().add(pRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 1520, 1080));

        pManage.setBackground(new java.awt.Color(239, 233, 221));
        pManage.setForeground(new java.awt.Color(23, 68, 55));
        pManage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(23, 68, 55));
        jLabel2.setText("Room Number:");
        pManage.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 250, -1, 30));

        txtRoom.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        txtRoom.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(23, 68, 55), 2, true));
        txtRoom.setPreferredSize(new java.awt.Dimension(35, 35));
        pManage.add(txtRoom, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 280, 250, -1));

        btnDeleteRoom.setBackground(new java.awt.Color(165, 89, 40));
        btnDeleteRoom.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 18)); // NOI18N
        btnDeleteRoom.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteRoom.setText("Delete Room");
        btnDeleteRoom.setBorder(null);
        btnDeleteRoom.setBorderPainted(false);
        btnDeleteRoom.setPreferredSize(new java.awt.Dimension(149, 50));
        btnDeleteRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteRoomActionPerformed(evt);
            }
        });
        pManage.add(btnDeleteRoom, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 680, 250, -1));

        btnAddroom.setBackground(new java.awt.Color(165, 89, 40));
        btnAddroom.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 18)); // NOI18N
        btnAddroom.setForeground(new java.awt.Color(255, 255, 255));
        btnAddroom.setText("Add Room");
        btnAddroom.setBorder(null);
        btnAddroom.setBorderPainted(false);
        btnAddroom.setPreferredSize(new java.awt.Dimension(123, 50));
        btnAddroom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddroomActionPerformed(evt);
            }
        });
        pManage.add(btnAddroom, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 560, 250, -1));

        btnUpdate.setBackground(new java.awt.Color(165, 89, 40));
        btnUpdate.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 18)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Update Room");
        btnUpdate.setBorder(null);
        btnUpdate.setBorderPainted(false);
        btnUpdate.setPreferredSize(new java.awt.Dimension(149, 50));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        pManage.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 620, 250, -1));

        jLabel5.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(23, 68, 55));
        jLabel5.setText("Price:");
        pManage.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 460, -1, 30));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(23, 68, 55));
        jLabel3.setText("Room Type:");
        pManage.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 320, -1, 30));

        txtPrice.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        txtPrice.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(23, 68, 55), 2, true));
        txtPrice.setPreferredSize(new java.awt.Dimension(35, 35));
        pManage.add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 490, 250, -1));

        comboBed.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        comboBed.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Single", "Double", "Triple" }));
        comboBed.setBorder(null);
        comboBed.setPreferredSize(new java.awt.Dimension(35, 35));
        pManage.add(comboBed, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 420, 250, -1));

        comboRoom.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        comboRoom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "AC", "Non-AC" }));
        comboRoom.setBorder(null);
        comboRoom.setPreferredSize(new java.awt.Dimension(35, 35));
        pManage.add(comboRoom, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 350, 250, -1));

        jLabel40.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(23, 68, 55));
        jLabel40.setText("Bed:");
        pManage.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 390, -1, 30));

        spManage.setBackground(new java.awt.Color(239, 233, 221));
        spManage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(165, 89, 40), 3));

        manageTbl.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        manageTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Room Number", "Room Type", "Bed", "Price", "Status"
            }
        ));
        manageTbl.setFocusable(false);
        manageTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                manageTblMouseClicked(evt);
            }
        });
        spManage.setViewportView(manageTbl);

        pManage.add(spManage, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 1100, 760));

        jLabel1.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(23, 68, 55));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Manage Rooms");
        pManage.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1520, -1));

        jLabel4.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(23, 68, 55));
        jLabel4.setText("Search:");
        pManage.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 173, -1, 30));

        txtSearchManage.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        txtSearchManage.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(23, 68, 55), 2, true));
        txtSearchManage.setPreferredSize(new java.awt.Dimension(35, 35));
        txtSearchManage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchManageKeyReleased(evt);
            }
        });
        pManage.add(txtSearchManage, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 350, -1));

        getContentPane().add(pManage, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 1520, 1080));

        pReservation.setBackground(new java.awt.Color(239, 233, 221));
        pReservation.setPreferredSize(new java.awt.Dimension(1210, 632));
        pReservation.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 36)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(23, 68, 55));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Reservation");
        pReservation.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1520, -1));

        jLabel26.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(23, 68, 55));
        jLabel26.setText("Last Name:");
        pReservation.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, -1, -1));

        txtNameRes.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        txtNameRes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(23, 68, 55), 2, true));
        txtNameRes.setPreferredSize(new java.awt.Dimension(35, 35));
        pReservation.add(txtNameRes, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 250, -1));

        jLabel27.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(23, 68, 55));
        jLabel27.setText("Address: (Optional)");
        pReservation.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 150, -1, -1));

        txtAddressRes.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        txtAddressRes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(23, 68, 55), 2, true));
        txtAddressRes.setPreferredSize(new java.awt.Dimension(35, 35));
        pReservation.add(txtAddressRes, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 180, 250, -1));

        jLabel28.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(23, 68, 55));
        jLabel28.setText("Phone Number: (Optional)");
        pReservation.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 150, -1, -1));

        txtPhoneRes.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        txtPhoneRes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(23, 68, 55), 2, true));
        txtPhoneRes.setPreferredSize(new java.awt.Dimension(35, 35));
        pReservation.add(txtPhoneRes, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 180, 250, -1));

        jLabel29.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(23, 68, 55));
        jLabel29.setText("Check In Date:");
        pReservation.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, -1, 30));

        jLabel30.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(23, 68, 55));
        jLabel30.setText("Search:");
        pReservation.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 403, -1, 30));

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
        pReservation.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 220, 100, 30));

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

        spReservation.setBackground(new java.awt.Color(239, 233, 221));
        spReservation.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(165, 89, 40), 3));

        reservationTbl.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        reservationTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Email Address", "Address", "Phone Number", "Check-In Date", "Bed Type", "Room Type", "Room Number", "Price", "Status"
            }
        ));
        reservationTbl.setFocusable(false);
        reservationTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reservationTblMouseClicked(evt);
            }
        });
        spReservation.setViewportView(reservationTbl);

        pReservation.add(spReservation, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 450, 1410, 460));

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
        pReservation.add(btnConfirmRes, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 310, 250, -1));

        txtEmail.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        txtEmail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(23, 68, 55), 2, true));
        txtEmail.setPreferredSize(new java.awt.Dimension(35, 35));
        pReservation.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 180, 250, -1));

        jLabel38.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(23, 68, 55));
        jLabel38.setText("Email: (Optional)");
        pReservation.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 150, -1, -1));

        btnDelte1.setBackground(new java.awt.Color(165, 89, 40));
        btnDelte1.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 18)); // NOI18N
        btnDelte1.setForeground(new java.awt.Color(255, 255, 255));
        btnDelte1.setText("Delete");
        btnDelte1.setBorder(null);
        btnDelte1.setPreferredSize(new java.awt.Dimension(50, 50));
        btnDelte1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelte1ActionPerformed(evt);
            }
        });
        pReservation.add(btnDelte1, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 970, 250, -1));

        btntransfer.setBackground(new java.awt.Color(165, 89, 40));
        btntransfer.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 18)); // NOI18N
        btntransfer.setForeground(new java.awt.Color(255, 255, 255));
        btntransfer.setText("Check In");
        btntransfer.setBorder(null);
        btntransfer.setPreferredSize(new java.awt.Dimension(50, 50));
        btntransfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntransferActionPerformed(evt);
            }
        });
        pReservation.add(btntransfer, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 970, 250, -1));

        txtSearchRes.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        txtSearchRes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(23, 68, 55), 2, true));
        txtSearchRes.setPreferredSize(new java.awt.Dimension(35, 35));
        txtSearchRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchResActionPerformed(evt);
            }
        });
        txtSearchRes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchResKeyReleased(evt);
            }
        });
        pReservation.add(txtSearchRes, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 400, 350, -1));

        jLabel44.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(23, 68, 55));
        jLabel44.setText("Bed:");
        pReservation.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, -1, 30));

        txtNameRes1.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        txtNameRes1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(23, 68, 55), 2, true));
        txtNameRes1.setPreferredSize(new java.awt.Dimension(35, 35));
        pReservation.add(txtNameRes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 180, 250, -1));

        cbbReservationFilter.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        cbbReservationFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Without Account", "With Account" }));
        cbbReservationFilter.setBorder(null);
        cbbReservationFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbReservationFilterActionPerformed(evt);
            }
        });
        pReservation.add(cbbReservationFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 400, 250, 35));

        jLabel45.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(23, 68, 55));
        jLabel45.setText("First Name:");
        pReservation.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, -1, -1));

        txtCheckInRes.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        pReservation.add(txtCheckInRes, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 250, 35));

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
        pReservation.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1340, 310, 130, 50));

        reservationWithoutCounter.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        reservationWithoutCounter.setForeground(new java.awt.Color(24, 71, 58));
        reservationWithoutCounter.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        reservationWithoutCounter.setText("13 Accounts");
        pReservation.add(reservationWithoutCounter, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 910, 280, 40));

        reservationWithCounter.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        reservationWithCounter.setForeground(new java.awt.Color(24, 71, 58));
        reservationWithCounter.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        reservationWithCounter.setText("13 Accounts");
        pReservation.add(reservationWithCounter, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 910, 280, 40));

        getContentPane().add(pReservation, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 1520, 1080));

        pHome.setBackground(new java.awt.Color(239, 233, 221));
        pHome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jLabel36.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 36)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(24, 71, 58));
        jLabel36.setText("Room Status:");
        pHome.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, -1, -1));

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

        pCheckOut.setBackground(new java.awt.Color(239, 233, 221));
        pCheckOut.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 36)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(24, 71, 58));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Check Out");
        pCheckOut.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 70, 1510, -1));

        txtSearchOut.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        txtSearchOut.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(23, 68, 55), 2, true));
        txtSearchOut.setPreferredSize(new java.awt.Dimension(35, 35));
        txtSearchOut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchOutKeyReleased(evt);
            }
        });
        pCheckOut.add(txtSearchOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 350, -1));

        jLabel43.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(23, 68, 55));
        jLabel43.setText("Search:");
        pCheckOut.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 193, -1, 30));

        spCheckOut.setBackground(new java.awt.Color(239, 233, 221));
        spCheckOut.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(165, 89, 40), 3));

        checkOutTbl.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        checkOutTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer ID", "Name", "Email Address", "Address", "Phone Number", "Check IN Date", "Room Number", "Bed", "Room Type", "Price Per Day"
            }
        ));
        checkOutTbl.setFocusable(false);
        checkOutTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkOutTblMouseClicked(evt);
            }
        });
        spCheckOut.setViewportView(checkOutTbl);

        pCheckOut.add(spCheckOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 780, 720));

        cbbCheckoutFilter.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        cbbCheckoutFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Without Account", "With Account" }));
        cbbCheckoutFilter.setBorder(null);
        cbbCheckoutFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbCheckoutFilterActionPerformed(evt);
            }
        });
        pCheckOut.add(cbbCheckoutFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 190, 250, 35));

        checkOutWithoutCounter.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        checkOutWithoutCounter.setForeground(new java.awt.Color(24, 71, 58));
        checkOutWithoutCounter.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        checkOutWithoutCounter.setText("13 Accounts");
        pCheckOut.add(checkOutWithoutCounter, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 960, 280, 40));

        checkOutWithCounter.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        checkOutWithCounter.setForeground(new java.awt.Color(24, 71, 58));
        checkOutWithCounter.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        checkOutWithCounter.setText("13 Accounts");
        pCheckOut.add(checkOutWithCounter, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 960, 280, 40));

        pCheckOutConfirm.setBackground(new java.awt.Color(239, 233, 221));
        pCheckOutConfirm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(165, 89, 40), 3));
        pCheckOutConfirm.setForeground(new java.awt.Color(165, 89, 40));
        pCheckOutConfirm.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCheckOutConfirm.setBackground(new java.awt.Color(165, 89, 40));
        btnCheckOutConfirm.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 18)); // NOI18N
        btnCheckOutConfirm.setForeground(new java.awt.Color(255, 255, 255));
        btnCheckOutConfirm.setText("Check-Out");
        btnCheckOutConfirm.setBorder(null);
        btnCheckOutConfirm.setPreferredSize(new java.awt.Dimension(50, 50));
        btnCheckOutConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckOutConfirmActionPerformed(evt);
            }
        });
        pCheckOutConfirm.add(btnCheckOutConfirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 640, 250, 50));

        jLabel24.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 30)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Checkout Summary");
        pCheckOutConfirm.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 80));

        jLabel17.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(24, 71, 58));
        jLabel17.setText("Price:");
        pCheckOutConfirm.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 430, 60, 40));

        jLabel18.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 20)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(24, 71, 58));
        jLabel18.setText("Name:");
        pCheckOutConfirm.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 70, 40));

        jLabel19.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 20)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(24, 71, 58));
        jLabel19.setText("Check-In Date:");
        pCheckOutConfirm.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, 150, 40));

        jLabel20.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 20)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(24, 71, 58));
        jLabel20.setText("Check-Out Date:");
        pCheckOutConfirm.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 170, 40));

        jLabel21.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 20)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(24, 71, 58));
        jLabel21.setText("Days Stay:");
        pCheckOutConfirm.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 110, 40));

        jLabel22.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 20)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(24, 71, 58));
        jLabel22.setText("Room Number:");
        pCheckOutConfirm.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, 160, 40));

        jLabel23.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 20)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(24, 71, 58));
        jLabel23.setText("Total Amount:");
        pCheckOutConfirm.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 520, 150, 40));

        txtNameOut.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 20)); // NOI18N
        txtNameOut.setForeground(new java.awt.Color(24, 71, 58));
        txtNameOut.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        txtNameOut.setText("1");
        pCheckOutConfirm.add(txtNameOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 130, 220, 40));

        txtCheckInOut.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 20)); // NOI18N
        txtCheckInOut.setForeground(new java.awt.Color(24, 71, 58));
        txtCheckInOut.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        txtCheckInOut.setText("2");
        pCheckOutConfirm.add(txtCheckInOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 190, 220, 40));

        txtCheckOutOut.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 20)); // NOI18N
        txtCheckOutOut.setForeground(new java.awt.Color(24, 71, 58));
        txtCheckOutOut.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        txtCheckOutOut.setText("3");
        pCheckOutConfirm.add(txtCheckOutOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 250, 220, 40));

        txtNoStayOutt.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 20)); // NOI18N
        txtNoStayOutt.setForeground(new java.awt.Color(24, 71, 58));
        txtNoStayOutt.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        txtNoStayOutt.setText("4");
        pCheckOutConfirm.add(txtNoStayOutt, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 310, 220, 40));

        txtRoomNoOut.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 20)); // NOI18N
        txtRoomNoOut.setForeground(new java.awt.Color(24, 71, 58));
        txtRoomNoOut.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        txtRoomNoOut.setText("5");
        pCheckOutConfirm.add(txtRoomNoOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 370, 220, 40));

        txtPriceOut.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 20)); // NOI18N
        txtPriceOut.setForeground(new java.awt.Color(24, 71, 58));
        txtPriceOut.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        txtPriceOut.setText("6");
        pCheckOutConfirm.add(txtPriceOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 430, 220, 40));

        txtTotalOut.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 20)); // NOI18N
        txtTotalOut.setForeground(new java.awt.Color(24, 71, 58));
        txtTotalOut.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        txtTotalOut.setText("7");
        pCheckOutConfirm.add(txtTotalOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 520, 220, 40));

        btnCancel.setBackground(new java.awt.Color(165, 89, 40));
        btnCancel.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 18)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("Cancel");
        btnCancel.setBorder(null);
        btnCancel.setPreferredSize(new java.awt.Dimension(50, 50));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        pCheckOutConfirm.add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 640, 250, 50));

        jPanel8.setBackground(new java.awt.Color(165, 89, 40));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        pCheckOutConfirm.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 80));

        jPanel3.setBackground(new java.awt.Color(24, 71, 58));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(24, 71, 58)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 488, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        pCheckOutConfirm.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 500, 490, 4));

        pCheckOut.add(pCheckOutConfirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 240, 590, 720));

        getContentPane().add(pCheckOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 1520, 1080));

        pAdmin.setBackground(new java.awt.Color(239, 233, 221));
        pAdmin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbAccountStatus.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        cbAccountStatus.setForeground(new java.awt.Color(24, 71, 58));
        cbAccountStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Activated", "Deactivated" }));
        cbAccountStatus.setBorder(null);
        cbAccountStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAccountStatusActionPerformed(evt);
            }
        });
        pAdmin.add(cbAccountStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 140, 150, 35));

        lblFilter.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lblFilter.setForeground(new java.awt.Color(24, 71, 58));
        lblFilter.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblFilter.setText("13 Accounts");
        pAdmin.add(lblFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 910, 280, 40));

        tfSearch.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        tfSearch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(24, 71, 58), 2, true));
        tfSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfSearchKeyReleased(evt);
            }
        });
        pAdmin.add(tfSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 350, 35));

        btnBlock.setBackground(new java.awt.Color(165, 89, 40));
        btnBlock.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 18)); // NOI18N
        btnBlock.setForeground(new java.awt.Color(255, 255, 255));
        btnBlock.setText("Deactivate");
        btnBlock.setBorder(null);
        btnBlock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBlockActionPerformed(evt);
            }
        });
        pAdmin.add(btnBlock, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 970, 250, 50));

        btnUnblock.setBackground(new java.awt.Color(165, 89, 40));
        btnUnblock.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 18)); // NOI18N
        btnUnblock.setForeground(new java.awt.Color(255, 255, 255));
        btnUnblock.setText("Activate");
        btnUnblock.setBorder(null);
        btnUnblock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnblockActionPerformed(evt);
            }
        });
        pAdmin.add(btnUnblock, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 970, 250, 50));

        cdTableFilter.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        cdTableFilter.setForeground(new java.awt.Color(24, 71, 58));
        cdTableFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Guest", "Employee" }));
        cdTableFilter.setBorder(null);
        cdTableFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cdTableFilterActionPerformed(evt);
            }
        });
        pAdmin.add(cdTableFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 140, 150, 35));

        lblAll.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lblAll.setForeground(new java.awt.Color(24, 71, 58));
        lblAll.setText("20 Accounts");
        pAdmin.add(lblAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 910, 270, 40));

        spAdmin.setBackground(new java.awt.Color(239, 233, 221));
        spAdmin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(165, 89, 40), 3));
        spAdmin.setForeground(new java.awt.Color(24, 71, 58));

        tblAdmin.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        tblAdmin.setFocusable(false);
        tblAdmin.setGridColor(new java.awt.Color(51, 51, 255));
        tblAdmin.getTableHeader().setReorderingAllowed(false);
        tblAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAdminMouseClicked(evt);
            }
        });
        tblAdmin.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tblAdminPropertyChange(evt);
            }
        });
        spAdmin.setViewportView(tblAdmin);

        pAdmin.add(spAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 1410, 720));

        jButton1.setBackground(new java.awt.Color(165, 89, 40));
        jButton1.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Add");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        pAdmin.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 970, 250, 50));

        jButton3.setBackground(new java.awt.Color(165, 89, 40));
        jButton3.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Delete");
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        pAdmin.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 970, 250, 50));

        FDS.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        FDS.setForeground(new java.awt.Color(24, 71, 58));
        FDS.setText("Search:");
        pAdmin.add(FDS, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 90, 35));

        FDS1.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        FDS1.setForeground(new java.awt.Color(24, 71, 58));
        FDS1.setText("Account Status:");
        pAdmin.add(FDS1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 140, 140, 35));

        FDS2.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        FDS2.setForeground(new java.awt.Color(24, 71, 58));
        FDS2.setText("Account Type:");
        pAdmin.add(FDS2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 140, 130, 35));

        jLabel46.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 36)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(23, 68, 55));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("Manage Accounts");
        pAdmin.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 70, 1510, -1));

        getContentPane().add(pAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 1520, 1080));

        pCheckIn.setBackground(new java.awt.Color(239, 233, 221));
        pCheckIn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        checkInWithoutCounter.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        checkInWithoutCounter.setForeground(new java.awt.Color(24, 71, 58));
        checkInWithoutCounter.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        checkInWithoutCounter.setText("13 Accounts");
        pCheckIn.add(checkInWithoutCounter, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 1000, 280, 40));

        jLabel6.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(23, 68, 55));
        jLabel6.setText("Search:");
        pCheckIn.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 405, -1, -1));

        jLabel7.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(23, 68, 55));
        jLabel7.setText("First Name:");
        pCheckIn.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, -1, -1));

        txtNameIn.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        txtNameIn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(23, 68, 55), 2, true));
        txtNameIn.setPreferredSize(new java.awt.Dimension(35, 35));
        txtNameIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameInActionPerformed(evt);
            }
        });
        pCheckIn.add(txtNameIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 250, -1));

        jLabel8.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(23, 68, 55));
        jLabel8.setText("Address: (Optional)");
        pCheckIn.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 150, -1, -1));

        txtAddressIn.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        txtAddressIn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(23, 68, 55), 2, true));
        txtAddressIn.setPreferredSize(new java.awt.Dimension(35, 35));
        pCheckIn.add(txtAddressIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 180, 250, -1));

        jLabel9.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(23, 68, 55));
        jLabel9.setText("Phone Number: (Optional)");
        pCheckIn.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 150, -1, -1));

        txtPhoneIn.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        txtPhoneIn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(23, 68, 55), 2, true));
        txtPhoneIn.setPreferredSize(new java.awt.Dimension(35, 35));
        pCheckIn.add(txtPhoneIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 180, 250, -1));

        jLabel10.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(23, 68, 55));
        jLabel10.setText("Check-in Date:");
        pCheckIn.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 225, -1, -1));

        txtInDateIn.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        txtInDateIn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(23, 68, 55), 2, true));
        txtInDateIn.setPreferredSize(new java.awt.Dimension(35, 35));
        pCheckIn.add(txtInDateIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 250, 250, -1));

        jLabel11.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(23, 68, 55));
        jLabel11.setText("Bed:");
        pCheckIn.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 225, -1, -1));

        comboBedIn.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        comboBedIn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "    ", "Single", "Double ", "Triple" }));
        comboBedIn.setBorder(null);
        comboBedIn.setPreferredSize(new java.awt.Dimension(35, 35));
        comboBedIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBedInActionPerformed(evt);
            }
        });
        pCheckIn.add(comboBedIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 250, 250, -1));

        jLabel12.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(23, 68, 55));
        jLabel12.setText("Room Type:");
        pCheckIn.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(638, 225, -1, -1));

        comboRoomtypeIn.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        comboRoomtypeIn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "AC", "Non-AC" }));
        comboRoomtypeIn.setBorder(null);
        comboRoomtypeIn.setPreferredSize(new java.awt.Dimension(35, 35));
        comboRoomtypeIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboRoomtypeInActionPerformed(evt);
            }
        });
        pCheckIn.add(comboRoomtypeIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 250, 250, -1));

        jLabel13.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(23, 68, 55));
        jLabel13.setText("Room Number:");
        pCheckIn.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 225, -1, -1));

        comboRoomNoIn.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        comboRoomNoIn.setBorder(null);
        comboRoomNoIn.setPreferredSize(new java.awt.Dimension(35, 35));
        comboRoomNoIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboRoomNoInMouseClicked(evt);
            }
        });
        comboRoomNoIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboRoomNoInActionPerformed(evt);
            }
        });
        pCheckIn.add(comboRoomNoIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 250, 250, -1));

        jLabel14.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(23, 68, 55));
        jLabel14.setText("Price:");
        pCheckIn.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 225, -1, -1));

        txtPriceCheckIn.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        txtPriceCheckIn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(23, 68, 55), 2, true));
        txtPriceCheckIn.setPreferredSize(new java.awt.Dimension(35, 35));
        pCheckIn.add(txtPriceCheckIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 250, 250, -1));

        btnAllot.setBackground(new java.awt.Color(165, 89, 40));
        btnAllot.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 18)); // NOI18N
        btnAllot.setForeground(new java.awt.Color(255, 255, 255));
        btnAllot.setText("Allote Room");
        btnAllot.setBorder(null);
        btnAllot.setBorderPainted(false);
        btnAllot.setPreferredSize(new java.awt.Dimension(50, 50));
        btnAllot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllotActionPerformed(evt);
            }
        });
        pCheckIn.add(btnAllot, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 310, 250, -1));

        jButton2.setBackground(new java.awt.Color(165, 89, 40));
        jButton2.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Clear");
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        pCheckIn.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1340, 310, 130, -1));

        spCheckIn.setBackground(new java.awt.Color(239, 233, 221));
        spCheckIn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(165, 89, 40), 3));

        checkInTbl.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        checkInTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer ID", "Name", "Address", "Phone Number", "Check-In Date", "Bed Type", "Room Type", "Room Number", "Price"
            }
        ));
        checkInTbl.setFocusable(false);
        spCheckIn.setViewportView(checkInTbl);

        pCheckIn.add(spCheckIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 450, 1410, 550));

        jLabel39.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(23, 68, 55));
        jLabel39.setText("Email: (Optional)");
        pCheckIn.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 150, -1, -1));

        txtEmailIn.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        txtEmailIn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(23, 68, 55), 2, true));
        txtEmailIn.setPreferredSize(new java.awt.Dimension(35, 35));
        txtEmailIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailInActionPerformed(evt);
            }
        });
        pCheckIn.add(txtEmailIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 180, 250, -1));

        txtSearchIn.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        txtSearchIn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(23, 68, 55), 2, true));
        txtSearchIn.setPreferredSize(new java.awt.Dimension(35, 35));
        txtSearchIn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchInKeyReleased(evt);
            }
        });
        pCheckIn.add(txtSearchIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 400, 350, -1));

        jLabel42.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 36)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(23, 68, 55));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("Check In");
        pCheckIn.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(-1, 70, 1520, -1));

        txtNameIn1.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        txtNameIn1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(23, 68, 55), 2, true));
        txtNameIn1.setPreferredSize(new java.awt.Dimension(35, 35));
        txtNameIn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameIn1ActionPerformed(evt);
            }
        });
        pCheckIn.add(txtNameIn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 180, 250, -1));

        jLabel15.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(23, 68, 55));
        jLabel15.setText("Last Name:");
        pCheckIn.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, -1, -1));

        cbbCheckinFilter.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        cbbCheckinFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Without Account", "With Account" }));
        cbbCheckinFilter.setBorder(null);
        cbbCheckinFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbCheckinFilterActionPerformed(evt);
            }
        });
        pCheckIn.add(cbbCheckinFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(1218, 400, 250, 35));

        checkInWithCounter.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        checkInWithCounter.setForeground(new java.awt.Color(24, 71, 58));
        checkInWithCounter.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        checkInWithCounter.setText("13 Accounts");
        pCheckIn.add(checkInWithCounter, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 1000, 280, 40));

        getContentPane().add(pCheckIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 1520, 1080));

        pMenu.setBackground(new java.awt.Color(25, 71, 58));
        pMenu.setForeground(new java.awt.Color(239, 233, 221));
        pMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotelreservationmanagementsystem/MenuLogo.jpg"))); // NOI18N
        pMenu.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 22, 300, -1));

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

        pMenu.add(pcHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 360, 70));

        pcCheckin.setBackground(new java.awt.Color(24, 71, 58));

        btnCheckin.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 36)); // NOI18N
        btnCheckin.setForeground(new java.awt.Color(255, 255, 255));
        btnCheckin.setText("Check In");
        btnCheckin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCheckinMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pcCheckinLayout = new javax.swing.GroupLayout(pcCheckin);
        pcCheckin.setLayout(pcCheckinLayout);
        pcCheckinLayout.setHorizontalGroup(
            pcCheckinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pcCheckinLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(btnCheckin)
                .addContainerGap(161, Short.MAX_VALUE))
        );
        pcCheckinLayout.setVerticalGroup(
            pcCheckinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pcCheckinLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnCheckin, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pMenu.add(pcCheckin, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 360, -1));

        pcCheckout.setBackground(new java.awt.Color(24, 71, 58));

        btnCheckout.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 36)); // NOI18N
        btnCheckout.setForeground(new java.awt.Color(255, 255, 255));
        btnCheckout.setText("Check Out");
        btnCheckout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCheckoutMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pcCheckoutLayout = new javax.swing.GroupLayout(pcCheckout);
        pcCheckout.setLayout(pcCheckoutLayout);
        pcCheckoutLayout.setHorizontalGroup(
            pcCheckoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pcCheckoutLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(btnCheckout)
                .addContainerGap(131, Short.MAX_VALUE))
        );
        pcCheckoutLayout.setVerticalGroup(
            pcCheckoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pcCheckoutLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnCheckout, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pMenu.add(pcCheckout, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 520, 360, -1));

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

        pMenu.add(pcReservation, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 590, 360, -1));

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

        pcAccount.setBackground(new java.awt.Color(24, 71, 58));

        btnAccount.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 36)); // NOI18N
        btnAccount.setForeground(new java.awt.Color(255, 255, 255));
        btnAccount.setText("Accounts");
        btnAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAccountMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pcAccountLayout = new javax.swing.GroupLayout(pcAccount);
        pcAccount.setLayout(pcAccountLayout);
        pcAccountLayout.setHorizontalGroup(
            pcAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pcAccountLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(btnAccount)
                .addContainerGap(152, Short.MAX_VALUE))
        );
        pcAccountLayout.setVerticalGroup(
            pcAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pcAccountLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pMenu.add(pcAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 800, 360, -1));

        pcRequest.setBackground(new java.awt.Color(24, 71, 58));

        btnReques.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 36)); // NOI18N
        btnReques.setForeground(new java.awt.Color(255, 255, 255));
        btnReques.setText("Request");
        btnReques.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRequesMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pcRequestLayout = new javax.swing.GroupLayout(pcRequest);
        pcRequest.setLayout(pcRequestLayout);
        pcRequestLayout.setHorizontalGroup(
            pcRequestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pcRequestLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(btnReques)
                .addContainerGap(169, Short.MAX_VALUE))
        );
        pcRequestLayout.setVerticalGroup(
            pcRequestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pcRequestLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnReques, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pMenu.add(pcRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 660, 360, 70));

        pcRoom.setBackground(new java.awt.Color(24, 71, 58));

        btnRoom.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 36)); // NOI18N
        btnRoom.setForeground(new java.awt.Color(255, 255, 255));
        btnRoom.setText("Rooms");
        btnRoom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRoomMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pcRoomLayout = new javax.swing.GroupLayout(pcRoom);
        pcRoom.setLayout(pcRoomLayout);
        pcRoomLayout.setHorizontalGroup(
            pcRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pcRoomLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(btnRoom)
                .addContainerGap(195, Short.MAX_VALUE))
        );
        pcRoomLayout.setVerticalGroup(
            pcRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pcRoomLayout.createSequentialGroup()
                .addComponent(btnRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pMenu.add(pcRoom, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 730, 360, -1));

        getContentPane().add(pMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 1080));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void manageTblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageTblMouseClicked
        DefaultTableModel tblModel = (DefaultTableModel) manageTbl.getModel();
        String tblRoomNo = tblModel.getValueAt(manageTbl.getSelectedRow(), 0).toString();
        String tblRoomType = tblModel.getValueAt(manageTbl.getSelectedRow(), 1).toString();
        String tblBed = tblModel.getValueAt(manageTbl.getSelectedRow(), 2).toString();
        String tblPrice = tblModel.getValueAt(manageTbl.getSelectedRow(), 3).toString();

        txtRoom.setText(tblRoomNo);
        comboRoom.setSelectedItem(tblRoomType);
        comboBed.setSelectedItem(tblBed);
        txtPrice.setText(tblPrice);

    }//GEN-LAST:event_manageTblMouseClicked

    private void btnAddroomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddroomActionPerformed

        try {
            //add room
            String roomNo = txtRoom.getText();
            String roomType = (String) comboRoom.getSelectedItem();
            String bed = (String) comboBed.getSelectedItem();
            int price = Integer.parseInt(txtPrice.getText());

            pst = con.prepareStatement("INSERT INTO room (roomNo, roomType, bed, price, status) VALUES (?, ?, ?, ?, 'Available')");
            pst.setString(1, roomNo);
            pst.setString(2, roomType);
            pst.setString(3, bed);
            pst.setInt(4, price);

            int k = pst.executeUpdate();

            if (k == 1) {
                txtRoom.setText("");
                txtPrice.setText("");
                JOptionPane.showMessageDialog(this, "Room added.");
                updateManage();
            } else {
                JOptionPane.showMessageDialog(this, "Failed!");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
    }//GEN-LAST:event_btnAddroomActionPerformed

    private void btnDeleteRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteRoomActionPerformed
        try {
            // delete room
            DefaultTableModel tblModel = (DefaultTableModel) manageTbl.getModel();
            String tblRoomNo = tblModel.getValueAt(manageTbl.getSelectedRow(), 0).toString();

            pst = con.prepareStatement("DELETE FROM room WHERE roomNo = ? and status = 'Available'");
            pst.setString(1, tblRoomNo);
            int k = pst.executeUpdate();

            if (k == 1) {
                JOptionPane.showMessageDialog(this, "Room Deleted Successfully!");
                updateManage();
            } else {
                JOptionPane.showMessageDialog(this, "Please Select a Room to Delete!");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
    }//GEN-LAST:event_btnDeleteRoomActionPerformed

    private void comboBedInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBedInActionPerformed
        roomDetailsCheckIn();
    }//GEN-LAST:event_comboBedInActionPerformed

    private void comboRoomtypeInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboRoomtypeInActionPerformed
        roomDetailsCheckIn();
    }//GEN-LAST:event_comboRoomtypeInActionPerformed

    private void comboRoomNoInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboRoomNoInActionPerformed

    }//GEN-LAST:event_comboRoomNoInActionPerformed
    //method for check in
    void checkIn() {
        try {
            String name = txtNameIn.getText();
            String name1 = txtNameIn1.getText();
            String email = txtEmailIn.getText();
            String address = txtAddressIn.getText();
            String phone = txtPhoneIn.getText();
            String checkin = txtInDateIn.getText();

            String bed = (String) comboBedIn.getSelectedItem();
            String roomType = (String) comboRoomtypeIn.getSelectedItem();
            String roomNo = (String) comboRoomNoIn.getSelectedItem();
            String price = txtPriceCheckIn.getText();
            String notice = null;
            String visitorId = null;
            String guestId = null;

            //update room status
            if (!price.equals("")) {
                try {
                    pst = con.prepareStatement("UPDATE room SET status = 'Occupied' WHERE roomNo = ?");
                    pst.setString(1, roomNo);
                    int k = pst.executeUpdate();

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
                    System.out.println(e);
                }
            }

            //checks if visitor has an account, if true the person id will reference the guest id, else visitor information will be stored in visitorinformation and have its id as the person id
            pst = con.prepareStatement("Select * from guestInformation where email=?");
            pst.setString(1, email);
            rs = pst.executeQuery();
            if (rs.next()) {
                guestId = rs.getString("guest_id");

                pst = con.prepareStatement("INSERT INTO transaction (person_id, checkIn, roomNo) VALUES (?, ?, ?)");
                pst.setString(1, guestId);

                pst.setString(2, checkin);
                pst.setString(3, roomNo);

                int j = pst.executeUpdate();

                if (j == 1) {
                    updateReservationWithout();
                    JOptionPane.showMessageDialog(this, "Added!");
                    updateCheckInWith();
                    cbbCheckinFilter.getModel().setSelectedItem("With Account");
                    Calendar cal = Calendar.getInstance();
                    txtCheckInRes.setDate((cal.getTime()));

                    txtNameIn1.setText("");
                    txtNameIn.setText("");
                    txtAddressIn.setText("");
                    txtEmailIn.setText("");
                    txtPhoneIn.setText("");
                    comboBedIn.setSelectedIndex(0);
                    comboRoomtypeIn.setSelectedIndex(0);
                    comboRoomNoIn.setSelectedIndex(0);

                    txtPriceCheckIn.setText("");

                } else {
                    JOptionPane.showMessageDialog(this, "Adding Failed!");
                }
            } else {
                //auto incrementing guest_id (odd numbers)
                int visitorCount = 0;
                pst = con.prepareStatement("Select * from visitorinformation  ORDER BY visitor_id DESC LIMIT 1");
                rs = pst.executeQuery();
                if (rs.next()) {
                    visitorCount = rs.getInt(("visitor_id"));
                } else {
                    visitorCount = 1;
                }
                visitorCount += 2;
                pst = con.prepareStatement("INSERT INTO visitorinformation (visitor_id, firstName, lastName, email,address, phone) VALUES (?, ?, ?,?, ?,?) ");
                pst.setInt(1, visitorCount);
                pst.setString(2, name);
                pst.setString(3, name1);
                pst.setString(4, email);
                pst.setString(5, address);
                pst.setString(6, phone);

                int j = pst.executeUpdate();
                if (j == 1) {
                    pst = con.prepareStatement("SELECT * FROM visitorinformation ORDER BY visitor_id DESC LIMIT 1");
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        visitorId = rs.getString("visitor_id");
                    }
                    if (visitorId.equals(null)) {
                        System.out.println("no user");
                    } else {

                        pst = con.prepareStatement("INSERT INTO transaction (person_id, checkIn, roomNo) VALUES (?, ?, ?)");
                        pst.setString(1, visitorId);

                        pst.setString(2, checkin);
                        pst.setString(3, roomNo);

                        int k = pst.executeUpdate();

                        if (k == 1) {
                            updateReservationWithout();
                            JOptionPane.showMessageDialog(this, "Added!");

                            updateCheckInWithout();
                            cbbCheckinFilter.getModel().setSelectedItem("Without Account");
                            Calendar cal = Calendar.getInstance();
                            txtCheckInRes.setDate((cal.getTime()));
                            txtNameIn1.setText("");
                            txtNameIn.setText("");
                            txtAddressIn.setText("");
                            txtEmailIn.setText("");
                            txtPhoneIn.setText("");
                            comboBedIn.setSelectedIndex(0);
                            comboRoomtypeIn.setSelectedIndex(0);
                            comboRoomNoIn.setSelectedIndex(0);

                        } else {
                            JOptionPane.showMessageDialog(this, "Adding Failed!");
                        }
                    }
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
    }
    private void btnAllotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllotActionPerformed
        //check-in button
        String name = txtNameIn.getText();
        String lastName = txtNameIn1.getText();
        String email = txtEmailIn.getText();
        String checkin = txtInDateIn.getText();

        String bed = (String) comboBedIn.getSelectedItem();
        String roomType = (String) comboRoomtypeIn.getSelectedItem();
        String roomNo = (String) comboRoomNoIn.getSelectedItem();
        String price = txtPriceCheckIn.getText();
        if (name.equals("") || lastName.equals("") || price.equals("") || bed == null || checkin == null || roomType == null || roomNo == null) {

            JOptionPane.showMessageDialog(this, "Please fill up the required fields!.", "Failed!", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                String selected = (String) comboRoomNoIn.getSelectedItem();
                pst = con.prepareStatement("SELECT  g.email, a.roomNo, a.status FROM guestInformation g INNER JOIN reservation a ON(g.guest_id = a.person_id) where  status = 'Pending' and roomNo =?");

                pst.setString(1, selected);

                rs = pst.executeQuery();

                if (rs.next()) {
                    if (JOptionPane.showConfirmDialog(this, "This room is already requested. Do you still want to continue?", "",
                            JOptionPane.YES_NO_OPTION) == 0) {
                        checkIn();

                    }
                } else {

                    checkIn();

                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_btnAllotActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        clearFields();

        comboBedIn.setSelectedIndex(0);
        comboRoomtypeIn.setSelectedIndex(0);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtNameInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameInActionPerformed

    }//GEN-LAST:event_txtNameInActionPerformed

    private void comboBedResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBedResActionPerformed
        roomDetailsReservation();
    }//GEN-LAST:event_comboBedResActionPerformed

    private void comboRoomTypeResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboRoomTypeResActionPerformed
        roomDetailsReservation();
    }//GEN-LAST:event_comboRoomTypeResActionPerformed

    private void comboRoomNoResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboRoomNoResActionPerformed

    }//GEN-LAST:event_comboRoomNoResActionPerformed

    private void reservationTblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reservationTblMouseClicked

    }//GEN-LAST:event_reservationTblMouseClicked
//request method

    void Request() {
        try {
            String name = txtNameRes.getText();
            String name1 = txtNameRes1.getText();
            String email = txtEmail.getText();
            String address = txtAddressRes.getText();
            String phone = txtPhoneRes.getText();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String checkIn = sdf.format(txtCheckInRes.getDate());
            String bed = (String) comboBedRes.getSelectedItem();
            String roomType = (String) comboRoomTypeRes.getSelectedItem();
            String roomNo = (String) comboRoomNoRes.getSelectedItem();
            String price = txtPriceRes.getText();
            String guestId = null;
            if (!price.equals("")) {
                //update room status
                try {
                    pst = con.prepareStatement("UPDATE room SET status = 'Reserved' WHERE roomNo = ?");
                    pst.setString(1, roomNo);
                    int k = pst.executeUpdate();

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
                    System.out.println(e);
                }
            }

            //checks if visitor has an account, if true the person id will reference the guest id, else visitor information will be stored in visitorinformation and have its id as the person id
            pst = con.prepareStatement("Select * from guestInformation where email=?");
            pst.setString(1, email);
            rs = pst.executeQuery();
            if (rs.next()) {
                guestId = rs.getString("guest_id");
                pst = con.prepareStatement("INSERT INTO reservation (person_id, roomNo, checkIn, status) VALUES (?, ?, ?, 'Reserved')");
                pst.setString(1, guestId);
                pst.setString(2, roomNo);
                pst.setString(3, checkIn);
                int j = pst.executeUpdate();
                if (j == 1) {
                    updateReservationWithout();
                    JOptionPane.showMessageDialog(this, "Added!");
//                    Calendar cal = Calendar.getInstance();
//                    txtCheckInRes.setDate((cal.getTime()));
                    updateReservationWith();
                    cbbReservationFilter.getModel().setSelectedItem("With Account");
                    txtNameRes.setText("");
                    txtAddressRes.setText("");
                    txtEmail.setText("");
                    txtPhoneRes.setText("");
                    comboBedRes.setSelectedIndex(0);
                    comboRoomTypeRes.setSelectedIndex(0);
                    comboRoomNoRes.setSelectedIndex(0);
                    txtCheckInRes.setCalendar(null);
                    txtPriceRes.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Adding Failed!");
                }
            } else {
                //auto incrementing guest_id (odd numbers)
                int visitorCount = 0;
                pst = con.prepareStatement("Select * from visitorinformation  ORDER BY visitor_id DESC LIMIT 1");
                rs = pst.executeQuery();
                if (rs.next()) {
                    visitorCount = rs.getInt(("visitor_id"));
                } else {
                    visitorCount = 1;
                }
                visitorCount += 2;
                pst = con.prepareStatement("INSERT INTO visitorinformation (visitor_id, firstName, lastName, email,address, phone) VALUES (?, ?, ?,?, ?,?) ");
                pst.setInt(1, visitorCount);
                pst.setString(2, name);
                pst.setString(3, name1);
                pst.setString(4, email);
                pst.setString(5, address);
                pst.setString(6, phone);
                int j = pst.executeUpdate();
                if (j == 1) {
                    pst = con.prepareStatement("SELECT * FROM visitorinformation ORDER BY visitor_id DESC LIMIT 1");
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        guestId = rs.getString("visitor_id");
                    }
                    if (guestId.equals(null)) {
                        System.out.println("no user");
                    } else {
                        pst = con.prepareStatement("INSERT INTO reservation (person_id, roomNo, checkIn, status) VALUES (?, ?, ?, 'Reserved')");
                        pst.setString(1, guestId);
                        pst.setString(3, checkIn);
                        pst.setString(2, roomNo);
                        int k = pst.executeUpdate();
                        if (k == 1) {
                            JOptionPane.showMessageDialog(this, "Added!");
                            updateReservationWithout();
                            cbbReservationFilter.getModel().setSelectedItem("Without Account");
                            txtNameRes.setText("");
                            txtAddressRes.setText("");
                            txtEmail.setText("");
                            txtPhoneRes.setText("");
                            comboBedRes.setSelectedIndex(0);
                            comboRoomTypeRes.setSelectedIndex(0);
                            comboRoomNoRes.setSelectedIndex(0);
                            txtPriceRes.setText("");
                            txtNameRes1.setText("");
                            Calendar cal = Calendar.getInstance();
//                          txtCheckInRes.setDate((cal.getTime()));
//                          txtCheckInRes.setCalendar(null);
                            txtCheckInRes.setCalendar(null);
                        } else {
                            JOptionPane.showMessageDialog(this, "Adding Failed!");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
    }
    private void btnConfirmResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmResActionPerformed

        String name = txtNameRes.getText();
        String email = txtEmail.getText();
        String address = txtAddressRes.getText();
        String phone = txtPhoneRes.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String checkIn = sdf.format(txtCheckInRes.getDate());
        String bed = (String) comboBedRes.getSelectedItem();
        String roomType = (String) comboRoomTypeRes.getSelectedItem();
        String roomNo = (String) comboRoomNoRes.getSelectedItem();
        String price = txtPriceRes.getText();
        String guestId = null;

        //check if fields are empty
        if (name.equals("") || bed == null || checkIn == null || roomType == null || roomNo == null) {
            JOptionPane.showMessageDialog(this, "Please fill up the required fields!.", "Failed!", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                String selected = (String) comboRoomNoRes.getSelectedItem();
                //checks if room is already requested
                pst = con.prepareStatement("SELECT  g.email, a.roomNo, a.status FROM guestInformation g INNER JOIN reservation a ON(g.guest_id = a.person_id) where  status = 'Pending' and roomNo =?");
                pst.setString(1, selected);
                rs = pst.executeQuery();
                if (rs.next()) {
                    if (JOptionPane.showConfirmDialog(this, "This room is already requested. Do you still want to continue?", "",
                            JOptionPane.YES_NO_OPTION) == 0) {
                        Request();
                    }
                } else {
                    Request();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_btnConfirmResActionPerformed

    private void cbAccountStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAccountStatusActionPerformed
        filter();
    }//GEN-LAST:event_cbAccountStatusActionPerformed

    private void tfSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSearchKeyReleased
        // used to filter the records shown on the table using textfield
        String text = tfSearch.getText();
        if (!text.equals("")) {
            try {
                pst = con.prepareStatement("SELECT CONCAT(firstName,' ',lastName) As Name ,username As Username,email As Email FROM (SELECT  firstName,lastName  ,username,email FROM guestInformation UNION SELECT  firstName,lastName  ,username,email FROM employeeInformation) unioned"
                        + " where username != 'administrator' and CONCAT(firstName,' ',lastName) like '%" + tfSearch.getText() + "%'");
                rs = pst.executeQuery();
                tblAdmin.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (SQLException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            updateAdminTable();
        }
    }//GEN-LAST:event_tfSearchKeyReleased

    private void btnBlockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBlockActionPerformed
        permission("Are you sure you want to block the account/s shown in the table?", 3);
    }//GEN-LAST:event_btnBlockActionPerformed

    private void btnUnblockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnblockActionPerformed
        permission("Are you sure you want to unblock the account/s shown in the table?", 0);
    }//GEN-LAST:event_btnUnblockActionPerformed

    private void cdTableFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cdTableFilterActionPerformed
        filter();
    }//GEN-LAST:event_cdTableFilterActionPerformed

    private void tblAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAdminMouseClicked

        int attempt = 0;
        int row = tblAdmin.getSelectedRow();
        String hasInGuest = null;
        String hasInEmployee = null;
        String selectedRow = tblAdmin.getModel().getValueAt(row, 1).toString();
        System.out.println(selectedRow);
        try {
            //check if the user double-clicked the table
            if (evt.getClickCount() == 2 && evt.getButton() == MouseEvent.BUTTON1) {
                pst = con.prepareStatement("Select * from guestInformation where username=?");
                pst.setString(1, selectedRow);
                rs = pst.executeQuery();
                while (rs.next()) {
                    hasInGuest = rs.getString("username");
                    attempt = rs.getInt("attempt");
                }
                if (hasInGuest != null) {
                    tableName = "guestInformation";
                    adminTableClick(selectedRow, attempt);
                } else {
                    pst = con.prepareStatement("Select * from employeeInformation where username=?");
                    pst.setString(1, selectedRow);
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        hasInEmployee = rs.getString("username");
                        attempt = rs.getInt("attempt");
                    }
                    if (hasInEmployee != null) {
                        tableName = "employeeInformation";

                        adminTableClick(selectedRow, attempt);
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
    }//GEN-LAST:event_tblAdminMouseClicked

    private void tblAdminPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tblAdminPropertyChange
        //show count of items in admin table 
        if (tblAdmin.getRowCount() > 1) {
            lblFilter.setText("Result: " + tblAdmin.getRowCount() + " Accounts");
        } else {
            lblFilter.setText("Result: " + tblAdmin.getRowCount() + " Account");
        }
    }//GEN-LAST:event_tblAdminPropertyChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new Account("fromAdmin").setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String hasInGuest = null;
        String hasInEmployee = null;
        int row = tblAdmin.getSelectedRow();
        int dialogResult;
        String selectedRow = tblAdmin.getModel().getValueAt(row, 1).toString();
        System.out.println(selectedRow);
        //deletes the selected item in the table
        try {
            System.out.println(selectedRow);
            pst = con.prepareStatement("Select * from guestInformation where username=?");
            pst.setString(1, selectedRow);
            rs = pst.executeQuery();
            if (rs.next()) {
                hasInGuest = rs.getString("username");

                dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to delete " + hasInGuest + " from Guest table", "Warning", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    if (hasInGuest != null) {
                        pst = con.prepareStatement("Delete from guestInformation where username=?");
                        pst.setString(1, selectedRow);
                    }
                }
                pst.execute();
            } else {
                pst = con.prepareStatement("Select * from employeeInformation where username=?");
                pst.setString(1, selectedRow);
                rs = pst.executeQuery();
                if (rs.next()) {
                    hasInEmployee = rs.getString("username");
                }
                dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to delete " + hasInEmployee + " from Employee table", "Warning", JOptionPane.YES_NO_OPTION);
                if (hasInEmployee != null) {
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        pst = con.prepareStatement("Delete from employeeInformation where username=?");
                        pst.setString(1, selectedRow);
                        pst.execute();
                    }
                }
            }
            updateAdminTable();
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnCheckinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCheckinMouseClicked
        //changes color of the selected button in the menu
        pcHome.setBackground(new Color(24, 71, 58));
        pcCheckin.setBackground(new Color(239, 233, 221));
        pcCheckout.setBackground(new Color(24, 71, 58));
        pcRoom.setBackground(new Color(24, 71, 58));
        pcReservation.setBackground(new Color(24, 71, 58));
        pcAccount.setBackground(new Color(24, 71, 58));
        pcRequest.setBackground(new Color(24, 71, 58));

        btnReques.setForeground(new Color(255, 255, 255));
        btnHome.setForeground(new Color(255, 255, 255));
        btnCheckin.setForeground(new Color(24, 71, 58));
        btnCheckout.setForeground(new Color(255, 255, 255));
        btnRoom.setForeground(new Color(255, 255, 255));
        btnReservation.setForeground(new Color(255, 255, 255));
        btnAccount.setForeground(new Color(255, 255, 255));

        pHome.setVisible(false);
        pAdmin.setVisible(false);
        pManage.setVisible(false);
        pCheckIn.setVisible(true);
        pCheckOut.setVisible(false);
        pRequest.setVisible(false);
        pReservation.setVisible(false);
        updateCheckInWithout();
        clearFields();
        cbbCheckinFilter.getModel().setSelectedItem("Without Account");
//        comboBedIn.setSelectedIndex(0);
// comboRoomtypeIn.setSelectedIndex(0);
// comboRoomNoIn.setSelectedIndex(0);
//                cbbCheckinFilter.setSelectedIndex(0);

    }//GEN-LAST:event_btnCheckinMouseClicked

    private void btnCheckoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCheckoutMouseClicked
        clearFields();
        pcHome.setBackground(new Color(24, 71, 58));
        pcCheckin.setBackground(new Color(24, 71, 58));
        pcCheckout.setBackground(new Color(239, 233, 221));
        pcRoom.setBackground(new Color(24, 71, 58));
        pcReservation.setBackground(new Color(24, 71, 58));
        pcAccount.setBackground(new Color(24, 71, 58));
        pcRequest.setBackground(new Color(24, 71, 58));

        btnReques.setForeground(new Color(255, 255, 255));
        btnHome.setForeground(new Color(255, 255, 255));
        btnCheckin.setForeground(new Color(255, 255, 255));
        btnCheckout.setForeground(new Color(24, 71, 58));
        btnRoom.setForeground(new Color(255, 255, 255));
        btnReservation.setForeground(new Color(255, 255, 255));
        btnAccount.setForeground(new Color(255, 255, 255));

        pHome.setVisible(false);
        pAdmin.setVisible(false);
        pManage.setVisible(false);
        pCheckIn.setVisible(false);
        pCheckOut.setVisible(true);
        pCheckOutConfirm.setVisible(true);
        pRequest.setVisible(false);
        pReservation.setVisible(false);
        updateCheckOutWithout();
        cbbCheckoutFilter.setSelectedIndex(0);
        cbbCheckoutFilter.getModel().setSelectedItem("Without Account");
    }//GEN-LAST:event_btnCheckoutMouseClicked

    private void btnRequesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRequesMouseClicked
        clearFields();
        pcHome.setBackground(new Color(24, 71, 58));
        pcCheckin.setBackground(new Color(24, 71, 58));
        pcCheckout.setBackground(new Color(24, 71, 58));
        pcRoom.setBackground(new Color(24, 71, 58));
        pcReservation.setBackground(new Color(24, 71, 58));
        pcAccount.setBackground(new Color(24, 71, 58));
        pcRequest.setBackground(new Color(239, 233, 221));

        btnReques.setForeground(new Color(24, 71, 58));
        btnHome.setForeground(new Color(255, 255, 255));
        btnCheckin.setForeground(new Color(255, 255, 255));
        btnCheckout.setForeground(new Color(255, 255, 255));
        btnRoom.setForeground(new Color(255, 255, 255));
        btnReservation.setForeground(new Color(255, 255, 255));
        btnAccount.setForeground(new Color(255, 255, 255));

        pHome.setVisible(false);
        pAdmin.setVisible(false);
        pManage.setVisible(false);
        pCheckIn.setVisible(false);
        pCheckOut.setVisible(false);
        pRequest.setVisible(true);
        pReservation.setVisible(false);
        updateTables();

    }//GEN-LAST:event_btnRequesMouseClicked

    private void btnRoomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRoomMouseClicked
        clearFields();
        pcHome.setBackground(new Color(24, 71, 58));
        pcCheckin.setBackground(new Color(24, 71, 58));
        pcCheckout.setBackground(new Color(24, 71, 58));
        pcRoom.setBackground(new Color(239, 233, 221));
        pcReservation.setBackground(new Color(24, 71, 58));
        pcAccount.setBackground(new Color(24, 71, 58));
        pcRequest.setBackground(new Color(24, 71, 58));

        btnReques.setForeground(new Color(255, 255, 255));
        btnHome.setForeground(new Color(255, 255, 255));
        btnCheckin.setForeground(new Color(255, 255, 255));
        btnCheckout.setForeground(new Color(255, 255, 255));
        btnRoom.setForeground(new Color(24, 71, 58));
        btnReservation.setForeground(new Color(255, 255, 255));
        btnAccount.setForeground(new Color(255, 255, 255));

        pHome.setVisible(false);
        pAdmin.setVisible(false);
        pManage.setVisible(true);
        pCheckIn.setVisible(false);
        pCheckOut.setVisible(false);
        pRequest.setVisible(false);
        pReservation.setVisible(false);
        updateTables();
//        comboBed.setSelectedIndex(0);
//        comboRoom.setSelectedIndex(0);
    }//GEN-LAST:event_btnRoomMouseClicked

    private void btnReservationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReservationMouseClicked

        clearFields();
        pcHome.setBackground(new Color(24, 71, 58));
        pcCheckin.setBackground(new Color(24, 71, 58));
        pcCheckout.setBackground(new Color(24, 71, 58));
        pcRoom.setBackground(new Color(24, 71, 58));
        pcReservation.setBackground(new Color(239, 233, 221));
        pcAccount.setBackground(new Color(24, 71, 58));
        pcRequest.setBackground(new Color(24, 71, 58));

        btnReques.setForeground(new Color(255, 255, 255));
        btnHome.setForeground(new Color(255, 255, 255));
        btnCheckin.setForeground(new Color(255, 255, 255));
        btnCheckout.setForeground(new Color(255, 255, 255));
        btnRoom.setForeground(new Color(255, 255, 255));
        btnReservation.setForeground(new Color(24, 71, 58));
        btnAccount.setForeground(new Color(255, 255, 255));

        pHome.setVisible(false);
        pAdmin.setVisible(false);
        pManage.setVisible(false);
        pCheckIn.setVisible(false);
        pCheckOut.setVisible(false);
        pRequest.setVisible(false);
        pReservation.setVisible(true);
        updateReservationWithout();
//        comboBedRes.setSelectedIndex(0);
//comboRoomTypeRes.setSelectedIndex(0);
// comboRoomNoRes.setSelectedIndex(0);
        cbbReservationFilter.setSelectedIndex(0);
        cbbReservationFilter.getModel().setSelectedItem("Without Account");
    }//GEN-LAST:event_btnReservationMouseClicked

    private void btnHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseClicked
        clearFields();
        pcRequest.setBackground(new Color(24, 71, 58));
        pcHome.setBackground(new Color(239, 233, 221));
        pcCheckin.setBackground(new Color(24, 71, 58));
        pcCheckout.setBackground(new Color(24, 71, 58));
        pcRoom.setBackground(new Color(24, 71, 58));
        pcReservation.setBackground(new Color(24, 71, 58));
        pcAccount.setBackground(new Color(24, 71, 58));

        btnReques.setForeground(new Color(255, 255, 255));
        btnHome.setForeground(new Color(24, 71, 58));
        btnCheckin.setForeground(new Color(255, 255, 255));
        btnCheckout.setForeground(new Color(255, 255, 255));
        btnRoom.setForeground(new Color(255, 255, 255));
        btnReservation.setForeground(new Color(255, 255, 255));
        btnAccount.setForeground(new Color(255, 255, 255));

        pHome.setVisible(true);
        pAdmin.setVisible(false);
        pManage.setVisible(false);
        pCheckIn.setVisible(false);
        pCheckOut.setVisible(false);
        pRequest.setVisible(false);
        pReservation.setVisible(false);
        updateTables();
    }//GEN-LAST:event_btnHomeMouseClicked

    private void btnLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseClicked
        btnLogout.setBackground(new Color(24, 71, 58));
        btnLogout.setForeground(new Color(255, 255, 255));
        //conficmation if user want logout
        if (JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "",
                JOptionPane.YES_NO_OPTION) == 0) {
            setVisible(false);
            new Account().setVisible(true);
        }
    }//GEN-LAST:event_btnLogoutMouseClicked

    private void btnAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAccountMouseClicked
        clearFields();
        pcRequest.setBackground(new Color(24, 71, 58));
        pcHome.setBackground(new Color(24, 71, 58));
        pcCheckin.setBackground(new Color(24, 71, 58));
        pcCheckout.setBackground(new Color(24, 71, 58));
        pcRoom.setBackground(new Color(24, 71, 58));
        pcReservation.setBackground(new Color(24, 71, 58));
        pcAccount.setBackground(new Color(239, 233, 221));

        btnReques.setForeground(new Color(255, 255, 255));
        btnHome.setForeground(new Color(255, 255, 255));
        btnCheckin.setForeground(new Color(255, 255, 255));
        btnCheckout.setForeground(new Color(255, 255, 255));
        btnRoom.setForeground(new Color(255, 255, 255));
        btnReservation.setForeground(new Color(255, 255, 255));
        btnAccount.setForeground(new Color(24, 71, 58));

        pHome.setVisible(false);
        pAdmin.setVisible(true);
        pManage.setVisible(false);
        pCheckIn.setVisible(false);
        pCheckOut.setVisible(false);
        pRequest.setVisible(false);
        pReservation.setVisible(false);
        updateTables();
        cdTableFilter.setSelectedItem("All");
        cbAccountStatus.setSelectedItem("All");
        cdTableFilter.setSelectedIndex(0);
        cbAccountStatus.setSelectedIndex(0);
    }//GEN-LAST:event_btnAccountMouseClicked

    private void checkOutTblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkOutTblMouseClicked
        //check if checkout table is double clicked
        if (evt.getClickCount() == 2) {
            try {
                DefaultTableModel tblModel = (DefaultTableModel) checkOutTbl.getModel();
                String tblRoomNo = tblModel.getValueAt(checkOutTbl.getSelectedRow(), 5).toString();
                String tblName = tblModel.getValueAt(checkOutTbl.getSelectedRow(), 1).toString();
                pCheckOutConfirm.setVisible(true);
                //setting number format of price and total amount
                DecimalFormat formatDecimal = new DecimalFormat("#,###.00");
                DecimalFormat formatNoDecimal = new DecimalFormat("#,###");
                pst = con.prepareStatement("SELECT    a.*, g.* FROM room g INNER JOIN transaction a ON(g.roomNo = a.roomNo) WHERE a.checkout is NULL and a.roomNo =?");
                pst.setString(1, tblRoomNo);
                rs = pst.executeQuery();
                if (rs.next() == true) {
                    btnCheckOutConfirm.setVisible(true);
                    btnCancel.setVisible(true);
                    txtNameOut.setText(tblName);

                    txtTotalOut.setText(rs.getString("totalAmount"));
                    txtRoomNoOut.setText(rs.getString("roomNo"));

                    float priceFormat = Float.parseFloat(rs.getString("price"));
                    txtPriceOut.setText(String.valueOf(formatNoDecimal.format(priceFormat)));
                    SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Calendar cal = Calendar.getInstance();
                    txtCheckInOut.setText(rs.getString("checkin"));
                    txtCheckOutOut.setText(myFormat.format(cal.getTime()));

                    String dateBeforeString = rs.getString("checkin");
                    Date dateBefore = myFormat.parse(dateBeforeString);

                    String dateAfterString = myFormat.format(cal.getTime());
                    Date dateAfter = myFormat.parse(dateAfterString);
                    //calculate total days of stay
                    long difference = dateAfter.getTime() - dateBefore.getTime();

                    int noOfDayStay = (int) (difference) / (1000 * 60 * 60 * 24);
                    if (noOfDayStay == 0) {
                        noOfDayStay = 1;
                    }

                    txtNoStayOutt.setText(String.valueOf(noOfDayStay));
                    float price = Float.parseFloat(rs.getString("price"));
                    //calculate total price
                    txtTotalOut.setText(String.valueOf(formatDecimal.format(noOfDayStay * price)));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
                System.out.println(e);
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_checkOutTblMouseClicked

    private void btnCheckOutConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckOutConfirmActionPerformed

        try {
            String checkOut = txtCheckOutOut.getText();
            String noStay = txtNoStayOutt.getText();
            String roomNo = txtRoomNoOut.getText();
            double total = Double.parseDouble(txtTotalOut.getText().replace(",", ""));

            pst = con.prepareStatement("UPDATE transaction SET numberOfDaysStay = ?, totalAmount = ?, checkout = ? WHERE roomNo = ? ");
            pst.setString(1, noStay);
            pst.setDouble(2, total);
            pst.setString(3, checkOut);
            pst.setString(4, roomNo);

            int k = pst.executeUpdate();

            //updates room status
            pst = con.prepareStatement("UPDATE room SET status = 'Available' where roomNo = ?");
            pst.setString(1, roomNo);
            int j = pst.executeUpdate();

            if (j == 1) {

                txtNameOut.setText("");
                txtCheckInOut.setText("");
                txtCheckOutOut.setText("");
                txtNoStayOutt.setText("");
                txtRoomNoOut.setText("");
                txtPriceOut.setText("");
                txtTotalOut.setText("");
                btnCheckOutConfirm.setVisible(false);
                btnCancel.setVisible(false);
                JOptionPane.showMessageDialog(this, "Check-Out Successfully!");

                String selection = cbbCheckoutFilter.getSelectedItem().toString();
                if (selection.equals("Without Account")) {
                    updateCheckOutWithout();
                    cbbReservationFilter.getModel().setSelectedItem("Without Account");

                } else if (selection.equals("With Account")) {
                    updateCheckOutWith();
                    cbbReservationFilter.getModel().setSelectedItem("With Account");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Check-Out Failed!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
    }//GEN-LAST:event_btnCheckOutConfirmActionPerformed

    private void tblRequestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRequestMouseClicked

    }//GEN-LAST:event_tblRequestMouseClicked

    private void btnApproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApproveActionPerformed

        int row = tblRequest.getSelectedRow();
        String selectedRow = tblRequest.getModel().getValueAt(row, 0).toString();
        String roomNumber = tblRequest.getModel().getValueAt(row, 5).toString();
        String requestName = tblRequest.getModel().getValueAt(row, 1).toString();
        System.out.println(selectedRow);
        try {
            System.out.println("room" + roomNumber);

            //check if the user double-clicked the table
            if (JOptionPane.showConfirmDialog(this, "Approve reservation request of " + requestName + "?", "",
                    JOptionPane.YES_NO_OPTION) == 0) {

                pst = con.prepareStatement("Select * from reservation where  status = 'Reserved' and roomNo=?");
                pst.setString(1, roomNumber);
                rs = pst.executeQuery();
                if (!rs.next()) {
                    //update room status
                    pst = con.prepareStatement("Select * from room where roomNo=? and status='Available'");
                    pst.setString(1, roomNumber);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        pst = con.prepareStatement("Update reservation set status = 'Reserved' where reserve_id=?");
                        pst.setString(1, selectedRow);
                        if (pst.executeUpdate() == 1) {

                            pst = con.prepareStatement("UPDATE room SET status = 'Reserved' WHERE roomNo = ?");

                            pst.setString(1, roomNumber);
                            pst.executeUpdate();
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Room is already occupied.", "Failed!", JOptionPane.WARNING_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Room is already reserved.", "Failed!", JOptionPane.WARNING_MESSAGE);
                }
                updateTables();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }

    }//GEN-LAST:event_btnApproveActionPerformed

    private void btnDelteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelteActionPerformed
        int row = tblRequest.getSelectedRow();

        String selectedRow = tblRequest.getModel().getValueAt(row, 0).toString();
        String roomNumber = tblRequest.getModel().getValueAt(row, 5).toString();
        String requestName = tblRequest.getModel().getValueAt(row, 1).toString();
        System.out.println(selectedRow);
        try {
            System.out.println("room" + roomNumber);

            //check if the user double-clicked the table
            if (JOptionPane.showConfirmDialog(this, "Deny the reservation request of " + requestName + "?", "",
                    JOptionPane.YES_NO_OPTION) == 0) {
                pst = con.prepareStatement("Delete from reservation where reserve_id=?");
                pst.setString(1, selectedRow);
                pst.executeUpdate();
                updateTables();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
    }//GEN-LAST:event_btnDelteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            //update room
            String roomNo = txtRoom.getText();
            String roomType = (String) comboRoom.getSelectedItem();
            String bed = (String) comboBed.getSelectedItem();
            int price = Integer.parseInt(txtPrice.getText());

            pst = con.prepareStatement("UPDATE room set  roomType=?, bed=?, price=? where roomNo=?");
            pst.setString(1, roomType);
            pst.setString(2, bed);
            pst.setInt(3, price);
            pst.setString(4, roomNo);

            int k = pst.executeUpdate();

            if (k == 1) {
                txtRoom.setText("");
                txtPrice.setText("");
                JOptionPane.showMessageDialog(this, "Successfully updated.");
                updateManage();
            } else {
                JOptionPane.showMessageDialog(this, "Failed!");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

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

    private void btnDelte1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelte1ActionPerformed
        //delete records in reservation table
        int row = reservationTbl.getSelectedRow();
        String selectedRow = reservationTbl.getModel().getValueAt(row, 0).toString();
        String requestName = reservationTbl.getModel().getValueAt(row, 1).toString();
        String tblRoomNo = reservationTbl.getModel().getValueAt(row, 6).toString();
        try {
            if (JOptionPane.showConfirmDialog(this, "Delete the reservation request of " + requestName + "?", "",
                    JOptionPane.YES_NO_OPTION) == 0) {
                pst = con.prepareStatement("Delete from reservation where reserve_id=?");
                pst.setString(1, selectedRow);
                int k = pst.executeUpdate();

                if (k == 1) {
                    //update room status
                    pst = con.prepareStatement("UPDATE room SET status = 'Available' WHERE roomNo = ?");
                    pst.setString(1, tblRoomNo);
                    pst.executeUpdate();
                }

                String selection = cbbReservationFilter.getSelectedItem().toString();

                if (selection.equals("Without Account")) {
                    updateReservationWithout();
                    cbbReservationFilter.getModel().setSelectedItem("Without Account");

                } else if (selection.equals("With Account")) {
                    updateReservationWith();
                    cbbReservationFilter.getModel().setSelectedItem("With Account");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
    }//GEN-LAST:event_btnDelte1ActionPerformed

    private void comboRoomNoInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboRoomNoInMouseClicked
        try {
            //show roomNo of selected available room
            String roomNo = (String) comboRoomNoIn.getSelectedItem();
            pst = con.prepareStatement("SELECT * FROM room WHERE roomNo = ?");
            pst.setString(1, roomNo);
            rs = pst.executeQuery();
            while (rs.next()) {
                txtPriceCheckIn.setText(rs.getString(4));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
    }//GEN-LAST:event_comboRoomNoInMouseClicked

    private void btntransferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntransferActionPerformed
        int row = reservationTbl.getSelectedRow();
        String tblPersonID = reservationTbl.getModel().getValueAt(row, 1).toString();
        String tblName = reservationTbl.getModel().getValueAt(row, 2).toString();
        String tblCheckIn = reservationTbl.getModel().getValueAt(row, 10).toString();
        String tblRoomNo = reservationTbl.getModel().getValueAt(row, 6).toString();
        String selection = cbbReservationFilter.getSelectedItem().toString();
        try {
            //checks if selected combobox value is without account or with account
            if (selection.equals("Without Account")) {
                if (reservationTbl.getSelectedRowCount() == 1) {
                    if (JOptionPane.showConfirmDialog(this, "Transfer reservation request of " + tblName + "?", "",
                            JOptionPane.YES_NO_OPTION) == 0) {
                        pst = con.prepareStatement("INSERT INTO transaction (person_id, checkIn, roomNo) VALUES (?, ?, ?)");
                        pst.setString(1, tblPersonID);

                        pst.setString(2, tblCheckIn);
                        pst.setString(3, tblRoomNo);
                        int k = pst.executeUpdate();

                        if (k == 1) {
                            pst = con.prepareStatement("UPDATE room SET status = 'Occupied' WHERE roomNo = ?");
                            pst.setString(1, tblRoomNo);
                            int h = pst.executeUpdate();

                            pst = con.prepareStatement("DELETE FROM reservation where roomNo = ?");
                            pst.setString(1, tblRoomNo);
                            int j = pst.executeUpdate();

                            JOptionPane.showMessageDialog(this, "Customer Check-In Successfully!");

                            if (selection.equals("Without Account")) {
                                updateReservationWithout();
                                cbbReservationFilter.getModel().setSelectedItem("Without Account");

                            } else if (selection.equals("With Account")) {
                                updateReservationWith();
                                cbbReservationFilter.getModel().setSelectedItem("With Account");
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please select a row you want to check-in!", "Failed!", JOptionPane.ERROR_MESSAGE);
                }
            } else if (selection.equals("With Account")) {
                if (reservationTbl.getSelectedRowCount() == 1) {
                    if (JOptionPane.showConfirmDialog(this, "Transfer reservation request of " + tblName + "?", "",
                            JOptionPane.YES_NO_OPTION) == 0) {
                        pst = con.prepareStatement("INSERT INTO transaction (person_id, checkIn, roomNo) VALUES (?, ?, ?)");
                        pst.setString(1, tblPersonID);

                        pst.setString(2, tblCheckIn);
                        pst.setString(3, tblRoomNo);
                        int k = pst.executeUpdate();

                        if (k == 1) {
                            pst = con.prepareStatement("UPDATE room SET status = 'Occupied' WHERE roomNo = ?");
                            pst.setString(1, tblRoomNo);
                            int h = pst.executeUpdate();

                            pst = con.prepareStatement("DELETE FROM reservation where roomNo = ?");
                            pst.setString(1, tblRoomNo);
                            int j = pst.executeUpdate();

                            JOptionPane.showMessageDialog(this, "Customer Check-In Successfully!");
                            updateReservationWithout();
                            updateCheckInWithout();
                            updateManage();
                            updateCheckOutWith();
                            updateRequestList();
                            updateReservationWith();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please select a row you want to check-in!", "Failed!", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
    }//GEN-LAST:event_btntransferActionPerformed

    private void txtSearchResKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchResKeyReleased
        //query for searching a record in reservation table
        String search = txtSearchRes.getText();
        String selection = cbbReservationFilter.getSelectedItem().toString();
        if (selection.equals("Without Account")) {
            if (!search.equals("")) {
                try {
                    pst = con.prepareStatement("SELECT reservation.reserve_id AS ID, visitorinformation.visitor_id As PersonID, CONCAT( visitorinformation.firstName, ' ' ,visitorinformation.lastName ) As Name, visitorinformation.email AS Email, visitorinformation.address AS Address,  visitorinformation.phone AS Phone, reservation.roomNo AS RoomNumber,room.bed AS Bed, room.roomType AS RoomType,room.price AS Price, reservation.checkIn As CheckIn, reservation.status AS Status\n"
                            + "FROM reservation  \n"
                            + "JOIN visitorinformation ON visitorinformation.visitor_id = reservation.person_id \n"
                            + "JOIN room ON room.roomNo = reservation.roomNo WHERE  reservation.status= 'Reserved' and visitorinformation.firstName like '%" + search + "%'");
                    rs = pst.executeQuery();
                    reservationTbl.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            } else {
                updateReservationWithout();
            }
        } else {
            if (!search.equals("")) {
                try {
                    pst = con.prepareStatement("SELECT reservation.reserve_id AS ID, guestinformation.guest_id As PersonID, CONCAT( guestinformation.firstName, ' ' ,guestinformation.lastName ) As Name, guestinformation.email AS Email, guestinformation.address AS Address,  guestinformation.phone AS Phone, reservation.roomNo AS RoomNumber,room.bed AS Bed, room.roomType AS RoomType,room.price AS Price, reservation.checkIn As CheckIn, reservation.status AS Status\n"
                            + "FROM reservation  \n"
                            + "JOIN guestinformation ON guestinformation.guest_id = reservation.person_id \n"
                            + "JOIN room ON room.roomNo = reservation.roomNo  WHERE  reservation.status= 'Reserved' and guestinformation.firstName like '%" + search + "%'");
                    rs = pst.executeQuery();
                    reservationTbl.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            } else {
                updateReservationWith();
            }
        }

    }//GEN-LAST:event_txtSearchResKeyReleased

    private void txtSearchManageKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchManageKeyReleased

        //query for searching a record in room table
        String search = txtSearchManage.getText();
        if (!search.equals("")) {
            try {
                pst = con.prepareStatement("Select roomNo As RoomNumber, roomType AS RoomType, bed AS Bed, price AS Price, status AS Status from room where roomNo like '%" + search + "%'");
                rs = pst.executeQuery();
                manageTbl.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        } else {
            updateManage();
        }
    }//GEN-LAST:event_txtSearchManageKeyReleased

    private void txtSearchInKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchInKeyReleased
        //query for searching a record in check-in table
        String search = txtSearchIn.getText();
        String selection = cbbCheckinFilter.getSelectedItem().toString();
        if (selection.equals("Without Account")) {
            if (!search.equals("")) {
                try {
                    pst = con.prepareStatement("SELECT transaction.transaction_id As ID , CONCAT( visitorinformation.firstName, ' ' ,visitorinformation.lastName ) As Name, visitorinformation.email As Email, visitorinformation.address As Address,  visitorinformation.phone As Phone, transaction.roomNo As RoomNumber, room.bed As Bed, room.roomType As RoomType, room.price As Price,transaction.checkIn As CheckIn FROM transaction JOIN visitorinformation ON visitorinformation.visitor_id = transaction.person_id JOIN room ON room.roomNo = transaction.roomNo WHERE transaction.checkout is NULL and transaction.roomNo like '%" + search + "%'");
                    rs = pst.executeQuery();
                    rs = pst.executeQuery();
                    checkInTbl.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            } else {
                updateCheckInWithout();
            }
        } else {
            if (!search.equals("")) {
                try {
                    pst = con.prepareStatement("SELECT transaction.transaction_id As ID ,   CONCAT( guestinformation.firstName, ' ' ,guestinformation.lastName ) As Name, guestinformation.email As Email, guestinformation.address As Address,  guestinformation.phone As Phone, transaction.roomNo As RoomNumber, room.bed As Bed, room.roomType As RoomType, room.price As Price,transaction.checkIn As CheckIn FROM transaction JOIN guestinformation ON guestinformation.guest_id = transaction.person_id JOIN room ON room.roomNo = transaction.roomNo WHERE transaction.checkout is NULL and transaction.roomNo like '%" + search + "%'");
                    rs = pst.executeQuery();
                    checkInTbl.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            } else {
                updateCheckInWith();
            }
        }
    }//GEN-LAST:event_txtSearchInKeyReleased

    private void txtSearchOutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchOutKeyReleased

        //query for searching a record in check-out table
        String search = txtSearchOut.getText();
        String selection = cbbCheckoutFilter.getSelectedItem().toString();
        if (selection.equals("Without Account")) {
            if (!search.equals("")) {
                try {
                    pst = con.prepareStatement("SELECT transaction.transaction_id As ID , CONCAT( visitorinformation.firstName, ' ' ,visitorinformation.lastName ) As Name, visitorinformation.email As Email, visitorinformation.address As Address,  visitorinformation.phone As Phone, transaction.roomNo As RoomNumber, room.bed As Bed, room.roomType As RoomType, room.price As Price,transaction.checkIn As CheckIn FROM transaction JOIN visitorinformation ON visitorinformation.visitor_id = transaction.person_id JOIN room ON room.roomNo = transaction.roomNo WHERE transaction.checkout is NULL and transaction.roomNo like '%" + search + "%'");
                    rs = pst.executeQuery();
                    rs = pst.executeQuery();
                    checkOutTbl.setModel(DbUtils.resultSetToTableModel(rs));

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            } else {
                updateCheckInWithout();
            }
        } else {
            if (!search.equals("")) {
                try {
                    pst = con.prepareStatement("SELECT transaction.transaction_id As ID ,   CONCAT( guestinformation.firstName, ' ' ,guestinformation.lastName ) As Name, guestinformation.email As Email, guestinformation.address As Address,  guestinformation.phone As Phone, transaction.roomNo As RoomNumber, room.bed As Bed, room.roomType As RoomType, room.price As Price,transaction.checkIn As CheckIn FROM transaction JOIN guestinformation ON guestinformation.guest_id = transaction.person_id JOIN room ON room.roomNo = transaction.roomNo WHERE transaction.checkout is NULL and transaction.roomNo like '%" + search + "%'");
                    rs = pst.executeQuery();
                    checkOutTbl.setModel(DbUtils.resultSetToTableModel(rs));

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            } else {
                updateCheckInWith();
            }
        }
    }//GEN-LAST:event_txtSearchOutKeyReleased

    private void txtSearchReqKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchReqKeyReleased
//query for searching a record in request reservation table
        String search = txtSearchReq.getText();
        if (!search.equals("")) {
            try {
                pst = con.prepareStatement("SELECT reservation.reserve_id AS ID, CONCAT( guestinformation.firstName, ' ' ,guestinformation.lastName ) As Name, guestinformation.email AS Email, guestinformation.address AS Address,  guestinformation.phone AS Phone, reservation.roomNo AS RoomNumber,room.bed AS Bed, room.roomType AS RoomType,room.price AS Price, reservation.checkIn As CheckIn, reservation.status AS Status\n"
                        + "FROM reservation  \n"
                        + "JOIN guestinformation ON guestinformation.guest_id = reservation.person_id \n"
                        + "JOIN room ON room.roomNo = reservation.roomNo WHERE reservation.status= 'Pending' and guestinformation.firstName like '%" + search + "%'");

                rs = pst.executeQuery();
                tblRequest.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        } else {
            updateRequestList();
        }
    }//GEN-LAST:event_txtSearchReqKeyReleased

    private void txtSearchResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchResActionPerformed

    }//GEN-LAST:event_txtSearchResActionPerformed


    private void cbbReservationFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbReservationFilterActionPerformed
        String selection = cbbReservationFilter.getSelectedItem().toString();
        if (selection.equals("Without Account")) {
            updateReservationWithout();
            txtSearchRes.setText("");
        } else if (selection.equals("With Account")) {
            updateReservationWith();
            txtSearchRes.setText("");
        }
    }//GEN-LAST:event_cbbReservationFilterActionPerformed

    private void txtNameIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameIn1ActionPerformed

    }//GEN-LAST:event_txtNameIn1ActionPerformed

    private void cbbCheckinFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbCheckinFilterActionPerformed
        String selection = cbbCheckinFilter.getSelectedItem().toString();
        if (selection.equals("Without Account")) {
            updateCheckInWithout();
            txtSearchOut.setText("");
        } else if (selection.equals("With Account")) {
            updateCheckInWith();
            txtSearchOut.setText("");
        }
    }//GEN-LAST:event_cbbCheckinFilterActionPerformed

    private void cbbCheckoutFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbCheckoutFilterActionPerformed
        String selection = cbbCheckoutFilter.getSelectedItem().toString();
        //clear checkout summary text 
        if (selection.equals("Without Account")) {
            updateCheckOutWithout();
            txtNameOut.setText("");
            txtCheckInOut.setText("");
            txtCheckOutOut.setText("");
            txtNoStayOutt.setText("");
            txtRoomNoOut.setText("");
            txtPriceOut.setText("");
            txtTotalOut.setText("");
            txtSearchOut.setText("");
            btnCheckOutConfirm.setVisible(false);
            btnCancel.setVisible(false);
        } else if (selection.equals("With Account")) {
            updateCheckOutWith();
            txtNameOut.setText("");
            txtCheckInOut.setText("");
            txtCheckOutOut.setText("");
            txtNoStayOutt.setText("");
            txtRoomNoOut.setText("");
            txtPriceOut.setText("");
            txtTotalOut.setText("");
            txtSearchOut.setText("");
            btnCheckOutConfirm.setVisible(false);
            btnCancel.setVisible(false);

        }
    }//GEN-LAST:event_cbbCheckoutFilterActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        txtNameOut.setText("");
        txtCheckInOut.setText("");
        txtCheckOutOut.setText("");
        txtNoStayOutt.setText("");
        txtRoomNoOut.setText("");
        txtPriceOut.setText("");
        txtTotalOut.setText("");
        btnCancel.setVisible(false);
        btnCheckOutConfirm.setVisible(false);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void txtEmailInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailInActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailInActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
      
        clearFields();
        comboBedRes.setSelectedIndex(0);
        comboRoomTypeRes.setSelectedIndex(0);
        comboRoomNoRes.setSelectedIndex(0);
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
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Employee().setVisible(true);
                //new UIManager().ManagerUI();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel FDS;
    private javax.swing.JLabel FDS1;
    private javax.swing.JLabel FDS2;
    private javax.swing.JLabel btnAccount;
    private javax.swing.JButton btnAddroom;
    private javax.swing.JButton btnAllot;
    private javax.swing.JButton btnApprove;
    private javax.swing.JButton btnBlock;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCheckOutConfirm;
    private javax.swing.JLabel btnCheckin;
    private javax.swing.JLabel btnCheckout;
    private javax.swing.JButton btnConfirmRes;
    private javax.swing.JButton btnDeleteRoom;
    private javax.swing.JButton btnDelte;
    private javax.swing.JButton btnDelte1;
    private javax.swing.JLabel btnHome;
    private javax.swing.JLabel btnLogout;
    private javax.swing.JLabel btnReques;
    private javax.swing.JLabel btnReservation;
    private javax.swing.JLabel btnRoom;
    private javax.swing.JButton btnUnblock;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btntransfer;
    private javax.swing.JComboBox<String> cbAccountStatus;
    private javax.swing.JComboBox<String> cbbCheckinFilter;
    private javax.swing.JComboBox<String> cbbCheckoutFilter;
    private javax.swing.JComboBox<String> cbbReservationFilter;
    private javax.swing.JComboBox<String> cdTableFilter;
    private javax.swing.JTable checkInTbl;
    private javax.swing.JLabel checkInWithCounter;
    private javax.swing.JLabel checkInWithoutCounter;
    private javax.swing.JTable checkOutTbl;
    private javax.swing.JLabel checkOutWithCounter;
    private javax.swing.JLabel checkOutWithoutCounter;
    private javax.swing.JComboBox<String> comboBed;
    private javax.swing.JComboBox<String> comboBedIn;
    private javax.swing.JComboBox<String> comboBedRes;
    private javax.swing.JComboBox<String> comboRoom;
    private javax.swing.JComboBox<String> comboRoomNoIn;
    private javax.swing.JComboBox<String> comboRoomNoRes;
    private javax.swing.JComboBox<String> comboRoomTypeRes;
    private javax.swing.JComboBox<String> comboRoomtypeIn;
    private javax.swing.JLabel dAvailable;
    private javax.swing.JLabel dOccupied;
    private javax.swing.JLabel dPicture;
    private javax.swing.JLabel dReserved;
    private javax.swing.JLabel dTotal;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
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
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lblAll;
    private javax.swing.JLabel lblAvailable;
    private javax.swing.JLabel lblAvailable1;
    private javax.swing.JLabel lblFilter;
    private javax.swing.JLabel lblOccupied;
    private javax.swing.JLabel lblOccupied1;
    private javax.swing.JLabel lblReserve;
    private javax.swing.JLabel lblReserve1;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotal1;
    private javax.swing.JTable manageTbl;
    private javax.swing.JPanel pAdmin;
    private javax.swing.JPanel pCheckIn;
    private javax.swing.JPanel pCheckOut;
    private javax.swing.JPanel pCheckOutConfirm;
    private javax.swing.JPanel pHome;
    private javax.swing.JPanel pManage;
    private javax.swing.JPanel pMenu;
    private javax.swing.JPanel pRequest;
    private javax.swing.JPanel pReservation;
    private javax.swing.JPanel pcAccount;
    private javax.swing.JPanel pcCheckin;
    private javax.swing.JPanel pcCheckout;
    private javax.swing.JPanel pcHome;
    private javax.swing.JPanel pcLogout;
    private javax.swing.JPanel pcRequest;
    private javax.swing.JPanel pcReservation;
    private javax.swing.JPanel pcRoom;
    private javax.swing.JLabel requestCounter;
    private javax.swing.JTable reservationTbl;
    private javax.swing.JLabel reservationWithCounter;
    private javax.swing.JLabel reservationWithoutCounter;
    private javax.swing.JLabel sAvailable;
    private javax.swing.JLabel sOccupied;
    private javax.swing.JLabel sPicture;
    private javax.swing.JLabel sReserved;
    private javax.swing.JLabel sTotal;
    private javax.swing.JScrollPane spAdmin;
    private javax.swing.JScrollPane spCheckIn;
    private javax.swing.JScrollPane spCheckOut;
    private javax.swing.JScrollPane spManage;
    private javax.swing.JScrollPane spRequest;
    private javax.swing.JScrollPane spReservation;
    private javax.swing.JLabel tAvailable;
    private javax.swing.JLabel tOccupied;
    private javax.swing.JLabel tPicture;
    private javax.swing.JLabel tReserved;
    private javax.swing.JLabel tTotal;
    private javax.swing.JTable tblAdmin;
    private javax.swing.JTable tblRequest;
    private javax.swing.JTextField tfSearch;
    private javax.swing.JTextField txtAddressIn;
    private javax.swing.JTextField txtAddressRes;
    private javax.swing.JLabel txtCheckInOut;
    private com.toedter.calendar.JDateChooser txtCheckInRes;
    private javax.swing.JLabel txtCheckOutOut;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmailIn;
    private javax.swing.JTextField txtInDateIn;
    private javax.swing.JTextField txtNameIn;
    private javax.swing.JTextField txtNameIn1;
    private javax.swing.JLabel txtNameOut;
    private javax.swing.JTextField txtNameRes;
    private javax.swing.JTextField txtNameRes1;
    private javax.swing.JLabel txtNoStayOutt;
    private javax.swing.JTextField txtPhoneIn;
    private javax.swing.JTextField txtPhoneRes;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtPriceCheckIn;
    private javax.swing.JLabel txtPriceOut;
    private javax.swing.JTextField txtPriceRes;
    private javax.swing.JTextField txtRoom;
    private javax.swing.JLabel txtRoomNoOut;
    private javax.swing.JTextField txtSearchIn;
    private javax.swing.JTextField txtSearchManage;
    private javax.swing.JTextField txtSearchOut;
    private javax.swing.JTextField txtSearchReq;
    private javax.swing.JTextField txtSearchRes;
    private javax.swing.JLabel txtTotalOut;
    // End of variables declaration//GEN-END:variables
}
