//java -cp .;"C:\Users\PAWAN KUMAR\OneDrive\Desktop\java\mysql-connector-j-8.0.32.jar" JavaProject

import java.sql.*;
import java.sql.*;
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JavaProject {
    public static void main(String[] args) {
//Declaring all the frames
        JFrame firstFrame = new JFrame("welcome page");
        JFrame secondFrame = new JFrame("login page");
        JFrame thirdFrame = new JFrame("SignUp page");
        JFrame fourthFrame = new JFrame("Home page");

 // Database connection parameters
          final String DB_URL = "jdbc:mysql://localhost:3306/dbtest1";
          final String DB_USER = "root";
          final String DB_PASSWORD = "7550pawan@";

 // Declare signup page components components
    JLabel nameLabel, yearLabel, branchLabel, genderLabel, ageLabel, passLabel;
    JTextField nameField, yearField, branchField, ageField, passField;
    JComboBox<String> genderComboBox;
    JButton submitButton;
 
// Set signupframe properties
        thirdFrame.setTitle("Registration Form");
        thirdFrame.setSize(400, 300);
        thirdFrame.setLocationRelativeTo(null);
        thirdFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




 // Initialize signup form components
        nameLabel = new JLabel("Name:");
        yearLabel = new JLabel("Current Year:");
        branchLabel = new JLabel("Branch:");
        genderLabel = new JLabel("Gender:");
        ageLabel = new JLabel("Age:");
        passLabel = new JLabel("password:");

        nameField = new JTextField();
        yearField = new JTextField();
        branchField = new JTextField();
        ageField = new JTextField();
        passField = new JTextField();

        genderComboBox = new JComboBox<String>();
        genderComboBox.addItem("Male");
        genderComboBox.addItem("Female");
        genderComboBox.addItem("Other");

        submitButton = new JButton("Submit");
        
 // Add form components to layout
        JPanel formPanel = new JPanel(new GridLayout(7, 2));
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(yearLabel);
        formPanel.add(yearField);
        formPanel.add(branchLabel);
        formPanel.add(branchField);
        formPanel.add(genderLabel);
        formPanel.add(genderComboBox);
        formPanel.add(ageLabel);
        formPanel.add(ageField);
        formPanel.add(passLabel);
        formPanel.add(passField);
        formPanel.add(submitButton);
        thirdFrame.add(formPanel);

        thirdFrame.setVisible(false);



// workin on login window
// declaring elements

    final long serialVersionUID = 1L;
    JLabel name1Label, passwordLabel, statusLabel;
    JTextField name1TextField;
    JPasswordField passwordTextField;
    JButton loginButton;


// setting layout and adding panel to login window

        secondFrame.setTitle("Login window");
        secondFrame.setSize(400, 300);
        secondFrame.setLocationRelativeTo(null);
        secondFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel loginpanel = new JPanel(new GridLayout(3, 2));
        secondFrame.add(loginpanel);
// initiailising the login window elements
        name1Label = new JLabel("Name:");
        loginpanel.add(name1Label);
        name1TextField = new JTextField(20);
        loginpanel.add(name1TextField);

        passwordLabel = new JLabel("Password:");
        loginpanel.add(passwordLabel);
        passwordTextField = new JPasswordField(20);
        loginpanel.add(passwordTextField);

        loginButton = new JButton("Login");
        loginpanel.add(loginButton);

        statusLabel = new JLabel();
        loginpanel.add(statusLabel);

        secondFrame.setVisible(false);


// adding action to login button of login window
loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                   
                    // Get form field values
            String name = name1TextField.getText();
            String password = String.valueOf(passwordTextField.getPassword());

                    // connecting to database and checking the validity
try {
                // Connect to the MySQL database
                
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbtest1", "root", "7550pawan@");
                Statement stmt = con.createStatement();
                
                // Check if name and password exist in the students table
                String query = "SELECT * FROM students WHERE name = '" + name + "' AND pass = '" + password + "'";
                ResultSet rs = stmt.executeQuery(query);
                
                // If both name and password are found, display "Login Successful"
                if (rs.next()) {
                    statusLabel.setText("Login Successful");
                    secondFrame.setVisible(false);
                    fourthFrame.setVisible(true);
                } 
                // If name and/or password are not found, display an error message
                else {
                    statusLabel.setText("Invalid Name or Password");
                    JOptionPane.showMessageDialog(null,"Invalid Name or Password!");
                    
                }
                
                // Close the connection to the database
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Error: " + ex.getMessage());
            }

           }
        });

