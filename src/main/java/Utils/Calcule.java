package Utils;

import Exceptions.ValidationException;

import java.io.*;

public class Calcule {


    public static void setSaptamana(Integer newWeek){
        String file  = "src/main/java/Utils/SaptamanaCurenta.txt";
        try(BufferedWriter br = new BufferedWriter(new FileWriter(file))){
            br.write(Integer.toString(newWeek));

        }catch (FileNotFoundException ex){
            throw new ValidationException(ex.getMessage());
        }catch (IOException e){
            throw new ValidationException(e.getMessage());
        }
    }

    /*public Integer getSaptamana2(){
        Integer sapt = 1;

        //sapt = sapt + LocalDateTime.now();
        //LocalDateTime date = new DateTimeFormatter().format("");
        //return (int)sapt;

    }*/
    public static Integer getSaptamana(){
        try (BufferedReader br = new BufferedReader(new FileReader("src/Utils/SaptamanaCurenta.txt"))) {
            return Integer.parseInt(br.readLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
