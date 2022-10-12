import java.util.*;
import java.io.*;

public class PhoneBook {

    private HashMap<String, ArrayList<String>> phoneList ;

    public PhoneBook() {
        phoneList = new HashMap<>();
    }

    synchronized void addPhoneNumber(String name, String phone) {
        if (phoneList.containsKey(name)) {
            ArrayList<String> tmp = phoneList.get(name);
            if (!phone.equals(""))
                tmp.add(phone);
            phoneList.put(name, tmp);
        } else {
            ArrayList<String> tmp = new ArrayList<>();
            if (!phone.equals(""))
                tmp.add(phone);

            phoneList.put(name, tmp);
        }
    }

    HashMap<String, ArrayList<String>> getPhoneBook() {

        return phoneList;
    }

    boolean containName(String name) {
        return phoneList.containsKey(name);
    }

    boolean containPhone(String name, String phone) {
        if (containName(name))
            return phoneList.get(name).contains(phone);
        else
            return false;
    }

    synchronized void readFile() {
        try {
        
            phoneList = new HashMap<>();
            String path = "/home/ilya/Desktop/phonelist.txt";
            File list = new File(path);
            Scanner sc = new Scanner(list);
            

            while (sc.hasNextLine()) {
             	String[] tmp = sc.nextLine().split(" ");
                StringBuilder name = new StringBuilder();
                StringBuilder phone = new StringBuilder();
                int i = 0;
                name.append(tmp[i].replaceAll(":", ""));
                this.addPhoneNumber(name.toString(), "");
                if(tmp.length > 1){
                	i++;
                	phone.append(tmp[i]);
                	i++;
                	this.addPhoneNumber(name.toString(), phone.toString());
                	for (; i < tmp.length; i++) {
                    		phone = new StringBuilder();
                    		phone.append(tmp[i]);
                    		this.addPhoneNumber(name.toString(), phone.toString());
                	}
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    synchronized void writeFile() {
        try {
            String path = "/home/ilya/Desktop/phonelist.txt";
            FileWriter fstream = new FileWriter(path);
            BufferedWriter out = new BufferedWriter(fstream);

            for (Map.Entry<String, ArrayList<String>> pairs : phoneList.entrySet()) {

                out.write(pairs.getKey() + ": ");
                ArrayList<String> value = pairs.getValue();
                if(value.size()!= 0){
                	out.write(value.get(0));
                	for (int i = 1; i < value.size(); i++) {
                    		out.write(" " + value.get(i));
                	}
                }
                out.write("\n");

            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
