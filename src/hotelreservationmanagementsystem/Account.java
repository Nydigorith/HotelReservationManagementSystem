package hotelreservationmanagementsystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class Account extends javax.swing.JFrame {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String tableName = null;
    boolean isFromAdmin;

    //default constructor
    public Account() {
        toInitialize();
        isFromAdmin = false;
        pForgotPassword.setVisible(false);
        pRegister.setVisible(false);
        pRegisterContinue.setVisible(false);

    }

    //constructor is invoked when adding user button is clicked in the manage account tab
    public Account(String verification) {
        tableName = "guestInformation";
        toInitialize();
        isFromAdmin = true;
        pForgotPassword.setVisible(false);
        pLogin.setVisible(false);
        jLabel21.setVisible(false);
        rLogin.setVisible(false);
        pRegisterContinue.setVisible(false);
        rAdministrator.setVisible(false);
        rStaff.setVisible(false);
        getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(24, 71, 58)));
    }

    void toInitialize() {
        // new UIManager().ManagerUI();
        initComponents();
        con = DbConnection.ConnectDb();
        //sets JFrame to appear centered, regardless of monitor resolution
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);

        rPassword.setEchoChar('*');
        lPassword.setEchoChar('*');
        rConfirmPassword.setEchoChar('*');
        fPassword.setEchoChar('*');
        fConfirmPassword.setEchoChar('*');

        rAddress.setText("");
        rPhone.setText("");
    }

    //reset the value of text fields and checkboxes
    void clearTextField() {
        fUsername.setText("");
        fAnswer.setText("");
        fPassword.setText("");
        fConfirmPassword.setText("");
        rFirstName.setText("");
        rName.setText("");
        rUsername.setText("");
        rPassword.setText("");
        rConfirmPassword.setText("");
        rPhone.setText("");
        rAddress.setText("");
        rAnswer.setText("");
        lUsername.setText("");
        lPassword.setText("");
        rEmail.setText("");
        rShowPassword.setSelected(false);
        lShowPassword.setSelected(false);
        fShowPassword.setSelected(false);
        lblRCase.setText("    ");
        lblFCase.setText("    ");
        lblRCase.setText("    ");
        lblFCase.setText("    ");
        lblRDigit.setText("    ");
        lblFDigit.setText("    ");
        lblRDigit.setText("    ");
        lblFDigit.setText("    ");
        lblRSix.setText("    ");
        lblFSix.setText("    ");
        lblRSix.setText("    ");
        lblFSix.setText("    ");
        fQuestion.setText("Security question will appear here.");
    }

    boolean emailValidation(String email) {
        //validates email address
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    //method for password validation
    boolean passwordValidation(String password) {
        if (password.length() > 0 && password.length() < 7) {
            boolean hasDigit = false, hasUppercase = false, hasLowercase = false;
            char character;
            for (int i = 0; i < password.length(); i++) {
                character = password.charAt(i);
                if (Character.isDigit(character)) {
                    hasDigit = true;

                } else if (Character.isUpperCase(character)) {
                    hasUppercase = true;

                } else if (Character.isLowerCase(character)) {
                    hasLowercase = true;
                }
            }
            if (hasDigit && hasUppercase && hasLowercase) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    void passwordValidationEvent(String password) {

        boolean hasDigit = false, hasUppercase = false, hasLowerCase = false;
        char character;
        for (int i = 0; i < password.length(); i++) {
            character = password.charAt(i);
            if (Character.isDigit(character)) {
                hasDigit = true;
            } else if (Character.isUpperCase(character)) {
                hasUppercase = true;

            } else if (Character.isLowerCase(character)) {
                hasLowerCase = true;
            } else {
                hasLowerCase = false;
                hasUppercase = false;
                hasDigit = false;
            }
        }
        if (hasUppercase && hasLowerCase) {
            lblRCase.setText("✔");
            lblFCase.setText("✔");
        } else {
            lblRCase.setText("❌");
            lblFCase.setText("❌");
        }
        if (hasDigit) {
            lblRDigit.setText("✔");
            lblFDigit.setText("✔");
        } else {
            lblRDigit.setText("❌");
            lblFDigit.setText("❌");
        }
        if (password.length() > 0 && password.length() < 7) {
            lblRSix.setText("✔");
            lblFSix.setText("✔");
        } else {
            lblRSix.setText("❌");
            lblFSix.setText("❌");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgEmployee = new javax.swing.ButtonGroup();
        pLogo = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        close = new javax.swing.JLabel();
        pRegisterContinue = new javax.swing.JPanel();
        rAnswer = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        rQuestion = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        rRegister = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        asd = new javax.swing.JLabel();
        ds = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        rConfirmPassword = new javax.swing.JPasswordField();
        sdasdasd = new javax.swing.JLabel();
        rPassword = new javax.swing.JPasswordField();
        lblRDigit = new javax.swing.JLabel();
        lblRCase = new javax.swing.JLabel();
        lblRSix = new javax.swing.JLabel();
        rShowPassword = new javax.swing.JCheckBox();
        pRegister = new javax.swing.JPanel();
        rUsername = new javax.swing.JTextField();
        rAdministrator = new javax.swing.JRadioButton();
        rStaff = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        rName = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        bEmployee = new javax.swing.JLabel();
        rEmail = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        rightLine = new javax.swing.JPanel();
        leftLine = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        rLogin = new javax.swing.JLabel();
        bGuest = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        rFirstName = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        rAddress = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        rPhone = new javax.swing.JTextField();
        pForgotPassword = new javax.swing.JPanel();
        fShowPassword = new javax.swing.JCheckBox();
        fUsername = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        fChangePassword = new javax.swing.JButton();
        fPassword = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        fAnswer = new javax.swing.JTextField();
        fConfirmPassword = new javax.swing.JPasswordField();
        fQuestion = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        fLogin = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        sdasdasd1 = new javax.swing.JLabel();
        ds1 = new javax.swing.JLabel();
        lblFDigit = new javax.swing.JLabel();
        lblFCase = new javax.swing.JLabel();
        lblFSix = new javax.swing.JLabel();
        pLogin = new javax.swing.JPanel();
        lLogin = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        lForgotPassword = new javax.swing.JLabel();
        lUsername = new javax.swing.JTextField();
        lPassword = new javax.swing.JPasswordField();
        lShowPassword = new javax.swing.JCheckBox();
        lRegister = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pLogo.setBackground(new java.awt.Color(25, 71, 58));
        pLogo.setForeground(new java.awt.Color(51, 255, 51));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotelreservationmanagementsystem/AccountLogo.jpg"))); // NOI18N

        javax.swing.GroupLayout pLogoLayout = new javax.swing.GroupLayout(pLogo);
        pLogo.setLayout(pLogoLayout);
        pLogoLayout.setHorizontalGroup(
            pLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pLogoLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );
        pLogoLayout.setVerticalGroup(
            pLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pLogoLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(114, Short.MAX_VALUE))
        );

        getContentPane().add(pLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 720));

        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotelreservationmanagementsystem/CloseIcon.png"))); // NOI18N
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
        });
        getContentPane().add(close, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 10, -1, -1));

        pRegisterContinue.setBackground(new java.awt.Color(239, 233, 221));
        pRegisterContinue.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rAnswer.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        rAnswer.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(45, 88, 76), 2, true));
        rAnswer.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                rAnswerPropertyChange(evt);
            }
        });
        pRegisterContinue.add(rAnswer, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 500, 360, 35));

        jLabel10.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(24, 71, 58));
        jLabel10.setText("Answer");
        pRegisterContinue.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 470, -1, 35));

        rQuestion.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        rQuestion.setForeground(new java.awt.Color(24, 71, 58));
        rQuestion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose a Question", "What was the name of your first pet?", "What is your favorite sports team?", "What is your favorite movie?", "What is your closest sibling’s name?", "What is your favorite food?", "What street did you grow up on?", "Where’s the first place you spent a vacation?" }));
        rQuestion.setBorder(null);
        rQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rQuestionActionPerformed(evt);
            }
        });
        pRegisterContinue.add(rQuestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 440, 360, 35));

        jLabel9.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(24, 71, 58));
        jLabel9.setText("Security Question");
        pRegisterContinue.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 130, -1, -1));

        rRegister.setBackground(new java.awt.Color(165, 89, 40));
        rRegister.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 18)); // NOI18N
        rRegister.setForeground(new java.awt.Color(255, 255, 255));
        rRegister.setText("Register");
        rRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rRegisterActionPerformed(evt);
            }
        });
        pRegisterContinue.add(rRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 580, 360, 50));

        jLabel27.setFont(new java.awt.Font("Noto Sans Black", 0, 15)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(24, 71, 58));
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotelreservationmanagementsystem/BackIcon.png"))); // NOI18N
        jLabel27.setText(" back");
        jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel27MouseClicked(evt);
            }
        });
        pRegisterContinue.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 80, -1, -1));

        jLabel8.setFont(new java.awt.Font("Noto Sans Medium", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(139, 138, 136));
        jLabel8.setText("Must not be more than 6 characters.");
        pRegisterContinue.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 280, -1, 20));

        asd.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        asd.setForeground(new java.awt.Color(24, 71, 58));
        asd.setText("Confirm Password");
        pRegisterContinue.add(asd, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 320, -1, 30));

        ds.setFont(new java.awt.Font("Noto Sans Medium", 0, 12)); // NOI18N
        ds.setForeground(new java.awt.Color(139, 138, 136));
        ds.setText("Must include atleast one number.");
        pRegisterContinue.add(ds, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 300, -1, 20));

        jLabel11.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(24, 71, 58));
        jLabel11.setText("Password");
        pRegisterContinue.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 210, -1, 30));

        rConfirmPassword.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        rConfirmPassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(24, 71, 58), 2, true));
        rConfirmPassword.setMargin(new java.awt.Insets(5, 5, 5, 5));
        pRegisterContinue.add(rConfirmPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 350, 360, 35));

        sdasdasd.setFont(new java.awt.Font("Noto Sans Medium", 0, 12)); // NOI18N
        sdasdasd.setForeground(new java.awt.Color(139, 138, 136));
        sdasdasd.setText("Must include lower and upper case characters.");
        pRegisterContinue.add(sdasdasd, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 290, -1, 20));

        rPassword.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        rPassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(24, 71, 58), 2, true));
        rPassword.setMargin(new java.awt.Insets(5, 5, 5, 5));
        rPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rPasswordKeyReleased(evt);
            }
        });
        pRegisterContinue.add(rPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 240, 360, 35));

        lblRDigit.setText("    ");
        pRegisterContinue.add(lblRDigit, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 300, -1, 20));

        lblRCase.setText("    ");
        pRegisterContinue.add(lblRCase, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 290, -1, 20));

        lblRSix.setText("    ");
        pRegisterContinue.add(lblRSix, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 280, -1, 20));

        rShowPassword.setBackground(new java.awt.Color(239, 233, 221));
        rShowPassword.setFont(new java.awt.Font("Noto Sans Medium", 0, 14)); // NOI18N
        rShowPassword.setText("Show Password");
        rShowPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rShowPasswordActionPerformed(evt);
            }
        });
        pRegisterContinue.add(rShowPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 390, -1, 30));

        getContentPane().add(pRegisterContinue, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pRegister.setBackground(new java.awt.Color(239, 233, 221));
        pRegister.setForeground(new java.awt.Color(0, 255, 0));
        pRegister.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rUsername.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        rUsername.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(24, 71, 58), 2, true));
        rUsername.setMargin(new java.awt.Insets(5, 5, 5, 5));
        pRegister.add(rUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 260, 360, 35));

        rAdministrator.setBackground(new java.awt.Color(239, 233, 221));
        bgEmployee.add(rAdministrator);
        rAdministrator.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        rAdministrator.setForeground(new java.awt.Color(24, 71, 58));
        rAdministrator.setText("Administrator");
        rAdministrator.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        pRegister.add(rAdministrator, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 490, -1, -1));

        rStaff.setBackground(new java.awt.Color(239, 233, 221));
        bgEmployee.add(rStaff);
        rStaff.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        rStaff.setForeground(new java.awt.Color(24, 71, 58));
        rStaff.setText("Staff");
        rStaff.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        pRegister.add(rStaff, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 490, -1, -1));

        jLabel12.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(24, 71, 58));
        jLabel12.setText("Email");
        pRegister.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 290, -1, 30));

        rName.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        rName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(24, 71, 58), 2, true));
        rName.setMargin(new java.awt.Insets(5, 5, 5, 5));
        rName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rNameKeyReleased(evt);
            }
        });
        pRegister.add(rName, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 200, 170, 35));

        jLabel15.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(24, 71, 58));
        jLabel15.setText("Last Name");
        pRegister.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 170, -1, 30));

        bEmployee.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 17)); // NOI18N
        bEmployee.setForeground(new java.awt.Color(139, 138, 136));
        bEmployee.setText("             Employee");
        bEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bEmployeeMouseClicked(evt);
            }
        });
        pRegister.add(bEmployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 130, 180, -1));

        rEmail.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        rEmail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(24, 71, 58), 2, true));
        rEmail.setMargin(new java.awt.Insets(5, 5, 5, 5));
        pRegister.add(rEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 320, 360, 35));

        jLabel18.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(24, 71, 58));
        jLabel18.setText("Username");
        pRegister.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 230, -1, 30));

        jLabel21.setFont(new java.awt.Font("Noto Sans Medium", 0, 15)); // NOI18N
        jLabel21.setText("Have an Account? ");
        pRegister.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 610, -1, -1));

        rightLine.setBackground(new java.awt.Color(139, 138, 136));
        rightLine.setForeground(new java.awt.Color(51, 255, 51));
        rightLine.setPreferredSize(new java.awt.Dimension(360, 4));

        javax.swing.GroupLayout rightLineLayout = new javax.swing.GroupLayout(rightLine);
        rightLine.setLayout(rightLineLayout);
        rightLineLayout.setHorizontalGroup(
            rightLineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );
        rightLineLayout.setVerticalGroup(
            rightLineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );

        pRegister.add(rightLine, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 160, 180, -1));

        leftLine.setBackground(new java.awt.Color(23, 71, 58));
        leftLine.setForeground(new java.awt.Color(51, 255, 51));
        leftLine.setPreferredSize(new java.awt.Dimension(360, 4));

        javax.swing.GroupLayout leftLineLayout = new javax.swing.GroupLayout(leftLine);
        leftLine.setLayout(leftLineLayout);
        leftLineLayout.setHorizontalGroup(
            leftLineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );
        leftLineLayout.setVerticalGroup(
            leftLineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );

        pRegister.add(leftLine, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 160, 180, -1));

        jLabel19.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 36)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(23, 71, 58));
        jLabel19.setText("Register");
        pRegister.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 60, -1, -1));

        rLogin.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        rLogin.setForeground(new java.awt.Color(24, 71, 58));
        rLogin.setText("Login");
        rLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rLoginMouseClicked(evt);
            }
        });
        pRegister.add(rLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 620, -1, 30));

        bGuest.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 17)); // NOI18N
        bGuest.setForeground(new java.awt.Color(24, 71, 58));
        bGuest.setText("                 Guest");
        bGuest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bGuestMouseClicked(evt);
            }
        });
        pRegister.add(bGuest, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 130, 180, -1));

        jButton1.setBackground(new java.awt.Color(165, 89, 40));
        jButton1.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Continue");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        pRegister.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 540, 360, 50));

        rFirstName.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        rFirstName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(24, 71, 58), 2, true));
        rFirstName.setMargin(new java.awt.Insets(5, 5, 5, 5));
        rFirstName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rFirstNameKeyReleased(evt);
            }
        });
        pRegister.add(rFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 200, 180, 35));

        jLabel17.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(24, 71, 58));
        jLabel17.setText("First Name");
        pRegister.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 170, -1, 30));

        jLabel20.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(24, 71, 58));
        jLabel20.setText("Address (Optional)");
        pRegister.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 350, -1, 30));

        rAddress.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        rAddress.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(24, 71, 58), 2, true));
        rAddress.setMargin(new java.awt.Insets(5, 5, 5, 5));
        pRegister.add(rAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 380, 360, 35));

        jLabel22.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(24, 71, 58));
        jLabel22.setText("Phone (Optional)");
        pRegister.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 410, -1, 30));

        rPhone.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        rPhone.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(24, 71, 58), 2, true));
        rPhone.setMargin(new java.awt.Insets(5, 5, 5, 5));
        pRegister.add(rPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 440, 360, 35));

        getContentPane().add(pRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pForgotPassword.setBackground(new java.awt.Color(239, 233, 221));
        pForgotPassword.setForeground(new java.awt.Color(0, 255, 0));
        pForgotPassword.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fShowPassword.setBackground(new java.awt.Color(239, 233, 221));
        fShowPassword.setFont(new java.awt.Font("Noto Sans Medium", 0, 14)); // NOI18N
        fShowPassword.setText("Show Password");
        fShowPassword.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                fShowPasswordStateChanged(evt);
            }
        });
        fShowPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fShowPasswordMouseClicked(evt);
            }
        });
        fShowPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fShowPasswordActionPerformed(evt);
            }
        });
        pForgotPassword.add(fShowPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 480, -1, 30));

        fUsername.setFont(new java.awt.Font("Noto Sans Medium", 0, 15)); // NOI18N
        fUsername.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(24, 71, 58), 2, true));
        fUsername.setPreferredSize(new java.awt.Dimension(24, 20));
        fUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fUsernameKeyReleased(evt);
            }
        });
        pForgotPassword.add(fUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 170, 360, 35));

        jLabel2.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(24, 71, 58));
        jLabel2.setText("Username/Email");
        pForgotPassword.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 140, -1, 35));

        jLabel5.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(24, 71, 58));
        jLabel5.setText("Confirm Password");
        pForgotPassword.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 410, -1, 35));

        jLabel6.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(24, 71, 58));
        jLabel6.setText("Answer");
        pForgotPassword.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 240, -1, 35));

        fChangePassword.setBackground(new java.awt.Color(165, 89, 40));
        fChangePassword.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 18)); // NOI18N
        fChangePassword.setForeground(new java.awt.Color(255, 255, 255));
        fChangePassword.setText("Continue");
        fChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fChangePasswordActionPerformed(evt);
            }
        });
        pForgotPassword.add(fChangePassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 530, 360, 50));

        fPassword.setFont(new java.awt.Font("Noto Sans Medium", 0, 15)); // NOI18N
        fPassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(24, 71, 58), 2, true));
        fPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fPasswordKeyReleased(evt);
            }
        });
        pForgotPassword.add(fPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 330, 360, 35));

        jLabel7.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(24, 71, 58));
        jLabel7.setText("New Password");
        pForgotPassword.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 300, -1, 35));

        fAnswer.setFont(new java.awt.Font("Noto Sans Medium", 0, 15)); // NOI18N
        fAnswer.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(24, 71, 58), 2, true));
        pForgotPassword.add(fAnswer, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 270, 360, 35));

        fConfirmPassword.setFont(new java.awt.Font("Noto Sans Medium", 0, 15)); // NOI18N
        fConfirmPassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(24, 71, 58), 2, true));
        pForgotPassword.add(fConfirmPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 440, 360, 35));

        fQuestion.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        fQuestion.setForeground(new java.awt.Color(24, 71, 58));
        fQuestion.setText("Question");
        pForgotPassword.add(fQuestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 210, -1, 35));

        jLabel28.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 36)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(24, 71, 58));
        jLabel28.setText("Forgot Password");
        pForgotPassword.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 70, -1, -1));

        jLabel29.setFont(new java.awt.Font("Noto Sans Medium", 0, 15)); // NOI18N
        jLabel29.setText("Remembered Your Password?");
        pForgotPassword.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 600, 220, -1));

        fLogin.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        fLogin.setForeground(new java.awt.Color(24, 71, 58));
        fLogin.setText("Login");
        fLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fLoginMouseClicked(evt);
            }
        });
        pForgotPassword.add(fLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 610, 50, 30));

        jLabel14.setFont(new java.awt.Font("Noto Sans Medium", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(139, 138, 136));
        jLabel14.setText("Must not be more than 6 characters.");
        pForgotPassword.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 370, -1, 20));

        sdasdasd1.setFont(new java.awt.Font("Noto Sans Medium", 0, 12)); // NOI18N
        sdasdasd1.setForeground(new java.awt.Color(139, 138, 136));
        sdasdasd1.setText("Must include lower and upper case characters.");
        pForgotPassword.add(sdasdasd1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 380, -1, 20));

        ds1.setFont(new java.awt.Font("Noto Sans Medium", 0, 12)); // NOI18N
        ds1.setForeground(new java.awt.Color(139, 138, 136));
        ds1.setText("Musct include atleast one number.");
        pForgotPassword.add(ds1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 390, -1, 20));

        lblFDigit.setText("    ");
        pForgotPassword.add(lblFDigit, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 390, -1, 20));

        lblFCase.setText("    ");
        pForgotPassword.add(lblFCase, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 380, -1, 20));

        lblFSix.setText("    ");
        pForgotPassword.add(lblFSix, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 370, -1, 20));

        getContentPane().add(pForgotPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pLogin.setBackground(new java.awt.Color(239, 233, 221));
        pLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pLoginMouseClicked(evt);
            }
        });
        pLogin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lLogin.setBackground(new java.awt.Color(165, 89, 43));
        lLogin.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 18)); // NOI18N
        lLogin.setForeground(new java.awt.Color(255, 255, 255));
        lLogin.setText("Login");
        lLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lLoginActionPerformed(evt);
            }
        });
        pLogin.add(lLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 430, 360, 50));

        jLabel23.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 36)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(24, 71, 58));
        jLabel23.setText("Login");
        pLogin.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 160, -1, -1));

        jLabel24.setFont(new java.awt.Font("Noto Sans Medium", 0, 15)); // NOI18N
        jLabel24.setText("Don't Have an Account?");
        pLogin.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 500, 180, -1));

        jLabel25.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(24, 71, 58));
        jLabel25.setText("Username/Email");
        pLogin.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 230, -1, 35));

        jLabel26.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(24, 71, 58));
        jLabel26.setText("Password");
        pLogin.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 290, -1, 35));

        lForgotPassword.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 15)); // NOI18N
        lForgotPassword.setForeground(new java.awt.Color(24, 71, 58));
        lForgotPassword.setText("Forgot Password?");
        lForgotPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lForgotPasswordMouseClicked(evt);
            }
        });
        pLogin.add(lForgotPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 380, -1, 20));

        lUsername.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        lUsername.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(45, 88, 76), 2, true));
        pLogin.add(lUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 260, 360, 35));

        lPassword.setFont(new java.awt.Font("Noto Sans Medium", 0, 16)); // NOI18N
        lPassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(45, 88, 76), 2, true));
        pLogin.add(lPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 320, 360, 35));

        lShowPassword.setBackground(new java.awt.Color(239, 233, 221));
        lShowPassword.setFont(new java.awt.Font("Noto Sans Medium", 0, 14)); // NOI18N
        lShowPassword.setText("Show Password");
        lShowPassword.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                lShowPasswordStateChanged(evt);
            }
        });
        lShowPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lShowPasswordMouseClicked(evt);
            }
        });
        lShowPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lShowPasswordActionPerformed(evt);
            }
        });
        pLogin.add(lShowPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 360, -1, -1));

        lRegister.setFont(new java.awt.Font("Noto Sans ExtraBold", 0, 16)); // NOI18N
        lRegister.setForeground(new java.awt.Color(24, 71, 58));
        lRegister.setText("Register");
        lRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lRegisterMouseClicked(evt);
            }
        });
        pLogin.add(lRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 510, -1, 30));

        getContentPane().add(pLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        jLabel16.setText("jLabel16");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 350, -1, -1));

        jLabel3.setText("Forgot Password");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 90, -1, -1));

        jLabel13.setText("Login");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 250, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fShowPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fShowPasswordActionPerformed
        //toggling the visibility of the password
        if (fShowPassword.isSelected()) {
            fPassword.setEchoChar((char) 0);
            fConfirmPassword.setEchoChar((char) 0);
        } else {
            fPassword.setEchoChar('*');
            fConfirmPassword.setEchoChar('*');
        }
    }//GEN-LAST:event_fShowPasswordActionPerformed

    private void fShowPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fShowPasswordMouseClicked

    }//GEN-LAST:event_fShowPasswordMouseClicked

    private void fShowPasswordStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_fShowPasswordStateChanged


    }//GEN-LAST:event_fShowPasswordStateChanged

    private void fPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fPasswordKeyReleased
        String password = fPassword.getText();
        passwordValidationEvent(password);
    }//GEN-LAST:event_fPasswordKeyReleased

    //method is invoked in continue button click event
    void changePassword(String password, String confirmPassword, String user, String asnwer, String condition, String tableName) {
        try {
            //checks if the  password entered meets the requirements
            if (passwordValidation(password)) {
                //checks if the text in the password and confirm password text filed is similar
                if (password.equals(confirmPassword)) {
                    //updates user password
                    pst = con.prepareStatement("Update " + tableName + " set password = PASSWORD(?) where " + condition + "=? and answer = PASSWORD(?)");
                    pst.setString(1, password);
                    pst.setString(2, user);
                    pst.setString(3, asnwer);
                    if (pst.executeUpdate() == 1) {
                        clearTextField();
                        JOptionPane.showMessageDialog(this, "Your password has been changed.\nPlease sign in with your new password.", "Successful!", JOptionPane.INFORMATION_MESSAGE);
                        pLogin.setVisible(true);
                        pForgotPassword.setVisible(false);

                    } else {
                        JOptionPane.showMessageDialog(this, "Incorrect Answer.", "Failed!", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Passwords do not match.", "Failed!", JOptionPane.ERROR_MESSAGE);
                }
            } else {

                JOptionPane.showMessageDialog(this, "Password does not meet the requirements.", "Failed!", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
    }
    private void fChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fChangePasswordActionPerformed
        String tableName = null;
        String user = fUsername.getText();
        String asnwer = fAnswer.getText().toLowerCase();
        String password = fPassword.getText();
        String confirmPassword = fConfirmPassword.getText();
        String hasInGuest = null;
        String hasInEmployee = null;
        String condition = null;
        try {
            //checks if the text fields are empty or not
            if (!user.equals("") || !password.equals("") || !confirmPassword.equals("") || !asnwer.equals("")) {
                //method is used to identify wether the value in user field is ausername or email
                if (emailValidation(user)) {
                    condition = "email";
                } else {
                    condition = "username";
                }
                //check if the username exists in the guestinformationtable
                pst = con.prepareStatement("Select * from guestInformation where " + condition + "=?");
                pst.setString(1, user);
                rs = pst.executeQuery();
                if (rs.next()) {
                    hasInGuest = rs.getString("username");
                }
                if (hasInGuest != null) {
                    tableName = "guestInformation";
                    changePassword(password, confirmPassword, user, asnwer, condition, tableName);
                } else {
                    //check if the username exists in the employeeInformation

                    pst = con.prepareStatement("Select * from employeeInformation where " + condition + "=?");
                    pst.setString(1, user);
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        hasInEmployee = rs.getString("username");
                    }
                    if (hasInEmployee != null) {
                        tableName = "employeeInformation";
                        changePassword(password, confirmPassword, user, asnwer, condition, tableName);
                    } else {

                        JOptionPane.showMessageDialog(this, "Account does not exist.", "Failed!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all the fields.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
    }//GEN-LAST:event_fChangePasswordActionPerformed

    private void fUsernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fUsernameKeyReleased
        //key released event is used to automatically show the security question answered  by a user to a label
        String user = fUsername.getText();
        String name = null;
        String question = null;
        String condition = null;
        try {
            if (emailValidation(user)) {
                condition = "email";
            } else {
                condition = "username";
            }
            pst = con.prepareStatement("Select * from guestInformation where " + condition + "=?");
            pst.setString(1, user);
            rs = pst.executeQuery();
            if (rs.next()) {
                name = rs.getString("username");
                question = rs.getString("question");
                fQuestion.setText(question);
            } else {
                pst = con.prepareStatement("Select * from employeeInformation where " + condition + "=?");
                pst.setString(1, user);
                rs = pst.executeQuery();
                if (rs.next()) {
                    name = rs.getString("username");
                    question = rs.getString("question");
                    fQuestion.setText(question);
                } else {
                    fQuestion.setText("Security question will appear here.");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
    }//GEN-LAST:event_fUsernameKeyReleased

    private void lShowPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lShowPasswordActionPerformed
        if (lShowPassword.isSelected()) {
            lPassword.setEchoChar((char) 0);
        } else {
            lPassword.setEchoChar('*');
        }
    }//GEN-LAST:event_lShowPasswordActionPerformed

    private void lShowPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lShowPasswordMouseClicked

    }//GEN-LAST:event_lShowPasswordMouseClicked

    private void lShowPasswordStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_lShowPasswordStateChanged

    }//GEN-LAST:event_lShowPasswordStateChanged
    //method is invoked in login button click event

    void login(int attempt, String user, String password, String condition) {
        try {
            if (attempt < 3) {
                pst = con.prepareStatement("Select * from " + tableName + " where " + condition + "=? and password=PASSWORD(?)");
                pst.setInt(1, attempt);
                pst.setString(1, user);
                pst.setString(2, password);
                rs = pst.executeQuery();
                //checks login attempt count and return how many attempts the user has left
                if (rs.next()) {
                    pst = con.prepareStatement("Update " + tableName + " set attempt = ? where " + condition + "=?");
                    pst.setInt(1, 0);
                    pst.setString(2, user);
                    pst.executeUpdate();
                    if (tableName.equals("guestInformation")) {
                        new Guest(rs.getString("email"), rs.getString("firstName")).setVisible(true);
                    } else if (tableName.equals("employeeInformation")) {
                        new Employee(rs.getString("email"), rs.getString("firstName")).setVisible(true);
                    }
                    clearTextField();
                    setVisible(false);
                } else {
                    //checks login attempt count and return how many attempts the user has left
                    if (attempt == 0) {
                        JOptionPane.showMessageDialog(this, "Password Incorrect. You have 2 attemps left.", "Failed!", JOptionPane.WARNING_MESSAGE);
                    } else if (attempt == 1) {
                        JOptionPane.showMessageDialog(this, "Password Incorrect. You have 1 attemp left.", "Failed!", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Password Incorrect. Account has been disabled.", "Failed!", JOptionPane.WARNING_MESSAGE);
                    }
                    //increases the user's attempt count
                    pst = con.prepareStatement("Update " + tableName + " set attempt = ? where " + condition + "=?");
                    attempt++;
                    System.out.println(attempt);
                    pst.setInt(1, attempt);
                    pst.setString(2, user);
                    pst.executeUpdate();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Your account has been disabled. Plase ty again later.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
    }
    private void lLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lLoginActionPerformed

        String condition = null;
        String user = lUsername.getText();
        String password = lPassword.getText();
        String hasInGuest = null;
        String hasInEmployee = null;
        //String username = null;
        int attempt = 0;
        try {
            //checks if the text fields are empty or not
            if (!user.equals("") || !password.equals("")) {
                //method is used to identify wether the value in user field is a username or email
                if (emailValidation(user)) {
                    condition = "email";
                } else {
                    condition = "username";
                }
                tableName = "guestInformation";
                //method is used to identify wether the value in the user field is a username or email
                pst = con.prepareStatement("Select * from " + tableName + " where " + condition + "=?");
                pst.setString(1, user);
                rs = pst.executeQuery();
                while (rs.next()) {
                    hasInGuest = rs.getString("username");
                    attempt = rs.getInt("attempt");
                }
                if (hasInGuest != null) {
                    //checks if the user account is disabled or not
                    login(attempt, user, password, condition);
                } else {
                    tableName = "employeeInformation";
                    //check if the username already exists in the employeeInformation table or in not
                    pst = con.prepareStatement("Select * from " + tableName + " where " + condition + "=?");
                    pst.setString(1, user);
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        hasInEmployee = rs.getString("username");
                        attempt = rs.getInt("attempt");
                        // username = rs.getString("username");
                    }
                    if (hasInEmployee != null) {
                        //checks if the user account is disabled or not
                        if (attempt < 3) {
                            login(attempt, user, password, condition);
                        } else {
                            JOptionPane.showMessageDialog(this, "Your account is disabled. Plase ty again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Account does not exist.", "Failed!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all the required fields.", "Failed!", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
    }//GEN-LAST:event_lLoginActionPerformed

    private void rNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rNameKeyReleased

    }//GEN-LAST:event_rNameKeyReleased

    private void rAnswerPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_rAnswerPropertyChange

    }//GEN-LAST:event_rAnswerPropertyChange

    private void rRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rRegisterActionPerformed

        String address = rAddress.getText();
        String phone = rPhone.getText();
        String lastName = rName.getText();
        String firstName = rFirstName.getText();
        String username = rUsername.getText();
        String email = rEmail.getText();
        String password = rPassword.getText();
        String confirmPassword = rConfirmPassword.getText();
        String question = rQuestion.getSelectedItem().toString();
        String answer = rAnswer.getText().toLowerCase();
        String employeeType = null;
        String verify = null;

        //sets value to the radiobuttons
        rAdministrator.setActionCommand("Administrator");
        rStaff.setActionCommand("Staff");
        try {
            if (passwordValidation(password)) {
                //checks if the text in the password and confirm password text filed is similar
                if (password.equals(confirmPassword)) {

                    //checks if the text fields are empty or not
                    if (!firstName.equals("") && !lastName.equals("") && !username.equals("") && !password.equals("") && !confirmPassword.equals("") && !question.equals("Choose a Question") && !answer.equals("")) {

                        //auto incrementing guest_id (even numbers)
                        if (tableName.equals("guestInformation")) {
                            int guestInformation = 0;

                            pst = con.prepareStatement("Select * from guestInformation  ORDER BY guest_id DESC LIMIT 1");
                            rs = pst.executeQuery();
                            if (rs.next()) {
                                guestInformation = rs.getInt(("guest_id"));

                            } else {
                                guestInformation = 0;
                            }
                            guestInformation += 2;
                            pst = con.prepareStatement("INSERT INTO " + tableName + "(guest_id,firstName,lastName,username,email,address, phone,password,question,answer,attempt)VALUES(?,?,?,?,?,?,?,PASSWORD(?),?,PASSWORD(?),?)");
                            pst.setInt(1, guestInformation);
                            pst.setString(2, firstName);
                            pst.setString(3, lastName);
                            pst.setString(4, username);
                            pst.setString(5, email);
                            pst.setString(6, address);
                            pst.setString(7, phone);
                            pst.setString(8, password);
                            pst.setString(9, question);
                            pst.setString(10, answer);
                            pst.setInt(11, 0);
                            if (pst.executeUpdate() == 1) {
                                if (isFromAdmin) {

                                    setVisible(false);
                                    new Employee().updateAdminTable();

                                } else {
                                    JOptionPane.showMessageDialog(this, "Your account has been created successfully.", "Successful!", JOptionPane.INFORMATION_MESSAGE);
                                    new Guest(email, firstName).setVisible(true);
                                    setVisible(false);
                                }
                                clearTextField();

                            } else {
                                JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
                            }
                        } else if (tableName.equals("employeeInformation")) {
                            //check if the user chooses an item in the button group
                            if (bgEmployee.getSelection() != null) {
                                employeeType = bgEmployee.getSelection().getActionCommand();
                                //in creating an employee account, the administrator password must be entered to continue with account registration
                                JPasswordField adminPassword = new JPasswordField();
                                int adminVerification = JOptionPane.showConfirmDialog(null, adminPassword, "Enter Administrator Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                                if (adminVerification == JOptionPane.OK_OPTION) {
                                    verify = new String(adminPassword.getPassword());
                                    //String adminVerification = JOptionPane.showInputDialog("Enter Password", JOptionPane.OK_CANCEL_OPTION);
                                    pst = con.prepareStatement("Select * from " + tableName + " where username='administrator' and password=PASSWORD(?)");
                                    pst.setString(1, verify);
                                    rs = pst.executeQuery();
                                    if (rs.next()) {
                                        pst = con.prepareStatement("INSERT INTO " + tableName + "(firstName,lastName,username,email,address, phone,password,question,answer,attempt,type)VALUES(?,?,?,?,?,?,PASSWORD(?),?,PASSWORD(?),?,?)");
                                        pst.setString(1, firstName);
                                        pst.setString(2, lastName);
                                        pst.setString(3, username);
                                        pst.setString(4, email);
                                        pst.setString(5, address);
                                        pst.setString(6, phone);
                                        pst.setString(7, password);
                                        pst.setString(8, question);
                                        pst.setString(9, answer);
                                        pst.setInt(10, 0);
                                        pst.setString(11, employeeType);
                                        if (pst.executeUpdate() == 1) {
                                            if (isFromAdmin) {
                                                setVisible(false);
                                                new Employee().updateAdminTable();
                                            } else {
                                                JOptionPane.showMessageDialog(this, "Your account has been created successfully.", "Successful!", JOptionPane.INFORMATION_MESSAGE);
                                                new Employee(email, firstName).setVisible(true);
                                                setVisible(false);
                                            }
                                            clearTextField();

                                        } else {
                                            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(this, "Incorrect Password.", "Failed!", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            } else if (bgEmployee.getSelection() == null) {
                                JOptionPane.showMessageDialog(this, "Please elect an employee type.", "Failed!", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Please fill in all the required fields.", "Failed!", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Passwords do not match.", "Failed!", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Password does not meet the requirements.", "Failed!", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
    }//GEN-LAST:event_rRegisterActionPerformed

    private void bGuestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bGuestMouseClicked
        bEmployee.setForeground(Color.decode("#8B8A88"));
        rightLine.setBackground(Color.decode("#8B8A88"));
        bGuest.setForeground(Color.decode("#18473A"));
        leftLine.setBackground(Color.decode("#18473A"));
        rAdministrator.setVisible(false);
        rStaff.setVisible(false);
        tableName = "guestInformation";
    }//GEN-LAST:event_bGuestMouseClicked

    private void bEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bEmployeeMouseClicked

        bEmployee.setForeground(Color.decode("#18473A"));
        rightLine.setBackground(Color.decode("#18473A"));
        bGuest.setForeground(Color.decode("#8B8A88"));
        leftLine.setBackground(Color.decode("#8B8A88"));
        rAdministrator.setVisible(true);
        rStaff.setVisible(true);
        tableName = "employeeInformation";
    }//GEN-LAST:event_bEmployeeMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //sets value to the radiobuttons
        rAdministrator.setActionCommand("Administrator");
        rStaff.setActionCommand("Staff");
        String name = rName.getText();
        String username = rUsername.getText();
        String address = rAddress.getText();
        String phone = rPhone.getText();
        String email = rEmail.getText();
//        String password = rPassword.getText();
//        String confirmPassword = rConfirmPassword.getText();
        String question = rQuestion.getSelectedItem().toString();
        String answer = rAnswer.getText().toLowerCase();
        String employeeType = null;
        String hasDuplicateUsername = null;
        String hasDuplicateEmail = null;
        try {
            //checks if the text fields are empty or not

            if (!name.equals("") && !username.equals("")) {

                pst = con.prepareStatement("SELECT information.username FROM (SELECT username FROM guestInformation UNION ALL SELECT username FROM employeeInformation) AS information where username=?");
                pst.setString(1, username);
                rs = pst.executeQuery();
                while (rs.next()) {
                    hasDuplicateUsername = rs.getString("username");
                }
                //checks if username has already been taken
                if (hasDuplicateUsername == null) {
                    pst = con.prepareStatement("SELECT information.email FROM (SELECT email FROM guestInformation UNION ALL SELECT email FROM employeeInformation) AS information where email=?");
                    pst.setString(1, email);
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        hasDuplicateEmail = rs.getString("email");
                    }
                    //checks if email has already been taken
                    if (hasDuplicateEmail == null) {
                        if (username.length() <= 6) {
                            if (emailValidation(email)) {
                                //checks if the  password entered meets the requirements
                                if (phone.length() == 11 || phone.equals("")) {
                                    phone = "0";
                                    pRegisterContinue.setVisible(true);
                                    pRegister.setVisible(false);
                                } else {
                                    JOptionPane.showMessageDialog(this, "Invalid phone number.", "Failed!", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "Invalid email address.", "Failed!", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Username must not be more than 6 character", "Failed!", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Email has already been taken", "Failed!", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Username has already been taken", "Failed!", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all the required fields.", "Failed!", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unable to process your request. Please try again later.", "Failed!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void pLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pLoginMouseClicked

    }//GEN-LAST:event_pLoginMouseClicked

    private void lRegisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lRegisterMouseClicked
        clearTextField();
        pRegister.setVisible(true);
        pLogin.setVisible(false);
        rAdministrator.setVisible(false);
        rStaff.setVisible(false);
        tableName = "guestInformation";
        lblRSix.setText("    ");
        lblFCase.setText("    ");
        lblRDigit.setText("    ");
        lblFDigit.setText("    ");
        lblFCase.setText("    ");
        lblRCase.setText("    ");
    }//GEN-LAST:event_lRegisterMouseClicked

    private void rLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rLoginMouseClicked
        clearTextField();
        pLogin.setVisible(true);
        pRegister.setVisible(false);
    }//GEN-LAST:event_rLoginMouseClicked

    private void lForgotPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lForgotPasswordMouseClicked
        clearTextField();
        pLogin.setVisible(false);
        pForgotPassword.setVisible(true);
        lblRSix.setText("    ");
        lblFCase.setText("    ");
        lblRDigit.setText("    ");
        lblFDigit.setText("    ");
        lblFCase.setText("    ");
        lblRCase.setText("    ");
    }//GEN-LAST:event_lForgotPasswordMouseClicked

    private void fLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fLoginMouseClicked
        clearTextField();
        pLogin.setVisible(true);
        pForgotPassword.setVisible(false);
    }//GEN-LAST:event_fLoginMouseClicked

    private void rQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rQuestionActionPerformed

    }//GEN-LAST:event_rQuestionActionPerformed

    private void rFirstNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rFirstNameKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_rFirstNameKeyReleased

    private void jLabel27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseClicked
        pRegisterContinue.setVisible(false);
        pRegister.setVisible(true);


    }//GEN-LAST:event_jLabel27MouseClicked

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        if (isFromAdmin) {
            setVisible(false);
        } else {
            if (JOptionPane.showConfirmDialog(this, "Are you sure you want to close this program", "",
                    JOptionPane.YES_NO_OPTION) == 0) {
                System.exit(0);
            }
        }
    }//GEN-LAST:event_closeMouseClicked

    private void rShowPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rShowPasswordActionPerformed

        //toggling the visibility of the password
        if (rShowPassword.isSelected()) {
            rPassword.setEchoChar((char) 0);
            rConfirmPassword.setEchoChar((char) 0);
        } else {
            rPassword.setEchoChar('*');
            rConfirmPassword.setEchoChar('*');
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_rShowPasswordActionPerformed

    private void rPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rPasswordKeyReleased
        String password = rPassword.getText();
        passwordValidationEvent(password);
        // TODO add your handling code here:
    }//GEN-LAST:event_rPasswordKeyReleased

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
            java.util.logging.Logger.getLogger(Account.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Account.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Account.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Account.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Account().setVisible(true);
                // new UIManager().ManagerUI();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel asd;
    private javax.swing.JLabel bEmployee;
    private javax.swing.JLabel bGuest;
    private javax.swing.ButtonGroup bgEmployee;
    private javax.swing.JLabel close;
    private javax.swing.JLabel ds;
    private javax.swing.JLabel ds1;
    private javax.swing.JTextField fAnswer;
    private javax.swing.JButton fChangePassword;
    private javax.swing.JPasswordField fConfirmPassword;
    private javax.swing.JLabel fLogin;
    private javax.swing.JPasswordField fPassword;
    private javax.swing.JLabel fQuestion;
    private javax.swing.JCheckBox fShowPassword;
    private javax.swing.JTextField fUsername;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lForgotPassword;
    private javax.swing.JButton lLogin;
    private javax.swing.JPasswordField lPassword;
    private javax.swing.JLabel lRegister;
    private javax.swing.JCheckBox lShowPassword;
    private javax.swing.JTextField lUsername;
    private javax.swing.JLabel lblFCase;
    private javax.swing.JLabel lblFDigit;
    private javax.swing.JLabel lblFSix;
    private javax.swing.JLabel lblRCase;
    private javax.swing.JLabel lblRDigit;
    private javax.swing.JLabel lblRSix;
    private javax.swing.JPanel leftLine;
    private javax.swing.JPanel pForgotPassword;
    private javax.swing.JPanel pLogin;
    private javax.swing.JPanel pLogo;
    private javax.swing.JPanel pRegister;
    private javax.swing.JPanel pRegisterContinue;
    private javax.swing.JTextField rAddress;
    private javax.swing.JRadioButton rAdministrator;
    private javax.swing.JTextField rAnswer;
    private javax.swing.JPasswordField rConfirmPassword;
    private javax.swing.JTextField rEmail;
    private javax.swing.JTextField rFirstName;
    private javax.swing.JLabel rLogin;
    private javax.swing.JTextField rName;
    private javax.swing.JPasswordField rPassword;
    private javax.swing.JTextField rPhone;
    private javax.swing.JComboBox<String> rQuestion;
    private javax.swing.JButton rRegister;
    private javax.swing.JCheckBox rShowPassword;
    private javax.swing.JRadioButton rStaff;
    private javax.swing.JTextField rUsername;
    private javax.swing.JPanel rightLine;
    private javax.swing.JLabel sdasdasd;
    private javax.swing.JLabel sdasdasd1;
    // End of variables declaration//GEN-END:variables
}
