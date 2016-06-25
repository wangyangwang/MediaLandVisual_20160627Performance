using UnityEngine;
using System.Collections;
using System.Collections.Generic;
using System;

public class oscReceive : MonoBehaviour {

	private string UDPHost = "127.0.0.1";
	private int listenerPort = 1999;
	private int broadcastPort = 57131;
	private Osc oscHandler; //mellow : float 

	public float received;


	// Use this for initialization
	void Start () {
		UDPPacketIO udp = GetComponent<UDPPacketIO>();
		udp.init(UDPHost,broadcastPort,listenerPort);
		oscHandler = GetComponent<Osc>();
		oscHandler.init(udp);
		oscHandler.SetAllMessageHandler(getInput);
	}
		
	void Update(){
		
	}

	public void getInput(OscMessage oscMessage) {
		Debug.Log ("stuff coming");
		OscMessage m = oscMessage;
		Osc.OscMessageToString(m);
		received = (float)m.Values [0];
	}

}
