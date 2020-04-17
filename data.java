/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readcsv;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;




public class data
{
  public static final String SEPARATOR = ",";
  public static final String QUOTE = "\"";
  
 public static boolean formatFile(String source, String dest){
    BufferedReader br = null;
    String rutaFile = source;
    String rutaDestino = dest;
    String newFileName="";
    boolean status=false;
    report obj=new report();
    obj.format.setEnabled(false);
    //String namefile ="reporte (24).csv";
    
    int day = 0;
    int dayInLine = 0;
    FileWriter fw = null;
    PrintWriter pw = null;
    int readLine = 0;
    Date d = null;
    String line = "";
    DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
    SimpleDateFormat dateFormat2 = new SimpleDateFormat("hh:mm:ss aa");
    
    try {
        
      //br = new BufferedReader(new FileReader(String.valueOf(rutaFile) + namefile));
      br = new BufferedReader(new FileReader(String.valueOf(rutaFile)));
      line = br.readLine();
      while (line != null) {
        line = line.replace("unknown;unknown;org.openqa.selenium.UnhandledAlertException: Modal dialog present Build info: version: 'unknown', revision: 'unknown', time: 'unknown' System info: os.name: 'Linux', os.arch: 'amd64', ", "");
        line = line.replace("Exception, no current browser!", "");
        line = line.replace("unknown;unknown;org.openqa.selenium.remote.SessionNotFoundException: The FirefoxDriver cannot be used after quit() was called. Build info: version: 'unknown', revision: 'unknown', time: 'unknown' Syst", "");
        line = line.replace("'unknown',", "");
        if (readLine > 0) {
          String[] fields = removeTrailingQuotes(line.split(","));
          try {
            dayInLine = Integer.parseInt(fields[6].split("/")[0]);
          } catch (NumberFormatException ex) {
            dayInLine = 0;
          } 
          
          if (day == 0 || day != dayInLine) {
            if (fw != null) {
              fw.close();
            }
            day = dayInLine;
            String[] dateSeparate = fields[6].split("/");
            String str;
            switch ((str = dateSeparate[1]).hashCode()) { case 1537: if (!str.equals("01")) {
                  break;
                }
                dateSeparate[1] = "enero"; break;
              case 1538:
                if (!str.equals("02"))
                  break;  dateSeparate[1] = "febrero"; break;
              case 1539:
                if (!str.equals("03"))
                  break;  dateSeparate[1] = "marzo"; break;
              case 1540:
                if (!str.equals("04"))
                  break;  dateSeparate[1] = "abril"; break;
              case 1541:
                if (!str.equals("05"))
                  break;  dateSeparate[1] = "mayo"; break;
              case 1542:
                if (!str.equals("06"))
                  break;  dateSeparate[1] = "junio"; break;
              case 1543:
                if (!str.equals("07"))
                  break;  dateSeparate[1] = "julio"; break;
              case 1544:
                if (!str.equals("08"))
                  break;  dateSeparate[1] = "agosto"; break;
              case 1545:
                if (!str.equals("09"))
                  break;  dateSeparate[1] = "septiembre"; break;
              case 1567:
                if (!str.equals("10"))
                  break;  dateSeparate[1] = "octubre"; break;
              case 1568:
                if (!str.equals("11"))
                  break;  dateSeparate[1] = "noviembre"; break;
              case 1569:
                if (!str.equals("12"))
                  break;  dateSeparate[1] = "diciembre";
                break; }

            newFileName=String.valueOf(dateSeparate[0]) + " - " + dateSeparate[1] + " interface robot.txt";
            System.out.println(newFileName);
              System.out.println("rutaDest"+rutaDestino);
            fw = new FileWriter(String.valueOf(rutaDestino+"\\") + newFileName);
            
            pw = new PrintWriter(fw);
            pw.println("\tNodo\t\t\t|\tCR\t|\tEstado\t\t\t|\tFecha-Inicio\t|\tHora-Inicio\t|Tiempo-Ejecución|   Código\t|\tFecha-Recibido\t|\tHora-Recibido\t|\tVia\t|");
          } 
          
          String str1;
          
          switch ((str1 = fields[4]).hashCode()) { case -1507395290: if (!str1.equals("ficha-cliente")) {
                break;
              }

              fields[4] = "ficha-cliente   ";
              break;
            case -1097329270:
              if (!str1.equals("logout")) {
                break;
              }
              fields[4] = "logout          "; break;case -932743981: if (!str1.equals("contratacion")) break;  fields[4] = "contratacion    "; break;case 103506: if (!str1.equals("hoy"))
                break;  fields[4] = "hoy             "; break;case 103149417: if (!str1.equals("login"))
                break;  fields[4] = "login           "; break;case 292668600: if (!str1.equals("mi-gestion"))
                break;  fields[4] = "mi-gestion      ";
              break; }
         
          d = dateFormat.parse(fields[7].substring(0, 8));
          String d2 = dateFormat2.format(d);
          
          d = dateFormat.parse(fields[12].substring(0, 8));
          String d3 = dateFormat2.format(d);
          
          if(fields[0].length()<=23){
              fields[0]=String.valueOf(fields[0])+"\t";
          }

          
          if (fields[10].length() > 30 && fields[10].startsWith("Escenarios")) {
            String linea = String.valueOf(fields[0]) + "\t|\t" + fields[1] + "\t|\t" + fields[4] + "\t|\t" + fields[6] + "\t|\t" + d2 + "\t|\t" + fields[8] + "\t|\t" + fields[9] + "\t|\t" + fields[11] + "\t|\t" + d3 + "\t|\t" + fields[10].substring(25, 31).replace("VÃ­a ", "Vía ").replaceAll(";about:blank", "").replaceAll(";", "") + "\t|\t";
            pw.println(linea);
          } else {
            
            String linea = String.valueOf(fields[0]) + "\t|\t" + fields[1] + "\t|\t" + fields[4] + "\t|\t" + fields[6] + "\t|\t" + d2 + "\t|\t" + fields[8] + "\t|\t" + fields[9] + "\t|\t" + fields[11] + "\t|\t" + d3 + "\t|\t" + "\t|\t";
            
            pw.println(linea);
          } 
        } 
        
        line = br.readLine();
        readLine++;
      } 
      if (fw != null && br != null) {
        fw.close();
        br.close();
        
        
      }
      status=true;
      JOptionPane.showMessageDialog(null,"Writed " + readLine + " lines in "+newFileName);
      
    } catch (Exception e) {
        
      JOptionPane.showMessageDialog(null, line+ e,"Error", JOptionPane.ERROR_MESSAGE);
      e.printStackTrace();
    } 
 return status;}
  

  private static String[] removeTrailingQuotes(String[] fields) {
    String[] result = new String[fields.length];
    
    for (int i = 0; i < result.length; i++) {
      result[i] = fields[i].replaceAll("^\"", "").replaceAll("\"$", "");
    }
    return result;
  }
}
