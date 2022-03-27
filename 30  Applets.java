Java program Types 
  a) java Applications
     - Stand alone Program with main method   
  b) java Applets
     - Browse embeded java program

Java Applet
- Java programs for web  browser
- There is no main method in Applets
- Awt(Applet), swing (JApplet)
- deafult Layout flow Layout
- default Container (Panel)

Security Restrictions
Applet cannot read/write local files, call local programs, or connect to any host other than the one from which it was loaded

 Applet can not
 1-Read from the local (client) disk
        Applets cannot read arbitrary files 
        They can, however, instruct the browser to display pages that are generally accessible on the Web, 
            which might include some local files
2- Write to the local (client) disk
     The browser may choose to cache certain files, including some loaded by applets, but this choice is 
      not under direct control of the applet


3- Open network connections other than to the server from which the applet was loaded
   This restriction prevents applets from browsing behind network firewalls

Note :
Browsers cache applets: in Netscape, use Shift-RELOAD to force loading of new applet.  In IE, use Control-RELOAD
Can use appletviewer for initial testing

The Java Console
Standard output (from System.out.println) is sent to the Java Console



Syntax (Awt)
public class MyApplet extends Applet{

}
Syntax (Swing)
public class MyApplet extends JApplet{

}

- Applet life Cycle
    1- public void init ()   load time initialization
       Called when applet is first loaded into the browser.
        Not called each time the applet is executed
    2- start    called after init  or restart
        Called immediately after init initially Reinvoked each time user         returns to page after having left it
        Used to start animation threads
    3- stop     call when applet stops
    4- destroy  call when applet is terminated
    5- paint     
       Called by the browser after init and start 
       Reinvoked whenever the browser redraws the screen 
        (typically when part of the screen has been obscured and then          reexposed)
        This method is where user-level drawing is placed

........................................................ ...........
Example : print Hello Applet 
                          MyApplet.java
import java.awt.*;
import java.applet.*;
public class MyApplet extends Applet{

public void paint(Graphics g ){
 g.drawString (" Hello Applet",100,100);
}
}

.........................................................................
- Applet should be embedded within html file
- Preinstalled java run time is a major requirement
			MyApplet.html			
<html>
<body>
<h1> My Own Applet </h1> <br>

<applet code="MyApplet" width=400 height =400>
</applet>
</body>
</html>
.............................................................................
How to Run Applet?

1- Browser  (open html file)
2- appletviewer MyApplet.html
..............................................................................
Example Applet Life Cycle Methods

import java.awt.*;
import java.applet.*;
public class MyApplet extends Applet{

 public void init(){
  setLayout(new BoarderLayout());
  ta=new TextArea();
  add(ta);
  msg=ta.getText()+ "init method";
  ta.setText(msg);  
 }
 public void start(){
 msg=ta.getText()+ "\n Start  method";
  ta.setText(msg);  
 }
 public void stop(){
 msg=ta.getText()+ "\n Stop method";
  ta.setText(msg);  
 }
 public void destroy(){
  msg=ta.getText()+ "\n destroy method";
  ta.setText(msg);  
 }
 public void paint(Graphics g ){
  msg=ta.getText()+ " paint  method";
  ta.setText(msg);  
 }
 TextArea ta;
 String msg="";
}
................................................................................
Example Display message on browser Status bar

import java.applet.Applet;
public class MyApplet extends Applet {

  public void init() {
    this.showStatus("Applet Developed in UCP");
  }
}
.............................................................................................
Example Read parameters from HTML

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
public class MyApplet extends Applet {
  private String name = "";
  public void init() {
    name = getParameter("name");
  }
  public void paint(Graphics g) {
    g.setColor(Color.BLUE);
    g.drawString(name, 0, 0);
  }
} 
....................................................................................... 
               MyApplet.html

<html>
<head>
  <title>Parameterized Applet</title>
</head>
<body>
    <applet code="MyApplet" height="150" width="350">
        <param name="SFarooq" value="abc" />
    </applet>
</body>
</html>
..................................................................


Useful Graphics Methods
1- drawString(string, left, bottom)
    Draws a string in the current font and color with the bottom left corner of the string at the specified location
    One of the few methods where the y coordinate refers to the bottom of shape, not the top. 
     But y values are still with respect to the top left corner of the applet window

2- drawRect(left, top, width, height)
   Draws the outline of a rectangle (1-pixel border) in the current color
   fillRect(left, top, width, height)
   Draws a solid rectangle in the current color
   drawLine(x1, y1, x2, y2)
   Draws a 1-pixel-thick line from (x1, y1) to (x2, y2)
3- drawOval, fillOval
    Draws an outlined and solid oval, where the arguments describe a rectangle that bounds the oval
    drawPolygon, fillPolygon
    Draws an outlined and solid polygon whose points are defined by arrays or a Polygon (a class that stores a series of points)
    By default, polygon is closed; to make an open polygon use the drawPolyline method
4- drawImage
    Draws an image
    Images can be in JPEG or GIF (including GIF89A) format


Drawing Color

