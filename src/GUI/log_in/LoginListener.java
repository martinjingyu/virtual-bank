/**

The LoginListener interface is used to define callback methods for handling login events in the GUI of the bank application.
It contains two methods for handling login events for kid and parent users, respectively.
This interface performs the following steps:
Declares two abstract methods for handling login events, one for kid users and one for parent users.
The onLogin_kid method takes a String parameter representing the ID of the kid user who logged in.
The onLogin_parent method takes a String parameter representing the ID of the parent user who logged in.
Note: Implementing classes must provide concrete implementations for the abstract methods defined in this interface.**/

package GUI.log_in;

import javax.swing.*;

public interface LoginListener {
    void onLogin_kid(String id);

    void onLogin_parent(String id);
}
