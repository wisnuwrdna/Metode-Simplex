package Hitung;

import WINDOW.Variabel;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 *
 * @author WISNU
 */
public class Clases extends javax.swing.JInternalFrame {

    /**
     * Creates new form Clases
     */
    int kisit = Variabel.DP_KISIT;
    int degisken = Variabel.DP_DEGISKEN;
    // MATRISLER
    public double Z[] = new double[degisken + kisit + 2];
    public double K[][] = new double[kisit + 1][degisken + kisit + 1];
    public int KDS[] = new int[kisit + 1]; //TEMELDEKİ DEĞİŞKENLER INDISI
    public String E[] = new String[kisit + 1];
    public double S[] = new double[kisit + 1];

    protected JTextField textField;
    protected JLabel etiket;
    protected JComboBox kombo;

    public Clases() {
        initComponents();
        
        
        setTitle("Kasus: " + Variabel.DP_PROBLEM+"");
        kisit = Variabel.DP_KISIT;
        degisken = Variabel.DP_DEGISKEN;
        jLabel1.setText(Variabel.DP_PROBLEM); // ETIKETIN SORUNA AYARLANMASI
        double tZ[] = new double[degisken + 1];
        double tK[][] = new double[kisit + 1][degisken + kisit + 1];
        String tE[] = new String[kisit + 1];
        double tS[] = new double[kisit + 1];
        String AmacD = "";
        if (Variabel.DP_DOSYADAN) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(Variabel.DP_PROBLEM));
                String line;
                int ik = 1;
                while ((line = in.readLine()) != null) {
                    String parts[] = line.split(";");
                    if (ik == 1) {

                    } else if (ik == 2) {
                        for (int i = 1; i <= degisken; i++) {
                            //System.out.println(parts[i]);
                            tZ[i] = Double.parseDouble(parts[i]);
                            AmacD = parts[0];
                        }
                    } else {
                        for (int i = 1; i <= degisken; i++) {
                            tK[ik - 2][i] = Double.parseDouble(parts[i]);
                        }
                        tE[ik - 2] = parts[degisken + 1];
                        tS[ik - 2] = Double.parseDouble(parts[degisken + 2]);
                    }
                    ik++;
                }
                in.close();
            } catch (IOException e) {

            }
        }
        int ent = 90;
        for (int i = 1; i <= degisken; i++) {
            textField = new JTextField();
            textField.setText("0.00");
            textField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            textField.setName("Z-" + i);
            jPanel1.add(textField, new org.netbeans.lib.awtextra.AbsoluteConstraints(ent, 40, 60, -1));
            etiket = new JLabel();
            etiket.setText("X" + i);
            if (tZ[i] != 0.0) {
                textField.setText(tZ[i] + "");
            }
            jPanel1.add(etiket, new org.netbeans.lib.awtextra.AbsoluteConstraints(ent + 25, 23, 60, -1));
            ent += 60;
        }
        ent = 90;
        int ust = 68;
        for (int i = 1; i <= kisit; i++) {
            for (int k = 1; k <= degisken; k++) {
               
                textField = new JTextField();
                textField.setText("0.00");
                textField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                textField.setName("KD-" + i + "-" + k);
                if (tK[i][k] != 0.0) {
                    textField.setText(tK[i][k] + "");
                }
                jPanel1.add(textField, new org.netbeans.lib.awtextra.AbsoluteConstraints(ent, ust, 60, -1));
                ent += 60;
            }
           

            kombo = new JComboBox();
            if ("<=".equals(tE[i])) {
                kombo.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"<=", ">=", "="}));
            } else if (">=".equals(tE[i])) {
                kombo.setModel(new javax.swing.DefaultComboBoxModel(new String[]{">=", "<=", "="}));
            } else if ("=".equals(tE[i])) {
                kombo.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"=", "<=", ">="}));
            } else {
                kombo.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"<=", ">=", "="}));
            }
            if ("Min".equals(AmacD)) {
                jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Min", "Max"}));
            } else {
                jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Max", "Min"}));
            }
            kombo.setName("KE-" + i + "");
            jPanel1.add(kombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(ent, ust, 60, -1));
            
            textField = new JTextField();
            textField.setText("0.00");
            textField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            textField.setName("KS-" + i);
            if (tS[i] != 0.0) {
                textField.setText(tS[i] + "");
            }
            jPanel1.add(textField, new org.netbeans.lib.awtextra.AbsoluteConstraints(ent + 63, ust, 60, -1));

            etiket = new JLabel();
            etiket.setText("Const " + i);
            jPanel1.add(etiket, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, ust + 6, 60, -1));

            ent = 90;
            ust += 27;
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setVisible(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("KASUS");
        jLabel1.setName("soruetiket"); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 120, 30));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("FUNGSI"));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Max", "Min" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 80, 30));
        jComboBox1.getAccessibleContext().setAccessibleName("");

        jButton1.setBackground(new java.awt.Color(153, 153, 255));
        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton1.setText("HITUNG");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, -1, 30));

        jScrollPane1.setViewportView(jPanel1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 440, 210));

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jTable1);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 273, 810, 280));

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setBorder(javax.swing.BorderFactory.createTitledBorder("HASIL"));
        jScrollPane2.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(512, 50, 350, 210));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 580));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public double yuvarla(double x) {
        return (double) Math.round((x * 100)) / 100;
    }

    public void hata(String mesaj) {
        JOptionPane.showMessageDialog(this, mesaj, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void matrisyukle() {
        Component[] cList = jPanel1.getComponents();
        for (int x = 0; x < cList.length; x++) {
            if (cList[x] instanceof javax.swing.JTextField) {
                String value = ((javax.swing.JTextField) cList[x]).getName();
                String[] parts = value.split("-");
                if (parts[0].contains("Z")) {
                    int t = Integer.parseInt(parts[1]);
                    Z[t] = -1 * Double.parseDouble(((javax.swing.JTextField) cList[x]).getText());
                }
                if (parts[0].contains("KD")) {
                    int k = Integer.parseInt(parts[1]);
                    int d = Integer.parseInt(parts[2]);
                    K[k][d] = Double.parseDouble(((javax.swing.JTextField) cList[x]).getText());
                }
                if (parts[0].contains("KS")) {
                    int k = Integer.parseInt(parts[1]);
                    S[k] = Double.parseDouble(((javax.swing.JTextField) cList[x]).getText());
                    KDS[k] = degisken + k;
                }
            }
            if (cList[x] instanceof javax.swing.JComboBox) {
                String value = ((javax.swing.JComboBox) cList[x]).getName();
                if (value != null) {
                    String[] parts = value.split("-");
                    if (parts[0].contains("KE")) {
                        int k = Integer.parseInt(parts[1]);
                        E[k] = ((javax.swing.JComboBox) cList[x]).getSelectedItem().toString();
                    }
                }
            }
        }
    }

    public void metin_ekle(String x) {
        jTextArea1.setText(jTextArea1.getText() + x + "\n");
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        matrisyukle();
       
        String Amac = jComboBox1.getSelectedItem().toString();
        jTable1.setModel(new DefaultTableModel());
        jTextArea1.setText("");
        boolean Max = false;
        for (int k = 1; k <= kisit; k++) {
            if (E[k] != "<=") {
                hata("OPERASI SALAH");
                return;
            }
            if (S[k] < 0) {
                hata("MATRIZ (B) >=0");
                return;
            }
        }
        if (Amac == "Max") {
            Max = true;
        } else if (Amac == "Min") {
            Max = false;
        }
        // DOLĞU DEĞİŞKENLERİNİN EKLENMESİ
        for (int i = 1; i <= kisit; i++) {
            for (int k = 1; k <= kisit; k++) {
                if (i == k) {
                    K[i][degisken + k] = 1.00;

                } else {
                    K[i][degisken + k] = 0.00;
                }
            }
            Z[degisken + i] = 0.00;
        }
     
        Z[kisit + degisken + 1] = 0.0;
      
        boolean optimal = false;
        int itr = 1;

      
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTable1.setDefaultRenderer(String.class, centerRenderer);

        DefaultTableModel dtm;
        dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column < 0;
            }
        };
        jTable1.setModel(dtm);
        dtm.addColumn("VAR");
        for (int k = 1; k <= (degisken + kisit); k++) {
            dtm.addColumn("X" + k);
        }

        dtm.addColumn("SOL(B)");
        for (int i = 0; i <= (degisken + kisit + 1); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        do {
            Vector rowx = new Vector();
            rowx.add(itr + " Literasi");
            dtm.addRow(rowx);
            Vector row = new Vector();
            row.add("Z");
            for (int k = 1; k <= (degisken + kisit + 1); k++) {
                row.add(yuvarla(Z[k]));
                dtm.isCellEditable(1, k);
            }
            dtm.addRow(row);
            for (int i = 1; i <= (kisit); i++) {
                Vector row2 = new Vector();
                row2.add("X" + KDS[i]);
                for (int k = 1; k <= (degisken + kisit); k++) {
                    row2.add(yuvarla(K[i][k]));
                    dtm.isCellEditable(i, k);
                }
                row2.add(yuvarla(S[i]));
                dtm.addRow(row2);
            }

            if (Max) {
               
                int PC = 0; 
                double PCV = 0; 
                for (int c = 1; c <= (degisken + kisit); c++) {
                    boolean temeldemi = false;
                    for (int i = 1; i <= kisit; i++) {
                        if (temeldemi) {

                        } else {
                            if (KDS[i] == c) {
                                temeldemi = true;
                            }
                        }
                    }
                    double salt = 0 - Z[c];
                    if ((salt > PCV) && (!temeldemi)) {
                        PCV = salt;
                        PC = c;
                    }
                }
                int PR = 0;
                double PRV = 0;
                for (int k = 1; k <= kisit; k++) {
                    double salt = S[k] / K[k][PC];
                    if (k == 1) {
                        PRV = salt;
                    }
                    if ((salt > 0) && (salt <= PRV)) {
                        PRV = salt;
                        PR = k;
                        
                    }
                }
                
                System.out.println(PR + "-" + PC);
                if (PC == 0) {
                    optimal = true;
                    metin_ekle("Itr: " + itr);
                    metin_ekle("Z = " + yuvarla(Z[degisken + kisit + 1]));
                    for (int k = 1; k <= kisit; k++) {
                        metin_ekle("X" + KDS[k] + " = " + yuvarla(S[k]));
                    }
                }

                if (!optimal) { 
                    KDS[PR] = PC;
                    
                    double pivot = 0.00;
                    for (int k = 1; k <= (degisken + kisit); k++) {
                        if (k == 1) {
                            pivot = K[PR][PC];
                        }
                        double hucre = (K[PR][k] / pivot);
                        K[PR][k] = hucre;
                    }
                    S[PR] = S[PR] / pivot;
                    
                    for (int i = 1; i <= (kisit); i++) {
                        if (i != PR) {
                            pivot = K[i][PC];
                            for (int k = 1; k <= degisken + kisit; k++) {
                                K[i][k] = K[i][k] - (pivot * K[PR][k]);
                            }
                            S[i] = S[i] - (pivot * S[PR]);
                        }
                    }
                  
                    pivot = Z[PC];
                    for (int k = 1; k <= (degisken + kisit); k++) {
                        Z[k] = Z[k] - (pivot * K[PR][k]);
                    }
                 
                    int cz = degisken + kisit + 1;
                    Z[cz] = Z[cz] - pivot * S[PR];
                }
            }
            if (!Max) {
              
                int PC = 0;
                double PCV = 0; // PRIMARY KOLON Z SATIR DEĞERİ
                for (int c = 1; c <= (degisken + kisit); c++) {
                    boolean temeldemi = false;
                    for (int i = 1; i <= kisit; i++) {
                        if (temeldemi) {

                        } else {
                            if (KDS[i] == c) {
                                temeldemi = true;
                            }
                        }
                    }
                    double salt = Z[c];
                    if ((salt > PCV) && (!temeldemi)) {
                        PCV = salt;
                        PC = c;
                    }
                }
                int PR = 0;
                double PRV = 0;
                for (int k = 1; k <= kisit; k++) {
                    double salt = S[k] / K[k][PC];
                    if (k == 1) {
                        PRV = salt;
                    }
                    if ((salt > 0) && (salt <= PRV)) {
                        PRV = salt;
                        PR = k;
                    }
                    
                }
               
                if (PC == 0) {
                    optimal = true;
                }
                if (PC == 0) {
                    optimal = true;
                    metin_ekle("Itr: " + itr);
                    metin_ekle("Z = " + yuvarla(Z[degisken + kisit + 1]));
                    for (int k = 1; k <= kisit; k++) {
                        metin_ekle("X" + KDS[k] + " = " + yuvarla(S[k]));
                    }
                }
                if (!optimal) { 
                    KDS[PR] = PC;
                  
                    double pivot = 0.00;
                    for (int k = 1; k <= (degisken + kisit); k++) {
                        if (k == 1) {
                            pivot = K[PR][PC];
                        }
                        double hucre = (K[PR][k] / pivot);
                        K[PR][k] = hucre;
                    }
                    S[PR] = S[PR] / pivot;
            
                    for (int i = 1; i <= (kisit); i++) {
                        if (i != PR) {
                            pivot = K[i][PC];
                            for (int k = 1; k <= degisken + kisit; k++) {
                                K[i][k] = K[i][k] - (pivot * K[PR][k]);
                            }
                            S[i] = S[i] - (pivot * S[PR]);
                        }
                    }
             
                    pivot = Z[PC];
                    for (int k = 1; k <= (degisken + kisit); k++) {
                        Z[k] = Z[k] - (pivot * K[PR][k]);
                    }
         
                    int cz = degisken + kisit + 1;
                    Z[cz] = Z[cz] - pivot * S[PR];
                }
            }
            itr++;
        } while (!optimal);
        

    }//GEN-LAST:event_jButton1ActionPerformed
    public String bosluk(int x) {
        String rtr = "";
        for (int i = 1; i <= x; i++) {
            rtr = rtr + " ";
        }
        return rtr;
    }

    public void dyaz(String metin, String dosya) {
        try {
            String content = metin;

            File file = new File(dosya);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
      
    }//GEN-LAST:event_jComboBox1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
