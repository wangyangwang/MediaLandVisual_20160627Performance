using UnityEngine;
using System.Collections;

public class ScaleMyself : MonoBehaviour {

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
		float received = GameObject.Find ("OSCManager").GetComponent<oscReceive> ().received;
		gameObject.transform.localScale = new Vector3 (received, received, received);
	}
}
