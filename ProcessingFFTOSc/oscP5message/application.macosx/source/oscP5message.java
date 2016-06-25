import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import oscP5.*; 
import netP5.*; 
import processing.sound.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class oscP5message extends PApplet {

/**
 * oscP5message by andreas schlegel
 * example shows how to create osc messages.
 * oscP5 website at http://www.sojamo.de/oscP5
 */





AudioIn in;
Amplitude amp;

OscP5 oscP5;
NetAddress myRemoteLocation;

public void setup() {
  
  amp = new Amplitude(this);

  oscP5 = new OscP5(this, 12000);
  myRemoteLocation = new NetAddress("127.0.0.1", 1999);
  in = new AudioIn(this, 0);
  in.start();
  amp.input(in);
}

public void draw() {

  background(0);
  textAlign(CENTER);
  textSize(12);
  text("Sending Music Analysis Data to Unity 3D Now...", width/2, height/2);
  text(amp.analyze()*100, width/2, height/2 + 12);
  OscMessage myMessage = new OscMessage("/test");
  myMessage.add(amp.analyze()*100); /* add an int to the osc message */
  oscP5.send(myMessage, myRemoteLocation);
}

public void oscEvent(OscMessage theOscMessage) {
  /* print the address pattern and the typetag of the received OscMessage */
  print("### received an osc message.");
  print(" addrpattern: "+theOscMessage.addrPattern());
  println(" typetag: "+theOscMessage.typetag());
}

  public void settings() {  size(500, 200); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "oscP5message" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
