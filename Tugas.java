/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

// Import library yang dibutuhkan
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author alvarez
 */

/**
 * Kelas Tugas merupakan kelas utama yang mengimplementasikan GUI untuk aplikasi biodata.
 */
public class Tugas extends JFrame {
    private TableModelLatihan tableModel; // Model untuk data tabel

    /**
     * Konstruktor kelas Tugas, inisialisasi GUI dan komponen-komponennya.
     */
    public Tugas() {
        // Pengaturan frame utama
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Aplikasi Biodata");

        // Label judul header
        JLabel headerLabel = new JLabel("Form Biodata", JLabel.CENTER);
        headerLabel.setBounds(0, 20, 500, 10);

        // Label dan input field untuk nama
        JLabel labelNama = new JLabel("Nama: ");
        labelNama.setBounds(15, 40, 350, 10);
        JTextField textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 500, 30);
        
        // Menambahkan KeyListener untuk memeriksa input nama
        textFieldNama.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
                char character = e.getKeyChar();
                if (!Character.isLetter(character) && character != KeyEvent.VK_SPACE) {
                    e.consume(); // Mencegah karakter non-huruf dimasukkan
                    JOptionPane.showMessageDialog(Tugas.this, "Hanya huruf yang diizinkan dalam nama!",
                    "Input Tidak Valid", JOptionPane.WARNING_MESSAGE);
                }
            }

            public void keyPressed(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
            }
        });

        // Label dan input field untuk nomor telepon
        JLabel labelTelp = new JLabel("Nomor Telepon: ");
        labelTelp.setBounds(15, 100, 350, 10);
        JTextField textFieldTelp = new JTextField();
        textFieldTelp.setBounds(15, 120, 500, 30);
        
        // Menambahkan KeyAdapter untuk memeriksa input nomor telepon
        textFieldTelp.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                char ch = ke.getKeyChar();
                if (Character.isDigit(ch) || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    textFieldTelp.setEditable(true);
                } else {
                    textFieldTelp.setEditable(false);
                    JOptionPane.showMessageDialog(Tugas.this, "Hanya angka yang diizinkan dalam nomor telepon!",
                    "Input Tidak Valid", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Label dan radio button untuk jenis kelamin
        JLabel labelRadio = new JLabel("Jenis Kelamin: ");
        labelRadio.setBounds(15, 160, 350, 10);
        JRadioButton radioButton1 = new JRadioButton("Laki-Laki", true);
        radioButton1.setBounds(15, 170, 350, 30);
        JRadioButton radioButton2 = new JRadioButton("Perempuan");
        radioButton2.setBounds(15, 195, 350, 30);
        // Grup untuk mengelompokkan radio button
        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton1);
        bg.add(radioButton2);

        // Label dan input field untuk alamat
        JLabel labelAlamat = new JLabel("Alamat: ");
        labelAlamat.setBounds(15, 230, 450, 10);
        JTextArea textAreaAlamat = new JTextArea();
        textAreaAlamat.setBounds(15, 250, 500, 100);

        // Tombol Simpan data
        JButton simpanButton = new JButton("Simpan");
        simpanButton.setBounds(15, 360, 100, 40);

        // Tabel untuk menampilkan data
        javax.swing.JTable table = new JTable();
        tableModel = new TableModelLatihan(); // Inisialisasi model tabel
        table.setModel(tableModel); // Menghubungkan tabel dengan model

        // Scroll pane untuk tabel
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 410, 500, 150);
        
        // Tombol Edit data
        JButton buttonEdit = new JButton("Edit");
        buttonEdit.setBounds(130, 360, 100, 40);

        // Tombol Hapus data
        JButton hapusButton = new JButton("Hapus");
        hapusButton.setBounds(245, 360, 100, 40);        
        
        // Tombol Simpan File
        JButton simpanFileButton = new JButton("Save File");
        simpanFileButton.setBounds(15, 570, 100, 40);
        
        // Tombol Keluar Aplikasi
        JButton buttonExit = new JButton("Exit");
        buttonExit.setBounds(130,570,100,40);

        // ActionListener untuk tombol Simpan data
        simpanButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mendapatkan data dari input fields
                String jenisKelamin = "";
                String nama = textFieldNama.getText();
                String telepon = textFieldTelp.getText();
                String alamat = textAreaAlamat.getText();

                // Memeriksa apakah semua kolom input terisi
                if (nama.isEmpty() || telepon.isEmpty() || alamat.isEmpty() || (!radioButton1.isSelected() && !radioButton2.isSelected())) {
                    JOptionPane.showMessageDialog(Tugas.this, "Harap isi semua kolom input!");
                } else {
                    // Memeriksa jenis kelamin yang dipilih
                    if (radioButton1.isSelected()) {
                        jenisKelamin = radioButton1.getText();
                    } else if (radioButton2.isSelected()) {
                        jenisKelamin = radioButton2.getText();
                    }
                    // Menampilkan konfirmasi sebelum menyimpan data
                    int confirmation = JOptionPane.showConfirmDialog(Tugas.this,
                            "Apakah Anda yakin ingin menyimpan data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (confirmation == JOptionPane.YES_OPTION) {
                        // Menambahkan data ke tabel
                        tableModel.add(new ArrayList<>(Arrays.asList(nama, telepon, jenisKelamin, alamat)));
                        // Mengosongkan input fields
                        textFieldNama.setText("");
                        textFieldTelp.setText("");
                        radioButton1.setSelected(true);
                        radioButton2.setSelected(false);
                        textAreaAlamat.setText("");
                        JOptionPane.showMessageDialog(Tugas.this, "Data telah disimpan!");
                    }
                }
            }
        });

        // ActionListener untuk tombol Edit data
        buttonEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Mendapatkan data dari baris yang dipilih
                    ArrayList<String> rowData = tableModel.getData().get(selectedRow);
                    textFieldNama.setText(rowData.get(0));
                    textFieldTelp.setText(rowData.get(1));
                    // Memeriksa jenis kelamin yang dipilih
                    if (rowData.get(2).equals("Laki-Laki")) {
                        radioButton1.setSelected(true);
                        radioButton2.setSelected(false);
                    } else {
                        radioButton1.setSelected(false);
                        radioButton2.setSelected(true);
                    }
                    textAreaAlamat.setText(rowData.get(3));
                    // Menghapus data dari tabel
                    tableModel.remove(selectedRow);
                    JOptionPane.showMessageDialog(Tugas.this, "Data telah diedit!");
                } else {
                    JOptionPane.showMessageDialog(Tugas.this, "Pilih baris yang akan diedit!");
                }
            }
        });

        // ActionListener untuk tombol Hapus data
        hapusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Menampilkan konfirmasi sebelum menghapus data
                    int confirmation = JOptionPane.showConfirmDialog(Tugas.this,
                    "Apakah Anda yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (confirmation == JOptionPane.YES_OPTION) {
                        // Menghapus data dari tabel
                        tableModel.remove(selectedRow);
                        JOptionPane.showMessageDialog(Tugas.this, "Data telah dihapus!");
                    }
                } else {
                    JOptionPane.showMessageDialog(Tugas.this, "Pilih baris yang akan dihapus!");
                }
            }
        });
        
        // Menambahkan ActionListener untuk tombol "Exit"
        buttonExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Menampilkan konfirmasi sebelum keluar dari aplikasi
                int confirmation = JOptionPane.showConfirmDialog(Tugas.this,
                        "Apakah anda yakin ingin keluar dari aplikasi?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    System.exit(0);
                } else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
        
        // ActionListener untuk tombol Simpan ke File
        simpanFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveDataToFile(); // Menyimpan data ke file
                JOptionPane.showMessageDialog(Tugas.this, "Data telah disimpan ke file!");
            }
        });
        
        // Menambahkan windows listener untuk memunculkan dialog ketika keluar aplikasi
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(Tugas.this,
                        "Apakah anda yakin ingin keluar dari aplikasi?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    dispose(); // Menutup window
                } else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
        
        // Menambahkan komponen-komponen ke frame
        this.add(headerLabel);
        this.add(labelNama);
        this.add(textFieldNama);
        this.add(labelTelp);
        this.add(textFieldTelp);
        this.add(labelRadio);
        this.add(radioButton1);
        this.add(radioButton2);
        this.add(labelAlamat);
        this.add(textAreaAlamat);
        this.add(simpanButton);
        this.add(scrollableTable);
        this.add(buttonEdit);
        this.add(hapusButton);
        this.add(buttonExit);
        this.add(simpanFileButton);

        // Mengatur ukuran dan layout frame
        this.setSize(550, 700);
        this.setLayout(null);
        this.setVisible(true);
    }

    // Metode untuk menyimpan data ke file.
    private void saveDataToFile() {
        String filePath = "C:/Users/fowaz/Documents/data.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (ArrayList<String> rowData : tableModel.getData()) {
                String line = String.join(", ", rowData);
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metode utama (entry point) untuk menjalankan aplikasi biodata.
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable () {
            public void run() {
                // Membuat objek Tugas dan menampilkannya
                Tugas h = new Tugas();
                h.setVisible(true);
            }
        });
    }
}