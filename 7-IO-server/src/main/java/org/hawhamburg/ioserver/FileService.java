package org.hawhamburg.ioserver;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    private static final Path resourcesDirectory = Paths.get("src", "main", "resources");

    public String readFile(final String fileName) throws IOException {

        // TODO: Change my implementation by using a BufferedReader instead
        // FileReader in = new FileReader((resourcesDirectory.resolve(fileName).toString()));
        // BufferedReader br = new BufferedReader(in);

        BufferedReader br = Files.newBufferedReader(resourcesDirectory.resolve(fileName));

        StringBuilder fileContent = new StringBuilder("");
        String nextLine = br.readLine();
        while (nextLine != null) {
            fileContent.append(nextLine);
            nextLine = br.readLine();
        }

        br.close();
        //in.close();

        return fileContent.toString();
        //return Files.readString(resourcesDirectory.resolve(fileName));
    }

    public void writeFile(final String fileName, final String text) throws IOException {

        // TODO: Change my implementation by using a BufferedWriter instead
        //FileWriter out = new FileWriter((resourcesDirectory.resolve(fileName).toString()));
        BufferedWriter bw = Files.newBufferedWriter(resourcesDirectory.resolve(fileName));


        bw.write(text);

        bw.close();
        //out.close();

        //Files.writeString(resourcesDirectory.resolve(fileName), text);
    }
}
