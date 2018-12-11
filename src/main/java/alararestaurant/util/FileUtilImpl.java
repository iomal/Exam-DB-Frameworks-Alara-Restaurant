package alararestaurant.util;

import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileUtilImpl implements FileUtil {
    @Override
    public String readFile(String filePath) throws IOException {
        File file = new ClassPathResource(filePath).getFile();
        StringBuilder jsonOrxml = new StringBuilder();
        Scanner in = new Scanner(new FileReader(file));
        while(in.hasNextLine()){
            jsonOrxml.append(in.nextLine());
            jsonOrxml.append(System.lineSeparator());
        }
        String str=jsonOrxml.toString();
        return  str;
    }
}
