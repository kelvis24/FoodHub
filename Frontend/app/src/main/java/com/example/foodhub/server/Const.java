package com.example.foodhub.server;

/**
 * A collection of constant values relevant for server communication
 * @author Sumon Biswas, Arvid Gustafson
 */
public class Const {

    /**
     * A URL for a local backend for testing; swap names for a quick switch.
     */
    public static final String URL = "http://10.0.2.2:3000/";

    /**
     * A URL for a remote backend for deployment; swap names for a quick switch.
     */
    public static final String OTHER_URL = "http://coms-309-002.cs.iastate.edu:3000/";

    /**
     * A URL for the local chat server path
     */
    //ws://localhost:8025/myContextRoot
    //public static final String CHAT_SERVER_PATH = " ws://demo.piesocket.com/v3/channel_1?api_key=oCdCMcMPQpbvNjUIzqtvF1d2X2okWpDQj4AwARJuAgtjhzKxVEjQU6IdCjwm&notify_self";


    public static final String CHAT_SERVER_PATH ="wss://demo.piesocket.com/v3/channel_1?api_key=oCdCMcMPQpbvNjUIzqtvF1d2X2okWpDQj4AwARJuAgtjhzKxVEjQU6IdCjwm&notify_self";

    //= "ws://coms-309-002.cs.iastate.edu:3000/OTC/675";
            //"ws://localhost:3000/OTC/2343";


<<<<<<< HEAD
    //http://coms-309-002.cs.iastate.edu:3000/debug-get-customers
    //     ws://coms-309-002.cs.iastate.edu:3000/OTC/1232
=======
    public static final String OTHER_CHAT_SERVER_PATH = "ws://192.168.0.136:3000";

>>>>>>> c37506eb535347d6203f9e503d20c8594f3d8470
}
