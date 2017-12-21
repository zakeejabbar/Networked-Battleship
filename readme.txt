Zakee Jabbar UIC


"Networked Battleship"


This is a GUI version of the Battleship Game. In order for the game to work, one player needs to be the server, while the other player is the client. First, the player that is the server needs to use the menu item Actions -> Establish server side connection and click on start listening. Then the second player needs to choose the menu item Actions -> Establish client side connection and enter the IP Address and Port number of the server. Once the connection has been establised both the players can set their ships and they can begin playing battleship.



Supported Functions:


File -> Exit : Quits the game.
Actions -> Establish server side connection: Starts listening on a socket.
Actions -> Establish client side connection: Establishes the connection with the server by entering the server IP address and port number.




To run this program on command line:

javac Game.java
java Game