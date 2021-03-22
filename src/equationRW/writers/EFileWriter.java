package equationRW.writers;

import equationRW.writers.interfaces.EquationsWritable;
import matrixReaders.UPCommunitation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EFileWriter implements EquationsWritable {
    private String fileName = "";

    @Override
    public void write(String... strings) {
        int size = strings.length;
        String outpt ="";
        
        if(size == 0) return; 
        
        for (int i = 0; i < size; i++) {
            outpt = outpt.concat(strings[i] + " | ");
        }


        File file = new File(askForFileName());
        FileWriter fileWriter = null;

        try{

            if(!file.exists()) {
                file.createNewFile();
            }
            fileWriter = new FileWriter(file, true);
//            OutputStreamWriter writer = new java.io.FileWriter(askForFileName());
            fileWriter.append(outpt + "\n");
//            writer.write();
        }catch (IOException ex) {

        }finally {
            try {
                fileWriter.close();
            }catch (NullPointerException | IOException ex) {/*NOPE*/}
        }
    }
    protected String askForFileName() {
        if(fileName.length() == 0) {
            fileName = UPCommunitation.askForString(x -> x.contains(".txt"),"Имя файла для вывода вида: name.txt", new Scanner(System.in));
        }
        return fileName;
    }
}
