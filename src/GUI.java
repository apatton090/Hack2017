import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    /*
     * public static void main(String[] args) { EventQueue.invokeLater(new
     * Runnable() {
     * 
     * @Override public void run() { try { GUI frame = new GUI();
     * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
     * }); }
     */

    /**
     * Create the frame.
     */
    public GUI() {
        this.buildGUI();
    }

    private void buildGUI() {//Builds each component of the GUI

        //Creates the JFrame window

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);//clicking the x closes the window
        this.contentPane = new JPanel();
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.contentPane.setLayout(new BorderLayout(0, 0));
        this.setContentPane(this.contentPane);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(0, 0, screenSize.width, screenSize.height);
        this.setVisible(true);
        this.pack();
        this.setSize(screenSize.width / 2, screenSize.height / 2);//resize

        this.setLocationRelativeTo(null);//center on screen
        this.setResizable(false);
        this.setTitle("JAD Compression");//window title

        //Creates the user input text field
        final JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Calibri", Font.BOLD, 20));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        this.add(new JScrollPane(textArea), BorderLayout.CENTER);

        //Creates the menu bar of the JFrame window

        //menuBar - Menu Bar
        JMenuBar menuBar = new JMenuBar();

        //mnFile - Menu Bar - File Menu
        JMenu mnFile = new JMenu("File");

        //mntmSave - Menu Bar - File > Save - saves textArea to file
        JMenuItem mntmSave = new JMenuItem("Save");
        mntmSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setApproveButtonText("Save");
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    try {
                        File fileName = new File(
                                fileChooser.getSelectedFile() + ".txt");
                        FileWriter writer = new FileWriter(fileName);
                        BufferedWriter bw = new BufferedWriter(writer);
                        textArea.write(bw);
                        bw.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    textArea.requestFocus();
                }
            }
        });

        //mntmImport - Menu Bar - File > Import... - imports text file into textArea
        JMenuItem mntmImport = new JMenuItem("Import...");
        mntmImport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    try {
                        File selectedFile = fileChooser.getSelectedFile();
                        FileReader reader = new FileReader(selectedFile);
                        BufferedReader br = new BufferedReader(reader);
                        textArea.read(br, null);
                        br.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    textArea.requestFocus();
                }
            }
        });

        //Add all menu components
        mnFile.add(mntmSave);
        mnFile.add(mntmImport);
        menuBar.add(mnFile);

        //Set this menu bar in the current frame
        this.setJMenuBar(menuBar);

    }

}
