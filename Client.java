//Client
import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class Client extends JFrame
{
Socket s;
BufferedReader br; 
PrintWriter out;
//declaring components of gui
private JLabel heading = new JLabel("Client Area");
private JTextArea messagebox = new JTextArea();
private JTextField messageInput= new JTextField();
private Font font = new Font("Roboto",Font.PLAIN,20);



public Client()
{
try{
System.out.println("sending request to server");

s=new Socket("127.0.0.1",7777);

System.out.println("connection done");
br = new BufferedReader(new InputStreamReader(s.getInputStream()));
out = new PrintWriter(s.getOutputStream());
createGUI();
handleEvents();
startReading();
//startWriting();
}catch(Exception e)
{e.printStackTrace();}


}
private void handleEvents()
{
messageInput.addKeyListener(new KeyListener(){
@Override
public void keyTyped(KeyEvent e){}
@Override
public void keyPressed(KeyEvent e){}
@Override
public void keyReleased(KeyEvent e){

    if(e.getKeyCode()==10){
        String contenttosend = messageInput.getText();
        messagebox.append("me:"+contenttosend+"\n");
        out.println(contenttosend);
        out.flush();
        messageInput.setText("");
        messageInput.requestFocus();
        if(contenttosend.equals("exit"))
        {messageInput.setEnabled(false);}
    }
}
});


}



private void createGUI()
{
this.setTitle("Client Messanger[END]");
this.setSize(500,500);
this.setLocationRelativeTo(null);
this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//coding for componetnts
heading.setFont(font);
messagebox.setFont(font);
messageInput.setFont(font);
messagebox.setEditable(false);

//jframe ka layut set kre
this.setLayout(new BorderLayout());



//aadding components to jframe
this.add(heading,BorderLayout.NORTH);
JScrollPane pane = new JScrollPane(messagebox);
this.add(pane,BorderLayout.CENTER);
this.add(messageInput,BorderLayout.SOUTH);


this.setVisible(true);

}

public void startReading()
{

Runnable r1=()->{
try{
while(true)
{
String msg = br.readLine();
if(msg.equals("exit"))
{System.out.println("server terminated");
messagebox.append("server: left the chat"+"\n");
messageInput.setEnabled(false);
s.close();
break;
}
System.out.println("server:"+msg);
messagebox.append("server:"+msg+"\n");
}

}catch(Exception e)
{//e.printStackTrace();
System.out.println("conection closed");
}

};
new Thread(r1).start();

}

//startwriting is put as comment during its call as this is only for console purpose in gui eventhandeler is doing the job

public void startWriting()
{
Runnable r2=()->{
try{
while(!s.isClosed())
{

BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
String content = br1.readLine();
out.println(content);
out.flush();
if(content.equals("exit"))
{
s.close();
break;
}

}
}catch(Exception e)
{e.printStackTrace();}

};
new Thread(r2).start();

}



public static void main(String args[])
{
System.out.println("this is client side");

new Client();



}





}