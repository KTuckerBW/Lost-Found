package src.auth;

import src.model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileAuthService implements AuthService {

    public List<Map<String, String>> records = new ArrayList<>();
    private final String filePath;

    public FileAuthService(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public User authenticate(String username, String password) {

        records = getRecords();
        for(Map<String, String> row: records){
            if(username.equals(row.get("Username")) && password.equals(row.get("Password"))){
                return User.fromRow(row);
            }
        }

        return null;
    }

    /*
    This represents a .csv file content as a List of Hashmaps
     */
    private List getRecords() {


        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String delimiter = ",";
            String[] header = bufferedReader.readLine().split(delimiter);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                Map<String, String> row = new HashMap<>();
                for (int i = 0; i < header.length; i++) {
                    row.put(header[i], values[i]);
                }
                records.add(row);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return records;
    }
}
