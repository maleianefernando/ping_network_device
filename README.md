# Ping a device on your network

This java project allow the user to ping some device on their network to see if this is avaliable or not.
This is based on the ping command avaliable on the Windows, Linux and MacOS computers.

## Compilation
To compile and run this program, first of all your need to have the JDK (Java Development Kit) just clone this repository and run the following commands:
To compile:
``` javac Ping.java ```
``` javac Main.java ```

To run program:
``` java Main.java ```


## How to use?
Once you are running the program, you need to type the the ```ping``` command followed by the ip adress that you want to ping in the text field and press enter
``` ping localhost```
Or
```ping 127.0.0.1```

To clear the screen you just need to type "clear" into the text field and then press enter
```clear```

## Documentation
- `set_frame()`: initializes and do the first settings to the window
- `set_cmd_label()`: settings to the label that on linux OS indicates the user and the hostname in this format: ```user@hostname:~$```
- `set_text_field_cmd()`: sets the textfield that acts as an input to receive the command
- `set_ping_result()`: initializes the container to show the ping results
- `keyReleased(KeyEvent e)`: this is a method of the `KeyListener` interface that handles the keyboard event to process the written command on the UI program.


## Licence
