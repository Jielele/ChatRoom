import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Login extends JFrame implements ActionListener{
    private JPanel jpl=new JPanel();
    private JLabel jbl1=new JLabel("用户登录"),jbl2=new JLabel("用户名"), jbl3=new JLabel(" 密码 "),
            jbl4 =new JLabel("用户注册"),jbl5 =new JLabel(" 用户名 "), jbl6 =new JLabel("设置密码"),
            jbl7 =new JLabel("确认密码");

    private JButton jb1=new JButton("登录"),jb2=new JButton("注册"),jb3=new JButton("提交"),jb4=new JButton("返回");;
    private JTextField jtf1 =new JTextField(),jtf2=new JTextField();
    private JPasswordField jpw1=new JPasswordField(),jpw2=new JPasswordField(), jpw3 =new JPasswordField();
    /**注意:
     * 登录界面单独会用到的有jpl1,jpl2,jpl3       jb1,jb2  jtf1,jpw1
     * 注册界面单独会用到的有jpl4,jpl5,jpl6,jpl7  jb3,jb4  jtf2,jpw2,jpw2
     */

    static String name;

    /**
     * 初始化界面
     */
    public Login(){
        this.setTitle("Be a happy person");
        this.add(jpl);jpl.setLayout(null);this.setVisible(true);
        this.setSize(500,320);this.setResizable(false);//不可以改变布局大小
        this.setLocationRelativeTo(null);this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //设置各类字体
        Font font1=new Font("楷体",Font.BOLD,30);
        Font font2=new Font("楷体",Font.BOLD,20);
        Font font3=new Font("宋体",Font.BOLD,20);

        //用户登录
        jpl.add(jbl1);jbl1.setLocation(185,20);jbl1.setSize(200,50);jbl1.setFont(font1);
        //输入用户名
        jpl.add(jbl2);jbl2.setLocation(80,80);jbl2.setSize(100,30);jbl2.setFont(font2);
        jpl.add(jtf1);jtf1.setLocation(150,80);jtf1.setSize(250,30);jtf1.setFont(font3);
        //输入密码
        jpl.add(jbl3);jbl3.setLocation(80,130);jbl3.setSize(100,30);jbl3.setFont(font2);
        jpl.add(jpw1);jpw1.setLocation(150,130);jpw1.setSize(250,30);jpw1.setFont(font3);
        //添加登录键和注册键
        jpl.add(jb1);jb1.setLocation(130,180);jb1.setSize(100,40);jb1.setFont(font2);
        jpl.add(jb2);jb2.setLocation(270,180);jb2.setSize(100,40);jb2.setFont(font2);

        //用户注册
        jpl.add(jbl4);jbl4.setLocation(185,20);jbl4.setSize(200,50);jbl4.setFont(font1);
        //添加用户名
        jpl.add(jbl5);jbl5.setLocation(50,80);jbl5.setSize(125,30);jbl5.setFont(font2);
        jpl.add(jtf2);jtf2.setLocation(150,80);jtf2.setSize(250,30);jtf2.setFont(font3);
        //添加设置密码，第一次输入密码
        jpl.add(jbl6);jbl6.setLocation(50,130);jbl6.setSize(125,30);jbl6.setFont(font2);
        jpl.add(jpw2);jpw2.setLocation(150,130);jpw2.setSize(250,30);jpw2.setFont(font3);
        //添加确认密码，第二次输入密码
        jpl.add(jbl7);jbl7.setLocation(50,180);jbl7.setSize(125,30);jbl7.setFont(font2);
        jpl.add(jpw3);jpw3.setLocation(150,180);jpw3.setSize(250,30);jpw3.setFont(font3);
        //添加提交键和返回键
        jpl.add(jb3);jb3.setLocation(130,220);jb3.setSize(100,40);jb3.setFont(font2);
        jpl.add(jb4);jb4.setLocation(270,220);jb4.setSize(100,40);jb4.setFont(font2);

        //设置初始可视化
        //登录界面可见
        jbl1.setVisible(true);jbl2.setVisible(true);jbl3.setVisible(true);
        jb1.setVisible(true);jb2.setVisible(true);
        jtf1.setVisible(true);jpw1.setVisible(true);
        //注册界面不可见
        jbl4.setVisible(false);jbl5.setVisible(false);jbl6.setVisible(false);jbl7.setVisible(false);
        jb3.setVisible(false);jb4.setVisible(false);
        jtf2.setVisible(false);jpw2.setVisible(false);jpw3.setVisible(false);


        jb1.addActionListener(this);jb2.addActionListener(this);
        jb3.addActionListener(this);jb4.addActionListener(this);
    }

    @Override
    /**四个按钮，重点在于登录和提交
     * 登录要从文件中确定是否有该用户，并且其密码是否正确
     * 提交需要把用户名和密码写入文件，并且创建新的文件，作为他的六级单词掌握
     * 注册和返回都只涉及页面的可视化
     */
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();boolean x=false;
        if(s.equals("登录"))
        {
            String name= jtf1.getText();
            char[] word= jpw1.getPassword();
            String password=new String(word);
            System.out.println(name+" "+password);
            try {
                x= match(name,password,1);
                if(x){
                    this.name=name;
                    this.dispose();//关闭窗口
                    new client();
                }else{
                    JOptionPane.showMessageDialog(null,"密码错误！请重试！","登陆失败",JOptionPane.ERROR_MESSAGE);
                }
            }catch (Exception ex){JOptionPane.showMessageDialog(null, "登陆失败！请重试！", "登陆失败",JOptionPane.ERROR_MESSAGE);}
        }
        else if(s.equals("注册"))//点击注册跳转至注册界面
        {
            //登录界面不可见
            jbl1.setVisible(false);jbl2.setVisible(false);jbl3.setVisible(false);
            jb1.setVisible(false);jb2.setVisible(false);
            jtf1.setVisible(false);jpw1.setVisible(false);
            //注册界面可见
            jbl4.setVisible(true);jbl5.setVisible(true);jbl6.setVisible(true);jbl7.setVisible(true);
            jb3.setVisible(true);jb4.setVisible(true);
            jtf2.setVisible(true);jpw2.setVisible(true);jpw3.setVisible(true);
        }
        else if(s.equals("提交"))
        {
            String name = jtf2.getText();
            char[] pwa =jpw2.getPassword(),pwb = jpw3.getPassword();
            String pwx = new String(pwa),pwy = new String(pwb);
            //判断第一次输入的密码和第二次输入的密码是否一致，不一致则提示密码不匹配，重新输入
            if(!pwx.equals(pwy)){
                JOptionPane.showMessageDialog(null,"密码输入不匹配！","输入错误",JOptionPane.ERROR_MESSAGE);
            }else{
                try {
                    x=match(name,pwx,0);
                    if(x){
                        JOptionPane.showMessageDialog(null,"用户已存在","提示",JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        newUser(name,pwx);
                        JOptionPane.showMessageDialog(null,"注册成功！返回就可以登录啦！","提示",JOptionPane.INFORMATION_MESSAGE);
                    }
                }catch (Exception ex){}
                jtf2.setText("");jpw2.setText("");
                jpw3.setText("");//将它们都设置为空
            }
        }
        else if(s.equals("返回"))
        {
            //登录界面可见
            jbl1.setVisible(true);jbl2.setVisible(true);jbl3.setVisible(true);
            jb1.setVisible(true);jb2.setVisible(true);
            jtf1.setVisible(true);jpw1.setVisible(true);
            //注册界面不可见
            jbl4.setVisible(false);jbl5.setVisible(false);jbl6.setVisible(false);jbl7.setVisible(false);
            jb3.setVisible(false);jb4.setVisible(false);
            jtf2.setVisible(false);jpw2.setVisible(false);jpw3.setVisible(false);
        }
    }


    private void newUser(String name, String password) throws Exception{
        FileOutputStream fos = new FileOutputStream("C:\\Users\\21285\\Desktop\\应用实践基础\\users.txt",true);
        PrintStream ps = new PrintStream(fos);
        ps.println(name+" "+password);
        ps.close();
        fos.close();
        File f = new File("C:\\Users\\21285\\Desktop\\应用实践基础\\"+name);
        f.mkdir();//创建文件夹
        File f1 = new File("C:\\Users\\21285\\Desktop\\应用实践基础\\"+name+"\\群聊记录.txt");
        f1.createNewFile();
    }

    public boolean match(String name,String password,int mode) throws Exception{//mode=1则匹配用户名与密码，=0则只寻找用户名
        FileReader fr = new FileReader("C:\\Users\\21285\\Desktop\\应用实践基础\\users.txt");
        BufferedReader bf = new BufferedReader(fr);
        if(mode==1){
            int flag = 0;
            do {
                String s = bf.readLine();
                String[] s2 = s.split(" ");
                if (s2[0].equals(name) && s2[1].equals(password)) {
                    flag = 1;
                    break;
                }
            } while (bf != null);
            return flag == 1 ? true : false;
        }else{
            int flag = 0;
            String s = bf.readLine();
            while(s!=null){
                String[] s2 = s.split(" ");
                if (s2[0].equals(name)) {
                    flag = 1;
                    break;
                }
                s = bf.readLine();
            }
            return flag == 1 ? true : false;
        }
    }
}