- setColor, getColor
   Specifies the foreground color prior to drawing operation
   By default, the graphics object receives the foreground color of the window
   AWT has 16 predefined colors (Color.red, Color.blue, etc.) or create your own color: new Color(r, g, b)
   Changing the color of the Graphics object affects only the drawing that explicitly uses that Graphics object
   To make permanent changes, call the applet’s setForeground method.

Graphics Font

setFont, getFont

Specifies the font to be used for drawing text
Determine the size of a character through FontMetrics (in Java 2 use LineMetrics)
Setting the font for the Graphics object does not persist to subsequent invocations of paint
Set the font of the window (I.e., call the applet’s setFont method) for permanent changes to the font
5 major fonts are available: Serif (aka TimesRoman), SansSerif (aka Helvetica), Monospaced (aka Courier), 
Dialog,  and DialogInput

....................................................................................................
Example Draw image on Applet

import java.applet.Applet;
import java.awt.*;

/** An applet that loads an image from a relative URL. */

public class JavaMan1 extends Applet {
  private Image javaMan;

  public void init() {
    javaMan = getImage(getCodeBase(),
                       "images/Java-Man.gif");
  }

  public void paint(Graphics g) {
    g.drawImage(javaMan, 0, 0, this);
  }
}


......................................................................................
Example Load image from Web

import java.applet.Applet;
import java.awt.*;
import java.net.*;
public class JavaMan1 extends Applet {
  private Image javaMan;

  public void init() {
    try {
      URL imageFile =
        new URL("http://www.corewebprogramming.com" +
                "/images/Java-Man.gif");
      javaMan = getImage(imageFile);
    } catch(MalformedURLException mue) {
      showStatus("Bogus image URL.");
      System.out.println("Bogus URL");
    }
  }

..........................................................................................

Events on Applets

Example Mouse Listener

import java.applet.Applet;
import java.awt.*;

public class ClickReporter extends Applet {
  public void init() {
    setBackground(Color.yellow);
    addMouseListener(new ClickListener());
  }
}

import java.awt.event.*;

public class ClickListener extends MouseAdapter {
  public void mousePressed(MouseEvent event) {
    System.out.println("Mouse pressed at (" +
                       event.getX() + "," +
                       event.getY() + ").");
  }
}

...............................................................................

Example Draw Circle

import java.applet.Applet;
import java.awt.*;

public class CircleDrawer1 extends Applet {
  public void init() {
    setForeground(Color.blue);
    addMouseListener(new CircleListener());
  }
}

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class CircleListener extends MouseAdapter {
  private int radius = 25;

  public void mousePressed(MouseEvent event) {
    Applet app = (Applet)event.getSource();
    Graphics g = app.getGraphics();
    g.fillOval(event.getX()-radius,
               event.getY()-radius,
               2*radius,
               2*radius);
  }
}
.............................................................................
Example White Board

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class SimpleWhiteboard extends Applet {
  protected int lastX=0, lastY=0;

  public void init() {
    setBackground(Color.white);
    setForeground(Color.blue);
    addMouseListener(new PositionRecorder());
    addMouseMotionListener(new LineDrawer());
  }

  protected void record(int x, int y) {
    lastX = x; lastY = y;
  }
private class PositionRecorder extends MouseAdapter {
    public void mouseEntered(MouseEvent event) {
      requestFocus(); // Plan ahead for typing
      record(event.getX(), event.getY());
    }

    public void mousePressed(MouseEvent event) {
      record(event.getX(), event.getY());
    }
  }
private class LineDrawer extends MouseMotionAdapter {
    public void mouseDragged(MouseEvent event) {
      int x = event.getX();
      int y = event.getY();
      Graphics g = getGraphics();
      g.drawLine(lastX, lastY, x, y);
      record(x, y);
    }
  }
}
....................................................................
Example Free Hand White Board

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class Whiteboard extends SimpleWhiteboard {
  protected FontMetrics fm;

  public void init() {
    super.init();
    Font font = new Font("Serif", Font.BOLD, 20);
    setFont(font);
    fm = getFontMetrics(font);
    addKeyListener(new CharDrawer());
  }

private class CharDrawer extends KeyAdapter {
    // When user types a printable character,
    // draw it and shift position rightwards.
    
    public void keyTyped(KeyEvent event) {
      String s = String.valueOf(event.getKeyChar());
      getGraphics().drawString(s, lastX, lastY);
      record(lastX + fm.stringWidth(s), lastY);
    }
  }
}

..........................................................

Example : Covert the following code into applet

import java.awt.*;
class MyFrame{
 MyFrame() {
  Frame f = new Frame("UCP");
  Button b1 = new Button("Save");
  f.add(b1);
  f.setSize(300,300);
  f.setVisible(true);
 }
}
class Test{
public static void main(String a[]){
    MyFrame frame= new MyFrame();
  
}
}
...........................................................

import java.awt.*;
class MyFrame extends Applet{
 public void init() {
   Button b1 = new Button("Save");
   add(b1);
    
 }
}

<html>
<body>
<h1> My Own Applet </h1> <br>

<applet code="MyFrame" width=400 height =400>
</applet>
</body>
</html>