// adding action to submit button of signup form
       submitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                   
                    // Get form field values
            String name = nameField.getText();
            String year = yearField.getText();
            String branch = branchField.getText();
            String gender = (String) genderComboBox.getSelectedItem();
            String age = ageField.getText();
            String pass = passField.getText();

                    // Insert values into database
            try (
                
                Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String query = "INSERT INTO students (name, year, branch, gender, age, pass) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, name);
                stmt.setString(2, year);
                stmt.setString(3, branch);
                stmt.setString(4, gender);
                stmt.setString(5, age);
                stmt.setString(6, pass);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null,"Registration successful!");
                stmt.close();
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Error: " + ex.getMessage());
            }
                  thirdFrame.setVisible(false);
                  secondFrame.setVisible(true);
           }
        });


// workin on welcome window
// declaring elements and initializing
       JLabel wellcome = new JLabel("wellcome to the chatting app made by:");
       JLabel name1 = new JLabel("PAWAN 12108258");
       JLabel name2 = new JLabel("SHASHWAT 12110961");
       JLabel name3 = new JLabel("MUKESH 12101037");
       JLabel name4 = new JLabel("Click on either of the buttons to proceed");
       JButton signupbutton = new JButton("Signup");
       JButton login1button = new JButton("login");




// setting layout and adding panel to welcome window

        firstFrame.setTitle("wellcome window");
        firstFrame.setSize(600, 600);
        firstFrame.setLocationRelativeTo(null);
        firstFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel homepanel = new JPanel(new GridLayout(7, 1));
        homepanel.add(wellcome);
        homepanel.add(name1);
        homepanel.add(name2);
        homepanel.add(name3);

        homepanel.add(name4);
        homepanel.add(signupbutton);
        homepanel.add(login1button);

        firstFrame.add(homepanel);

        
// adding action to buttons of home window
     
        signupbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 firstFrame.setVisible(false);
                thirdFrame.setVisible(true);
            }
        });

        login1button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 firstFrame.setVisible(false);
                secondFrame.setVisible(true);
            }
        });


//working on home window 
//declaring elements and initializing

    JComboBox<String> filterCombo;
    JTextField searchField;
    JTextArea resultArea;
    JButton searchButton;
        filterCombo = new JComboBox<>(new String[] {"Name", "Branch", "Year"});
        
        searchField = new JTextField();
        
        searchButton = new JButton("Search");
        
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);


// setting layout and adding panel to home window
        fourthFrame.setTitle("HOME");
        fourthFrame.setSize(800, 800);
        fourthFrame.setLocationRelativeTo(null);
        fourthFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel workpanel = new JPanel(new GridLayout(6, 1));
        workpanel.add(new JLabel("Filter by:"));
        workpanel.add(filterCombo);
        workpanel.add(searchField);
        workpanel.add(searchButton);
        workpanel.add(resultArea);
        workpanel.add(scrollPane);
        fourthFrame.add(workpanel);



//adding action to search button of home window

searchButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                   
                    // Get field values
            String filter = filterCombo.getSelectedItem().toString();
            String searchText = searchField.getText();
            String query = "SELECT name FROM students WHERE " + filter.toLowerCase() + " LIKE '%" + searchText + "%'";

                    // Insert values into database
            try {
                 Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbtest1", "root", "7550pawan@");
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query); 

                resultArea.setText(""); // clear previous results

                while (resultSet.next()) {
                    resultArea.append(resultSet.getString(1) + "\n");
                }

                if (resultArea.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No matching records found.");
                }
                connection.close();
                statement.close();
            }catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Error: " + ex.getMessage());
            }
                 
           }
        });


        



        firstFrame.setSize(600, 600);
        firstFrame.setVisible(true);
    }
}