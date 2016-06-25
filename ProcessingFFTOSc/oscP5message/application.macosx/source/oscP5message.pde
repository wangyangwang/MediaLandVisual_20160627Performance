/**
 * oscP5message by andreas schlegel
 * example shows how to create osc messages.
 * oscP5 website at http://www.sojamo.de/oscP5
 */

import oscP5.*;
import netP5.*;
import processing.sound.*;

AudioIn in;
Amplitude amp;

OscP5 oscP5;
NetAddress myRemoteLocation;

void setup() {
  size(500, 200);
  amp = new Amplitude(this);

  oscP5 = new OscP5(this, 12000);
  myRemoteLocation = new NetAddress("127.0.0.1", 1999);
  in = new AudioIn(this, 0);
  in.start();
  amp.input(in);
}

void draw() {

  background(0);
  textAlign(CENTER);
  textSize(12);
  text("Sending Music Analysis Data to Unity 3D Now...", width/2, height/2);
  text(amp.analyze()*100, width/2, height/2 + 12);
  OscMessage myMessage = new OscMessage("/test");
  myMessage.add(amp.analyze()*100); /* add an int to the osc message */
  oscP5.send(myMessage, myRemoteLocation);
}

void oscEvent(OscMessage theOscMessage) {
  /* print the address pattern and the typetag of the received OscMessage */
  print("### received an osc message.");
  print(" addrpattern: "+theOscMessage.addrPattern());
  println(" typetag: "+theOscMessage.typetag());
}
