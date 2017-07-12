/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jwifi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Solomon
 */
public class JWiFi {

    public static String[] cmd(String command) throws IOException {
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", command);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        String[] lines = new String[99];
        int i = 0;
        while (true) {
            line = r.readLine();
            if (line==null) {
                break;
            }
            lines[i] = line;
            i++;
        }
        return lines;
    }

    public static void main(String[] args) {
        System.out.println("  ____  __    __  ____  _____  ____ \n" +
" |    ||  T__T  Tl    j|     |l    j\n" +
" l__  ||  |  |  | |  T |   __j |  T \n" +
" __j  ||  |  |  | |  | |  l_   |  | \n" +
"/  |  |l  `  '  ! |  | |   _]  |  | \n" +
"\\  `  | \\      /  j  l |  T    j  l \n" +
" \\____j  \\_/\\_/  |____jl__j   |____j\n" +
"                                    \n" +
"");
        try {
            String[] ssids = cmd("netsh wlan show profile key=clear");
            for (String line : ssids) {
                //System.out.println(line);
                if (line != null) {
                    if (line.contains(" : ")) {
                        //System.out.println(line.substring(line.indexOf(": ")+2, line.length()));
                        //here
                        String[] lines = cmd("netsh wlan show profile " + line.substring(line.indexOf(": ") + 2, line.length()) + " key=clear");
                        for (String lin : lines) {
                            if (lin != null) {
                                if (lin.contains("clave") || lin.contains("Key Content")) {
                                    System.out.println(line.substring(line.indexOf(": ") + 2, line.length()) + " - " + lin.substring(lin.indexOf(": ") + 2, lin.length()));
                                    //here
                                }

                            }
                        }
                    }
                }

            }

            /*try {
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "netsh wlan show profile key=clear");
            builder.redirectErrorStream(true);
            Process p = builder.start();
            
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
            line = r.readLine();
            System.out.println(""+line);
            if (line.contains(" : ")){
            System.out.println(line.substring(line.indexOf(": ")+2, line.length()));
            //here
            }
            }       } catch (IOException ex) {
            Logger.getLogger(JWiFi.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        } catch (IOException ex) {
            Logger.getLogger(JWiFi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
